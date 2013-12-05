package com.otkmnb.sample.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.otkmnb.sample.DynamicDatasourceSampleScanPackage;

@ComponentScan(basePackageClasses = DynamicDatasourceSampleScanPackage.class)
@ImportResource("classpath:/Context.xml")
@EnableAutoConfiguration
public class DynamicDatasourceSampleConfig {

}
