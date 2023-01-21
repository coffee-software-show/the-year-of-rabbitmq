package com.example.producer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    private final String MQ = "rmq";

    @Bean
    ApplicationRunner producer(RabbitTemplate template) {
        return args -> template.convertAndSend(MQ, "新年快乐!");
    }

    @Bean
    Exchange exchange() {
        return ExchangeBuilder.directExchange(MQ).build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(this.queue()).to(this.exchange()).with(MQ).noargs();
    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable(MQ).build();
    }
}
