package ink.kangaroo.trying;

import com.alibaba.nacos.common.utils.MD5Utils;
import com.alibaba.nacos.shaded.com.google.protobuf.ServiceException;
import ink.kangaroo.common.core.utils.RestTemplateUtils;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.trying.domain.M3U8;
import ink.kangaroo.trying.domain.TS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
            M3U8 load = null;
            try {
                load = M3U8Loader.load(url);
            } catch (Exception e) {
                continue;
            }
            load.setDir("D:\\m3u8JavaTest");
            load.go();
        }

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
        String relativeUrl = url.substring(0, url.lastIndexOf("/") + 1);
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.contains("#EXTINF")) {
                TS ts = new TS();
                String s1 = split[++i];
                ts.setExtInf(s.replace("#EXTINF:", "").replace(",", ""));
                ts.setUri(StringUtils.isUrl(s1) ? s1 : mergeUrl(relativeUrl, s1));
                tsList.add(ts);
            }
        }
        m3U8.setThreadCount(m3U8.getThreadCount());
        m3U8.setTsList(tsList);
        m3U8.setTsList(tsList);
        return m3U8;
    }
    private static String mergeUrl(String start, String end) {
        if (end.startsWith("/"))
            end = end.replaceFirst("/", "");
        int position = 0;
        String subEnd, tempEnd = end;
        while ((position = end.indexOf("/", position)) != -1) {
            subEnd = end.substring(0, position + 1);
            if (start.endsWith(subEnd)) {
                tempEnd = end.replaceFirst(subEnd, "");
                break;
            }
            ++position;
        }
        return start + tempEnd;
    }

}
