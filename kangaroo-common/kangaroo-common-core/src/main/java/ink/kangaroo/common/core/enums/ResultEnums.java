package ink.kangaroo.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 19:39
 */
@Getter
@AllArgsConstructor
public enum ResultEnums {
    OK("00000", "一切 ok"),
    /**
     * 用户端错误
     */
    CLIENT_ERROR("A0001", "用户端错误"),
    /**
     * 用户注册错误
     */
    USER_REGISTRATION_ERROR("A0100", "用户注册错误"),
    /**
     * 用户未同意隐私协议
     */
    THE_USER_DID_NOT_AGREE_TO_THE_PRIVACY_AGREEMENT("A0101", "用户未同意隐私协议"),
    /**
     * 注册国家或地区受限
     */
    RESTRICTED_COUNTRY_OR_REGION_OF_REGISTRATION("A0102", "注册国家或地区受限"),
    /**
     * 用户名校验失败
     */
    USERNAME_VERIFICATION_FAILED("A0110", "用户名校验失败"),
    /**
     * 用户名已存在
     */
    USERNAME_ALREADY_EXISTS("A0111", "用户名已存在"),
    /**
     * 用户名包含敏感词
     */
    USERNAME_CONTAINS_SENSITIVE_WORDS("A0112", "用户名包含敏感词"),
    /**
     * 用户名包含特殊字符
     */
    USERNAME_CONTAINS_SPECIAL_CHARACTERS("A0113", "用户名包含特殊字符"),
    /**
     * 密码校验失败
     */
    PASSWORD_VERIFICATION_FAILED("A0120", "密码校验失败"),
    /**
     * 密码长度不够
     */
    PASSWORD_LENGTH_IS_NOT_ENOUGH("A0121", "密码长度不够"),
    /**
     * 密码强度不够
     */
    THE_PASSWORD_IS_NOT_STRONG_ENOUGH("A0110", "密码强度不够"),
    /**
     * 校验码输入错误
     */
    CHECK_CODE_INPUT_ERROR("A0130", "校验码输入错误"),
    /**
     * 短信校验码输入错误
     */
    SMS_VERIFICATION_CODE_INPUT_ERROR("A0110", "短信校验码输入错误"),
    /**
     * 邮件校验码输入错误
     */
    A0132("A0132", "邮件校验码输入错误"),
    /**
     * 用户名校验失败
     */
    A0133("A0133", "语音校验码输入错误"),
    /**
     * 用户证件异常
     */
    A0140("A0140", "用户证件异常"),
    /**
     * 用户证件类型未选择
     */
    A0141("A0141", "用户证件类型未选择"),
    /**
     * 大陆身份证编号校验非法
     */
    A0142("A0142", "大陆身份证编号校验非法"),

