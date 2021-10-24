package ink.kangaroo.common.pixiv.config;


import com.alibaba.fastjson.JSON;
import ink.kangaroo.common.pixiv.InputStreamBodyHandler;
import ink.kangaroo.common.pixiv.JsonBodyHandler;
import ink.kangaroo.common.pixiv.model.PixivCategory;
import ink.kangaroo.common.pixiv.model.PixivResult;
import ink.kangaroo.common.pixiv.model.artist.GetPixivArtistParam;
import ink.kangaroo.common.pixiv.model.artist.PixivArtistResult;
import ink.kangaroo.common.pixiv.model.illust.GetPixivIllustDetailParam;
import ink.kangaroo.common.pixiv.model.illust.GetPixivIllustPageParam;
import ink.kangaroo.common.pixiv.model.illust.PixivIllustDetailResult;
import ink.kangaroo.common.pixiv.model.illust.PixivIllustPageResult;
import ink.kangaroo.common.pixiv.model.rank.param.GetPixivRankParam;
import ink.kangaroo.common.pixiv.model.rank.result.PixivRankResult;
import ink.kangaroo.common.pixiv.model.search.SearchPixivParam;
import ink.kangaroo.common.pixiv.model.search.SearchPixivResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Slf4j
@Component
public class PixivClient {

    private HttpClient httpClient;
    private CookieManager cookieManager;
    private Integer retryCount = 3;

    public static void main(String[] args) throws IOException {
        String decode = URLDecoder.decode("%E7%85%89%E7%82%AD");
        System.out.println(decode);
        PixivProperties pixivProperties = new PixivProperties();
        pixivProperties.setCookie("63063042_bzdxnk6SGKaNg4DsdSJ4A853VSZcjOvr");
        PixivClient init = PixivClient.init(pixivProperties);
//        String url = "https://www.pixiv.net/ajax/search/top/%E7%85%89%E7%82%AD?lang=zh";//ok
//        String url = "https://www.pixiv.net/ajax/search/novels/%E7%85%89%E7%82%AD?word=%E7%85%89%E7%82%AD&order=date_d&mode=all&p=2&s_mode=s_tag_full&lang=zh";//ok
//        String url = "https://www.pixiv.net/ajax/search/illustrations/%E7%85%89%E7%82%AD?word=%E7%85%89%E7%82%AD&order=date_d&mode=all&p=1&s_mode=s_tag_full&type=illust_and_ugoira&lang=zh";//ok 插画
//        String url = "https://www.pixiv.net/ajax/search/manga/%E7%85%89%E7%82%AD?word=%E7%85%89%E7%82%AD&order=date_d&mode=all&p=1&s_mode=s_tag_full&type=manga&lang=zh";//ok 漫画
//        String url = "https://www.pixiv.net/ajax/search/suggestion?mode=all&lang=zh";//ok
//        String url = "https://www.pixiv.net/rpc/cps.php?keyword=%E7%85%89%E7%82%AD";// 关键字对剑 {"candidates":[{"tag_name":"\u7149\u70ad","access_count":"166804866","type":"prefix"}]}
//        String url = "https://www.pixiv.net/ajax/search/illustrations/wlopa?word=wlopa&order=date_d&mode=all&p=1&s_mode=s_tag&type=illust_and_ugoira&lang=zh";// 关键字对剑 {"candidates":[{"tag_name":"\u7149\u70ad","access_count":"166804866","type":"prefix"}]}
//
//        Object jsonSyncRetry = init.getJsonSyncRetry(url, String.class);
//        System.out.println(jsonSyncRetry);
//        System.out.println(JSON.toJSONString(jsonSyncRetry));
//        for (PixivCategory value : PixivCategory.values()) {
        SearchPixivParam searchPixivParam = new SearchPixivParam();
//            searchPixivParam.setCategory(value);
        searchPixivParam.setCategory(PixivCategory.PIXIV_CATEGORY_ILLUSTRATIONS);
        searchPixivParam.setKeyword("笼中鸟");
        SearchPixivResult search = init.search(searchPixivParam);
        System.out.println(search);
//        }


        //测试分类分页获取排行榜SDK
//        GetPixivRankParam getPixivRankParam = new GetPixivRankParam();
//        getPixivRankParam.setMode(PixivRankMode.PIXIV_RANK_DAILY);
//        getPixivRankParam.setContent(PixivRankContent.PIXIV_RANK_ALL);
//        getPixivRankParam.setPageNum(1);
//        getPixivRankParam.setDate("20211010");
//        PixivRankResult pixivRank = init.getPixivRank(getPixivRankParam);
//        System.out.println(pixivRank);

        //测试根据插画ID获取插画详情接口
//        GetPixivIllustDetailParam getPixivIllustDetailParam = new GetPixivIllustDetailParam();
//        getPixivIllustDetailParam.setIllustId(pixivRank.getContents().get(0).getIllustId());
//        PixivIllustDetailResult pixivIllustDetail = init.getPixivIllustDetail(getPixivIllustDetailParam);
//        System.out.println(pixivIllustDetail);
        //测试根据插画ID获取插画详情接口
//        GetPixivIllustPageParam getPixivIllustPageParam = new GetPixivIllustPageParam();
//        getPixivIllustPageParam.setIllustId(pixivRank.getContents().get(0).getIllustId());
//        getPixivIllustPageParam.setIllustId("93319736");
//        getPixivIllustPageParam.setLang("zh");
//        List<PixivIllustPageResult> pixivIllustPage = init.getPixivIllustPage(getPixivIllustPageParam);
//        System.out.println(pixivIllustPage);

        //测试根据作者id获取作者详情
//        GetPixivArtistParam getPixivArtistParam = new GetPixivArtistParam();
//        getPixivArtistParam.setArtistId(pixivRank.getContents().get(0).getUserId());
//        PixivArtistResult pixivArtist = init.getPixivArtist(getPixivArtistParam);
//        System.out.println(pixivArtist);

//        init.getJsonSync("")
    }

