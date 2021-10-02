package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 除非接口变得否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 17:22
 */
@Data
public class Target implements Serializable {

    /**
     * 地域
     * 传值为[]表示不限；传递上一级ID时，childrenID可以不传；不允许同时传parentID和childrenID；地域信息可通过地域接口获取；仅计划的campaign_type为5时，支持设置三级地域（例：山西-大同-左云，左云是三级地域）
     * region
     */
    @JSONField(name = "region")
    private List<Long> region;

    /**
     * 商圈定向
     * 与region字段不能同时传、白名单控制，最多选100个。可以通过/rest/openapi/v1/region/district/list接口获取商圈信息
     * merchandise_type
     */
    @JSONField(name = "district_ids")
    private List<Long> districtIds;

    /**
     * 用户类型
     * 0：实时；1：常驻；2：不限
     * user_type
     */
    @JSONField(name = "user_type")
    private Integer userType;

    /**
     * 自定义年龄段
     * 不传值表示不限，传值具体见下方表格；与ages_range不能同时传
     * age
     */
    @JSONField(name = "age")
    private Age age;

    /**
     * 固定年龄段
     * 与age不能同时传；【18：表示18-23岁】；【24：表示24-30岁】；【31：表示31-40岁】；【41：表示41-49岁】；【50：表示50-100岁】
     * ages_range
     */
    @JSONField(name = "ages_range")
    private List<Integer> ages_range;

    /**
     * 性别
     * 1：女性, 2：男性，0表示不限
     * gender
     */
    @JSONField(name = "gender")
    private Integer gender;

    /**
     * 操作系统
     * 1：Android，2：iOS，0表示不限；当计划类型为2（提升应用安装）时该字段可忽略
     * platform_os
     */
    @JSONField(name = "platform_os")
    private Integer platformOs;

    /**
     * Android版本
     * 3：不限，4：4.x+，5：5.x+，6：6.x+，7：7.x+，8：8.x+，9：9.x+，10：10.x+；当计划类型为2（提升应用安装）时，仅当app_id表示的是Android应用可使用此字段传递版本
     * android_osv
     */
    @JSONField(name = "android_osv")
    private Integer androidOsv;

    /**
     * iOS版本
     * 6：不限，7：7.x+，8：8.x+，9：9.x+，10：10.x+；11：11.x+；12：12.x+；13：13.x+；当计划类型为2（提升应用安装）时，仅当appId表示的是iOS应用可使用此字段传递版本
     * ios_osv
     */
    @JSONField(name = "ios_osv")
    private Integer ios_osv;

    /**
     * 网络环境
     * 1：Wi-Fi，2：移动网络，0：表示不限
     * network
     */
    @JSONField(name = "network")
    private Integer network;

    /**
     * 设备品牌
     * 传值为[]表示不限；当platform_os为2（iOS）时，没有设备品牌定向；1：OPPO，2：VIVO，3：华为，4：小米，5：荣耀，6：三星，7：金立，8：魅族，9：乐视10：其他，11：苹果（只有 platform_os为不限时支持此选项）
     * device_brand
     */
    @JSONField(name = "device_brand")
    private List<Integer> deviceBrand;

    /**
     * 设备价格
     * 传值为[]表示不限；1：1500元以下，2：1501~2000，3：2001~2500，4：2501~3000，5：3001~3500，6：3501~4000，7：4001~4500，8：4501~5000，9： 5001~5500，10：5500元以上
     * device_price
     */
    @JSONField(name = "device_price")
    private List<Integer> device_price;

    /**
     * 商业兴趣类型
     * 0：不限，1：智能推荐，2：按照兴趣标签；投放账户二级行业为空（个别情况）时不可使用智能推荐
     * business_interest_type
     */
    @JSONField(name = "business_interest_type")
    private Integer business_interest_type;

