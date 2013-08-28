package com.otkmnb.boot.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    
    private static final Logger L = LoggerFactory.getLogger(HelloWorldServiceImpl.class);

    @Override
    public void findBy(int id) {
        L.debug("■■findByが実行された" + id);
    }

}
