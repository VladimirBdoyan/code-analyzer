package com.example.masterservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
public class JobDTO {
    private final UUID jobId;
    private final String message;
}
