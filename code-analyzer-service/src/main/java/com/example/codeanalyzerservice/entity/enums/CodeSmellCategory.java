package com.example.codeanalyzerservice.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CodeSmellCategory {

    LOW(2), MEDIUM(4), HIGH(6);

    @Getter
    private final int coefficient;
}
