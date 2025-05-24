package org.example.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${app.rabbit.input-queue}")
    private String inputQueue;

    @Value("${app.rabbit.output-queue}")
    private String outputQueue;

    @Value("${app.rabbit.output-exchange}")
    private String outputExchange;

    @Value("${app.rabbit.output-routing-key}")
    private String outputRoutingKey;

    @Bean
    public Queue taskQueue() {
        return new Queue(inputQueue, true);
    }

    @Bean
    public Queue completedTaskQueue() {
        return new Queue(outputQueue, true);
    }

    @Bean
    public DirectExchange outputExchange() {
        return new DirectExchange(outputExchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(completedTaskQueue())
                .to(outputExchange())
                .with(outputRoutingKey);
    }
}
