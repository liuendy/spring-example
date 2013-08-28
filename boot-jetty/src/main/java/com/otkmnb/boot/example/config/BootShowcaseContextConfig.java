package com.otkmnb.boot.example.config;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:Context.xml")
//@ComponentScan
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
            container.setInitParameter("contextConfigLocation", "classpath:Context.xml");
            ServletRegistration.Dynamic dispatcher = container.addServlet("jersey", ServletContainer.class);
            dispatcher.setInitParameter("javax.ws.rs.Application", BootShowcaseJerseyConfig.class.getName());
            dispatcher.addMapping("/v1.0/*");
        }

    }

}