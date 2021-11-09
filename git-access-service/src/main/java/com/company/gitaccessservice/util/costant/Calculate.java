package com.company.gitaccessservice.util.costant;

public class Calculate {
    public static double density(int countByUser, int total) {
        return countByUser / (double) total * 100;
    }
}
