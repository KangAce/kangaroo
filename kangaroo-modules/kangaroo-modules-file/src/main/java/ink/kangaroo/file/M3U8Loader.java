package ink.kangaroo.file;

import com.alibaba.nacos.common.utils.MD5Utils;
import com.alibaba.nacos.shaded.com.google.protobuf.ServiceException;
import ink.kangaroo.common.core.utils.RestTemplateUtils;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.file.domain.M3U8;
import ink.kangaroo.file.domain.TS;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.Loader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ink.kangaroo.file.domain.Constant.FILESEPARATOR;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/25 0:21
 */
@Slf4j
@Component
public class M3U8Loader {
    //    @Resource
//    private RestTemplate restTemplate;
    public static void main(String[] args) throws ServiceException, IOException {
        List<String> urls = new ArrayList<>();
        for (String url : urls) {
            M3U8 m3U8 = M3U8Loader.load(url);
            m3U8.setDir("D:\\m3u8JavaTest");
//        m3U8.setFileName("Test");
            m3U8.go();
        }

//
//        File file = new File(m3U8.getDir()+FILESEPARATOR+"tmp.txt");
//        for (int i = 0; i < 10; i++) {
//            FileUtils.writeLines(file, Arrays.asList("file '" + "file '" + m3U8.getDir()+FILESEPARATOR+i+".ts" + "'"), true);
//        }
//        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
//        File file = new File("tmp.txt");
//        List<TS> tsList = m3U8.getTsList();
//        for (int i = 1, tsListSize = tsList.size(); i < tsListSize; i++) {
//            TS finishedFile = tsList.get(i);
//            try {
//                FileUtils.writeStringToFile(file, "file '" + m3U8.getDir()+FILESEPARATOR+".ts" + "'", Charset.defaultCharset());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        ProcessBuilder pb = new ProcessBuilder(ffmpeg, "-i", param.toString(), "-c", "copy", m3U8.getDir() + FILESEPARATOR + m3U8.getFileName() + ".mp4");
//        try {
//            int i = pb.inheritIO().start().waitFor();
//        } catch (InterruptedException | IOException e) {
//            e.printStackTrace();
//        }

    }

    public static M3U8 load(String url) throws ServiceException, IOException {

        System.out.println(url);
        if (url == null) {

        }
        Assert.hasText(url, "url must not be blank");
        M3U8 m3U8 = new M3U8();
        try {
            m3U8.setFileName(MD5Utils.md5Hex(url.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        log.debug("Downloading [{}]", url);
        // Download it
        ResponseEntity<String> downloadResponse = RestTemplateUtils.get(url, String.class);

        log.debug("Download response: [{}]", downloadResponse.getStatusCode());

        String body = downloadResponse.getBody();
        if (downloadResponse.getStatusCode().isError() || body == null) {
            throw new ServiceException("下载失败 " + url + ", 状态码: " + downloadResponse.getStatusCode());
        }
        log.debug("Downloaded [{}]", url);
        //获取ts真正的url
        m3U8.setRestTemplate(RestTemplateUtils.getRestTemplate());
        m3U8.setBaseUrl(StringUtils.getDomainForUrl(url));
        //判断是否是m3u8链接
        if (!body.contains("#EXTM3U")) {
            throw new ServiceException(url + "不是m3u8链接！");
        }
        String[] split = body.split("\\n");
        for (String s : split) {
            //如果含有此字段，则说明只有一层m3u8链接
            if (s.contains("#EXT-X-KEY")) {
                m3U8.loadKey(s);
            }
            //如果含有此字段，则说明ts片段链接需要从第二个m3u8链接获取
            if (s.contains(".m3u8")) {
                return load(m3U8.getBaseUrl() + s);
            }
            if (s.contains("#EXT-X-VERSION:")) {
                m3U8.setVersion(s.replace("#EXT-X-VERSION:", ""));
            }
            if (s.contains("#EXT-X-TARGETDURATION:")) {
                m3U8.setTargetDuration(s.replace("#EXT-X-TARGETDURATION:", ""));
            }
            if (s.contains("EXT-X-PLAYLIST-TYPE:")) {
                m3U8.setPlaylistType(s.replace("EXT-X-PLAYLIST-TYPE:", ""));
            }
            if (s.contains("#EXT-X-MEDIA-SEQUENCE:")) {
                m3U8.setMediaSequence(s.replace("#EXT-X-MEDIA-SEQUENCE:", ""));
            }

        }
        List<TS> tsList = new ArrayList<>();
        //将ts片段链接加入set集合
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.contains("#EXTINF")) {
                TS ts = new TS();
                ts.setExtInf(s.replace("#EXTINF:", "").replace(",", ""));
                ts.setUri(split[++i]);
                tsList.add(ts);
            }
        }
        m3U8.setTsList(tsList);
        m3U8.setThreadCount(100);
        return m3U8;
    }


}
