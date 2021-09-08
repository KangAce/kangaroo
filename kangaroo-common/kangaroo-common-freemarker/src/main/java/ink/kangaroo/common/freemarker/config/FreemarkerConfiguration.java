package ink.kangaroo.common.freemarker.config;

import freemarker.core.TemplateClassResolver;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/7 16:07
 */
public class FreemarkerConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public FreeMarkerConfigurer freemarkerConfig(/*TribeProperties TribeProperties*/) throws IOException, TemplateException {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPaths(/*FILE_PROTOCOL + TribeProperties.getWorkDir() + "templates/",*/ "classpath:/templates/");
        configurer.setDefaultEncoding("UTF-8");

        Properties properties = new Properties();
        properties.setProperty("auto_import", "/common/macro/common_macro.ftl as common,/common/macro/global_macro.ftl as global");

        configurer.setFreemarkerSettings(properties);

        // Predefine configuration
        freemarker.template.Configuration configuration = configurer.createConfiguration();

        configuration.setNewBuiltinClassResolver(TemplateClassResolver.SAFER_RESOLVER);

//        if (tribeProperties.isProductionEnv()) {
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//        }

        // Set predefined freemarker configuration
        configurer.setConfiguration(configuration);

        return configurer;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setAllowRequestOverride(false);
        resolver.setCache(false);
        resolver.setExposeRequestAttributes(false);
        resolver.setExposeSessionAttributes(false);
        resolver.setExposeSpringMacroHelpers(true);
        resolver.setSuffix(".ftlh");
        resolver.setContentType("text/html; charset=UTF-8");
        registry.viewResolver(resolver);
    }
}
