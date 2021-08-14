package ink.kangaroo.pixiv.model.result;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

@Data
public class PixivArtistDetailResult extends PixivArtistResult{
    @JsonSetter("following")
    private Integer following;
    @JsonSetter("followedBack")
    private Boolean followedBack;
    @JsonSetter("comment")
    private String comment;
    @JsonSetter("commentHtml")
    private String commentHtml;
    @JsonSetter("webpage")
    private Boolean webpage;
    /**
     * social: {
     * twitter: {
     * url: "https://twitter.com/rosuuri"
     * },
     * circlems: {
     * url: "https://portal.circle.ms/Circle/Index/10413684"
     * }
     * }
     */
    @JsonSetter("social")
    private List<Object> social;
    @JsonSetter("region")
    private Boolean region;
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
    /**
     * {
     * error: false,
     * message: "",
     * body: {
     * userId: "2993192",
     * name: "Rosuuri",
     * image: "https://i.pximg.net/user-profile/img/2020/10/08/11/39/05/19478356_23b03a33cada2c1858e399ac7c231b2e_50.png",
     * imageBig: "https://i.pximg.net/user-profile/img/2020/10/08/11/39/05/19478356_23b03a33cada2c1858e399ac7c231b2e_170.png",
     * premium: true,
     * isFollowed: false,
     * isMypixiv: false,
     * isBlocking: false,
     * background: null,
     * sketchLiveId: null,
     * partial: 1,
     * acceptRequest: false,
     * sketchLives: [ ],
     * following: 685,
     * followedBack: false,
     * comment: "Twitter - @rosuuri work email: rosuuriart@gmail.com ■お仕事履歴 https://www.rosuuri.com/work 他サイトへのアップロードは許可なしでもOKです！署名とリンクがあれば問題ないですよ！ 私のイラストは商業目的な複写、模写、発売・販売、出版,無断転載等は禁止です。ありがとうございます！ Repost + credit/source = OK! DON'T reprint/distribute/sell my art for commercial use! Thank you!",
     * commentHtml: "Twitter - @rosuuri<br />work email: rosuuriart@gmail.com<br /><br />■お仕事履歴<br /><a href="/jump.php?https%3A%2F%2Fwww.rosuuri.com%2Fwork" target="_blank">https://www.rosuuri.com/work</a><br /><br />他サイトへのアップロードは許可なしでもOKです！署名とリンクがあれば問題ないですよ！<br />私のイラストは商業目的な複写、模写、発売・販売、出版&#44;無断転載等は禁止です。ありがとうございます！<br /><br />Repost + credit/source = OK!<br />DON&#39;T reprint/distribute/sell my art for commercial use! Thank you!",
     * webpage: "http://www.rosuuri.com/",
     * social: {
     * twitter: {
     * url: "https://twitter.com/rosuuri"
     * },
     * circlems: {
     * url: "https://portal.circle.ms/Circle/Index/10413684"
     * }
     * },
     * region: {
     * name: "United States",
     * privacyLevel: "0"
     * },
     * birthDay: {
     * name: null,
     * privacyLevel: null
     * },
     * gender: {
     * name: "女性",
     * privacyLevel: "0"
     * },
     * job: {
     * name: "求职中",
     * privacyLevel: "0"
     * },
     * workspace: {
     * userWorkspaceTool: "Clip Studio Paint",
     * userWorkspaceTablet: "Wacom Cintiq 13HD"
     * },
     * official: false,
     * group: [
     * {
     * id: "1097",
     * title: "Pixiv Fantasia WorldWide",
     * iconUrl: "https://i.pximg.net/c/128x128/imgaz/2018/12/26/12/49/19/group_icon_1097_square1200.jpg"
     * }
     * ]
     * }
     * }
     */

}
