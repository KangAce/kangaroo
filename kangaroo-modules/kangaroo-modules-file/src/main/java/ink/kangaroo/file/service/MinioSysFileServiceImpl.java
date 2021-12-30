package ink.kangaroo.file.service;

import ink.kangaroo.file.config.MinioConfig;
import ink.kangaroo.file.utils.FileUploadUtils;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Minio 文件存储
 *
 * @author Kangaroo
 */
@Service
public class MinioSysFileServiceImpl implements ISysFileService {
    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient client;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String fileName = FileUploadUtils.extractFilename(file);
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        client.putObject(args);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }

    @Override
    public String signature(String fileName) throws Exception {
        try {
            MinioClient minioClient = MinioClient.builder().endpoint("http://127.0.0.1:9090")
                    .credentials("admin", "12345678").build();
            Map<String, String> reqParams = new HashMap<String, String>();
            reqParams.put("response-content-type", "application/json");
            String product = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT) //这里必须是PUT，如果是GET的话就是文件访问地址了。如果是POST上传会报错.
                            .bucket(minioConfig.getBucketName())
                            .object(fileName)
                            .expiry(60 * 60)
                            .extraQueryParams(reqParams)
                            .build());
            System.out.println(product); // 前端直传需要的url地址
            return product;
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
        return null;
    }

    @Override
    public void removeFile(String fileName) throws Exception {
        client.removeObject(RemoveObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(fileName)
                .build());
    }
}
