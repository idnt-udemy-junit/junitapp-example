# NOTES

## ¿Qué es JUnit5?
JUnit es un framework para escribir pruebas unitarias de nuestro código y ejecutarlas en la JVM. Utiliza programación funcionar y lambda, e incluye varios estilos diferentes de pruebas, configuraciones, anotaciones, ciclo de vida, etc.

---

## Arquitectura de JUnit 5
> **JUnit Plataform**  
> Es el componente que nos permite el lanzamiento de los frameworks de pruebas en la JVM y, entre otras cosas, también es el encargado de proporcionarnos la posibilidad de lanzar la plataforma desde línea de comandos y de los plugins para Gradle, Maven, etc...
  
> **JUnit Jupiter**  
> Es el componente que más utilizaremos a la hora de programar. Nos permite utilizar el nuevo modelo de programación para la escritura de los nuevos tests de JUnit 5.
  
> **JUnit Vintage**  
> Es el componente encargado de los tests de Junit 3 y 4, por si alguien los echa de menos.

---

## Test-Driven Development
TDD o Test-Driven Development (desarrollo dirigido por tests) es una práctica de programación que consiste en escribir primero las pruebas (generalmente unitarias), después escribir el código fuente que pase la prueba satisfactoriamente y, por último, refactorizar el código escrito.  

---

## Glosario

### + `@Test`
Permite marcar el método que servirá como Test de JUnit.

**Packaje:**  
`org.junit.jupiter.api.Test`

### + `Assertions`
Disponemos de los mismos Assert de JUnit 4, con algunas peculiaridades, como que ahora tanto la expresión como el mensaje del Assert, se pueden indicar mediante una lambda de Java 8.

Ahora el mensaje opcional de la aserción es el último parámetro, y al usar una lambda para indicar el mensaje, esta se evalúa cuando se va a mostrar (no cuando se ejecuta el assert), de esta manera se evita el tiempo de construir mensajes complejos innecesariamente.

**Packaje:**  
`org.junit.jupiter.api.Assertions.*`

**Packaje:**  
`org.junit.jupiter.api.Assertions`

### + `Assertions.assertNotNull()`
Comprueba que un parámetro pasado no sea null.

**Packaje:**  
`org.junit.jupiter.api.Assertions.assertNotNull`

### + `Assertions.assertEquals()`
Comprueba si 2 valores u objetos son iguales.

**Packaje:**  
`org.junit.jupiter.api.Assertions.assertEquals`

### + `Assertions.assertTrue()`
Comprueba que una expresión sea verdadera.

**Packaje:**  
`org.junit.jupiter.api.Assertions.assertTrue`

### + `Assertions.assertFalse()`
Comprueba que una expresión sea falsa.

**Packaje:**  
`org.junit.jupiter.api.Assertions.assertFalse`

### + `Assertions.assertThrows()`
Comprueba que se lanza una determinada excepción y la devuelve.

**Packaje:**  
`org.junit.jupiter.api.Assertions.assertThrows`

### + `Assertions.assertAll()`
Nos permite indicar grupos de `Asserts`, esto nos permite que se ejecuten todos, y que luego los fallos que se hayan producido también se reporten de forma conjunta.

**Packaje:**  
`org.junit.jupiter.api.Assertions.assertAll`

### + `Assertions.fail()`
Nos permite hacer que un test falle y lanzando la excepción `AssertFailedException`.

**Packaje:**  
`org.junit.jupiter.api.Assertions.fail`

### + `@DisplayName`
Nos permite establecer un String para que se visualice en vez del nombre del test (se pueden utilizar emoticonos).

**Packaje:**  
`org.junit.jupiter.api.DisplayName`

### + `@Disabled`
Un test marcado con esta anotación hace que no se ejecute.

**Packaje:**  
`org.junit.jupiter.api.Disabled`

### + `@BeforeEach`
Nos permite ejecutar un método antes de la ejecución de cada test.

**Packaje:**  
`org.junit.jupiter.api.BeforeEach`

### + `@AfterEach`
Nos permite ejecutar un método después de la ejecución de cada test.

**Packaje:**  
`org.junit.jupiter.api.AfterEach`

### + `@BeforeAll`
Nos permite ejecutar un método antes de la ejecución de todos los tests que se encuentran dentro de una clase.  
_Si el ciclo de vida esta configurado para que se cree una instancia por cada test, este método será estático._

