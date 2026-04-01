package com.talenthub;

public class MenuPrincipal {
    // Método que gestiona el menú principal con switch clásico
    public void mostrarOpcionClasico(int opcion) {

        switch (opcion) {
            case 1:
                System.out.println("Registrar empleado");
                break;
            case 2:
                System.out.println("Consultar empleado");
                break;
            case 3:
                System.out.println("Calcular salario");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    // Método moderno con Switch Expression (Java 17+)
    public void mostrarOpcionModerno(int opcion) {

        // Con -> no hay fall-through posible
        // Además puede devolver un valor directamente
        String resultado = switch (opcion) {
            case 1 -> "Registrar empleado";
            case 2 -> "Consultar empleado";
            case 3 -> "Calcular salario";
            default -> "Opción no válida";
        };

        System.out.println(resultado);
    }

    public String getSalaryCategory(double salary) {

    // Convert salary range to a level number
    int level = salary <= 2000 ? 1 :
                salary <= 4000 ? 2 :
                salary <= 7000 ? 3 : 4;

    // Switch Expression over the level
    return switch (level) {
        case 1 -> "Junior";
        case 2 -> "Mid";
        case 3 -> "Senior";
        case 4 -> "Expert";
        default -> "Unknown";
    };
}
}
