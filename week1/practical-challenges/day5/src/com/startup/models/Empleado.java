package com.startup.models;

/**
 * Empleado - Day 5
 *
 * Immutable data model using Java 17+ Record.
 * The record auto-generates: constructor, getters,
 * toString(), equals(), and hashCode().
 *
 * Immutability: once created, values cannot be changed.
 * There are no setters — by design.
 */
public record Empleado(String nombre, int id, double salarioBase, boolean esRemoto) {}