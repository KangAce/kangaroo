package ink.kangaroo.mail.api.model.param;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 14:18
 */
@Data
public class MailParam {

    @NotBlank(message = "收件人不能为空")
    @Email(message = "邮箱格式错误")
    private String to;

    @NotBlank(message = "主题不能为空")
    private String subject;

    @NotBlank(message = "内容不能为空")
    private String content;
}
