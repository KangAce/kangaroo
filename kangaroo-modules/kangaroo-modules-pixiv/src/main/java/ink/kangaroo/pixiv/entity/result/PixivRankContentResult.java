package ink.kangaroo.pixiv.entity.result;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PixivRankContentResult {
    /**
     * 插画标题
     */
    @JsonSetter("title")
    private String title;
    /**
     * 日期，应该是排行榜的日期，暂时没用
     */
    @JsonSetter("date")
    private String date;
    /**
     * 插画所有的标签
     */
    @JsonSetter("tags")
    private List<String> tags;
    /**
     * 缩略图
     */
    @JsonSetter("url")
    private String url;
    /**
     * 插图类型
     */
    @JsonSetter("illust_type")
    private Integer illustType;
    /**
     * 书的风格？
     */
    @JsonSetter("illust_book_style")
    private Integer illustBookStyle;
    /**
     * 页数，插画有几页；
     */
    @JsonSetter("illust_page_count")
    private Integer illustPageCount;
    /**
     * 作者名称
     */
    @JsonSetter("user_name")
    private String userName;
    /**
     * 个人资料 头像
     */
    @JsonSetter("profile_img")
    private String profileImg;
    /**
     * 内容类型 暂时不知道有啥用
     */
//    @JsonSetter("illust_content_type")
    //TODO
//    private List<IllustContentTypeResult> illustContentType;
//    private List<LinkedHashMap> illustContentType;
    /**
     * 是否是一个系列 如果是一个系列咋返回系列的对象，暂时没有动态解析
     */
    @JsonSetter("illust_series")
    private IllustSeriesResult illustSeries;
    /**
     * 插画id
     */
    @JsonSetter("illust_id")
    private String illustId;
    @JsonSetter("width")
    private Integer width;
    @JsonSetter("height")
    private Integer height;
    /**
     * 作者id
     */
    @JsonSetter("user_id")
    private String userId;
    /**
     * 当前排行
     */
    @JsonSetter("rank")
    private Integer rank;
    /**
     * 上次排行
     */
    @JsonSetter("yes_rank")
    private Integer yesRank;
    /**
     * 评分计数
     */
    @JsonSetter("rating_count")
    private String ratingCount;
    @JsonSetter("view_count")
    private Integer viewCount;

    @JsonSetter("illust_upload_timestamp")
    private Date illustUploadTimestamp;
    /**
     * 属性，暂时不知道什么意思
     */
    @JsonSetter("attr")
    private String attr;

}
