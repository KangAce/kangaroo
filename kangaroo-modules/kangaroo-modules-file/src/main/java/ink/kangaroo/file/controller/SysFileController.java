package ink.kangaroo.file.controller;

import com.qiwenshare.ufop.factory.UFOPFactory;
import com.qiwenshare.ufop.operation.upload.Uploader;
import com.qiwenshare.ufop.operation.upload.domain.UploadFile;
import com.qiwenshare.ufop.operation.upload.domain.UploadFileResult;
import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.utils.DateUtils;
import ink.kangaroo.common.core.utils.SecurityUtils;
import ink.kangaroo.common.core.utils.file.FileUtils;
import ink.kangaroo.file.service.FastDfsSysFileServiceImpl;
import ink.kangaroo.file.service.ISysFileService;
import ink.kangaroo.file.service.LocalSysFileServiceImpl;
import ink.kangaroo.file.service.MinioSysFileServiceImpl;
import ink.kangaroo.system.api.domain.SysFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.MultimediaInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件请求处理
 *
 * @author Kangaroo
 */
@RestController
public class SysFileController {
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    private final ISysFileService sysFileService;

    @Resource
    UFOPFactory ufopFactory;

    public SysFileController(@Qualifier("minioSysFileServiceImpl") ISysFileService sysFileService) {
        this.sysFileService = sysFileService;
    }

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public R<SysFile> upload(MultipartFile file, Long fileFolderId) {
        try {
            // 上传并返回访问地址
            if (sysFileService instanceof FastDfsSysFileServiceImpl) {
                System.out.println("FastDfsSysFileServiceImpl");
            } else if (sysFileService instanceof LocalSysFileServiceImpl) {
                System.out.println("LocalSysFileServiceImpl");
            } else if (sysFileService instanceof MinioSysFileServiceImpl) {
                System.out.println("MinioSysFileServiceImpl");
            }
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            try {
                URL URI = new URL(url);
                MultimediaObject object = new MultimediaObject(URI);
                MultimediaInfo info = object.getInfo();
                sysFile.setHeight(Long.valueOf(info.getVideo().getSize().getHeight()));
                sysFile.setWidth(Long.valueOf(info.getVideo().getSize().getWidth()));
                if (sysFile.getWidth() != null && sysFile.getHeight() != null) {
                    sysFile.setAspectRatio(sysFile.getWidth() / (sysFile.getHeight() * 1.00));
                }
                //时长
                sysFile.setDuration(info.getDuration());
                //视频码率
                sysFile.setBitRate(info.getVideo().getBitRate());
                //视频帧率
                sysFile.setFrameRate(info.getVideo().getFrameRate());
                //音频码率
                sysFile.setAudioBitRate(info.getAudio() == null ? null : info.getAudio().getBitRate());
                sysFile.setName(FileUtils.getName(url));
                sysFile.setUrl(url);
                sysFile.setFileFolderId(fileFolderId == null ? 0L : fileFolderId);
                sysFile.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
                sysFile.setCreateTime(DateUtils.getNowDate());
                sysFile.setIsFolder(false);

            } catch (Exception e) {
                log.error("upload文件失败", e);
            }
            return R.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            try {
                sysFileService.removeFile(file.getName());
            } catch (Exception ex) {
                log.error("上传文件失败后删除文件失败", ex);
            }
            return R.fail(e.getMessage());
        }
    }

