package org.example.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.producer.model.Task;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbit.exchange}")
    private String exchange;

    @Value("${app.rabbit.routing-key}")
    private String routingKey;

    public void sendTask(Task task) {
        log.info("Using message converter: {}", rabbitTemplate.getMessageConverter().getClass());
        rabbitTemplate.convertAndSend(exchange, routingKey, task);
    }
}
