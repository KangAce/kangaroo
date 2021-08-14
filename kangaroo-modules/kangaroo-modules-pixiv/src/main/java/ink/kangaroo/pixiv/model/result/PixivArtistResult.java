package ink.kangaroo.pixiv.model.result;

import com.fasterxml.jackson.annotation.JsonSetter;
import fun.imore.tribe.crawler.pixiv.Illustration.util.RequestUtil;
import lombok.Data;

import java.util.List;

@Data
public class PixivArtistResult {
    @JsonSetter("userId")
    private String userId;
    @JsonSetter("name")
    private String name;
    @JsonSetter("image")
    private String image;
    @JsonSetter("imageBig")
    private String imageBig;
    @JsonSetter("premium")
    private Boolean premium;
//    @JsonSetter("isFollowed")
//    private String isFollowed;
    @JsonSetter("isMypixiv")
    private Boolean isMypixiv;
    @JsonSetter("name")
    private Boolean isBlocking;
//    @JsonSetter("background")
//    private String background;
    @JsonSetter("sketchLiveId")
    private String sketchLiveId;
    @JsonSetter("partial")
    private Integer partial;
    @JsonSetter("acceptRequest")
    private Boolean acceptRequest;
    @JsonSetter("sketchLives")
    private List<String> sketchLives;

    public static PixivArtistResult getResult(RequestUtil requestUtil, String artistId) {
        //        PixivArtistResult
        String url = "https://www.pixiv.net/ajax/user/" + artistId + "?full=0&lang=zh";
        url = "https://www.pixiv.net/ajax/user/"+artistId+"/profile/all?lang=zh";
        PixivResult<PixivArtistResult> jsonSync = (PixivResult) requestUtil.getJsonSync(url, PixivResult.class);
        System.out.println(jsonSync);
//        return jsonSync.getBody();
        return null;
    }
    /**
     * {
     * error: false,
     * message: "",
     * body: {
     * userId: "24218478",
     * name: "￦ANKE",
     * image: "https://i.pximg.net/user-profile/img/2019/10/22/04/04/45/16445257_404ce224320f5dac49b6715fafd3824d_50.jpg",
     * imageBig: "https://i.pximg.net/user-profile/img/2019/10/22/04/04/45/16445257_404ce224320f5dac49b6715fafd3824d_170.jpg",
     * premium: true,
     * isFollowed: false,
     * isMypixiv: false,
     * isBlocking: false,
     * background: {
     * repeat: null,
     * color: null,
     * url: "https://i.pximg.net/c/1920x960_80_a2_g5/background/img/2019/05/29/00/48/38/24218478_033529089e244272f83043810e48881c.jpg",
     * isPrivate: false
     * },
     * sketchLiveId: null,
     * partial: 0,
     * acceptRequest: false,
     * sketchLives: [ ]
     * }
     * }
     */

}
