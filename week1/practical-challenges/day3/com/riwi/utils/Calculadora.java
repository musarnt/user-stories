package com.riwi.utils;

/**
 * Calculadora - Practical Challenge Day 3
 *
 * Business logic for salary calculation and employee ID categorization.
 * Demonstrates operator precedence and modulo operator.
 */

public class Calculadora {

    public static double calcularSalarioFinal(double salario, double bono) {
        return (salario + (bono * 1.10)) - (salario * 0.05);
    }

    /**
     * Categorizes employee by ID using modulo operator.
     * Even ID → Team A
     * Odd ID  → Team B
     */
    
    public static String categorizarEmpleado(int idEmpleado) {
        if (idEmpleado % 2 == 0) {
            return "Team A (even ID)";
        } else {
            return "Team B (odd ID)";
        }
    }
}