    /**
     * 商业兴趣
     * 当business_interest_type为2时必填，id不能重复且必须准确，具体id可通过下方标签接口获取
     * business_interest
     */
    @JSONField(name = "business_interest")
    private List<Long> business_interest;

    /**
     * 网红粉丝
     * id不能重复且必须准确，具体id可通过下方标签接口获取
     * fans_star
     */
    @JSONField(name = "fans_star")
    private List<Long> fans_star;

    /**
     * 兴趣视频用户
     * id不能重复且必须准确，具体id可通过下方标签接口获取
     * interest_video
     */
    @JSONField(name = "interest_video")
    private List<Long> interest_video;

    /**
     * APP行为-按分类
     * id不能重复且必须准确，具体id可通过下方标签接口获取；仅包含安卓数据，若操作系统定向IOS则无效；不能同时选择app_ids（旧标签体系字段，即将下线
     * app_interest
     */
    @JSONField(name = "app_interest")
    private Long app_interest;

    /**
     * APP行为-按分类
     * id不能重复且必须准确，具体id可通过下方标签接口获取；仅包含安卓数据，若操作系统定向IOS则无效；不能同时选择app_ids（新标签体系字段，替换app_interest，与app_interest同时传递，app_interest字段失效）
     * app_interest_ids
     */
    @JSONField(name = "app_interest_ids")
    private List<Long> app_interest_ids;

    /**
     * APP行为-按APP名称
     * id不能重复且必须准确，具体id可通过下方应用接口获取，建议不超过10个，否则可能出现报错；仅包含安卓数据，若操作系统定向IOS则无效；不能同时选择app_interest
     * app_ids
     */
    @JSONField(name = "app_ids")
    private List<Long> app_ids;

    /**
     * 过滤已转化人群纬度
     * 联盟广告、小店通不支持。优化目标不支持【封面曝光数】和【封面点击数】非应用下载类推广不支持过滤【APP】纬度。0(默认)：不限；1：广告组2：广告计划；3：本账户；4：公司主体；5：APP
     * filter_converted_level
     */
    @JSONField(name = "filter_converted_level")
    private Integer filterConvertedLevel;

    /**
     * 人群包定向
     * /rest/openapi/v1/dmp/population/list获取人群包id。population不能重复，与exclude_population不能有交集，不能传付费人群包，付费人群包标识字段：population_type=7
     * population
     */
    @JSONField(name = "population")
    private List<Long> population;

    /**
     * 人群包排除
     * /rest/openapi/v1/dmp/population/list获取人群包id。exclude_population不能重复，不能传付费人群包，付费人群包标识字段：population_type=7
     * exclude_population
     */
    @JSONField(name = "exclude_population")
    private List<Long> exclude_population;

    /**
     * 付费人群包id
     * /rest/openapi/v1/dmp/population/list获取人群包id。1、不能重复，只能传付费人群包，且third_platform_code要一样，2、如果传了【population或exclude_population】，报错；修改时要也要保证【population或exclude_population】不能同时传。3、创建时若已经有了【population或exclude_population】修改时只传paid_audience也会报错，这时需要把population或exclude_population】设为[]才行，反之也一样。总之，【population或exclude_population】与【paid_audience】不能同时存在
     * paid_audience
     */
    @JSONField(name = "paid_audience")
    private List<Long> paid_audience;

    /**
     * 是否投放开屏广告位
     * true:投放，false：不投放
     * splash_ad_switch
     */
    @JSONField(name = "splash_ad_switch")
    private Integer splash_ad_switch;

    /**
     * 智能扩量
     * 不传值表示不使用，传值具体见下方表格
     * intelli_extend
     */
    @JSONField(name = "intelli_extend")
    private IntelliExtend intelliExtend;

    /**
     * 行为兴趣定向
     * behavior.keyword 、behavior.label、interest.lable 其中一个必传，具体传值下方表格
     * behavior_interest
     */
    @JSONField(name = "behavior_interest")
    private BehaviorInterest behavior_interest;
}
