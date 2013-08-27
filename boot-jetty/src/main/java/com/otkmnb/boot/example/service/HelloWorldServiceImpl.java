package com.otkmnb.boot.example.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public void delete(int id) {
        System.out.println("test");
    }

}
