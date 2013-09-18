package com.otkmnb.boot.example.config;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.otkmnb.boot.example")
public class BootShowcaseContextConfig {
    
    private int port = 1234;

    @Bean
    public EmbeddedServletContainerFactory container() {
        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        factory.setPort(port);
        factory.setSessionTimeout(10, TimeUnit.MINUTES);
        factory.addInitializers(new BootShowcaseWebConfig());
        return factory;
    }

    public class BootShowcaseWebConfig implements ServletContextInitializer {

        public BootShowcaseWebConfig() {}

        @Override
        public void onStartup(ServletContext container) throws ServletException {
            ServletRegistration.Dynamic dispatcher = container.addServlet("jersey", ServletContainer.class);
            dispatcher.setInitParameter("javax.ws.rs.Application", BootShowcaseJerseyConfig.class.getName());
            dispatcher.addMapping("/v1.0/*");
        }

    }
    
    @ApplicationPath("/v1.0")
    public class BootShowcaseJerseyConfig extends ResourceConfig {
        
        public BootShowcaseJerseyConfig() {
            packages("com.otkmnb.boot.example.resource");
            packages(SpringLifecycleListener.class.getPackage().getName());
            property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
            property(ServerProperties.JSON_PROCESSING_FEATURE_DISABLE, true);
            property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
            property(ServerProperties.WADL_FEATURE_DISABLE, true);
            register(LoggingFilter.class);
        }

    }

}
