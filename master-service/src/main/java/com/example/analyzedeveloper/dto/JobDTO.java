package com.example.analyzedeveloper.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class JobDTO {
    private UUID jobId;
    private String message = "In progress";

    public JobDTO(UUID jobId, String message){
        this.jobId = jobId;
        this.message = message;
    }
}
