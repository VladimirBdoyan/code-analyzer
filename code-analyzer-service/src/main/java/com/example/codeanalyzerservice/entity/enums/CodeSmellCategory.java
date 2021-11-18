package com.example.findbugs.entity.enums;

public enum CodeSmellCategory {
    LOW(2), MEDIUM(4), HIGH(6);

    private final int coefficient;

    CodeSmellCategory(int coefficient){
        this.coefficient=coefficient;
    }
    public int getCoefficient(){
        return this.coefficient;
    }
}
