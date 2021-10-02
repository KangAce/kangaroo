package ink.kangaroo.common.ekuaishou.model.creative.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 除非接口变更否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 18:08
 */
@Data
public class CreateCreative2Param implements Serializable {
    /**
     * 必填	广告主 ID	在获取 access_token 的时候返回
     */
    @JSONField(name = "advertiser_id")
    private long advertiser_id;
    /**
     * 必填	广告组 ID	一个组下只能有一个程序化创意，只有这个广告组的 unit_type 为 7 才能创建程序化创意
     */
    @JSONField(name = "unit_id")
    private long unit_id;
    /**
     * 必填	程序化创意名称	不能为空，1-100 字符
     */
    @JSONField(name = "package_name")
    private String package_name;
    /**
     * 必填（即将下线）	横版视频 ID list	横版视频与竖版视频字段至少传一个，可共传，但横版+竖版视频最多可传 5 个。
     */
    @JSONField(name = "horizontal_photo_ids")
    private List<String> horizontal_photo_ids;
    /**
     * 必填（即将下线）	竖版视频 ID list	横版视频与竖版视频字段至少传一个，可共传，但横版+竖版视频最多可传 5 个。
     */
    @JSONField(name = "vertical_photo_ids")
    private List<String> vertical_photo_ids;
    /**
     * 必填（即将下线）	封面 list image_token	最多可传 4 个,智能抽帧下需要为空
     */
    @JSONField(name = "cover_image_tokens")
    private List<String> cover_image_tokens;
    /**
     * 非必填	落地页 ID	计划类型为 2 的时候才有效，需要和组层级选择的 app_id 保持一致
     */
    @JSONField(name = "site_id")
    private Long site_id;
    /**
     * 非必填	封面贴纸	如果使用封面贴纸 sticker_Styles 和 cover_slogans 必须同时传值，最多选择 6 个
     */
    @JSONField(name = "sticker_styles")
    private List<Integer> sticker_styles;
    /**
     * 非必填	封面广告语	0-14 字符，最多选择 6 个（每个中文和英文字符都算一个字符）
     */
    @JSONField(name = "cover_slogans")
    private List<String> cover_slogans;

    /**
     * 必填	行动号召按钮
     */
    @JSONField(name = "action_bar")
    private String action_bar;
    /**
     * 必填	作品广告语	每个不超过 30 个字符，英文字符两个算一个字符，最多可传 3 个
     */
    @JSONField(name = "captions")
    private List<String> captions;
    /**
     * 第三方点击检测链接	不能超过 1024 字符 ocpx_action_type 是 180 并且应用没有接入 sdk，监测链接必填； 计划 type 是 2（推广应用安装），ocpx_action_type 是注册（396）、付费（190）、完件（384）、授信（383），并且没有接入 sdk，监测链接必填
     */
    @JSONField(name = "click_url")
    private String click_url;

    /**
     * 第三方 ActionBar 点击监控链接	部分客户使用 actionbar_click_url 不为空时，click_url 必填，不能超过 1024 字符
     */
    @JSONField(name = "actionbar_click_url")
    private String actionbar_click_url;

    /**
     * 可选	创意分类	由创意分类查询接口获得； 必须是叶子结点；与创意标签同时传或同时不传
     */
    @JSONField(name = "creative_category")
    private int creative_category;
    /**
     * 选创意分类 必填	创意标签	与创意分类参数，要么都传，要么都不传；且单个创意的创意标签最多 20 个；单个创意标签不能为空且不能超过 10 字符
     */
    @JSONField(name = "creative_tag")
    private List<String> creative_tag;
    /**
     * 可选（程序化创意 3.0 新增字段）	素材列表	新创建程序化创意请使用此参数，最多支持 10 组素材(传递后将忽略 horizontal_photo_ids,vertical_photo_ids,cover_image_tokens，7.15 日后老字段下线)
     */
    @JSONField(name = "photo_list")
    private List<PhotoList> photoList;

}
