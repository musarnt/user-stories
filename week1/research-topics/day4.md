# Research Topics - Day 4

## 1. Convenciones Maven: groupId y artifactId

El `pom.xml` es el archivo de configuración central de Maven. Sus dos etiquetas más importantes son:

**`<groupId>`**
Identifica la organización o empresa dueña del proyecto.
Sigue la convención de dominio inverso para garantizar unicidad global:
```xml
<groupId>com.riwi</groupId>
```

**`<artifactId>`**
Identifica el proyecto específico dentro de esa organización.
Es el "nombre" del proyecto — único dentro del groupId:
```xml
<artifactId>micro-payroll</artifactId>
```

Juntos forman la identidad única del proyecto en el ecosistema Maven.
Cuando otros proyectos quieran usar este como dependencia, lo referencian así:
```xml
<dependency>
    <groupId>com.riwi</groupId>
    <artifactId>micro-payroll</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

---

## 2. ZGC: Z Garbage Collector

El ZGC es un recolector de basura de ultra baja latencia introducido en Java 11
y estabilizado en Java 15. Está diseñado para sistemas que no pueden permitirse
pausas largas en la ejecución.

**Comparación con recolectores legacy:**

| Característica | CMS (Java 8 Legacy) | G1GC (Java 17 default) | ZGC (Java 17+ opcional) |
|---|---|---|---|
| Pausas | Stop-the-world impredecibles | Pausas cortas y predecibles | Sub-milisegundo |
| Heap máximo | Limitado | Hasta terabytes | Hasta 16 terabytes |
| Uso ideal | Aplicaciones pequeñas | Uso general | Sistemas críticos de alta carga |

**¿Cómo funciona ZGC?**
A diferencia del CMS que detenía toda la aplicación para limpiar memoria,
ZGC realiza casi todo el trabajo de recolección **mientras la aplicación sigue corriendo**.
Las pausas son tan cortas (menos de 1ms) que son imperceptibles para el usuario.

**¿Cuándo usarlo?**
En sistemas como Corporate Talent Hub bajo alta carga concurrente,
donde una pausa de varios segundos podría afectar la experiencia del usuario.

---

## 3. Monitoreo: VisualVM y jconsole (Opcional)

**jconsole:**
Herramienta incluida en el JDK. Permite monitorear en tiempo real:
- Uso de memoria del Heap y Stack
- Hilos activos
- Actividad del Garbage Collector
- Carga de CPU

Se ejecuta directamente desde la terminal:
```bash
jconsole
```

**VisualVM:**
Herramienta más avanzada y visual. Además de lo que hace jconsole, permite:
- Hacer profiling — identificar qué métodos consumen más tiempo
- Analizar dumps de memoria para encontrar memory leaks
- Monitorear aplicaciones remotas

Ambas herramientas son útiles en producción para diagnosticar problemas
de rendimiento sin detener la aplicación.

---

## 4. Stack vs Heap — Analogía Profesional

**Stack — La mesa de trabajo:**
Imagina la mesa de un empleado. Solo tiene los elementos que necesita
para la tarea actual — su computador, un cuaderno, un esfero.
Cuando termina la tarea, limpia la mesa completamente.
Es rápida de usar pero tiene espacio limitado.
Así funciona el Stack: guarda los valores primitivos y referencias
del método actual, y se limpia automáticamente cuando el método termina.

**Heap — La bodega de la empresa:**
Es el almacén central donde viven todos los objetos grandes y compartidos.
Cualquier empleado (método) puede ir a buscar lo que necesita.
Es más lenta de acceder que la mesa, pero tiene mucho más espacio.
El Garbage Collector es el encargado de limpiar la bodega —
revisa qué objetos ya nadie está usando y los elimina.

**En código:**
```java
int id = 1001;           // vive en el Stack — valor directo
String nombre = "Carlos"; // la referencia vive en el Stack,
                          // pero el objeto String vive en el Heap
```