    /**
     * 初始化PIXIV客户端
     *
     * @return
     */
    public static PixivClient init(PixivProperties properties) throws IOException {
        String pixiv_cookies = "";
        CookieHandler cookieHandler = new CookieManager();
        Map<String, List<String>> cookies = new HashMap<>();
        cookies.put("PHPSESSID", List.of(properties.getCookie()));
        cookieHandler.put(URI.create(".pixiv.net"), cookies);

        cookies = new HashMap<>();
        cookies.put("login_ever", Collections.singletonList("yes"));
        cookieHandler.put(URI.create(".www.pixiv.net"), cookies);
        cookies = new HashMap<>();
        cookieHandler.put(URI.create("www.pixiv.net"), cookies);
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .proxy(ProxySelector.of(new InetSocketAddress(properties.getProxyIp(), properties.getProxyPort())))
                .cookieHandler(cookieHandler)
                .build();
        PixivClient pixivClient = new PixivClient();
        pixivClient.setHttpClient(client);
        return pixivClient;
    }


    /**
     * 搜索框 分类
     *
     * @return
     */
    public SearchPixivResult search(SearchPixivParam searchPixivParam) {
        String url = "https://www.pixiv.net/ajax/search/" + searchPixivParam.getCategory().getValue() + "/" + searchPixivParam.getKeyword() + "?word=" + searchPixivParam.getKeyword() + "&order=date_d&mode=all&p=2&s_mode=s_tag_full&lang=zh";
        PixivResult<Object> jsonSync = (PixivResult) getJsonSyncRetry(url, PixivResult.class);
        if (jsonSync == null) {
            return null;
        }
        System.out.println(jsonSync.getBody());
        return JSON.parseObject(JSON.toJSONString(jsonSync.getBody()), SearchPixivResult.class);
    }

