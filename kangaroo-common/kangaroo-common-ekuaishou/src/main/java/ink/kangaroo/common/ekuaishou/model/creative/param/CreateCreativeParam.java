package ink.kangaroo.common.ekuaishou.model.creative.param;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 18:08
 */
@Data
public class CreateCreativeParam implements Serializable {

    /**
     * 广告主ID
     * 在获取access_token的时候返回
     * <p>
     * advertiser_id
     */
    @NotNull
    @JsonAlias("advertiser_id")
    private Long advertiser_id;

    /**
     * 广告组ID
     * 在获取access_token的时候返回
     * <p>
     * unit_id
     */
    @NotNull
    @JsonAlias("unit_id")
    private Long unit_id;

    /**
     * 创意名称
     * 长度为1-100字符，同一个广告组下名称不能重复
     * <p>
     * creative_name
     */
    @NotNull
    @JsonAlias("creative_name")
    private String creative_name;

    /**
     * 广告组的投放状态
     * 不填时，创建的广告组为投放状态；填2时，创建的广告组为暂停状态，其他值非法
     * <p>
     * put_status
     */
    @JsonAlias("put_status")
    private Integer put_status;

    /**
     * 视频ID
     * 不填时，创建的广告组为投放状态；填2时，创建的广告组为暂停状态，其他值非法
     * <p>
     * photo_id
     */
    @JsonAlias("photo_id")
    private String photoId;

    /**
     * 封面图片token
     * 通过上传图片接口获得，不传值则直接使用视频的首帧作为封面图片，自定义封面的图片宽高要与视频宽高一致
     * <p>
     * image_token
     */
    @JsonAlias("image_token")
    private String image_token;

    /**
     * 素材类型
     * 1：竖版视频 2：横版视频 4：便利贴单图图片创意 5： 竖版图片（优选/联盟）6：横版图片(优选/联盟/信息流/快看点) 9：小图(优选/信息流/快看点) 10：组图(优选/信息流/快看点)
     * <p>
     * creative_material_type
     */
    @NotNull
    @JsonAlias("creative_material_type")
    private Integer creativeMaterialType;

    /**
     * 便利贴单图图片创意token
     * 不填时，创建的广告组为投放状态；填2时，创建的广告组为暂停状态，其他值非法
     * <p>
     * image_tokens
     */
    @JsonAlias("image_tokens")
    private List<Integer> image_tokens;

    /**
     * 行动号召按钮文案
     * 根据计划类型进行设置，详情见附录
     * <p>
     * action_bar_text
     */
    @NotNull
    @JsonAlias("action_bar_text")
    private String action_bar_text;

    /**
     * 广告语
     * 长度为1-30字符，不支持换行。 如果要使用动态词包，格式如"[地区]的[男性女性]都喜欢"， 联盟广告和程序化创意不支持动态词包， 词包名可以通过下方动态词包接口获取
     * <p>
     * description
     */
    @JsonAlias("description")
    private String description;

    /**
     * 便利贴创意短广告语
     * 长度为1-8字符，便利贴创意必填参数
     * <p>
     * short_slogan
     */
    @JsonAlias("short_slogan")
    private String shortSlogan;

    /**
     * 封面广告语标题
     * 不超过14字符， 如果要使用动态词包，格式如"[地区]的[男性女性]都喜欢"， 后贴片广告不支持动态词包， 词包名可以通过下方动态词包接口获取， sticker_title和overlay_type需同时为空值或同时非空
     * <p>
     * sticker_title
     */
    @JsonAlias("sticker_title")
    private String sticker_title;

    /**
     * 贴纸样式类型
     * 贴纸样式可以通过查询下方贴纸掩饰接口获取， sticker_title和overlay_type需同时为空值或同时非空
     * <p>
     * overlay_type
     */
    @JsonAlias("overlay_type")
    private String overlay_type;

    /**
     * 广告标签
     * 从 /expose_tags/list 接口获取，从下方9获取 更新的时候如果要将广告标签设置为 「暂不选择」，传空字符串即可，只支持计划类型为 2
     * <p>
     * expose_tag
     */
    @JsonAlias("expose_tag")
    private String expose_tag;

    /**
     * 广告标签2期
     * 与老的expose_tag兼容, 逐渐将老的expose_tag替换掉。可以按照相关格式传递两个推荐理由 举例{“text”:""},{"text":""}
     * <p>
     * new_expose_tag
     */
    @JsonAlias("new_expose_tag")
    private List<String> new_expose_tag;

    /**
     * 安卓下载中间页ID
     * 可从下方「获取已发布的下载页」接口获取（老建站6.30下线） 或通过「rest/openapi/v2/lp/page/list」获取新建站落地页（魔力建站"view_comps":7） 1.仅支持下载类广告 2.广告组选择的应用类型要为安卓 3.下载页对应的app_id要与广告组选择的app_id一致
     * <p>
     * site_id
     */
    @JsonAlias("site_id")
    private Long site_id;


    /**
     * 第三方点击检测链接
     * 仅当广告组scene_id为1、2、6、7、10时，可选填； 广告组优化目标为激活时，该字段必填（下载类广告投放的应用集成快手Android SDK时除外） 使用Marketing API创建时，监测链接使用以该文档为准
     * <p>
     * click_track_url
     */
    @JsonAlias("click_track_url")
    private String click_track_url;

    /**
     * 第三方开始播放监测链接
     * 仅当广告组scene_id为1、2、6、7、10时，可选填； 广告组优化目标为激活时，该字段必填（下载类广告投放的应用集成快手Android SDK时除外） 使用Marketing API创建时，监测链接使用以该文档为准
     * <p>
     * click_track_url
     */
    @JsonAlias("impression_url")
    private String impression_url;
    /**
     * 第三方有效播放监测链接
     * 仅历史个别账户使用且当广告组scene_id为3时可选，与impression_url不可同时使用
     * <p>
     * ad_photo_played_t3s_url
     */
    @JsonAlias("ad_photo_played_t3s_url")
    private String ad_photo_played_t3s_url;
    /**
     * 第三方点击按钮监测链接
     * 1.校验click_url必填的广告场景 优选(1)/信息流(2、7)/上下滑（6） 2.优化目标为激活功能必填点击监测链接,但如果安卓应用接入了快手监测sdk就不需要填写监测链接了 3.联盟场景检查click_url不能为空 4.若广告联盟的转化目标为激活，click_url、actionbar_click_url和监测SDK至少三选一
     * <p>
     * actionbar_click_url
     */
    @JsonAlias("actionbar_click_url")
    private String actionbar_click_url;

    /**
     * 创意分类
     * 由创意分类查询接口 获得；必须是叶子结点；与创意标签同时传或同时不传 必填账户可通过接口3.17——接口获取是否必填
     * <p>
     * 金融，教育，游戏，小说 如上行业必填
     * creative_category
     */
    @JsonAlias("creative_category")
    private Integer creative_category;

    /**
     * 创意标签
     * 与创意分类参数，要么都传，要么都不传；且单个创意的创意标签最多10个；单个创意标签不能为空且不能超过10字符
     * <p>
     * 选创意分类 必填
     * creative_tag
     */
    @JsonAlias("creative_tag")
    private List<String> creative_tag;

    /**
     * 直播类型（小店直播推广类型，计划type=14）
     * 1 - 直投直播；2 - 视频引流直播
     * <p>
     * live_creative_type
     */
    @JsonAlias("live_creative_type")
    private Integer live_creative_type;

}
