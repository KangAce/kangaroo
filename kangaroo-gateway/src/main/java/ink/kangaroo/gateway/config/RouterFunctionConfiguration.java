//package ink.kangaroo.gateway.config;
//
//import ink.kangaroo.gateway.security.handler.ValidateCodeHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.server.RequestPredicates;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//
///**
// * 路由配置信息
// *
// * @author kbw
// * @version 1.0
// * @date 2021/8/9 16:59
// */
//@Configuration
//public class RouterFunctionConfiguration {
//    @Autowired
//    private ValidateCodeHandler validateCodeHandler;
//
//    @SuppressWarnings("rawtypes")
//    @Bean
//    public RouterFunction routerFunction() {
//        return RouterFunctions.route(
//                RequestPredicates.GET("/code").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
//                validateCodeHandler);
//    }
//}
