# Temas de Investigación - Semana 2, Día 1: Control de Flujo Moderno

---

## 1. Fall-through: Origen y Riesgos de Seguridad

### ¿Por qué se diseñó así?
El `switch` clásico fue heredado por Java del lenguaje C (años 70). En esa época, el fall-through era **intencional** — permitía que varios casos compartieran el mismo bloque de código sin repetirlo:

```java
// Fall-through intencional: todos los días laborales comparten la misma lógica
switch (dia) {
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        System.out.println("Día laboral");
        break;
    case 6:
    case 7:
        System.out.println("Fin de semana");
        break;
}
```

En los primeros años de la computación, ahorrar líneas de código importaba más que la legibilidad. El fall-through era una característica, no un error.

### Riesgos históricos de seguridad
Con el tiempo, el fall-through accidental se convirtió en una de las fuentes de bugs más comunes en C, C++ y Java. El ejemplo real más famoso es el **bug SSL/TLS de Apple (CVE-2014-1266)**, conocido como el bug "goto fail". Una línea `goto fail;` duplicada hacía que la validación de certificados SSL se saltara por completo, lo que significaba que cualquier certificado — incluso los inválidos — pasaba como confiable. Esto expuso a millones de dispositivos Apple a ataques de intermediario (man-in-the-middle).

La causa raíz fue lógica de fall-through ejecutando código que nunca debió ejecutarse. Java mantuvo el fall-through por compatibilidad con código antiguo, pero las Switch Expressions (Java 17) fueron introducidas para eliminar el riesgo por completo.

---

## 2. Exhaustividad: Por qué el Compilador Exige Cubrir Todos los Casos

### ¿Qué es la exhaustividad?
Una Switch Expression es **exhaustiva** cuando cada valor posible de entrada tiene un caso que lo maneja. Si algún valor pudiera no tener coincidencia, la expresión no tendría valor que retornar — y en Java, toda expresión debe producir un valor.

### ¿Por qué el compilador lo exige?
En una Switch Expression, el resultado se asigna a una variable:

```java
String resultado = switch (opcion) {
    case 1 -> "Registrar";
    case 2 -> "Consultar";
    // ¿Qué pasa si opcion = 99?
};
```

Si `opcion = 99` no tiene caso correspondiente y no hay `default`, la variable `resultado` no tendría valor. Java no permite variables sin inicializar, por eso el compilador lo detecta en tiempo de compilación — antes de que el programa corra.

Esto es fundamentalmente diferente al `switch` clásico, que simplemente no hace nada cuando ningún caso coincide, ignorando la entrada en silencio.

### La regla
Toda Switch Expression debe:
- Cubrir todos los valores posibles explícitamente, o
- Incluir un caso `default` como comodín

---

## 3. `when` de Kotlin vs Switch Expression de Java

### La estructura `when` de Kotlin
Kotlin introdujo la expresión `when` como un reemplazo moderno y seguro del `switch`. Las Switch Expressions de Java se inspiraron directamente en ella.

```kotlin
// Kotlin
val categoria = when {
    salario <= 2000 -> "Junior"
    salario <= 4000 -> "Mid"
    salario <= 7000 -> "Senior"
    else -> "Expert"
}
```

### Diferencias clave

| Característica | `when` de Kotlin | Switch Expression de Java |
|---|---|---|
| Rangos de forma nativa | ✅ Sí | ❌ Necesita workaround |
| Sintaxis de flecha `->` | ✅ Sí | ✅ Sí (Java 17+) |
| Sin fall-through | ✅ Sí | ✅ Sí |
| Retorna un valor | ✅ Sí | ✅ Sí |
| Funciona con cualquier tipo | ✅ Sí | ⚠️ Limitado (Java 21 mejorando) |
| `else` / `default` obligatorio | ✅ Sí | ✅ Sí |

### Qué tomó Java de Kotlin
- La sintaxis de flecha `->`
- La capacidad de retornar un valor directamente desde la expresión
- La exhaustividad obligatoria
- La eliminación del fall-through

El `when` de Kotlin es más flexible porque soporta condiciones booleanas arbitrarias de forma nativa (sin workaround para rangos). Java está alcanzando ese nivel con **Pattern Matching for switch** en Java 21.

---

## 4. Pattern Matching for Switch (Java 21)

### ¿Qué es?
Pattern Matching for switch es una característica de Java 21 que hace las Switch Expressions aún más poderosas. En lugar de hacer switch solo sobre valores exactos, ahora puedes hacer switch sobre **tipos y condiciones**.

### Ejemplo

```java
// Java 21 - Pattern Matching for switch
static String describir(Object obj) {
    return switch (obj) {
        case Integer i when i > 0 -> "Entero positivo: " + i;
        case Integer i            -> "Entero no positivo: " + i;
        case String s             -> "String de longitud: " + s.length();
        case null                 -> "Valor nulo";
        default                   -> "Tipo desconocido";
    };
}
```

### Qué hace `when` aquí
La palabra clave `when` (llamada **guard** o guarda) agrega una condición extra a un caso de patrón. Es diferente al `when` de Kotlin — en Java 21 se usa solo dentro de un `case` para refinar una coincidencia.

### Por qué importa
Antes de Java 21, para manejar diferentes tipos necesitabas una cadena de comprobaciones con `instanceof`:

```java
// Forma antigua - verbosa y propensa a errores
if (obj instanceof Integer) {
    Integer i = (Integer) obj;
    // ...
} else if (obj instanceof String) {
    String s = (String) obj;
    // ...
}
```

Pattern Matching for switch reemplaza toda esa cadena con una expresión limpia, exhaustiva y verificada por el compilador. Es un paso importante hacia hacer el código Java tan expresivo como Kotlin o Scala.