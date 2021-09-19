# Glosario

## `@Test`
Permite marcar el método que servirá como Test de JUnit.  

`org.junit.jupiter.api.Test`

## `Assertions`
Disponemos de los mismos Assert de JUnit 4, con algunas peculiaridades, como que ahora tanto la expresión como el mensaje del Assert, se pueden indicar mediante una lambda de Java 8.  

Ahora el mensaje opcional de la aserción es el último parámetro, y al usar una lambda para indicar el mensaje, esta se evalúa cuando se va a mostrar (no cuando se ejecuta el assert), de esta manera se evita el tiempo de construir mensajes complejos innecesariamente.

`org.junit.jupiter.api.Assertions`
  
## `Assertions.assertNotNull()`
Comprueba que un parámetro pasado no sea null.

`org.junit.jupiter.api.Assertions.assertNotNull`

## `Assertions.assertEquals()`
Comprueba si 2 valores u objetos son iguales.

`org.junit.jupiter.api.Assertions.assertEquals`

## `Assertions.assertTrue()`
Comprueba que una expresión sea verdadera.

`org.junit.jupiter.api.Assertions.assertTrue`

## `Assertions.assertFalse()`
Comprueba que una expresión sea falsa.

`org.junit.jupiter.api.Assertions.assertFalse`

## `Assertions.assertThrows()`
Comprueba que se lanza una determinada excepción y la devuelve.

`org.junit.jupiter.api.Assertions.assertThrows`  

## `Assertions.assertAll()`
Nos permite indicar grupos de `Asserts`, esto nos permite que se ejecuten todos, y que luego los fallos que se hayan producido también se reporten de forma conjunta.  

`org.junit.jupiter.api.Assertions.assertAll`

## `Assertions.fail()`
Nos permite hacer que un test falle y lanzando la excepción `AssertFailedException`.  

`org.junit.jupiter.api.Assertions.fail`  

## `@DisplayName`
Nos permite establecer un String para que se visualice en vez del nombre del test (se pueden utilizar emoticonos).  

`org.junit.jupiter.api.DisplayName`

## `@Disabled`
Un test marcado con esta anotación hace que no se ejecute.  

`org.junit.jupiter.api.Disabled`  

## `@BeforeEach`
Nos permite ejecutar un método antes de la ejecución de cada test.  

`org.junit.jupiter.api.BeforeEach`

## `@AfterEach`
Nos permite ejecutar un método después de la ejecución de cada test.  

`org.junit.jupiter.api.AfterEach`  

## `@BeforeAll`
Nos permite ejecutar un método antes de la ejecución de todos los tests que se encuentran dentro de una clase.  
_Si el ciclo de vida esta configurado para que se cree una instancia por cada test, este método será estático._

`org.junit.jupiter.api.BeforeAll`

## `@AfterAll`
Nos permite ejecutar un método después de la ejecución de todos los tests que se encuentran dentro de una clase.  
_Si el ciclo de vida esta configurado para que se cree una instancia por cada test, este método será estático._  

`org.junit.jupiter.api.AfterAll`  

## `@TestInstance`
De forma predeterminada, tanto JUnit 4 como 5 crean una nueva instancia de la clase de prueba antes de ejecutar cada método de prueba. Esta anotación nos permite modificar el ciclo de vida de la clase de prueba.  

Esta anotación tiene dos modos. Uno es `LifeCycle.PER_METHOD` (el predeterminado). El otro es `LifeCycle.PER_CLASS`, el cual nos permite pedirle a JUnit que cree solo una instancia de la clase de prueba y la reutilice entre pruebas.  

`org.junit.jupiter.api.TestInstance`  