**Packaje:**  
`org.junit.jupiter.api.BeforeAll`

### + `@AfterAll`
Nos permite ejecutar un método después de la ejecución de todos los tests que se encuentran dentro de una clase.  
_Si el ciclo de vida esta configurado para que se cree una instancia por cada test, este método será estático._

**Packaje:**  
`org.junit.jupiter.api.AfterAll`

### + `@TestInstance`
De forma predeterminada, tanto JUnit 4 como 5 crean una nueva instancia de la clase de prueba antes de ejecutar cada método de prueba. Esta anotación nos permite modificar el ciclo de vida de la clase de prueba.

Esta anotación tiene dos modos. Uno es `LifeCycle.PER_METHOD` (el predeterminado). El otro es `LifeCycle.PER_CLASS`, el cual nos permite pedirle a JUnit que cree solo una instancia de la clase de prueba y la reutilice entre pruebas.  Ej.:  
`@TestInstance(TestInstance.Lifecycle.PER_CLASS)`

**Packaje:**  
`org.junit.jupiter.api.TestInstance`

### + `@EnabledOnOs`
Esta anotación nos permite habilitar tests según el sistema operativo que se esta utilizando. Ej.:  
`@EnabledOnOs(OS.WINDOWS)`
`@EnabledOnOs({OS.LINUX, OS.MAC})`

**Packaje:**  
`org.junit.jupiter.api.condition.EnabledOnOs`  
`org.junit.jupiter.api.condition.OS`

### + `@DisabledOnOs`
Esta anotación nos permite desabilitar tests según el sistema operativo que se esta utilizando. Ej.:  
`@DisabledOnOs(OS.WINDOWS)`
`@DisabledOnOs({OS.LINUX, OS.MAC})`

**Packaje:**  
`org.junit.jupiter.api.condition.DisabledOnOs`  
`org.junit.jupiter.api.condition.OS`

### + `@EnabledOnJre`
Esta anotación nos permite habilitar tests según la versión de Java que se esta utilizando. Ej.:  
`@EnabledOnJre(JRE.JAVA_16)`
`@EnabledOnJre({JRE.JAVA_8, JRE.JAVA_16})`

**Packaje:**  
`org.junit.jupiter.api.condition.EnabledOnJre`  
`org.junit.jupiter.api.condition.JRE`

### + `@DisabledOnJre`
Esta anotación nos permite deshabilitar tests según la versión de Java que se esta utilizando. Ej.:    
`@DisabledOnJre(JRE.JAVA_16)`
`@DisabledOnJre({JRE.JAVA_8, JRE.JAVA_16})`

### + `@EnabledForJreRange`
Esta anotación nos permite habilitar tests según la versión de Java que se esta utilizando, la cual tiene que coincidir en un rango de versiones. Ej.:  
`@EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_16)`

**Packaje:**  
`org.junit.jupiter.api.condition.EnabledForJreRange`  
`org.junit.jupiter.api.condition.JRE`

### + `@DisabledForJreRange`
Esta anotación nos permite deshabilitar tests según la versión de Java que se esta utilizando, la cual tiene que coincidir en un rango de versiones. Ej.:  
`@DisabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_16)`

**Packaje:**  
`org.junit.jupiter.api.condition.DisabledForJreRange`  
`org.junit.jupiter.api.condition.JRE`

### + `@EnabledIfSystemProperty` y `@EnabledIfSystemProperties`
Esta anotación nos permite habilitar tests según una o unas determinadas propiedades del sistema. Ej.:  
`@EnabledIfSystemProperty(named="java.version", matches = "16.0.2")`
```java
@EnabledIfSystemProperties({
    @EnabledIfSystemProperty(named="java.version", matches = "16.*"),
    @EnabledIfSystemProperty(named="java.vm.vendor", matches = "Oracle Corporation"),
    @EnabledIfSystemProperty(named="sun.arch.data.model", matches = "64")
})
```
**Packaje:**  
`org.junit.jupiter.api.condition.EnabledIfSystemProperty`  
`org.junit.jupiter.api.condition.EnabledIfSystemProperties`

### + `@DisabledIfSystemProperty` y `@DisabledIfSystemProperties`
Esta anotación nos permite deshabilitar tests según una o unas determinadas propiedades del sistema. Ej.:  
`@EnabledIfSystemProperty(named="java.version", matches = "16.0.2")`
```java
@DisabledIfSystemProperties({
    @DisabledIfSystemProperty(named="java.version", matches = "16.*"),
    @DisabledIfSystemProperty(named="java.vm.vendor", matches = "Oracle Corporation"),
    @DisabledIfSystemProperty(named="sun.arch.data.model", matches = "64")
})
```