    /**
     * 用户登录异常
     */
    A0200("A0200", "用户登录异常") ,
    /**
     * 用户账户不存在
     */
    ACCOUNT_NOT_EXIST("A0201", "用户账户不存在"),
    A0202("A0202", "用户账户被冻结"),
    ACCOUNT_DISABLE("A0203", "用户账户已作废"),
    /**
     * 用户密码错误
     */
    LOGIN_PASSWORD_ERROR("A0210", "用户密码错误"),
    A0211("A0211", "用户输入密码错误次数超限"),
    A0220("A0220", "用户身份校验失败"),
    A0221("A0221", "用户指纹识别失败"),
    A0222("A0222", "用户面容识别失败"),
    A0223("A0223", "用户未获得第三方登录授权"),
    /**
     * 用户登录已过期 帐户凭据已过期
     */
    ACCOUNT_CREDENTIAL_EXPIRED("A0230", "用户登录已过期"),
    A0240("A0240", "用户验证码错误"),
    A0241("A0241", "用户验证码尝试次数超限"),
    A0300("A0300", "访问权限异常"),
    USER_UNAUTHORIZED("A0301", "访问未授权"),
    A0302("A0302", "正在授权中"),
    A0303("A0303", "用户授权申请被拒绝"),
    A0310("A0310", "因访问对象隐私设置被拦截"),
    /**
     * 授权已过期
     */
    ACCOUNT_EXPIRED("A0311", "授权已过期"),
    PERMISSION_DENIED("A0312", "无权限使用 API"),
    A0320("A0320", "用户访问被拦截"),
    A0321("A0321", "黑名单用户"),
    /**
     * 账号被冻结
     */
    ACCOUNT_LOCKED("A0322", "账号被冻结"),
    A0323("A0323", "非法 IP 地址"),
    A0324("A0324", "网关访问受限"),
    A0325("A0325", "地域黑名单"),
    A0330("A0330", "服务已欠费"),
    A0340("A0340", "用户签名异常"),
    A0341("A0341", "RSA 签名错误"),
    A0400("A0400", "用户请求参数错误"),
    A0401("A0401", "包含非法恶意跳转链接"),
    A0402("A0402", "无效的用户输入"),
    A0410("A0410", "请求必填参数为空"),
    A0411("A0411", "用户订单号为空"),
    A0412("A0412", "订购数量为空"),
    A0413("A0413", "缺少时间戳参数"),
    A0414("A0414", "非法的时间戳参数"),
    A0420("A0420", "请求参数值超出允许的范围"),
    A0421("A0421", "参数格式不匹配"),
    A0422("A0422", "地址不在服务范围"),
    A0423("A0423", "时间不在服务范围"),
    A0424("A0424", "金额超出限制"),
    A0425("A0425", "数量超出限制"),
    A0426("A0426", "请求批量处理总个数超出限制"),
    A0427("A0427", "请求 JSON 解析失败"),
    A0430("A0430", "用户输入内容非法"),
    A0431("A0431", "包含违禁敏感词"),
    A0432("A0432", "图片包含违禁信息"),
    A0433("A0433", "文件侵犯版权"),
    A0440("A0440", "用户操作异常"),
    A0441("A0441", "用户支付超时"),
    A0442("A0442", "确认订单超时"),
    A0443("A0443", "订单已关闭"),
    A0500("A0500", "用户请求服务异常"),
    A0501("A0501", "请求次数超出限制"),
    A0502("A0502", "请求并发数超出限制"),
    A0503("A0503", "用户操作请等待"),
    A0504("A0504", "WebSocket 连接异常"),
    A0505("A0505", "WebSocket 连接断开"),
    A0506("A0506", "用户重复请求"),
    A0600("A0600", "用户资源异常"),
    A0601("A0601", "账户余额不足"),
    A0602("A0602", "用户磁盘空间不足"),
    A0603("A0603", "用户内存空间不足"),
    A0604("A0604", "用户 OSS 容量不足"),
    A0605("A0605", "用户配额已用光 蚂蚁森林浇水数或每天抽奖数"),
    A0700("A0700", "用户上传文件异常"),
    A0701("A0701", "用户上传文件类型不匹配"),
    A0702("A0702", "用户上传文件太大"),
    A0703("A0703", "用户上传图片太大"),
    A0704("A0704", "用户上传视频太大"),
    A0705("A0705", "用户上传压缩文件太大"),
    A0800("A0800", "用户当前版本异常"),
    A0801("A0801", "用户安装版本与系统不匹配"),
    A0802("A0802", "用户安装版本过低"),
    A0803("A0803", "用户安装版本过高"),
    A0804("A0804", "用户安装版本已过期"),
    A0805("A0805", "用户 API 请求版本不匹配"),
    A0806("A0806", "用户 API 请求版本过高"),
    A0807("A0807", "用户 API 请求版本过低"),
    A0900("A0900", "用户隐私未授权"),
    A0901("A0901", "用户隐私未签署"),
    A0902("A0902", "用户摄像头未授权"),
    A0903("A0903", "用户相机未授权"),
    A0904("A0904", "用户图片库未授权"),
    A0905("A0905", "用户文件未授权"),
    A0906("A0906", "用户位置信息未授权"),
    A0907("A0907", "用户通讯录未授权"),
    A1000("A1000", "用户设备异常"),
    A1001("A1001", "用户相机异常"),
    A1002("A1002", "用户麦克风异常"),
    A1003("A1003", "用户听筒异常"),
    ;

    private String code;
    private String message;
}
