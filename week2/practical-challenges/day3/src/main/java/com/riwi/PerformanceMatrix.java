package com.riwi;

public class PerformanceMatrix {

    public void generateReport() {

        // Matrix: 3 employees x 3 quarters
        double[][] scores = {
            {85.5, 90.0, 78.3},  // Employee 1
            {92.1, 88.7, 95.4},  // Employee 2
            {70.0, 75.5, 80.2}   // Employee 3
        };

        String[] employees = {"Alice", "Bob", "Carol"};

        double teamTotal = 0;

        System.out.println("=== Performance Report ===\n");

        // Nested loops to calculate each employee's average
        for (int i = 0; i < scores.length; i++) {

            double employeeTotal = 0;

            for (int j = 0; j < scores[i].length; j++) {
                employeeTotal += scores[i][j];
            }

            double average = employeeTotal / scores[i].length;
            teamTotal += average;

            // Explicit casting: double to int — decimals are truncated, not rounded
            int simplifiedScore = (int) average;

            System.out.println("Employee: " + employees[i]);
            System.out.println("Average:  " + average);
            System.out.println("Simplified score (cast to int): " + simplifiedScore);
            System.out.println();
        }

        // Team general average
        double teamAverage = teamTotal / scores.length;
        int simplifiedTeamScore = (int) teamAverage;

        System.out.println("=== Team Summary ===");
        System.out.println("Team average:           " + teamAverage);
        System.out.println("Simplified team score:  " + simplifiedTeamScore);
    }
}