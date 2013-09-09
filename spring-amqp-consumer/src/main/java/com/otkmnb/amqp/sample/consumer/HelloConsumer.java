package com.otkmnb.amqp.sample.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otkmnb.amqp.sample.service.HelloService;

@Component
public class HelloConsumer {

    private static final Logger L = LoggerFactory.getLogger(HelloConsumer.class);

    @Autowired
    private HelloService service;

    public void handleMessage(String hello) {
        L.debug("送信されたメッセージ：{}", hello);
        service.create(hello);
    }

    public void setService(HelloService service) {
        this.service = service;
    }

}
