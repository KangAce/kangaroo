package ink.kangaroo.common.pixiv.config;


import com.alibaba.fastjson.JSON;
import ink.kangaroo.common.pixiv.JsonBodyHandler;
import ink.kangaroo.common.pixiv.model.PixivResult;
import ink.kangaroo.common.pixiv.model.artist.GetPixivArtistParam;
import ink.kangaroo.common.pixiv.model.artist.PixivArtistResult;
import ink.kangaroo.common.pixiv.model.illust.GetPixivIllustDetailParam;
import ink.kangaroo.common.pixiv.model.illust.GetPixivIllustPageParam;
import ink.kangaroo.common.pixiv.model.illust.PixivIllustDetailResult;
import ink.kangaroo.common.pixiv.model.illust.PixivIllustPageResult;
import ink.kangaroo.common.pixiv.model.rank.PixivRankContent;
import ink.kangaroo.common.pixiv.model.rank.PixivRankMode;
import ink.kangaroo.common.pixiv.model.rank.param.GetPixivRankParam;
import ink.kangaroo.common.pixiv.model.rank.result.PixivRankResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
        PixivProperties pixivProperties = new PixivProperties();
        pixivProperties.setCookie("63063042_bzdxnk6SGKaNg4DsdSJ4A853VSZcjOvr");
        PixivClient init = PixivClient.init(pixivProperties);
        //测试分类分页获取排行榜SDK
        GetPixivRankParam getPixivRankParam = new GetPixivRankParam();
        getPixivRankParam.setMode(PixivRankMode.PIXIV_RANK_DAILY);
        getPixivRankParam.setContent(PixivRankContent.PIXIV_RANK_ALL);
        getPixivRankParam.setPageNum(1);
        getPixivRankParam.setDate("20211010");
        PixivRankResult pixivRank = init.getPixivRank(getPixivRankParam);
//        System.out.println(pixivRank);

        //测试根据插画ID获取插画详情接口
//        GetPixivIllustDetailParam getPixivIllustDetailParam = new GetPixivIllustDetailParam();
//        getPixivIllustDetailParam.setIllustId(pixivRank.getContents().get(0).getIllustId());
//        PixivIllustDetailResult pixivIllustDetail = init.getPixivIllustDetail(getPixivIllustDetailParam);
//        System.out.println(pixivIllustDetail);
        //测试根据插画ID获取插画详情接口
        GetPixivIllustPageParam getPixivIllustPageParam = new GetPixivIllustPageParam();
//        getPixivIllustPageParam.setIllustId(pixivRank.getContents().get(0).getIllustId());
//        getPixivIllustPageParam.setIllustId("93319736");
//        getPixivIllustPageParam.setLang("zh");
//        List<PixivIllustPageResult> pixivIllustPage = init.getPixivIllustPage(getPixivIllustPageParam);
//        System.out.println(pixivIllustPage);

        //测试根据作者id获取作者详情
        GetPixivArtistParam getPixivArtistParam = new GetPixivArtistParam();
        getPixivArtistParam.setArtistId(pixivRank.getContents().get(0).getUserId());
        PixivArtistResult pixivArtist = init.getPixivArtist(getPixivArtistParam);
        System.out.println(pixivArtist);

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
                .proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 1080)))
                .cookieHandler(cookieHandler)
                .build();
        PixivClient pixivClient = new PixivClient();
        pixivClient.setHttpClient(client);
        return pixivClient;
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
     * 根据插画ID获取插画详情
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
        } while (jsonSync == null || count > retryCount);
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
//                .header("cookie", "__cfduid=d5dadf4c8509237b7aaba684793f7e0d71607506810; first_visit_datetime_pc=2020-12-09+18%3A40%3A10; p_ab_id=5; p_ab_id_2=8; p_ab_d_id=1842471816; yuid_b=MIIVE4k; __utmz=235335808.1607507184.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _fbp=fb.1.1607507184901.1027224533; _ga=GA1.2.54296328.1607507184; device_token=2305bda413aa7a0c1d6748f6f7ad9dda; a_type=0; b_type=1; ki_r=; login_ever=yes; _gid=GA1.2.150731325.1608737523; __utmc=235335808; tag_view_ranking=5AKOA9olwV~rp6Te4VcwM~TsE2Gx1QTz~wOWgmV97Z_~pIuCOnlL8m~4A4qCL_ZMc~-T-DLTFy3n~a66ATxNyS8~_pwIgrV8TB~_hSAdpN9rx~RTJMXD26Ak~Lt-oEicbBr~C7PD30xMZg~yxXUNz8bXw~RleaZ7I8th~1p_sfUIeej~QZ1bSxMJC5~SdAE2gRbY9~UeTUIDIKsu~OfvEJkxqVs~PGh9Qwfchx~fHxx-ykk0X~g5QWNKvqWG~YYXnZO5Qu9~gM8nSdLN-y~C3mw5Wnr6o~cB9ZRJxpqD~8mLfOQVCVK~ySyZ9GtQO3~Qa8ggRsDmW~er4CrD9A5r~qx-Tlvmbdj~JL8rvDh62i~9TAZ-VDNKH~Ws9sPZJVZb~7PcJCEOPck~xbzADO2cNl~uIjT39GBZq~7WMFddFjc_~9uPr6PmtdQ~kGYw4gQ11Z~Wi1mGeJ7fz~vZkH7EkQtN~TPaY433as8~Cr3jSW1VoH~lH5YZxnbfC~HLWLeyYOUF; limited_ads=%7B%22responsive%22%3A%22%22%7D; categorized_tags=6sZKldb07K~BeQwquYOKY~IRbM9pb4Zw~OT-C6ubi9i~QZ1bSxMJC5~Qa8ggRsDmW~RkTaP3d-E6~UeTUIDIKsu; __utma=235335808.54296328.1607507184.1608832578.1608855803.17; tags_sended=1; ki_s=212334%3A0.0.0.0.0%3B212529%3A0.0.0.0.0; ki_t=1607507252915%3B1608855865842%3B1608855922930%3B7%3B33; __utmt=1; _gat=1; __cf_bm=087ea34052fd8fb989ed59008b5ef2b05b33e0c3-1608856510-1800-AeHXpvAWBH6OZfBBme1JxfkZ4yP9qdPmLuRQhhAVytFGJ1ipcVFD+FpLsUycnGbuAD4M0shLTmq+4iuRV3XGn9KV/rtOW8MFAR/RI7z94momfHKl8r6zba71eA45TM2KsjGesSQhAtyT6C/vA6xl12V/eX+gq/Uwdhw4RnDs5a1z; PHPSESSID=63063042_OBMilUBlV2rYtk69WOVHn8zvdg5N0wIE; c_type=26; privacy_policy_agreement=2; __utmv=235335808.|2=login%20ever=yes=1^3=plan=normal=1^5=gender=male=1^6=user_id=63063042=1^9=p_ab_id=5=1^10=p_ab_id_2=8=1^11=lang=zh=1; __utmb=235335808.11.9.1608856489783")
//                .header("cookie", "PHPSESSID=" + "6ha7uihbpque8v3kljhpnad3sc2mmsb0")
//                .header("referer", "https://www.pixiv.net/tags/Fate%2FGrandOrder/artworks")
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
}
