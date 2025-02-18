package com.example.taskmanager.config;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configura il servlet per permettere a Camel di gestire le richieste HTTP.
 */
@Configuration
public class CamelServletConfig {

    @Bean
    public ServletRegistrationBean<CamelHttpTransportServlet> camelServletRegistrationBean() {
        ServletRegistrationBean<CamelHttpTransportServlet> registrationBean =
                new ServletRegistrationBean<>(new CamelHttpTransportServlet(), "/api/*");
        registrationBean.setName("CamelServlet");
        return registrationBean;
    }
}
