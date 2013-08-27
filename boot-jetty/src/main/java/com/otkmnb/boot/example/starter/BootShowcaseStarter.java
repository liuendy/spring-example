package com.otkmnb.boot.example.starter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.otkmnb.boot.example.BootShowcaseApplication;

public class BootShowcaseStarter {

    public class BootShowcase implements WebApplicationInitializer {

        @Override
        public void onStartup(ServletContext container)
                throws ServletException {
            XmlWebApplicationContext context = new XmlWebApplicationContext();
            context.setConfigLocation("classpath:Context.xml");
            container.addListener(new ContextLoaderListener(context));
            ServletRegistration.Dynamic dispatcher = container.addServlet("jersey", ServletContainer.class);
            dispatcher.setInitParameter("javax.ws.rs.Application", BootShowcaseApplication.class.getName());
            dispatcher.addMapping("/v1/jersey");
        }

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootShowcase.class, args);
    }

}
