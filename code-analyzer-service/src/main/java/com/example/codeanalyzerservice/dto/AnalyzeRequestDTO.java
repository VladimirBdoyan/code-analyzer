package com.example.findbugs.dto;

import lombok.Data;
import java.util.Date;

@Data
public class AnalyzeRequestDTO {
    private String organization;
    private String username;
    private Date since;
    private Date till;
}
