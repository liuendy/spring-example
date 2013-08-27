package com.otkmnb.boot.example;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/v1/jersey")
public class BootShowcaseApplication extends ResourceConfig {
    
    public BootShowcaseApplication() {
        packages("com.otkmnb.sample.resources");
        register(LoggingFilter.class);
    }


}
