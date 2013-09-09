package com.otkmnb.amqp.sample.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.otkmnb.amqp.sample.consumer.HelloConsumerTest.HelloConsumerTestContext;
import com.otkmnb.amqp.sample.service.HelloService;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("development")
@ContextConfiguration(classes = HelloConsumerTestContext.class)
public class HelloConsumerTest {

    private static HelloService mock = Mockito.mock(HelloService.class);
    
    @Autowired
    private RabbitTemplate template;
    
    public static class HelloConsumerTestContext {
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
        
        @Bean
        public HelloConsumer consumer() {
            HelloConsumer consumer = new HelloConsumer();
            consumer.setService(mock);
            return consumer;
        }
        
        @Bean
        public HelloService mock() {
            return mock;
        }
    }
    
    @Test
    public void メッセージを受け取れるかどうか() throws Exception {
        Mockito.doNothing().when(mock).create("hello amqp world.");
        template.convertAndSend("hello.exchange", "", "hello amqp world.");
        Thread.sleep(500);
        Mockito.verify(mock).create("hello amqp world.");
    }

}
