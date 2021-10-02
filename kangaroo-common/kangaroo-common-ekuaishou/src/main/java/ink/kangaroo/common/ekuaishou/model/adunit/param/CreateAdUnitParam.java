package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 除非API变动，否则请勿修改。
 * <p>
 * 创建广告组参数
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 16:21
 */
@Data
public class CreateAdUnitParam {

    /**
     * 广告主ID*
     * 在获取access_token的时候返回
     * advertiser_id
     */
    @NotNull
    @JSONField(name = "advertiser_id")
    private Long advertiserId;
    /**
     * 广告计划ID*
     * 广告计划ID
     * campaign_id
     */
    @NotNull
    @JSONField(name = "campaign_id")
    private Long campaignId;
    /**
     * 广告组名称*
     * 长度为1-100个字符，同一个计划下的广告组名称不能重复
     * unit_name
     */
    @NotNull
    @JSONField(name = "unit_name")
    private String unitName;
    /**
     * 广告组的投放状态
     * 不填时，创建的广告组为投放状态；填2时，创建的广告组为暂停状态，其他值非法
     * put_status
     */
    @JSONField(name = "put_status")
    private Integer putStatus;
    /**
     * 定向模板id
     * <p>
     * template_id
     */
    @JSONField(name = "template_id")
    private Long templateId;
    /**
     * 优化目标出价类型
     * 1: CPM 2: CPC 10:OCPM 4: CPA只有计划类型为3，url_type=4才支持，而且ocpx_action_type只能为表单提交53)
     * bid_type
     */
    @NotNull
    @JSONField(name = "bid_type")
    private Integer bidType;
    /**
     * 广告主ID
     * 在获取access_token的时候返回
     * use_app_market
     */
    @JSONField(name = "use_app_market")
    private Integer useAppMarket;
    /**
     * 应用商店列表
     * 华为：huawei, OPPO：oppo VIVO：vivo 小米：xiaomi；魅族：meizu；锤子：smartisan 只支持计划类型为 2 且应用是 Android 类型的推广
     * app_store
     */
    @JSONField(name = "app_store")
    private List<Long> appStore;
    /**
     * 出价
     * bid_type为CPC/eCPC/CPM时该字段必填，单位：厘，不得低于0.2元，不得高于100元，不得高于组预算
     * bid
     */
    @JSONField(name = "bid")
    private Long bid;
    /**
     * 出价
     * bid_type是OCPM时该字段必填，单位：厘，ocpx_action_type为2时，不得低于1元，不得高于3000元；ocpx_action_type为180时，不得低于1元，不得高于3000元，ocpx_action_type为53时，不得低于5元，不得高于3000元；不得高于组预算
     * cpa_bid
     */
    @JSONField(name = "cpa_bid")
    private Long cpaBid;
    /**
     * 优先低成本出价(设置条件)
     * 当 speed 等于 3 时可用 ；0：手动出价，1：自动出价；1、当 smart_bid 为 1 时，cpa_bid 或 deep_conversion_bid 都不能设置值；2、快享和联盟不支持优先低成本，直接报错；3、优先低成本的 bid_type 只支持OCPM；4、优先低成本 必须设置统一预算或分日预算
     * smart_bid
     */
    @JSONField(name = "smart_bid")
    private Integer smartBid;
    /**
     * 优化目标
     * bid_type是OCPM时该字段必填；2：行为数（actionBar点击）；180：激活数；53：表单数；109：电话卡激活；137：量房；190： 付费；191：首日ROI；324：唤起应用（campaign_type=7+主站+加白）；348：有效线索；383: 授信；384: 完件394：订单提交；（计划类型获取电商下单，非金牛）；396：注册（联盟暂不支持）；715：微信复制优化目标；（campaign_type=5&bid_type=10）；716：多转化事件(campaign_type=5&bid_type=10&使用魔力建站&线索转化类组件>=2种)；717：广告观看次数(campaign_type=2&bid_type=10&appi与转化追踪工具联调过或者该广告主的在相应事件的回传数>=1）需通过接口「rest/openapi/v1/ad_unit/ocpc/conversion_infos」获取是否可以选择「激活」、「表单转化」、「付费」、「授信」、「完件」的优化目标;731：广告观看5次；732：广告观看10次；733：广告观看20次；773：关键行为；774：7日ROI；72：小店通商品和主页推广，转化目标“涨粉”；739:7日付费次数;392：小店通商品推广，优化目标“转化数”，转化目标“商品访问”；395：小店通商品和主页推广，转化目标“订单支付”，62：直播观看数,（小店直播推广&商品推广支持，计划type=14、13）;192：直播推广ROI；小店直播推广&商品推广支持，计划type=14、13）;403：近似购买（小店直播推广&商品推广支持，计划type=14、13）。需通过接口「获取可选的深度转化类型」，接口is_order_paied字段返回值为1才可以使用;
     * advertiser_id
     */
    @JSONField(name = "ocpx_action_type")
    private Long ocpxActionType;
    /**
     * 深度转化目标
     * 3:付费，7:次日留存，10:完件, 11:授信；13:添加购物车；14:提交订单；15:购买；44：有效线索；92：付费roi。可通过下方深度转化信息查询接口获取当前账户可选的深度转化目标
     * deep_conversion_type
     */
    @JSONField(name = "deep_conversion_type")
    private Integer deepConversionType;
    /**
     * 付费ROI系数
     * 优化目标为「首日ROI」时必填：ROI系数取值范围 ( 0,100 ] 最多支持到三位小数（如：0.066）
     * roi_ratio
     */
    @JSONField(name = "roi_ratio")
    private Double roiRatio;
    /**
     * 深度转化目标出价
     * 单位：厘，仅当deep_conversion_type可用且账户满足白名单时选填，出价需大于cpa_bid，小于min{组预算，3000元}，不得高于组预算，不支持从0改为其他值，也不支持从其他值改为0
     * deep_conversion_bid
     */
    @JSONField(name = "deep_conversion_bid")
    private Long deepConversionBid;
    /**
     * 资源位置
     * 1：优选广告位（当账户在联盟优选白名单中且campaign_type=2/3/5/7时为主站&联盟优选广告位 ）；3：视频播放页广告-便利贴广告（不支持深度转化目标的优化）；5：联盟广告；6：上下滑大屏广告；7：信息流广告；10：联盟场景 3、6、7、10可多选;当账户在联盟优选白名单中且campaign_type = 2/3/5/7，可传10；;当账户不在联盟优选白名单中或campaign_type不等于2/3/5/7时，不可传10；27：开屏广告位（白名单功能且splash_ad_switch为true是可选）
     * scene_id
     */
    @NotNull
    @JSONField(name = "scene_id")
    private List<Integer> sceneId;
    /**
     * 创意制作方式
     * 4: 自定义；5：程序化创意 7：程序化创意2.0
     * unit_type
     */
    @NotNull
    @JSONField(name = "unit_type")
    private Integer unitType;
    /**
     * 投放开始时间
     * 格式为yyyy-MM-dd，需大于等于当前时间
     * begin_time
     */
    @NotNull
    @JSONField(name = "begin_time")
    private String beginTime;
    /**
     * 投放结束时间
     * 格式为yyyy-MM-dd，不传值表示从今天开始长期投放，传值表示设置开始时间和结束时间，且投放结束时间需大于等于投放开始时间，开屏如果选择按开始时间和结束时间投放，时间(end_time - begin_time)必须超过三天
     * end_time
     */
    @JSONField(name = "end_time")
    private String endTime;
    /**
     * 投放时间段
     * 格式为24*7位字符串，且都为0和1，以一个小时为最小粒度，从周一零点开始至周日24点结束；0为不投放，1为投放，不传/全传1/全传0视为全时段投放
     * schedule_time
     */
    @JSONField(name = "schedule_time")
    private String scheduleTime;
    /**
     * 单日预算
     * 单位：厘，指定0表示预算不限，默认为0；每天不小于100元，不超过100000000元，仅支持输入数字；修改预算不得低于该广告组当日花费的120%，与day_budget_schedule不能同时传，均不能低于组出价
     * day_budget
     */
    @JSONField(name = "day_budget")
    private Long dayBudget;
    /**
     * 分日预算
     * 单位：厘，指定0表示预算不限，默认为0；每天不小于500元，不超过100000000元，仅支持输入数字；修改预算不得低于该计划当日花费的120%，与day_budget不能同时传，均不能低于该计划下任一广告组出价
     * advertiser_id
     */
    @NotNull
    @JSONField(name = "day_budget_schedule")
    private List<Long> day_budget_schedule;
    /**
     * 转化目标ID
     * 可通过下方【转化目标查询接口】获得，不同计划类型需要对应各自的转化目标类型：提升应用安装(campaign_type=2) - 安卓：convert_type:3、7 / IOS：convert_type:7；推广品牌活动(campaign_type=4) / 收集销售线索(campaign_type=5)：convert_type:1、2
     * convert_id
     */
    @JSONField(name = "convert_id")
    private Integer convertId;
    /**
     * url类型
     * 当计划类型为：3（获取电商下单）时必填：1 - 淘宝商品短链；2 - 淘宝商品itemID；4 - 金牛电商
     * url_type
     */
    @JSONField(name = "url_type")
    private Integer urlType;
    /**
     * url类型
     * 当计划类型为5（收集销售线索）&使用建站时必填：需使用魔力建站；不传默认1，2：落地页
     * web_uri_type
     */
    @JSONField(name = "web_uri_type")
    private Integer webUriType;
    /**
     * 投放链接
     * 当计划类型是3/4/5时必填；长度不超过1000字符；计划类型是3（获取电商下单）：金牛商品ID（必须为数字）；计划类型是4（推广品牌活动）：落地页URL；计划类型是5（收集销售线索）：落地页URL；计划类型是5（收集销售线索）：建站ID，通过/rest/openapi/v2/lp/page/list获取。「房地产」「家装建材」「招商加盟」三个二级行业【收集销售线索】目标下隐藏客户自有链接填写入口。
     * url
     */
    @JSONField(name = "url")
    private String url;
    /**
     * 调起链接
     * 提升应用活跃营销目标的调起链接；应用推广下在已经安装app的用户手机上可以拉起app（需要运营加白），开屏广告如果营销目标是应用活跃，调起链接必须在品牌开屏白名单中
     * schema_uri
     */
    @JSONField(name = "schema_uri")
    private String schemaUri;
    /**
     * 应用ID
     * 当计划类型为2时必填，可通过应用列表接口获取应用ID；为9时且detail_unit_type为0、2时必填
     * app_id
     */
    @JSONField(name = "app_id")
    private Long appId;
    /**
     * 创意展现方式
     * 1 - 轮播；2 - 优选
     * show_mode
     */
    @NotNull
    @JSONField(name = "show_mode")
    private Integer showMode;
    /**
     * 投放方式
     * 1 - 加速投放；2 - 平滑投放；3-优先低成本（投放时间范围只可为全天；预算不可为不限或空）
     * speed
     */
    @NotNull
    @JSONField(name = "speed")
    private Integer speed;
    /**
     * 预约广告
     * 1:IOS预约缺省为不传或传0
     * site_type
     * 加白
     */
    @JSONField(name = "site_type")
    private Long siteType;
    /**
     * 游戏礼包码
     * "gift_data": {}，仅支持计划类型为 2
     * gift_data
     * 加白
     */
    @JSONField(name = "gift_data")
    private GiftData giftData;
    /**
     * 是否使用落地页前置功能
     * true: 使用 false：不使用，不填使用系统默认值（只支持双feed流，推广品牌活动-落地页url填写、获取电商下单-淘客短链url填写、获取电商下单-金牛活动页、获取销售线、获取销售线索）
     * video_landing_page
     */
    @JSONField(name = "video_landing_page")
    private Boolean videoLandingPage;
    /**
     * 智能定向
     * 若开启智能定向，定向部分仅保留地域+年龄+性别+排除人群+系统纬度的定向，其他定向纬度暂不支持（报错处理）true表示开启，false表示未开启
     * auto_target
     */
    @JSONField(name = "auto_target")
    private Boolean autoTarget;
    /**
     * 是否开启自动生成视频
     * 加白使用
     * auto_create_photo
     */
    @JSONField(name = "auto_create_photo")
    private Boolean autoCreatePhoto;
    /**
     * 电商关联Id (小店通) 电商关联Id (小店通商品推广，计划type=13)
     * 1. merchantItemType为0时填写小店商品id；2. merchantItemType为2时不用填写，系统补充
     * item_id
     */
    @JSONField(name = "item_id")
    private Long itemId;
    /**
     * 电商广告投放类型（小店通）   电商广告投放类型（小店商品推广，计划type=13）
     * 0: 商品 2: 个人主页
     * merchant_item_put_type
     */
    @JSONField(name = "merchant_item_put_type")
    private Integer merchantItemPutType;
    /**
     * 小说ID
     * 仅支持“提升应用安装”、“收集销售线索”以及“提高应用活跃”三种计划类型，且一旦绑定，不可修改。此参数仅是绑定小说，并非自动关联小说生成的落地页，如需推广小说生成的落地页，请使用小说ID获取其生成的建站落地页后将落地页ID一并传入即可（落地页ID传参与之前建站落地页ID字段一致）
     * fiction_id
     */
    @JSONField(name = "fiction_id")
    private Long fictionId;
    /**
     * 程序化创意2.0智能抽帧
     * 是否开启智能抽帧
     * smart_cover
     * unit_type=7选填
     */
    @JSONField(name = "smart_cover")
    private Boolean smartCover;
    /**
     * 程序化创意2.0素材挖掘
     * 是否开启历史素材挖掘
     * asset_mining
     * unit_type=7选填
     */
    @JSONField(name = "asset_mining")
    private Boolean assetMining;
    /**
     * 咨询组件id
     * 1、仅可被用于线索类计划下的unit；2、仅当落地页使用了建站落地页时可使用；3、注意本字段不可被更新；4、本属性不可与附加表单组件(component_id)同时使用
     * consult_id
     * 选填:从工具-获取可选咨询组件
     */
    @JSONField(name = "consult_id")
    private Long consultId;
    /**
     * 高级创意开关
     * 0：关闭 1:开启
     * adv_card_option
     */
    @JSONField(name = "adv_card_option")
    private Integer advCardOption;
    /**
     * 绑定卡片id
     * card_type=100为1个；card_type=101为3个；card_type=102为3个；card_type=103为1个
     * adv_card_list
     */
    @JSONField(name = "adv_card_list")
    private List<Long> advCardList;
    /**
     * 卡片类型
     * 100:图片卡片 101:多利益卡-图文 102：多利益卡-多标签 103：电商促销样式 104：快捷评论卡
     * card_type
     */
    @JSONField(name = "card_type")
    private Long cardType;
    /**
     * 商品ID，且一旦绑定，不可修改
     * 此参数用于绑定商品（绑定商品类型受merchandise_type字段控制），与 fiction_id 字段互斥。merchandise_type=2，merchandise_id 为课程ID，仅支持“收集销售线索”计划类型，且一旦绑定不可修改
     * merchandise_id
     */
    @JSONField(name = "merchandise_id")
    private Long merchandiseId;
    /**
     * 选填
     * 与 merchandise_id 共同使用，merchandise_type=2，merchandise_id 为课程ID，仅支持“收集销售线索”计划类型，且一旦绑定不可修改
     * merchandise_type
     */
    @JSONField(name = "merchandise_type")
    private Integer merchandiseType;
    /**
     * 行为意向-系统优选
     * 行为意向是否开启系统优选，智能定向和行为意向系统优选不能同时开启
     * intention_target
     */
    @JSONField(name = "intention_target")
    private Long intentionTarget;
    /**
     * 试玩 ID
     * 可选字段，开启试玩时存在，否则不存在。当用户上传试玩素材成功时返回，用于之后对于广告组中试玩创意的编辑操作。
     * playable_id
     */
    @JSONField(name = "playable_id")
    private Long playableId;
    /**
     * 试玩按钮文字内容
     * 开启试玩时存在，否则不存在，示例按钮内容如下：1：立即试玩；2：试玩一下；3：立即体验；4：免装试玩；5：免装体验。启用试玩时：默认“立即试玩”，未启用试玩时：内容为空。
     * play_button
     */
    @JSONField(name = "play_button")
    private String playButton;
    /**
     * DPA相关商品信息
     * 当子计划类型为sdpa时必填
     * dpa_unit_param
     */
    @JSONField(name = "dpa_unit_param")
    private DpaUnitParam dpaUnitParam;
    /**
     * 定向数据
     * 具体见下方表格
     * target
     */
    @NotNull
    @JSONField(name = "target")
    private Target target;
}
