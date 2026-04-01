import com.riwi.utils.Calculadora;

/**
 * Prueba - Practical Challenge Day 3
 *
 * Entry point that uses Calculadora from a different package.
 * Demonstrates manual compilation with classpath (-cp).
 *
 * Compile from day3/ folder:
 *   javac com/riwi/utils/Calculadora.java Prueba.java
 *
 * Run from day3/ folder:
 *   java -cp . Prueba
 */

public class Prueba {

    public static void main(String[] args) {

        double salario = 3500000.00;
        double bono = 500000.00;

        // Using Calculadora from package com.riwi.utils
        double salarioFinal = Calculadora.calcularSalarioFinal(salario, bono);
        System.out.println("Final salary: $" + salarioFinal);

        // Categorize employees by ID
        System.out.println("Employee 1001: " + Calculadora.categorizarEmpleado(1001));
        System.out.println("Employee 1002: " + Calculadora.categorizarEmpleado(1002));
    }
}