package ink.kangaroo.common.freemarker.listener;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/7 13:52
 */
@Slf4j
@Component
public class FreemarkerConfigAwareListener {
    private final Configuration configuration;

    public FreemarkerConfigAwareListener(Configuration configuration) {
        this.configuration = configuration;
    }

    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public void onApplicationStartedEvent(ApplicationStartedEvent applicationStartedEvent) throws TemplateModelException {
        log.debug("Received application started event");

        loadThemeConfig();
        loadOptionsConfig();
        loadUserConfig();
    }

/*    @EventListener
    public void onThemeActivatedEvent(ThemeActivatedEvent themeActivatedEvent) throws TemplateModelException {
        log.debug("Received theme activated event");

        loadThemeConfig();
    }

    @EventListener
    public void onThemeUpdatedEvent(ThemeUpdatedEvent event) throws TemplateModelException {
        log.debug("Received theme updated event");

        loadThemeConfig();
    }

    @EventListener
    public void onUserUpdate(UserUpdatedEvent event) throws TemplateModelException {
        log.debug("Received user updated event, user id: [{}]", event.getUserId());

        loadUserConfig();
    }

    @EventListener
    public void onOptionUpdate(OptionUpdatedEvent event) throws TemplateModelException {
        log.debug("Received option updated event");

        loadOptionsConfig();
        loadThemeConfig();
    }*/


    private void loadUserConfig() throws TemplateModelException {
//        configuration.setSharedVariable("user", userService.getCurrentUser().orElse(null));
        log.debug("Loaded user");
    }

    private void loadOptionsConfig() throws TemplateModelException {

//        final String webBaseUrl = optionService.getWebBaseUrl();
//        final String context = optionService.isEnabledAbsolutePath() ? webBaseUrl + "/" : "/";

//        configuration.setSharedVariable("options", optionService.listOptions());
//        configuration.setSharedVariable("context", context);
//        configuration.setSharedVariable("version", TribeConst.TRIBE_VERSION);

//        configuration.setSharedVariable("globalAbsolutePathEnabled", optionService.isEnabledAbsolutePath());
//        configuration.setSharedVariable("web_title", optionService.getWebTitle());
//        configuration.setSharedVariable("web_url", webBaseUrl);
//        configuration.setSharedVariable("web_logo", optionService.getByPropertyOrDefault(WebProperties.WEB_LOGO, String.class, WebProperties.WEB_LOGO.defaultValue()));
//        configuration.setSharedVariable("seo_keywords", optionService.getByPropertyOrDefault(SeoProperties.KEYWORDS, String.class, SeoProperties.KEYWORDS.defaultValue()));
//        configuration.setSharedVariable("seo_description", optionService.getByPropertyOrDefault(SeoProperties.DESCRIPTION, String.class, SeoProperties.DESCRIPTION.defaultValue()));

//        configuration.setSharedVariable("rss_url", webBaseUrl + "/rss.xml");
//        configuration.setSharedVariable("atom_url", webBaseUrl + "/atom.xml");
//        configuration.setSharedVariable("sitemap_xml_url", webBaseUrl + "/sitemap.xml");
//        configuration.setSharedVariable("sitemap_html_url", webBaseUrl + "/sitemap.html");
//        configuration.setSharedVariable("links_url", context + optionService.getLinksPrefix());
//        configuration.setSharedVariable("photos_url", context + optionService.getPhotosPrefix());
//        configuration.setSharedVariable("journals_url", context + optionService.getJournalsPrefix());
//        configuration.setSharedVariable("archives_url", context + optionService.getArchivesPrefix());
//        configuration.setSharedVariable("categories_url", context + optionService.getCategoriesPrefix());
//        configuration.setSharedVariable("tags_url", context + optionService.getTagsPrefix());

        log.debug("Loaded options");
    }

    private void loadThemeConfig() {
        // Get current activated theme.
        /*themeService.fetchActivatedTheme().ifPresent(activatedTheme -> {
            String themeBasePath = (optionService.isEnabledAbsolutePath() ? optionService.getWebBaseUrl() : "") + "/themes/" + activatedTheme.getFolderName();
            try {
                configuration.setSharedVariable("theme", activatedTheme);

                // TODO: It will be removed in future versions
                configuration.setSharedVariable("static", themeBasePath);

                configuration.setSharedVariable("theme_base", themeBasePath);

                configuration.setSharedVariable("settings", themeSettingService.listAsMapBy(themeService.getActivatedThemeId()));
                log.debug("Loaded theme and settings");
            } catch (TemplateModelException e) {
                log.error("Failed to set shared variable!", e);
            }
        });*/

    }
}
