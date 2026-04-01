# Temas de Investigación - Semana 2, Día 3: Matriz de Desempeño

---

## 1. Memoria de Arreglos: ¿Cómo se almacenan las matrices en el Heap de la JVM?

### Estructura en memoria
En Java, una matriz bidimensional NO es una sola estructura continua en memoria. Es un **arreglo de referencias** — cada fila es un objeto separado en el Heap, y el arreglo principal guarda referencias (punteros) hacia cada una de esas filas.
```
Stack:
scores --> [referencia]
               |
               v
Heap:
[ref fila 0] [ref fila 1] [ref fila 2]
      |             |             |
      v             v             v
[85.5, 90.0, 78.3] [92.1, 88.7, 95.4] [70.0, 75.5, 80.2]
```

### ¿Por qué importa?
Esto significa que en Java puedes tener matrices donde cada fila tiene un tamaño diferente (Jagged Arrays — ver tema 3). En lenguajes como C, una matriz bidimensional sí es un bloque continuo de memoria, lo que la hace más eficiente pero menos flexible.

### Stack vs Heap
- La variable `scores` vive en el **Stack** — solo guarda la referencia al objeto.
- Los objetos reales (las filas con los datos) viven en el **Heap** — memoria gestionada por el Garbage Collector.

---

## 2. Pérdida de Precisión: Integer Overflow al castear `long` a `int`

### ¿Qué es Integer Overflow?
`int` puede almacenar valores entre -2,147,483,648 y 2,147,483,647. Si intentas castear un `long` que supera ese rango a `int`, Java no lanza un error — simplemente trunca los bits y el resultado es un número completamente diferente e incorrecto.
```java
long gigante = 3_000_000_000L;  // 3 mil millones — fuera del rango de int
int resultado = (int) gigante;

System.out.println(resultado);  // Imprime: -1294967296 — incorrecto
```

### ¿Por qué ocurre?
Java toma los 32 bits menos significativos del `long` y los interpreta como un `int`. Si el bit más significativo de esos 32 es 1, el número se interpreta como negativo (complemento a dos).

### Caso histórico famoso
El bug del año 2038 en sistemas Unix ocurre exactamente por este motivo — un `int` de 32 bits que almacena segundos desde 1970 se desbordará el 19 de enero de 2038, produciendo una fecha incorrecta.

### Regla profesional
Antes de castear un `long` a `int`, siempre valida el rango:
```java
long valor = 3_000_000_000L;

if (valor >= Integer.MIN_VALUE && valor <= Integer.MAX_VALUE) {
    int resultado = (int) valor;
} else {
    System.out.println("Error: value out of int range");
}
```

---

## 3. Arreglos Irregulares (Jagged Arrays)

### ¿Qué son?
Un Jagged Array es una matriz donde cada fila puede tener un número diferente de columnas. En Java esto es posible precisamente porque cada fila es un objeto independiente en el Heap.
```java
// Matriz irregular — cada fila tiene diferente longitud
int[][] jagged = new int[3][];
jagged[0] = new int[]{1, 2, 3};       // Fila 0: 3 elementos
jagged[1] = new int[]{4, 5};          // Fila 1: 2 elementos
jagged[2] = new int[]{6, 7, 8, 9};   // Fila 2: 4 elementos
```

### Diferencia con matriz rectangular
Una matriz rectangular tiene el mismo número de columnas en todas las filas:
```java
// Matriz rectangular — todas las filas tienen 3 columnas
double[][] rectangular = new double[3][3];
```

### ¿Cuándo se usan los Jagged Arrays?
- Almacenar datos que tienen longitud variable por fila (historial de transacciones por mes, donde cada mes tiene diferente número de días).
- Optimizar memoria cuando algunas filas son mucho más cortas que otras — una matriz rectangular desperdiciaría espacio.

---

## 4. Casting Implícito (Widening) vs Casting Explícito (Narrowing)

### Widening — casting implícito
Ocurre cuando conviertes un tipo de menor capacidad a uno de mayor capacidad. Java lo hace automáticamente porque no hay riesgo de pérdida de datos.
```
byte -> short -> int -> long -> float -> double
```
```java
int entero = 100;
double decimal = entero; // Java convierte automáticamente — no se pierde información
```

**Analogía:** Es como pasar agua de un vaso pequeño a un balde grande. Todo cabe, no se derrama nada.

### Narrowing — casting explícito
Ocurre cuando conviertes un tipo de mayor capacidad a uno de menor capacidad. Java te obliga a escribirlo explícitamente porque hay riesgo de pérdida de datos.
```java
double promedio = 84.6;
int simplificado = (int) promedio; // Debes indicarlo explícitamente — se pierden los decimales
```

**Analogía:** Es como pasar agua de un balde grande a un vaso pequeño. Si el balde tiene más agua de la que cabe en el vaso, el exceso se derrama y se pierde — igual que los decimales al castear a `int`.

### Resumen

| Tipo | Dirección | Automático | Riesgo |
|---|---|---|---|
| Widening | pequeño -> grande | Si | Ninguno |
| Narrowing | grande -> pequeño | No | Pérdida de datos |