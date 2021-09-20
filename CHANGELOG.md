# CHANGELOG
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## UNRELEASED

### ADDED  
- **`#02` - JUnit5 // `#020` - Ejecuciones de test condicionales con @EnabledEnvironmentVariable**  
  - Several conditional tests have been created with the following annotations: `@EnabledIfEnvironmentVariable `, `@EnabledIfEnvironmentVariables`, `@DisabledIfEnvironmentVariable ` and `@DisabledIfEnvironmentVariables`.
  - The `002-glosario` file has been updated.  
  

- **`#02` - JUnit5 // `#019` - Test condicionales @EnabledOnOs, @EnabledOnJre, @EnabledIfSystemProperty etc**  
  - Several conditional tests have been created with the following annotations: `@EnabledOnOs`, `@DisabledOnOs`, `@EnabledOnJre`, `@DisabledOnJre`, `@EnabledForJreRange`, `@DisabledForJreRange`, `@EnabledIfSystemProperty`, `@DisabledIfSystemProperty`, `@EnabledIfSystemProperties` and `@DisabledIfSystemProperties`.  
  - The `002-glosario` file has been updated.  
  

- **`#02` - JUnit5 // `#018` - Ciclo de vida anotaciones @AfterAll y @BeforeAll**
  - The methods to be executed before and after all tests in the `AccountTest` class have been created.
  - The `002-glosario` file has been updated.
  

- **`#02` - JUnit5 // `#017` - Ciclo de vida anotaciones @AfterEach y @BeforeEach**
  - The methods to be executed before and after each test have been established in the `AccountTest` class.  
  - The `002-glosario` file has been updated.
  

- **Notes have been created with everything that has been covered in the course**  
  

- **`#02` - JUnit5 // `#016` - Usando anotaciones @DisplayName y @Disabled**
Tests of the `AccountTest` class have been given more specific names using the `@DisplayName` annotation.
  

- **`#02` - JUnit5 // `#015` - Agregando mensajes de falla en los métodos assertions**
  - _An errors message have been added to the assert methods in `AccountTest` class, some with lambdas and some without lambdas._
  

- **`#02` - JUnit5 // `#014` - Usando el método assertAll**
  - _The use of the `assertAll` method has been added to the test `testRelationBankAccounts` of the `AccountTest` class to test it and see how it's used._


- **`#02` - JUnit5 // `#013` - Probando y afirmando las relaciones entre Banco y Cuenta**
  - _The `Bank` class has been edited to avoid throwing the `StackOverflowError` exception due to cyclic dependencies._
  - _A test has been added to check the relationships between the `Bank` class and the `Account` class, and between the `Account` class and the `Bank` class._  
  

- **`#02` - JUnit5 // `#012` - Añadiendo la clase Banco y la relación con las cuentas**
  - _The `Bank` class has been added._
  - _The relationship of the `Bank` class with the `Account` class and the `Account` class with the `Bank` class has been established._
  - _The method for transferring money between 2 accounts has been created._
  - _The test has been created to test the method created in the class `Bank` in the class `AccountTest`._  
  

- **`#02` - JUnit5 // `#011` - Probando y afirmando excepciones con assertThrows en JUnit 5**
  - _A new exception has been created (`NoEnoughtMoneyException`)._
  - _The `debit` method of the `Account` class has been updated to throw the `NoEnoughtMoneyException` exception when an attempt is made to subtract more money than the account balance._
  - _A new test has been added to the `AccountTest` class to test the new functionality added to the `debit` method of the `Account` class._  
  

- **`#02` - JUnit5 // `#010` - TDD para debito y crédito**
  - _The tests to test the `debit` and `credit` methods in `Account` class have been created._
  - _The `debit` and `credit` methods have been created and implemented in `Account` class._
  

- **`#02` - JUnit5 // `#009` - Test Driven Development TDD con JUnit**
  - _A test has been created in the `AccountTest` class to explain how assertEquals works when a class has the `equals` method implemented and when it doesn't._  
  

- **`#02` - JUnit5 // `#008` - Escribiendo test para el saldo**
  - _A test ins `AccountTest` class has been created to test the `getBalance` method of the `Account` class._
  

- **`#02` - JUnit5 // `#007` - Escribiendo y ejecutando primeras pruebas unitarias con Assertions**
  - _A constructor with all arguments has been added to the `Account` class._
  - _The `person` property of the `Account` class has been renamed to `name`._
  - _A test class has been created for the `Account` class._
  - _A test has been created to test the `getName` method of the `Account` class._
    

- **`#02` - JUnit5 // `#006` - Creando y configurando el proyecto con JUnit 5**
  - _The project has been created._  
  - _`Junit5` and `Lombock` dependencies has been added to the pom file._
  - _The POJO `Account` has been created._
  