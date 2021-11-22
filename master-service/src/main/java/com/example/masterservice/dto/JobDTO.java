package com.example.masterservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class JobDTO {
    private UUID jobId;
    private String message = "In progress";
}
