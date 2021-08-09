package ink.kangaroo.mail.controller;

import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.mail.model.param.MailParam;
import ink.kangaroo.mail.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Mail controller.
 *
 * @author johnniang
 * @date 2019-05-07
 */
@RestController
@RequestMapping("/api/admin/mails")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("test")
    @Operation(summary = "Tests the SMTP service")
    public AjaxResult testMail(@Valid @RequestBody MailParam mailParam) {
        mailService.sendTextMail(mailParam.getTo(), mailParam.getSubject(), mailParam.getContent());
        return AjaxResult.createAjaxResult().success("已发送，请查收。若确认没有收到邮件，请检查服务器日志");
    }

    @PostMapping("test/connection")
    @Operation(summary = "Test connection with email server")
//    @DisableOnCondition
    public AjaxResult testConnection() {
        mailService.testConnection();
        return AjaxResult.createAjaxResult().success("您和邮箱服务器的连接通畅");
    }

}
