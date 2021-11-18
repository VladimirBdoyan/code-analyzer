package com.example.codeanalyzerservice.dto;

import lombok.Data;

import java.net.URL;
import java.util.List;
@Data
public class GitAccessResponseDTO {
    private String userName;
    private List<URL> urls;
}
