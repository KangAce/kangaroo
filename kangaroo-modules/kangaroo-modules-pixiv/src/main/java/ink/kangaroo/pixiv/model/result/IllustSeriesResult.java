package ink.kangaroo.pixiv.model.result;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class IllustSeriesResult {
    /**
     * 系列id 			"illust_series_id": "85765",
     */
    @JsonSetter("illust_series_id")
    private Integer illustSeriesId;
    /**
     * 系列作者id		"illust_series_user_id": "4890179",
     */
    @JsonSetter("illust_series_user_id")
    private Integer illust_series_user_id;
    /**
     * 系列标题	"illust_series_title": "異世界勇者ミズキ",
     */
    @JsonSetter("illust_series_title")
    private String illust_series_title;
    /**
     * 示例为""暂时设置为String	"illust_series_caption": "",
     */
    @JsonSetter("illust_series_caption")
    private String illust_series_caption;
    /**
     * 系列内容数量		"illust_series_content_count": "27",
     */
    @JsonSetter("illust_series_content_count")
    private Integer illust_series_content_count;
    /**
     * 系列创建时间		"illust_series_create_datetime": "2020-06-21 00:51:37",
     */
    @JsonSetter("illust_series_create_datetime")
    private String illust_series_create_datetime;
    /**
     * 插画id 	"illust_series_content_illust_id": "86411271",
     */
    @JsonSetter("illust_series_content_illust_id")
    private Integer illust_series_content_illust_id;
    /**
     * 应该是当前插画所属第几个插画集 "illust_series_content_order": "27",
     */
    @JsonSetter("illust_series_content_order")
    private Integer illust_series_content_order;
    /**
     * 系列的地址 相对路径 "page_url": "/user/4890179/series/85765"
     */
    @JsonSetter("page_url")
    private String page_url;

}