**Packaje:**  
`org.junit.jupiter.api.condition.DisabledIfSystemProperty`  
`org.junit.jupiter.api.condition.DisabledIfSystemProperties`

### + `@EnabledIfEnvironmentVariable` y `@EnabledIfEnvironmentVariables`
Esta anotación nos permite habilitar tests según una o unas determinadas variables de sistema. Ej.:  
`@EnabledIfSystemProperty(named="java.version", matches = "16.0.2")`
```java
@EnabledIfEnvironmentVariables({
    @EnabledIfEnvironmentVariable(named="java.version", matches = "16.*"),
    @EnabledIfEnvironmentVariable(named="java.vm.vendor", matches = "Oracle Corporation"),
    @EnabledIfEnvironmentVariable(named="sun.arch.data.model", matches = "64")
})
```

**Packaje:**  
`org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable`  
`org.junit.jupiter.api.condition.EnabledIfEnvironmentVariables`


### + `@DisabledIfEnvironmentVariable` y `@DisabledIfEnvironmentVariables`
Esta anotación nos permite deshabilitar tests según una o unas determinadas variables de sistema. Ej.:  
`@DisabledIfEnvironmentVariable(named="java.version", matches = "16.0.2")`
```java
@DisabledIfEnvironmentVariables({
    @DisabledIfEnvironmentVariable(named="java.version", matches = "16.*"),
    @DisabledIfEnvironmentVariable(named="java.vm.vendor", matches = "Oracle Corporation"),
    @DisabledIfEnvironmentVariable(named="sun.arch.data.model", matches = "64")
})
```
**Packaje:**  
`org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable`  
`org.junit.jupiter.api.condition.DisabledIfEnvironmentVariables`

### + `Assumptions`
Son métodos estáticos para admitir la ejecución de un test condicional basado en suposiciones. Una suposición fallida dará como resultado la anulación de un test. Los supuestos se utilizan normalmente cuando no tiene sentido continuar con la ejecución de un método de prueba determinado.

**Packaje:**  
`org.junit.jupiter.api.Assumptions.*`

### + `Assumptions.assumeTrue()`
Comprueba que una expresión sea verdadera, y si lo es deshabilita el test.

**Packaje:**  
`org.junit.jupiter.api.Assumptions.assumeTrue`

### + `Assumptions.assumeFalse()`
Comprueba que una expresión sea falsa, y si lo es deshabilita el test.

**Packaje:**  
`org.junit.jupiter.api.Assumptions.assumeFalse`

### + `Assumptions.assumingThat()`
Comprueba que una expresión sea verdarea, y si lo es todos los asserts que contenga los ejecuta, de lo contrario no. Ej.:

`assumingThat(IS_DEV, () -> assertEquals(0, ACTUAL));`

**Packaje:**  
`org.junit.jupiter.api.Assumptions.assumingThat`

### + `@Nested`
Esta anotación permite marcar una clase anidada con varios tests dentro, de esta manera JUnit agrupará las pruebas por cada clase anidada. A esta clase anidada se le puede añadir la anotación `@DisplayName`.  
Solo sirve para organizar las salidas.

**Packaje:**  
`org.junit.jupiter.api.Nested`

### + `@RepeatedTest`
Esta anotación permite marcar una test para que ejecute un test x número de veces. Se le puede cambiar el nombre a la repetición, y también se pueden obtener para establecer en el nombre los siguientes atributos:
- `{displayName}` => String que contiene la anotación `@DisplayName`
- `{currentRepetition}` => El número de la repetición actual.
- `{totalRepetitions}` => El número de las repeticiones totales.

También se puede obtener por inyección de dependencias a través de la firma del método del test el objeto `RepetitionInfo`, el cual contiene la información respecto a la repetición del test. Ej.:

`@RepeatedTest(12)`  
`@RepeatedTest(value=12, name="{displayName} => Test [{currentRepetition}] // Total [{totalRepetitions}]")`
```java
void testRepeated(RepetitionInfo info) {
    if( info.getCurrentRepetition() == 5){
        System.out.println("Repetition: " + info.getCurrentRepetition());
    }
}
```

