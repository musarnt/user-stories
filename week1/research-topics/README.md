# Research Topics - Week 1

## 1. Exploración del Entorno: Carpeta `/bin` del JDK

**Ubicación en este equipo:**
`C:\Program Files\Eclipse Adoptium\jdk-21.0.10.7-hotspot\bin`

**Herramientas principales encontradas:**

| Herramienta | Descripción |
|---|---|
| `java.exe` | Ejecuta el bytecode (.class) en la JVM |
| `javac.exe` | Compila el código fuente (.java) a bytecode |
| `javap.exe` | Desensambla el bytecode a instrucciones legibles |
| `jar.exe` | Empaqueta múltiples .class en un archivo .jar distribuible |
| `javaw.exe` | Igual que java.exe pero sin ventana de consola, para apps de escritorio |
| `jdb.exe` | Debugger para encontrar errores en tiempo de ejecución |
| `jshell.exe` | Consola interactiva para probar código Java sin crear archivos |

---

## 2. JIT Compiler (Just-In-Time)

El JIT Compiler es un componente de la JVM que optimiza la ejecución en tiempo real.

Cuando un programa Java corre, la JVM detecta qué partes del código se ejecutan repetidamente — a ese código se le llama **"código caliente"**. El JIT toma ese código caliente y lo compila directamente a código nativo de la máquina (no bytecode), lo cachea y lo ejecuta sin pasar por la JVM en cada llamada.

**Diferencia con `javac`:**
- `javac` compila antes de ejecutar — transforma `.java` a bytecode.
- El JIT compila durante la ejecución — transforma bytecode a código nativo, solo donde hay repetición.

El resultado es que los programas Java se vuelven más rápidos a medida que corren, porque el JIT va optimizando progresivamente.

---

## 3. Independencia de Plataforma: "Write Once, Run Anywhere" (WORA)

WORA significa que un programa Java compilado puede ejecutarse en cualquier sistema operativo sin modificaciones.

Esto es posible gracias a la JVM. Cuando compilas con `javac`, el resultado no es código específico de Windows, Mac o Linux — es bytecode, un formato intermedio neutral. La JVM de cada sistema operativo sabe cómo leer ese bytecode y traducirlo a instrucciones nativas de esa máquina.

**El flujo:**
```
Arquitectura.java → javac → Arquitectura.class → JVM (Windows/Mac/Linux) → ejecución
```

El desarrollador distribuye el `.class`, no el `.java`. Cualquier máquina con JVM instalada lo ejecuta — sin recompilar, sin cambios.

---

## 4. Consulta: ¿Por qué el Bytecode es más seguro que el código binario nativo?

El bytecode es más seguro para distribuir aplicaciones por varias razones:

- **No expone el código fuente:** el `.class` es difícil de revertir a código Java legible, a diferencia del binario nativo que puede desensamblarse con más facilidad.
- **La JVM actúa como capa de seguridad:** antes de ejecutar el bytecode, la JVM lo verifica — comprueba que no haya instrucciones maliciosas o violaciones de memoria.
- **No accede directamente al hardware:** el bytecode no puede manipular memoria o recursos del sistema directamente. Todo pasa por la JVM, que controla qué está permitido.
- **El binario nativo es específico de la plataforma:** un `.exe` de Windows puede contener instrucciones que exploten vulnerabilidades del sistema operativo. El bytecode no tiene esa capacidad — está sandboxeado dentro de la JVM.