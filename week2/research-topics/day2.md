# Temas de Investigación - Semana 2, Día 2: Captura Dinámica de Información

---

## 1. Legibilidad vs Ambigüedad: ¿Cuándo es mala práctica usar `var`?

### ¿Qué es `var`?
`var` fue introducido en Java 11 y permite que el compilador infiera el tipo de una variable local. El tipo sigue siendo estático — solo se escribe menos código.

```java
// Con var
var lista = new ArrayList<String>(); // El compilador sabe que es ArrayList<String>

// Sin var
ArrayList<String> lista = new ArrayList<String>();
```

### ¿Cuándo NO usar `var`? — Guías de Google y Oracle

**1. Cuando el tipo no es obvio a simple vista:**
```java
// Malo — ¿qué tipo retorna getEmployee()?
var result = employeeService.getEmployee(id);

// Bueno — el tipo es explícito
Employee result = employeeService.getEmployee(id);
```

**2. Con literales numéricos:**
```java
// Ambiguo — ¿es int, long, float o double?
var value = 10;

// Claro
int value = 10;
```

**3. Con `null`:**
```java
// Error de compilación — el compilador no puede inferir el tipo de null
var name = null;
```

**4. En cadenas de métodos largas:**
```java
// Difícil de leer — ¿qué tipo es result?
var result = list.stream().filter(e -> e.getSalary() > 3000).findFirst();
```

### Regla de oro (Oracle Style Guide)
Usa `var` solo cuando el tipo sea obvio desde el lado derecho de la asignación. Si tienes que pensar para saber el tipo, escríbelo explícitamente.

---

## 2. Buffer del Scanner: El "Newline Character Problem"

### ¿Qué es el problema?
Cuando usas `nextInt()` seguido de `nextLine()`, el programa se salta la lectura del String. Esto ocurre porque `nextInt()` lee el número pero deja el carácter de nueva línea `\n` en el buffer. Luego `nextLine()` lee ese `\n` vacío en lugar de esperar input del usuario.

```java
Scanner scanner = new Scanner(System.in);

System.out.print("Enter age: ");
int age = scanner.nextInt();        // Lee "25" pero deja "\n" en el buffer

System.out.print("Enter name: ");
String name = scanner.nextLine();   // Lee el "\n" que quedó — no espera al usuario

System.out.println("Name: " + name); // Imprime vacío
```

### ¿Por qué ocurre?
`nextInt()`, `nextDouble()` y `nextBoolean()` son métodos que leen tokens — detienen la lectura en el espacio o la nueva línea, pero no consumen ese delimitador. `nextLine()` en cambio lee hasta la nueva línea y la consume, por eso agarra el `\n` que sobró.

### Solución profesional
Agregar un `scanner.nextLine()` vacío después de cada `nextInt()` para limpiar el buffer:

```java
System.out.print("Enter age: ");
int age = scanner.nextInt();
scanner.nextLine(); // Limpia el "\n" del buffer

System.out.print("Enter name: ");
String name = scanner.nextLine(); // Ahora sí espera al usuario
```

### Solución alternativa
Leer todo como String y luego convertir:

```java
System.out.print("Enter age: ");
int age = Integer.parseInt(scanner.nextLine()); // Sin problema de buffer
```

Esta segunda opción es más robusta en proyectos reales porque evita el problema de raíz.

---

## 3. Tipado Estático vs Dinámico: `var` en Java no es como en otros lenguajes

### La diferencia fundamental

**Tipado estático (Java):** El tipo de cada variable se determina en tiempo de compilación y no puede cambiar.

**Tipado dinámico (Python, JavaScript):** El tipo se determina en tiempo de ejecución y puede cambiar durante la vida del programa.

### `var` en Java es tipado estático con inferencia

```java
var nombre = "Samuel"; // El compilador infiere: String nombre = "Samuel"
nombre = 123;          // Error de compilación — no puedes cambiar el tipo
```

Una vez que el compilador infiere el tipo, la variable es exactamente igual a si hubieras escrito el tipo explícitamente. `var` es solo azúcar sintáctico — una conveniencia para escribir menos, no un cambio en cómo funciona Java.

### Comparación directa

```java
// Java con var — sigue siendo estático
var salario = 3500.0;  // El compilador lo trata como: double salario = 3500.0
salario = "alto";      // Error — el tipo ya está fijo como double
```

```javascript
// JavaScript — tipado dinámico
let salario = 3500.0;
salario = "alto";      // Válido — JavaScript permite cambiar el tipo
```

### ¿Por qué importa para Java?
El tipado estático permite que el compilador detecte errores antes de que el programa corra. En tipado dinámico, muchos errores de tipo solo aparecen en producción. Por eso Java — aunque permite `var` — nunca renuncia al tipado estático.

---

## 4. `var` con try-with-resources para el Scanner (Java 21)

### ¿Qué es try-with-resources?
Es una forma de manejar recursos que necesitan cerrarse (como `Scanner`, conexiones a base de datos, archivos) de manera automática. El recurso se declara en el paréntesis del `try` y Java lo cierra automáticamente al terminar el bloque, sin necesidad de llamar `scanner.close()` manualmente.

### Ejemplo con `var`

```java
// try-with-resources con var — Java cierra el Scanner automáticamente
try (var scanner = new Scanner(System.in)) {
    System.out.print("Enter your name: ");
    var name = scanner.nextLine();
    System.out.println("Hello, " + name);
} // scanner.close() se llama aquí automáticamente

// Si intentas usar scanner aquí, el compilador da error — ya fue cerrado
```

### ¿Por qué usar `var` aquí?
El tipo `Scanner` es obvio desde el lado derecho (`new Scanner(System.in)`), por lo que `var` es apropiado según la guía de Oracle. Además hace el código más limpio cuando el tipo es largo, por ejemplo:

```java
// Sin var — repetitivo
BufferedReader reader = new BufferedReader(new FileReader("archivo.txt"));

// Con var — más limpio, el tipo sigue siendo obvio
var reader = new BufferedReader(new FileReader("archivo.txt"));
```

### Ventaja principal
Si el bloque `try` lanza una excepción, el recurso igualmente se cierra. Con `scanner.close()` manual, si hay una excepción antes de esa línea, el recurso queda abierto — lo que puede causar fugas de memoria en aplicaciones de larga duración.