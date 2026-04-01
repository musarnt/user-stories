package com.startup.utils;

/**
 * CalculadoraNomina - Day 5
 *
 * Business logic for salary calculation.
 * Demonstrates operator precedence and overflow behavior.
 */
public class CalculadoraNomina {

    /**
     * Calculates net salary applying bonus and deduction.
     *
     * Expression: (salario + bono * 1.10) - (salario * 0.05)
     *
     * Execution order:
     * 1. bono * 1.10        → multiplication first
     * 2. salario + result   → addition
     * 3. salario * 0.05     → multiplication
     * 4. result - result    → subtraction last
     */
    public static double calcularNeto(double salario, double bono) {
        return (salario + bono * 1.10) - (salario * 0.05);
    }

    /**
     * Demonstrates byte overflow behavior.
     *
     * byte max value is 127. In binary: 01111111.
     * Adding 1 flips all bits to 10000000,
     * which in two's complement represents -128.
     */
    public static void demostrarOverflow() {
        byte nivelSeguridad = 127;
        nivelSeguridad = (byte) (nivelSeguridad + 1);
        System.out.println("Security level overflow: " + nivelSeguridad); // -128
    }
}