**Packaje:**  
`org.junit.jupiter.api.RepeatedTest`

### + `@ParameterizedTest`
Para poder utilizar esta anotación tendremos que añadir la siguiente dependencia:
```
<groupId>org.junit.jupiter</groupId>
<artifactId>junit-jupiter-params</artifactId>
```
Esta anotación permite marcar una test para que ejecute un test pasándole 1 o varios argumentos. Estos argumentos se pueden establecer de diferentes formas con las siguientes anotaciones:
- `@ValueSource`
- `@CsvSource`
- `@CsvFileSource`
- `@MethodSource`


Se le puede cambiar el nombre de los tests resultantes, y también se pueden obtener para establecer en dicho nombre los siguientes atributos:
- `{displayName}` => String que contiene la anotación `@DisplayName`
- `{index}` => El número del argumento actual.
- `{argumentsWithNames}` => El valor del argumento actual.

Ej.:

`@ParameterizedTest`  
`@ParameterizedTest(name="{displayName} => Test [{index}][{argumentsWithNames}]")`

```java
@ParameterizedTest
@ValueSource(strings = {"100", "200", "300", "500", "800", "1300", "2100", "3400", "4500"})
void test(final String quantity) {
```

```java
@ParameterizedTest
@CsvSource({"1,100", "2,200", "3,300", "4,500", "5,800", "6,1300", "7,2100", "8,3400", "9,4500"})
void test(final String index, final String value) {
```

```java
@ParameterizedTest
@CsvSource({"5000,100,4900", "2000,200,1800", "1000,300,700", "500,500,0", "1200,800,400", "1500,1300,200", "2500,2100,400", "3500,3400,100", "10000,4500,5500"})
void test(final String balance, final String quantity, final String expected) {
```

```java
@ParameterizedTest
@CsvFileSource(resources="/data.csv")
void test(final String quantity){
```

```java
private static List<String> getQuantityList(){
    return Arrays.asList("100", "200", "300", "500", "800", "1300", "2100", "3400", "4500");
}
        
@ParameterizedTest
@MethodSource("getQuantityList")
void test(final String quantity){
```

**Packaje:**  
`import org.junit.jupiter.params.ParameterizedTest`  
`import org.junit.jupiter.params.provider.ValueSource;`  
`import org.junit.jupiter.params.provider.CsvSource;`  
`import org.junit.jupiter.params.provider.CsvFileSource;`  
`import org.junit.jupiter.params.provider.MethodSource;`

### + `@Tag`
Esta anotación permite marcar una test con una etiqueta, esto nos permitirá ejecutar los tests por etiquetas (por grupos).

**Packaje:**  
`org.junit.jupiter.api.Tag`

### + `TestInfo`
Esta clase la podemos obtener a través de la inyección de dependencias de JUnit en nuestros tests. Esta clase trae la información del test (displayName, tags, method, etc).

**Packaje:**  
`org.junit.jupiter.api.TestInfo`

### + `TestReporter`
Esta clase la podemos obtener a través de la inyección de dependencias de JUnit en nuestros tests. Esta clase nos permite crear entradas en el LOG de JUnit.

**Packaje:**  
`org.junit.jupiter.api.TestReporter`

### + `@Timeout`
Esta anotación nos permite marcar un test para hacer que un test falle si su ejecución sobrepasa un determinado tiempo. Ej.:  
`@Timeout(5)`  
`@Timeout(value = 5, unit = TimeUnit.SECONDS )`

**Packaje:**  
`org.junit.jupiter.api.Timeout`  
`java.util.concurrent.TimeUnit`

### + `Assertions.assertTimeout()`
Comprueba si una ejecución sobrepasa un determinado tiempo. Ej.:  
`assertTimeout(Duration.ofSeconds(5), () -> TimeUnit.MILLISECONDS.sleep(5000) );`

**Packaje:**  
`org.junit.jupiter.api.Assertions.assertTimeout`  
`java.util.concurrent.TimeUnit`

### + Surefire Maven Plugin
Es un plugin de maven que nos permite ejecutar los test a través de los comandos de MAVEN.
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.0.0-M5</version>
</plugin>
```  

Para que sólo ejecute los tests con unas determinadas etiquetas hay que añadir la siguiente configuración:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.0.0-M5</version>
    <configuration>
        <groups>tagName1,tagName1,tagName1</groups>
    </configuration>
</plugin>
```