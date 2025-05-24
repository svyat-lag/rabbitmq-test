package org.example.consumer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.example.consumer.model.Task;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskConsumer {

    private static final int TASK_CALCULATION_TIME = 5000;

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbit.output-exchange}")
    private String outputExchange;

    @Value("${app.rabbit.output-routing-key}")
    private String outputRoutingKey;

    @RabbitListener(queues = "${app.rabbit.input-queue}")
    public void receiveTask(Task task) {
        log.info("Received task: {}", task);

        try {
            // Имметация обрбаотки задачи
            Thread.sleep(TASK_CALCULATION_TIME);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        rabbitTemplate.convertAndSend(outputExchange, outputRoutingKey, task);
        log.info("Task {} result sent to output queue", task.getId());

        long processingTime = System.currentTimeMillis() - task.getCreatedAt();
        log.info("Task {} processed in {} ms", task.getId(), processingTime);
    }
}
