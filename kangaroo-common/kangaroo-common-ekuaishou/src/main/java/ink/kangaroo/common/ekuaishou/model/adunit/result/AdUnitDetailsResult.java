/**
 * Copyright 2021 bejson.com
 */
package ink.kangaroo.common.ekuaishou.model.adunit.result;

import com.alibaba.fastjson.annotation.JSONField;
import ink.kangaroo.common.ekuaishou.model.adunit.param.Target;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2021-09-16 16:27:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class AdUnitDetailsResult {

    /**
     * status
     */
    @JSONField(name = "status")
    private int status;
    /**
     * bid
     */
    @JSONField(name = "bid")
    private int bid;
    /**
     * target
     */
    @JSONField(name = "target")
    private Target target;
    /**
     * speed
     */
    @JSONField(name = "speed")
    private int speed;
    /**
     * schedule
     */
    @JSONField(name = "schedule")
    private String schedule;
    /**
     * url
     */
    @JSONField(name = "url")
    private String url;
    /**
     * campaign_id
     */
    @JSONField(name = "campaign_id")
    private long campaign_id;
    /**
     * unit_id
     */
    @JSONField(name = "unit_id")
    private long unit_id;
    /**
     * unit_name
     */
    @JSONField(name = "unit_name")
    private String unit_name;
    /**
     * put_status
     */
    @JSONField(name = "put_status")
    private int put_status;
    /**
     * create_channel
     */
    @JSONField(name = "create_channel")
    private int create_channel;
    /**
     * bid_type
     */
    @JSONField(name = "bid_type")
    private int bid_type;
    /**
     * review_detail
     */
    @JSONField(name = "review_detail")
    private String review_detail;
    /**
     * cpa_bid
     */
    @JSONField(name = "cpa_bid")
    private int cpa_bid;
    /**
     * ocpx_action_type
     */
    @JSONField(name = "ocpx_action_type")
    private int ocpx_action_type;
    /**
     * deep_conversion_type
     */
    @JSONField(name = "deep_conversion_type")
    private int deep_conversion_type;
    /**
     * deep_conversion_bid
     */
    @JSONField(name = "deep_conversion_bid")
    private int deep_conversion_bid;
    /**
     * scene_id
     */
    @JSONField(name = "scene_id")
    private List<Integer> scene_id;
    /**
     * unit_type
     */
    @JSONField(name = "unit_type")
    private int unit_type;
    /**
     * create_time
     */
    @JSONField(name = "create_time")
    private Date create_time;
    /**
     * update_time
     */
    @JSONField(name = "update_time")
    private Date update_time;
    /**
     * day_budget
     */
    @JSONField(name = "day_budget")
    private int day_budget;
    /**
     * day_budget_schedule
     */
    @JSONField(name = "day_budget_schedule")
    private List<String> day_budget_schedule;
    /**
     * convert_id
     */
    @JSONField(name = "convert_id")
    private int convert_id;
    /**
     * template_id
     */
    @JSONField(name = "template_id")
    private int template_id;
    /**
     * schema_uri
     */
    @JSONField(name = "schema_uri")
    private String schema_uri;
    /**
     * smart_bid
     */
    @JSONField(name = "smart_bid")
    private int smart_bid;
    /**
     * use_app_market
     */
    @JSONField(name = "use_app_market")
    private int use_app_market;
    /**
     * app_store
     */
    @JSONField(name = "app_store")
    private List<String> app_store;
    /**
     * begin_time
     */
    @JSONField(name = "begin_time")
    private Date begin_time;
    /**
     * end_time
     */
    @JSONField(name = "end_time")
    private String end_time;
    /**
     * schedule_time
     */
    @JSONField(name = "schedule_time")

    private String schedule_time;
    /**
     * show_mode
     */
    @JSONField(name = "show_mode")
    private int show_mode;
    /**
     * url_type
     */
    @JSONField(name = "url_type")
    private int url_type;
    /**
     * web_uri_type
     */
    @JSONField(name = "web_uri_type")
    private int web_uri_type;
    /**
     * app_id
     */
    @JSONField(name = "app_id")
    private int app_id;
    /**
     * app_icon_url
     */
    @JSONField(name = "app_icon_url")
    private String app_icon_url;
    /**
     * diverse_data
     */
    @JSONField(name = "compensate_status")
    private DiverseData diverse_data;
    /**
     * gift_data
     */
    @JSONField(name = "compensate_status")
    private String gift_data;
    /**
     * study_status
     */
    @JSONField(name = "compensate_status")
    private int study_status;
    /**
     * compensate_status
     */
    @JSONField(name = "compensate_status")
    private int compensate_status;
    /**
     * site_type
     */
    @JSONField(name = "site_type")
    private int site_type;
    /**
     * roi_ratio
     */
    @JSONField(name = "roi_ratio")
    private int roi_ratio;
    /**
     * video_landing_page
     */
    @JSONField(name = "video_landing_page")
    private boolean video_landing_page;
    /**
     * component_id
     */
    @JSONField(name = "component_id")
    private int component_id;
    /**
     * auto_target
     */
    @JSONField(name = "auto_target")
    private boolean auto_target;
    /**
     * intention_target
     */
    @JSONField(name = "intention_target")
    private boolean intention_target;
    /**
     * smart_cover
     */
    @JSONField(name = "smart_cover")
    private boolean smart_cover;
    /**
     * asset_mining
     */
    @JSONField(name = "asset_mining")
    private boolean asset_mining;
    /**
     * auto_create_photo
     */
    @JSONField(name = "auto_create_photo")
    private boolean auto_create_photo;
    /**
     * support_unit_ids
     */
    @JSONField(name = "support_unit_ids")
    private List<String> support_unit_ids;
    /**
     * fiction_id
     */
    @JSONField(name = "fiction_id")
    private int fiction_id;
    /**
     * merchandise_id
     */
    @JSONField(name = "merchandise_id")
    private int merchandise_id;
    /**
     * merchandise_type
     */
    @JSONField(name = "merchandise_type")
    private int merchandise_type;
    /**
     * item_id
     */
    @JSONField(name = "item_id")
    private long item_id;
    /**
     * merchant_item_put_type
     */
    @JSONField(name = "merchant_item_put_type")
    private int merchant_item_put_type;
    /**
     * consult_id
     */
    @JSONField(name = "consult_id")
    private int consult_id;
    /**
     * adv_card_option
     */
    @JSONField(name = "adv_card_option")
    private int adv_card_option;
    /**
     * adv_card_list
     */
    @JSONField(name = "adv_card_list")
    private List<String> adv_card_list;
    /**
     * backflow_forecast
     */
    @JSONField(name = "backflow_forecast")
    private BackflowForecast backflow_forecast;
    /**
     * use_ska
     */
    @JSONField(name = "use_ska")
    private boolean use_ska;
    /**
     * playable_id
     */
    @JSONField(name = "playable_id")
    private int playable_id;
    /**
     * play_button
     */
    @JSONField(name = "play_button")
    private String play_button;
    /**
     * playable_url
     */
    @JSONField(name = "playable_url")
    private String playable_url;
    /**
     * playable_orientation
     */
    @JSONField(name = "playable_orientation")
    private int playable_orientation;
    /**
     * playable_switch
     */
    @JSONField(name = "playable_switch")
    private int playable_switch;
    /**
     * playable_file_name
     */
    @JSONField(name = "playable_file_name")
    private String playable_file_name;
    /**
     * library_id
     */
    @JSONField(name = "library_id")
    private int library_id;
    /**
     * outer_id
     */
    @JSONField(name = "outer_id")
    private String outer_id;
    /**
     * product_id
     */
    @JSONField(name = "product_id")
    private String product_id;
    /**
     * product_name
     */
    @JSONField(name = "product_name")
    private String product_name;
    /**
     * product_price
     */
    @JSONField(name = "product_price")
    private String product_price;
    /**
     * product_image
     */
    @JSONField(name = "product_image")
    private String product_image;
    /**
     * splash_ad_switch
     */
    @JSONField(name = "splash_ad_switch")
    private boolean splash_ad_switch;


}