package ink.kangaroo.gateway.security.handler;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.gateway.security.SliderVerificationCodeType;
import ink.kangaroo.gateway.security.domain.SliderVerificationVo;
import ink.kangaroo.gateway.security.service.ValidateService;
import lombok.NonNull;
import reactor.core.publisher.Mono;

/**
 * 验证码获取
 *
 * @author Kangaroo
 */
@Component
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {
    // @Autowired
    // private ValidateCodeService validateCodeService;
    @Autowired
    private ValidateService validateService;

    @Override
    public @NonNull Mono<ServerResponse> handle(ServerRequest serverRequest) {
        AjaxResult ajax = AjaxResult.fail("");
        MultiValueMap<String, String> queryParams = serverRequest.queryParams();
        Integer type = Integer.valueOf(Objects.requireNonNull(queryParams.getFirst("type")));
        R<SliderVerificationVo> sliderVerificationCode = validateService
                .getSliderVerificationCode(SliderVerificationCodeType.getByValue(type));
        if (sliderVerificationCode.getCode() == R.SUCCESS) {
            ajax = AjaxResult.createAjaxResult().setData(sliderVerificationCode.getData());
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(ajax));
    }
}