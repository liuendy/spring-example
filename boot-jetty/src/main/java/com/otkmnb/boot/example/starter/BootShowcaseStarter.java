package com.otkmnb.boot.example.starter;

import org.springframework.boot.SpringApplication;

import com.otkmnb.boot.example.config.BootShowcaseContextConfig;

public class BootShowcaseStarter {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootShowcaseContextConfig.class, args);
    }

}
