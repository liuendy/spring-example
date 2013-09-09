package com.otkmnb.amqp.sample.config;

import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

import com.otkmnb.amqp.sample.consumer.HelloConsumer;

@ComponentScan("com.otkmnb.amqp.sample")
public class AmqpContextConfig {

    @Bean
    public CachingConnectionFactory factory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPublisherReturns(true);
        return factory;
    }
    
    @Bean
    public MessageConverter converter() {
        return new JsonMessageConverter();
    }
    
    @Bean
    public RabbitTemplate template() {
        RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(factory());
        template.setMessageConverter(converter());
        return template;
    }
    
    @Bean
    @Profile("development")
    public RabbitAdmin admin() {
        return new RabbitAdmin(factory());
    }
    
    @Bean
    public Queue queue() {
        return new Queue("hello.queue");
    }
    
    @Bean
    public SimpleMessageListenerContainer container() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory());
        container.setQueues(queue());
        container.setMessageListener(listener());
        return container;
    }
    
    @Bean
    public MessageListener listener() {
        MessageListenerAdapter listener = new MessageListenerAdapter();
        listener.setMessageConverter(converter());
        listener.setDelegate(consumer());
        return listener;
    }
    
    public HelloConsumer consumer() {
        return new HelloConsumer();
    }
    
}
