package com.otkmnb.boot.example.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;

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
