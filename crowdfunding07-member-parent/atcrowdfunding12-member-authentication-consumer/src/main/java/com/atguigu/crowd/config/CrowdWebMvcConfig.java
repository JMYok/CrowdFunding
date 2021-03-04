package com.atguigu.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-03-04 21:38
 */
@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        String urlPath = "/auth/member/to/reg/page.html";

        String viewname="member-reg";

        registry.addViewController(urlPath).setViewName(viewname);

    }
}
