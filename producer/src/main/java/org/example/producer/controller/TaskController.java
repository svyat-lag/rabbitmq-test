package org.example.producer.controller;

import lombok.RequiredArgsConstructor;
import org.example.producer.model.Task;
import org.example.producer.model.TaskRequestDto;
import org.example.producer.service.TaskProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskProducer taskProducer;

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        var task = new Task(
                taskRequestDto.id(),
                taskRequestDto.description(),
                System.currentTimeMillis()
        );
        taskProducer.sendTask(task);
        return ResponseEntity.ok("Task sent to RabbitMQ");
    }
}
