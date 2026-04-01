# Research Topics - Day 2

## 1. IEEE 754: Float y Double en Cálculos Financieros

El estándar IEEE 754 define cómo los computadores representan números decimales en binario.
El problema es que no todos los decimales tienen representación exacta en binario.
Por ejemplo, 0.1 en binario es un número periódico infinito — como 1/3 en decimal.

Esto significa que operaciones como `0.1 + 0.2` en `double` no dan exactamente `0.3`,
sino algo como `0.30000000000000004`. En cálculos financieros esa diferencia es dinero real.

**El estándar industrial para dinero es `BigDecimal`:**
```java
BigDecimal salario = new BigDecimal("3500000.00");
BigDecimal bono = new BigDecimal("500000.00");
BigDecimal total = salario.add(bono); // exacto, sin errores de precisión
```

`BigDecimal` no usa IEEE 754 — representa los números como texto internamente,
lo que garantiza precisión exacta a costa de ser más lento que `double`.

---

## 2. Stack vs Heap

| | Stack | Heap |
|---|---|---|
| ¿Qué almacena? | Primitivos y referencias | Objetos completos |
| Velocidad | Muy rápida | Más lenta |
| Gestión | Automática (se libera al salir del método) | Garbage Collector |
| Ejemplo | `int id = 1001` | `new Empresa("Talent Corp", 5)` |

Cuando escribes `int id = 1001`, el valor `1001` vive directamente en el Stack.
Cuando escribes `Empresa e = new Empresa(...)`, el objeto vive en el Heap
y la variable `e` en el Stack guarda solo la dirección de memoria donde está ese objeto.

---

## 3. Records: equals() y hashCode() internos

En una clase convencional, si no escribes `equals()` y `hashCode()` manualmente,
Java compara por referencia de memoria — dos objetos con los mismos datos se consideran distintos.

Un Record genera estos métodos automáticamente comparando por **valor de los campos**:
```java
Empresa e1 = new Empresa("Talent Corp", 5);
Empresa e2 = new Empresa("Talent Corp", 5);

e1.equals(e2); // true — mismo nombre y sedes
e1 == e2;      // false — distintas referencias en el Heap
```

El `hashCode()` también se genera en base a los valores de los campos,
lo que hace que los Records funcionen correctamente en colecciones como `HashMap` y `HashSet`
sin configuración adicional.

---

## 4. Helpful NullPointerExceptions (Java 14+)

**Java 8:**
```
Exception in thread "main" java.lang.NullPointerException
```
Sin detalles — había que debuggear manualmente para encontrar qué era null.

**Java 14+:**
```
Exception in thread "main" java.lang.NullPointerException:
Cannot invoke "String.length()" because "employee.fullName" is null
```
Java ahora dice exactamente qué variable era null y qué método se intentó llamar.
Esto reduce significativamente el tiempo de diagnóstico en producción.