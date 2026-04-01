# Temas de Investigación - Semana 2, Día 4: Sistema Inquebrantable

---

## 1. Checked vs Unchecked Exceptions

### ¿Qué son las Checked Exceptions?
Son excepciones que el compilador te obliga a manejar. Si llamas a un método que puede lanzar una Checked Exception y no la envuelves en un `try-catch` o la declaras con `throws`, el código no compila.
```java
// FileNotFoundException es Checked — el compilador te obliga a manejarla
try {
    var reader = new FileReader("archivo.txt");
} catch (java.io.FileNotFoundException e) {
    System.out.println("File not found: " + e.getMessage());
}
```

### ¿Qué son las Unchecked Exceptions?
Son excepciones que el compilador NO te obliga a manejar. Heredan de `RuntimeException`. El programa compila sin `try-catch`, pero puede crashear en tiempo de ejecución si ocurren.
```java
// InputMismatchException es Unchecked — el compilador no te obliga a nada
var id = scanner.nextInt(); // Puede lanzar InputMismatchException en runtime
```

### ¿Por qué existe esta diferencia?

Las Checked Exceptions representan situaciones que el programador puede anticipar y de las que puede recuperarse: archivo no encontrado, conexión rechazada, etc. El compilador te obliga a pensar en esos casos.

Las Unchecked Exceptions representan errores de programación: índice fuera de rango, referencia nula, tipo de dato incorrecto. La idea es que estos errores se deben prevenir con buena lógica, no con `try-catch` en todos lados.

### Jerarquía
```
Throwable
├── Error (JVM errors — no se manejan)
└── Exception
    ├── Checked Exceptions (IOException, SQLException...)
    └── RuntimeException — Unchecked
        ├── NullPointerException
        ├── InputMismatchException
        ├── ArrayIndexOutOfBoundsException
        └── ...
```

---

## 2. El bloque `finally`

### ¿Para qué sirve?
El bloque `finally` se ejecuta **siempre**, sin importar si el `try` terminó normalmente o si el `catch` capturó una excepción. Su propósito principal es cerrar recursos — archivos, conexiones a base de datos, sockets — para evitar fugas de memoria.
```java
Scanner scanner = null;

try {
    scanner = new Scanner(System.in);
    var id = scanner.nextInt();
    System.out.println("ID: " + id);
} catch (java.util.InputMismatchException e) {
    System.out.println("Invalid input.");
} finally {
    // Esto se ejecuta siempre — con o sin excepción
    if (scanner != null) {
        scanner.close();
    }
    System.out.println("Scanner closed.");
}
```

### ¿Por qué es vital?
Si abres una conexión a una base de datos y ocurre una excepción antes de cerrarla, esa conexión queda abierta. En una aplicación con miles de usuarios, esto agota el pool de conexiones disponibles y el sistema colapsa.

### `finally` vs try-with-resources
En Java 7+ el bloque `try-with-resources` reemplaza al `finally` para el cierre de recursos — es más limpio y seguro. Sin embargo, `finally` sigue siendo útil para lógica que debe ejecutarse siempre, más allá del cierre de recursos.
```java
// try-with-resources — forma moderna, preferida
try (var scanner = new Scanner(System.in)) {
    var id = scanner.nextInt();
} catch (java.util.InputMismatchException e) {
    System.out.println("Invalid input.");
}
// El scanner se cierra automáticamente — sin finally
```

---

## 3. JEP 358: Helpful NullPointerExceptions (Java 14/15/17)

### ¿Qué era el problema antes?
Antes de Java 14, cuando ocurría una `NullPointerException`, el mensaje de error era completamente inútil:
```
Exception in thread "main" java.lang.NullPointerException
```

No decía qué variable era nula, ni en qué parte de la expresión ocurrió. En expresiones encadenadas era especialmente frustrante:
```java
employee.getAddress().getCity().toUpperCase();
// Si cualquiera de las tres es null — mismo mensaje genérico
```

### ¿Qué cambió con JEP 358?
A partir de Java 14 (experimental) y estabilizado en Java 17, la JVM ahora indica exactamente qué parte de la expresión fue nula:
```
Cannot invoke "String.toUpperCase()" because the return value
of "Address.getCity()" is null
```

### ¿Por qué importa para el desarrollo profesional?
En producción, los stack traces son la principal herramienta de diagnóstico. Un mensaje de error claro reduce el tiempo de debugging significativamente — especialmente en sistemas complejos con objetos anidados.

### Limitación importante
JEP 358 mejoró `NullPointerException` específicamente. Otras excepciones como `InputMismatchException` siguen retornando `null` en `getMessage()` — como vimos en el ejercicio de hoy. Cada excepción define su propio mensaje, y no todas fueron actualizadas.