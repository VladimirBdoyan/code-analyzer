package com.company.statisticsservice.util;

public class Calculate {
    public static double density(int countByUser, int total) {
        return countByUser / (double) total * 100;
    }
}