    /**
     * 获取作者信息
     *
     * @return
     */
    public PixivArtistResult getPixivArtist(GetPixivArtistParam getPixivArtistParam) {
        String url = "https://www.pixiv.net/ajax/user/" + getPixivArtistParam.getArtistId() + "?full=0&lang=zh";
        PixivResult<Object> jsonSync = (PixivResult) getJsonSyncRetry(url, PixivResult.class);
        if (jsonSync == null) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(jsonSync.getBody()), PixivArtistResult.class);
    }

    /**
     * 根据插画ID获取插画详情按页
     *
     * @return
     */
    public List<PixivIllustPageResult> getPixivIllustPage(GetPixivIllustPageParam getPixivRankParam) {
        String url = "https://www.pixiv.net/ajax/illust/" + getPixivRankParam.getIllustId() + "/pages?lang=" + getPixivRankParam.getLang();
        PixivResult<List<Object>> jsonSync = (PixivResult) getJsonSyncRetry(url, PixivResult.class);
        return jsonSync.getBody().stream().map(JSON::toJSONString).map(s -> JSON.parseObject(s, PixivIllustPageResult.class)).collect(Collectors.toList());
    }

    /**
     * 根据插画ID获取插画详情
     *
     * @return
     */
    public PixivIllustDetailResult getPixivIllustDetail(GetPixivIllustDetailParam getPixivRankParam) {
        String url = "https://www.pixiv.net/ajax/illust/" + getPixivRankParam.getIllustId();
        PixivResult<Object> jsonSync = (PixivResult<Object>) getJsonSyncRetry(url, PixivResult.class);
        return JSON.parseObject(JSON.toJSONString(jsonSync.getBody()), PixivIllustDetailResult.class);

    }

    /**
     * 分页分类获取排行榜
     *
     * @return
     */
    public PixivRankResult getPixivRank(GetPixivRankParam getPixivRankParam) {
        String url = "https://www.pixiv.net/ranking.php?mode=" + getPixivRankParam.getMode().getValue() + "&date=" + getPixivRankParam.getDate() + "&p=" + getPixivRankParam.getPageNum() + "&format=json";
        if (getPixivRankParam.getContent() != null && !"all".equals(getPixivRankParam.getContent().getValue())) {
            url += "&content=" + getPixivRankParam.getContent().getValue();
        }
        return (PixivRankResult) getJsonSyncRetry(url, PixivRankResult.class);
    }

    private Object getJsonSyncRetry(String url, Class target) {
        int count = 0;
        Object jsonSync;
        do {
            jsonSync = getJsonSync(url, target);
            count++;
        } while (jsonSync == null && count < retryCount);
        return jsonSync;
    }

    private Object getJsonSync(String url, Class target) {
        return getJsonSync(url, target, null);
    }

    public Object getJsonSync(String url, Class target, Map<String, String> headers) {
        /**
         * 记录请求链接
         */
        log.info("url -> {}", url);
        HttpRequest.Builder uri = HttpRequest.newBuilder()
                .uri(URI.create(url));
        decorateHeader(uri);
        if (headers != null) {
            for (String s : headers.keySet()) {
                uri.header(s, headers.get(s));
            }
        }

        HttpRequest getRank = uri
                .GET()
                .build();
        try {
            return httpClient.send(getRank, JsonBodyHandler.jsonBodyHandler(target)).body();
        } catch (IOException | InterruptedException e) {
            System.out.println("网络错误" + e.getMessage());
        }
        return null;
    }

    public static void decorateHeader(HttpRequest.Builder builder) {
        String[] hash = gethash();
        builder
                .header("referrer", "no-referrer")//
                .header("Artist-Agent", "PixivAndroidApp/5.0.93 (Android 5.1; m2)")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("App-OS", "android")
                .header("App-OS-Version", "5.1")
                .header("App-Version", "5.0.93")
                .header("Accept-Language", "zh_CN")
                .header("X-Client-Hash", hash[1])
                .header("X-Client-Time", hash[0]);
    }

    public static String[] gethash() {
        SimpleDateFormat simpleDateFormat;
        String fortmat = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";
        simpleDateFormat = new SimpleDateFormat(fortmat, Locale.US);
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String seed = time + "28c1fdd170a5204386cb1313c7077b34f83e4aaf4aa829ce78c231e05b0bae2c";
        assert md5 != null;
        byte[] digest = md5.digest(seed.getBytes());
        StringBuilder hash = new StringBuilder();
        for (byte b : digest) {
            hash.append(String.format("%02x", b));
        }
        return new String[]{time, hash.toString()};
    }

    public InputStream getInputStream(String url) {

        HttpRequest.Builder uri = HttpRequest.newBuilder()
                .uri(URI.create(url));
        decorateHeader(uri);
//        PixivUser pixivUser = oauthManager.getRandomPixivUser();
        HttpRequest getRank = uri
//                .header("Authorization", "Bearer " + pixivUser.getAccessToken())
//                .header("cookie", "PHPSESSID=6ha7uihbpque8v3kljhpnad3sc2mmsb0")
//                .header("referer", "https://www.pixiv.net/")
//                .header("sec-fetch-dest", "image")
//                .header("sec-fetch-mode", "no-cors")
//                .header("sec-fetch-site", "cross-site")
//                .header("accept", "image/avif,image/webp,image/apng,image/*,*/*;q=0.8")
//                .header("accept-encoding", "gzip, deflate, br")
//                .header("accept-language", "zh-CN,zh;q=0.9,zh-TW;q=0.8,en-US;q=0.7,en;q=0.6")
                .GET()
                .build();
        try {
            return (InputStream) httpClient.send(getRank, InputStreamBodyHandler.inputStreamBodyHandler(InputStream.class)).body();
//            return httpClient.send(getRank, ).body();
        } catch (IOException | InterruptedException e) {
            System.out.println("网络错误" + e.getMessage());
        }
        return null;
    }
}