    /**
     * 文件上传请求
     */
    @PostMapping("uploadByUfopBatch")
    public R<List<SysFile>> uploadByUfopBatch(HttpServletRequest httpServletRequest, UploadFile uploadFile, Long fileFolderId) {

        try {
            Uploader uploader = ufopFactory.getUploader();
            List<UploadFileResult> upload = uploader.upload(httpServletRequest, uploadFile);
            List<SysFile> resultList = new ArrayList<>();
            for (UploadFileResult uploadFileResult : upload) {
                String fileUrl = uploadFileResult.getFileUrl();
                SysFile sysFile = new SysFile();
                try {
                    URL URI = new URL(fileUrl);
                    MultimediaObject object = new MultimediaObject(URI);
                    MultimediaInfo info = object.getInfo();
                    sysFile.setHeight(Long.valueOf(info.getVideo().getSize().getHeight()));
                    sysFile.setWidth(Long.valueOf(info.getVideo().getSize().getWidth()));
                    if (sysFile.getWidth() != null && sysFile.getHeight() != null) {
                        sysFile.setAspectRatio(sysFile.getWidth() / (sysFile.getHeight() * 1.00));
                    }
                    //时长
                    sysFile.setDuration(info.getDuration());
                    //视频码率
                    sysFile.setBitRate(info.getVideo().getBitRate());
                    //视频帧率
                    sysFile.setFrameRate(info.getVideo().getFrameRate());
                    //音频码率
                    sysFile.setAudioBitRate(info.getAudio() == null ? null : info.getAudio().getBitRate());
                    sysFile.setName(FileUtils.getName(fileUrl));
                    sysFile.setUrl(fileUrl);
                    sysFile.setFileFolderId(fileFolderId == null ? 0L : fileFolderId);
                    sysFile.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
                    sysFile.setCreateTime(DateUtils.getNowDate());
                    sysFile.setIsFolder(false);
                    sysFile.setFileSize(uploadFileResult.getFileSize());
                    sysFile.setSuffix(uploadFileResult.getExtendName());
                    resultList.add(sysFile);
                } catch (Exception e) {
                    log.error("upload文件失败", e);
                }
            }
            return R.ok(resultList);
        } catch (Exception e) {
            log.error("上传文件失败", e);
//            try {
//                Deleter deleter = ufopFactory.getDeleter(3);
//                DeleteFile deleteFile = new DeleteFile();
//                deleteFile.setFileUrl(fileUrl);
//                deleter.delete();
//                sysFileService.removeFile(file.getName());
//            } catch (Exception ex) {
//                log.error("上传文件失败后删除文件失败", ex);
//            }
            return R.fail(e.getMessage());
        }

    }

    /**
     * 文件上传请求
     */
    @PostMapping("uploadByUfopSingle")
    public R<SysFile> uploadByUfopSingle(HttpServletRequest httpServletRequest, UploadFile uploadFile, Long fileFolderId) {
        try {
            Uploader uploader = ufopFactory.getUploader();
            List<UploadFileResult> upload = uploader.upload(httpServletRequest, uploadFile);
            UploadFileResult uploadFileResult = upload.get(0);
            String fileUrl = uploadFileResult.getFileUrl();
            SysFile sysFile = new SysFile();
            try {
                URL URI = new URL(fileUrl);
                MultimediaObject object = new MultimediaObject(URI);
                MultimediaInfo info = object.getInfo();
                sysFile.setHeight(Long.valueOf(info.getVideo().getSize().getHeight()));
                sysFile.setWidth(Long.valueOf(info.getVideo().getSize().getWidth()));
                if (sysFile.getWidth() != null && sysFile.getHeight() != null) {
                    sysFile.setAspectRatio(sysFile.getWidth() / (sysFile.getHeight() * 1.00));
                }
                //时长
                sysFile.setDuration(info.getDuration());
                //视频码率
                sysFile.setBitRate(info.getVideo().getBitRate());
                //视频帧率
                sysFile.setFrameRate(info.getVideo().getFrameRate());
                //音频码率
                sysFile.setAudioBitRate(info.getAudio() == null ? null : info.getAudio().getBitRate());
                sysFile.setName(FileUtils.getName(fileUrl));
                sysFile.setUrl(fileUrl);
                sysFile.setFileFolderId(fileFolderId == null ? 0L : fileFolderId);
                sysFile.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
                sysFile.setCreateTime(DateUtils.getNowDate());
                sysFile.setIsFolder(false);
                sysFile.setFileSize(uploadFileResult.getFileSize());
                sysFile.setSuffix(uploadFileResult.getExtendName());

            } catch (Exception e) {
                log.error("upload文件失败", e);
            }

            return R.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
//            try {
//                Deleter deleter = ufopFactory.getDeleter(3);
//                DeleteFile deleteFile = new DeleteFile();
//                deleteFile.setFileUrl(fileUrl);
//                deleter.delete();
//                sysFileService.removeFile(file.getName());
//            } catch (Exception ex) {
//                log.error("上传文件失败后删除文件失败", ex);
//            }
            return R.fail(e.getMessage());
        }
    }

    /**
     * 获取文件上传minio的签名地址
     */
    @PostMapping("signature")
    public R<SysFile> signature(MultipartFile file, String fileFolderId) {
        try {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setUrl(url);
            sysFile.setFileFolderId(Long.valueOf(fileFolderId));
            return R.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }
}