package com.sis.footballteams;

import com.sis.footballteams.config.ApplicationConfig;
import com.sis.footballteams.config.SecurityConfig;
import com.sis.footballteams.config.WebConfiguration;
import com.sis.footballteams.config.db.DatasourceConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class ApplicationBootstrap extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ApplicationConfig.class, DatasourceConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}