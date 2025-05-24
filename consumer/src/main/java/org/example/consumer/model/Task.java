package org.example.consumer.model;

import lombok.Data;

@Data
public class Task {
    private String id;
    private String description;
    private long createdAt;
}
