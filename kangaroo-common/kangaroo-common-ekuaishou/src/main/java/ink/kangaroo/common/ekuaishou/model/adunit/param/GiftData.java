package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 16:44
 */
@Data
public class GiftData {
    /**
     * 发送时机
     * android（支持的类型）:如果选择「优先从系统应用商店下载」（use_app_market 为 1）：2：检测到用户行为（actionBar 点击）没有选择优先应用商店下载：30： 开始下载后；31：下载完成后；32：安装完成后ios（ios支持的类型）：2：检测到用户行为（actionBar 点击）
     * target_action_type
     */
    @JsonAlias("target_action_type")
    private Integer target_action_type;
    /**
     * 礼包码
     * 最多20字符，只能字母+数字，一个中文汉字算两个字符
     * code
     */
    @JsonAlias("code")
    private String code;
}
