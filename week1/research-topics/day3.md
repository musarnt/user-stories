# Research Topics - Day 3

## 1. Precedencia: Desplazamiento de Bits vs Suma

El operador de desplazamiento de bits `<<` tiene **mayor precedencia** que la suma `+`.

Ejemplo:
```java
int resultado = 2 + 3 << 1;
// Java evalúa: 2 + (3 << 1) = 2 + 6 = 8
// NO evalúa:  (2 + 3) << 1 = 5 << 1 = 10
```

`3 << 1` significa desplazar los bits de 3 una posición a la izquierda,
lo que equivale a multiplicar por 2. El resultado es 6, no 5.

**Conclusión:** Cuando se mezclan operadores poco comunes como `<<` con aritméticos,
siempre usar paréntesis para hacer la intención explícita y evitar bugs difíciles de detectar.

---

## 2. Operadores de Cortocircuito

Con `&&`, si la primera condición es `false`, Java **no evalúa** la segunda.
El resultado ya es `false` de todas formas — no tiene sentido seguir evaluando.

**Ejemplo real:**
```java
// Si el empleado no está activo, Java ni revisa el puntaje
// Calcular el puntaje podría ser una operación costosa (consulta a base de datos)
if (empleado.isActivo() && empleado.getPuntaje() > 85) {
    // solo llega aquí si ambas condiciones son true
}
```

Lo mismo aplica para `||`: si la primera condición es `true`,
Java no evalúa la segunda porque el resultado ya es `true`.

**Beneficio:** Ahorra recursos de procesamiento y evita errores.
Por ejemplo, si el objeto es null, puedes verificarlo primero:
```java
// Si empleado es null, Java no intenta llamar .isActivo() — evita NullPointerException
if (empleado != null && empleado.isActivo()) { ... }
```

---

## 3. Estructura de Paquetes con Dominio Inverso

La industria usa el dominio de la empresa al revés como prefijo de paquetes
para garantizar **unicidad global**.

Los dominios web son únicos por definición — no pueden existir dos empresas
con el mismo dominio. Al invertirlo, se hereda esa unicidad:
```
riwi.com  →  com.riwi.proyecto
google.com →  com.google.utils
```

Si dos empresas crean un paquete llamado `utils` sin prefijo, hay conflicto.
Con dominio inverso, `com.riwi.utils` y `com.google.utils` son completamente distintos.

**Beneficio adicional:** La estructura de carpetas refleja el dominio,
lo que hace que cualquier desarrollador en el mundo pueda identificar
de qué empresa es ese código con solo ver el nombre del paquete.

---

## 4. Expresión Lógica Compleja — Orden de Evaluación
```java
(puntaje > 85 && edad < 30) || (idSede == 1 && !esActivo)
```

**Orden de evaluación:**
1. `!esActivo`              → ! evalúa primero (mayor precedencia)
2. `puntaje > 85`           → comparación
3. `edad < 30`              → comparación
4. `idSede == 1`            → comparación
5. `puntaje > 85 && edad < 30`      → && evalúa antes que ||
6. `idSede == 1 && !esActivo`       → && evalúa antes que ||
7. `resultado || resultado`          → || evalúa último

**Cortocircuito aplicado:**
Si `(puntaje > 85 && edad < 30)` es `true`, Java no evalúa
la segunda parte del `||` — el resultado ya es `true`.