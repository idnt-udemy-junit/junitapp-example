package org.idnt.udemy.junitapp.example.model.test;

import org.idnt.udemy.junitapp.example.exception.NotEnoughMoneyException;
import org.idnt.udemy.junitapp.example.model.Account;
import org.idnt.udemy.junitapp.example.model.Bank;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class AccountTest {
    private static final String ACCOUNT_NAME =  "Personal Account";
    private static final BigDecimal ACCOUNT_BALANCE =  new BigDecimal("5000.00");

//    @RepeatedTest(12)
    @RepeatedTest(value=12, name="{displayName} => Test [{currentRepetition}] // Total [{totalRepetitions}]")
    @DisplayName("Repeated Test")
    void testRepeated(RepetitionInfo info) {
        if( info.getCurrentRepetition() == 5){
            System.out.println(String.format("This is repetition %s", info.getCurrentRepetition()));
        }

        final int ACTUAL = new Random().nextInt(11);

        assertNotNull(ACTUAL, () -> "The result cant' be void");
        assertTrue(ACTUAL >= 0 && ACTUAL <= 10,
                () -> String.format("The random number must be between 0 and 10: NUMBER => %s", ACTUAL));
    }

    @Nested
    @DisplayName("Testing the basic attributes and methods of \"Account\" class")
    class AccountPOJOTest{
        private Account account;

        @BeforeEach
        void beforeTest(){
            System.out.println("RESTORE ACCOUNT");
            this.account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        }

        @Test
        @DisplayName("Test the current account name")
        void testAccountName() {
            final String EXPECTED = ACCOUNT_NAME;
            final String ACTUAL = this.account.getName();

            /**
             * ERROR MESSAGES WITHOUT LAMBDAS
             *
             * Less efficient than using lambdas to set the error message since in this way the message is always
             * instantiated even if it is not used.
             */
            assertNotNull(ACTUAL, "The account name cant' be void");

            //Checks if the account name corresponds to the name that has been established.
            assertEquals(EXPECTED, ACTUAL, "Account name isn't as expected");
            assertTrue(ACTUAL.equals(EXPECTED), String.format("Account name isn't as expected: EXPECTED => %s // ACTUAL => %s", EXPECTED, ACTUAL));
        }

        @Test
        @DisplayName("Test the current account balance")
        void testAccountBalance() {
            final BigDecimal ACTUAL = this.account.getBalance();

            /**
             * ERROR MESSAGES WHIT LAMBDAS
             *
             * More efficient than not using lambdas to set the error message, since in this way the message is only
             * instantiated if it is used.
             */
            assertNotNull(ACTUAL, () -> "The account balance cant' be void");

            //Checks if the account balance corresponds to the balance that has been established
            assertTrue(ACTUAL.compareTo(ACCOUNT_BALANCE) == 0, () -> "Account balance isn't as expected");
            assertFalse(ACTUAL.compareTo(BigDecimal.ZERO) == 0,
                    () -> String.format("Account balance isn't as expected: EXPECTED => %s // ACTUAL => %s", BigDecimal.ZERO, ACTUAL));
        }

        @Test
        @DisplayName("Test the \"equals\" method of \"Account\" class")
        void testEqualsMethodOfAccount() {
            Account account1 = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
            Account account2 = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);

            //If the equals method has been implemented in Account class, assertNotEquals return false, else return true.
//        assertNotEquals(account1, account2,
//                () -> String.format("Account 1 is the same as account 2: EXPECTED => %s // ACTUAL => %s",
//                        account1, account2));

            //If the equals method has been implemented in Account class, asserEquals return true, else return false.
            assertEquals(account1, account2,
                    () -> String.format("Account 1 is not the same as account 2: EXPECTED => %s // ACTUAL => %s",
                            account1, account2));
        }
    }

    @Nested
    @DisplayName("Testing the actions of \"Account\" class")
    class AccountActionsTest{
        private Account account;

        @BeforeEach
        void beforeTest(){
            System.out.println("RESTORE ACCOUNT");
            this.account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        }

        /**
         * <p>TDD (Test Driven Development)</p>
         * <p>First develop the tests, then develop the methods.</p>
         */
        @Test
        @DisplayName("Test the current account debit operation")
        void testAccountDebit() {
            this.account.debit(new BigDecimal("1000"));
            final BigDecimal ACTUAL = this.account.getBalance();

            assertNotNull(ACTUAL, () -> "The account balance cant' be void");

            //Checks if 1000 has been subtracted from the balance of the account.
            assertEquals("4000.00", ACTUAL.toPlainString(),
                    () -> String.format("The account balance must be 1000 less than before: BEFORE: %s", ACCOUNT_BALANCE));
            assertEquals(0, ACTUAL.compareTo(new BigDecimal("4000")),
                    () -> String.format("The account balance must be 1000 less than before: BEFORE: %s", ACCOUNT_BALANCE));
        }

        @Test
        @DisplayName("Test that it isn't possible to withdraw more money than is available in the current account")
        void testNotEnoughMoneyException() {
            final String EXPECTED_MSG = "No Enought Money !";
            final BigDecimal MONEY_TO_BE_SUBSTRACT = new BigDecimal("6000");

            //Checks if the "NoEnoughMoneyException" exception was thrown when subtracting an amount greater than the account balance.
            Exception exception = assertThrows(NotEnoughMoneyException.class,
                    () -> this.account.debit(MONEY_TO_BE_SUBSTRACT),
                    () -> String.format("The balance can't be negative. Subtracted %s from %s", MONEY_TO_BE_SUBSTRACT, ACCOUNT_BALANCE));

            //Checks if the message of the "NoEnoughMoneyException" exception is equal to "No Enought Money !".
            final String ACTUAL_MSG = exception.getMessage();
            assertEquals(EXPECTED_MSG, ACTUAL_MSG,
                    () -> String.format("The exception message doesn't correspond to the expected: EXPECTED => %s // ACTUAL => %s",
                            EXPECTED_MSG, ACTUAL_MSG));
        }

        /**
         * <p>TDD (Test Driven Development)</p>
         * <p>First develop the tests, then develop the methods.</p>
         */
        @Test
        @DisplayName("Test the current account credit operation")
        void testAccountCredit() {
            account.credit(new BigDecimal("1000"));
            final BigDecimal ACTUAL = this.account.getBalance();

            assertNotNull(ACTUAL, () -> "The account balance cant' be void");

            //Checks if 1000 has been added to the balance of the account.
            assertEquals("6000.00", ACTUAL.toPlainString(),
                    () -> String.format("The account balance must be 1000 more than before: BEFORE: %s", ACCOUNT_BALANCE));
            assertEquals(0, ACTUAL.compareTo(new BigDecimal("6000")),
                    () -> String.format("The account balance must be 1000 more than before: BEFORE: %s", ACCOUNT_BALANCE));
        }
    }

    @Nested
    @DisplayName("Testing the \"Bank\" class")
    class BankTest{
        @Test
        @DisplayName("Test the money transfer operation between 2 accounts")
        void testTransferMoneyAccounts() {
            final Bank BANK = new Bank("TROBO");
            final Account SOURCE = new Account("Cuenta de Ángel", ACCOUNT_BALANCE, BANK);
            final Account TARGET = new Account("Cuenta de Patricia", ACCOUNT_BALANCE, BANK);
            BANK.addAccount(SOURCE);
            BANK.addAccount(TARGET);

            BANK.transfer(SOURCE, TARGET, new BigDecimal("1000"));

            //Checks if 1000 has been subtracted from the balance of the source account.
            assertEquals(0, SOURCE.getBalance().compareTo(new BigDecimal("4000")),
                    () -> String.format("The origin account balance must be 1000 less than before: BEFORE: %s", ACCOUNT_BALANCE));

            //Checks if 1000 has been added to the balance of the target account.
            assertEquals(0, TARGET.getBalance().compareTo(new BigDecimal("6000")),
                    () -> String.format("The target account balance must be 1000 more than before: BEFORE: %s", ACCOUNT_BALANCE));
        }

        @Test
        @DisplayName("Test the relationship between the bank and the accounts")
        void testRelationBankAccounts() {
            final Bank BANK = new Bank("TROBO");
            final Account ACCOUNT_1 = new Account("Cuenta de Ángel", ACCOUNT_BALANCE, BANK);
            final Account ACCOUNT_2 = new Account("Cuenta de Patricia", ACCOUNT_BALANCE, BANK);
            BANK.addAccount(ACCOUNT_1);
            BANK.addAccount(ACCOUNT_2);

            //Checks if the accounts of "Bank" class property is null
            assertNotNull(BANK.getAccounts(), () -> "The list accounts of Bank cant' be void");

            /* Check that each block of "asserts" in each lambda function of the "assertAll" method are correct,
             * otherwise display the first error of each block.
             */
            assertAll(() -> {
                //Checks if account number 1 has the Bank class.
                assertEquals(BANK, ACCOUNT_1.getBank(), () -> "Not the bank that was expected in account 1");
            }, () -> {
                //Checks if account number 2 has the Bank class.
                assertEquals(BANK, ACCOUNT_2.getBank(), () -> "Not the bank that was expected in account 2");
            }, () -> {
                //Checks if the Bank has 2 accounts.
                assertEquals(2, BANK.getAccounts().size(), () -> "Not the number of accounts expected in the list of accounts held by the bank");
            }, () -> {
                //Checks if account number 1 is in the class "Bank".
                assertTrue(BANK.getAccounts().stream().anyMatch(account -> account.equals(ACCOUNT_1)),
                        () -> String.format("Account 1 was expected in the bank's list of accounts: EXPECTED => %s", ACCOUNT_1));
                assertEquals(ACCOUNT_1, BANK.getAccounts().stream().filter(account -> account.equals(ACCOUNT_1)).findFirst().get(),
                        () -> String.format("Account 1 was expected in the bank's list of accounts: EXPECTED => %s", ACCOUNT_1));
            }, () -> {
                //Checks if account number 2 is in the class "Bank".
                assertTrue(BANK.getAccounts().stream().anyMatch(account -> account.equals(ACCOUNT_2)),
                        () -> String.format("Account 2 was expected in the bank's list of accounts: EXPECTED => %s", ACCOUNT_2));
                assertEquals(ACCOUNT_2, BANK.getAccounts().stream().filter(account -> account.equals(ACCOUNT_2)).findFirst().get(),
                        () -> String.format("Account 2 was expected in the bank's list of accounts: EXPECTED => %s", ACCOUNT_2));
            });
        }
    }

    @Nested
    @DisplayName("Testing depending on the operating system")
    class OperativeSystemTest{
        @Test
        @EnabledOnOs(OS.WINDOWS)
        @DisplayName("Test enabled for Windows")
        void testEnableWindows() {

        }

        @Test
        @EnabledOnOs({OS.LINUX, OS.MAC})
        @DisplayName("Test enabled for Linux and MAC")
        void testEnableLinuxAndMAC() {

        }

        @Test
        @DisabledOnOs(OS.WINDOWS)
        @DisplayName("Test disabled for Windows")
        void testDisableWindows() {

        }
    }

    @Nested
    @DisplayName("Testing depending on the Java version")
    class JavaVersionTest{
        @Test
        @EnabledOnJre(JRE.JAVA_8)
        @DisplayName("Test enabled for Java 8")
        void testEnableJava8() {

        }

        @Test
        @DisabledOnJre({JRE.JAVA_8, JRE.JAVA_16})
        @DisplayName("Test disabled for Java 8 and 16")
        void testDisableJava8And16() {

        }

        @Test
        @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_16)
        @DisplayName("Test enabled for Java between 8 and 16")
        void testEnableFromJava8ToJava16() {

        }

        @Test
        @DisabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_16)
        @DisplayName("Test disabled for Java between 8 and 16")
        void testDisableFromJava8ToJava16() {

        }

        @Test
        @DisabledOnJre(JRE.JAVA_15)
        @DisplayName("Test disabled for Java 15")
        void testEnableJava15() {

        }
    }

    @Nested
    @DisplayName("Testing depending on the System Properties")
    class SystemPropertiesTest{
        @Test
        @DisplayName("Test to print the System Properties")
        void printSystemProperties() {
            Properties properties = System.getProperties();
            properties.forEach((key, value) -> System.out.println(String.format("%s => %s", key, value)));
        }

        @Test
        @EnabledIfSystemProperty(named="java.version", matches = "16.0.2")
        @DisplayName("Test enabled for Java 16.0.2 (System Properties)")
        void testEnableSystemPropertiesJavaVersion() {
        }

        @Test
        @EnabledIfSystemProperty(named="ENV", matches = "dev")
        @DisplayName("Test enabled for enviroment \"dev\" (System Properties)")
        void testEnableSystemPropertiesENVdev() {
        }

        @Test
        @DisabledIfSystemProperty(named="java.version", matches = "16.*")
        @DisplayName("Test disabled for Java 16.* (System Properties)")
        void testDisableSystemPropertiesJavaVersion() {
        }

        @Test
        @EnabledIfSystemProperties({
                @EnabledIfSystemProperty(named="java.version", matches = "16.*"),
                @EnabledIfSystemProperty(named="java.vm.vendor", matches = "Oracle Corporation"),
                @EnabledIfSystemProperty(named="sun.arch.data.model", matches = "64")
        })
        @DisplayName("Test disabled for some System Properties")
        void testEnableForSystemProperties() {
        }

        @Test
        @DisabledIfSystemProperties({
                @DisabledIfSystemProperty(named="java.version", matches = "16.*"),
                @DisabledIfSystemProperty(named="java.vm.vendor", matches = "Oracle Corporation"),
                @DisabledIfSystemProperty(named="sun.arch.data.model", matches = "64")
        })
        @DisplayName("Test disabled for some System Properties")
        void testDisableForSystemProperties() {
        }
    }

    @Nested
    @DisplayName("Testing depending on the environment variables")
    class EnviromentVariablesTest{
        @Test
        @DisplayName("Test to print the enviroment variables")
        void printEnviromentVariables() {
            Map<String, String> enviromentVariables = System.getenv();
            enviromentVariables.forEach((key, value) -> System.out.println(String.format("%s => %s", key, value)));
        }

        @Test
        @EnabledIfEnvironmentVariable(named="NUMBER_OF_PROCESSORS", matches = "8")
        @DisplayName("Test enabled for enviroment variables \"NUMBER_OF_PROCESSORS\" with value \"8\" (Enviroment Variables)")
        void testEnableEnvironmentVariableNumberProcessors8() {
        }

        @Test
        @DisabledIfEnvironmentVariable(named="NUMBER_OF_PROCESSORS", matches = "8")
        @DisplayName("Test disabled for enviroment variables \"NUMBER_OF_PROCESSORS\" with value \"8\" (Enviroment Variables)")
        void testdisableEnvironmentVariableNumberProcessors8() {
        }

        @Test
        @EnabledIfEnvironmentVariables({
                @EnabledIfEnvironmentVariable(named="NUMBER_OF_PROCESSORS", matches = "8"),
                @EnabledIfEnvironmentVariable(named="PROCESSOR_ARCHITECTURE", matches = "AMD64")
        })
        @DisplayName("Test enabled for some enviroment variables (Enviroment Variables)")
        void testEnableForEnvironmentVariables() {
        }

        @Test
        @DisabledIfEnvironmentVariables({
                @DisabledIfEnvironmentVariable(named="NUMBER_OF_PROCESSORS", matches = "8"),
                @DisabledIfEnvironmentVariable(named="PROCESSOR_ARCHITECTURE", matches = "AMD64")
        })
        @DisplayName("Test disabled for some enviroment variables (Enviroment Variables)")
        void testDisableForEnvironmentVariables() {
        }
    }

    @Nested
    @DisplayName("Tests with Assumptions")
    class AssumptionsTest{
        private Account account;

        @BeforeEach
        void beforeTest(){
            System.out.println("RESTORE ACCOUNT");
            this.account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        }

        @Test
        @DisplayName("Test the current account balance. It is only executed if the system property \"ENV\" is set to \"dev\"")
        void testAccountBalanceDev() {
            final String ENV = System.getProperty("ENV");
            final boolean IS_DEV = "dev".equals(ENV);

            assumeTrue(IS_DEV, () -> String.format("The \"ENV\" property of System property isn't \"dev\", else is %s", ENV));

            final BigDecimal ACTUAL = this.account.getBalance();

            assertNotNull(ACTUAL, () -> "The account balance cant' be void");
            assumingThat(IS_DEV, () -> {
                assertEquals(0, ACTUAL.compareTo(ACCOUNT_BALANCE),
                        () -> String.format("Account balance isn't as expected: EXPECTED => %s // ACTUAL => %s", BigDecimal.ZERO, ACTUAL));
            });
        }
    }

    @Nested
    @DisplayName("Testing the parameterized tests")
    class ParameterizedTests{
        private Account account;

        @BeforeEach
        void beforeTest(){
            System.out.println("RESTORE ACCOUNT");
            this.account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        }

        @ParameterizedTest(name="{displayName} => Test [{index}][{argumentsWithNames}]")
        @ValueSource(strings = {"100", "200", "300", "500", "800", "1300", "2100", "3400", "4500"})
        @DisplayName("Test the current account debit operation (Value source)")
        void testAccountDebitValueSource(final String quantity) {
            final BigDecimal EXPECTED = this.account.getBalance().subtract(new BigDecimal(quantity));
            this.account.debit(new BigDecimal(quantity));
            final BigDecimal ACTUAL = this.account.getBalance();

            assertNotNull(ACTUAL, () -> "The account balance cant' be void");
            assertEquals(0, ACTUAL.compareTo(EXPECTED),
                    () -> String.format("The account balance (%s) must be %s less: EXPECTED => %s // ACTUAL => %s",
                            ACCOUNT_BALANCE, quantity, EXPECTED, ACTUAL));
        }

        @ParameterizedTest(name="{displayName} => Test [{index}][{argumentsWithNames}]")
        @CsvSource({"1,100", "2,200", "3,300", "4,500", "5,800", "6,1300", "7,2100", "8,3400", "9,4500"})
        @DisplayName("Test the current account debit operation (CSV source)")
        void testAccountDebitFromCsvSource(final String index, final String value) {
            System.out.println(String.format("INDEX => %s // VALUE => %s", index, value));
            final BigDecimal EXPECTED = this.account.getBalance().subtract(new BigDecimal(value));
            this.account.debit(new BigDecimal(value));
            final BigDecimal ACTUAL = this.account.getBalance();

            assertNotNull(ACTUAL, () -> "The account balance cant' be void");
            assertEquals(0, ACTUAL.compareTo(EXPECTED),
                    () -> String.format("The account balance (%s) must be %s less: EXPECTED => %s // ACTUAL => %s",
                            ACCOUNT_BALANCE, value, EXPECTED, ACTUAL));
        }

        @ParameterizedTest(name="{displayName} => Test [{index}][{argumentsWithNames}]")
        @CsvSource({"5000,100,4900", "2000,200,1800", "1000,300,700", "500,500,0", "1200,800,400", "1500,1300,200", "2500,2100,400", "3500,3400,100", "10000,4500,5500"})
        @DisplayName("Test the current account debit operation (CSV source more parameters)")
        void testAccountDebitFromCsvSourceMoreParams(final String balance, final String quantity, final String expected) {
            System.out.println(String.format("BALANCE => %s // QUANTITY => %s // EXPECTED => %s", balance, quantity, expected));
            this.account.setBalance(new BigDecimal(balance));
            this.account.debit(new BigDecimal(quantity));
            final BigDecimal ACTUAL = this.account.getBalance();

            assertNotNull(ACTUAL, () -> "The account balance cant' be void");
            assertEquals(0, ACTUAL.compareTo(new BigDecimal(expected)),
                    () -> String.format("The account balance (%s) must be %s less: EXPECTED => %s // ACTUAL => %s",
                            balance, quantity, expected, ACTUAL));
        }

        @ParameterizedTest(name="{displayName} => Test [{index}][{argumentsWithNames}]")
        @CsvFileSource(resources="/data.csv")
        @DisplayName("Test the current account debit operation (CSV File source)")
        void testAccountDebitFromFileSource(final String quantity) {
            System.out.println(String.format("QUANTITY => %s", quantity));
            final BigDecimal EXPECTED = this.account.getBalance().subtract(new BigDecimal(quantity));
            this.account.debit(new BigDecimal(quantity));
            final BigDecimal ACTUAL = this.account.getBalance();

            assertNotNull(ACTUAL, () -> "The account balance cant' be void");
            assertEquals(0, ACTUAL.compareTo(EXPECTED),
                    () -> String.format("The account balance (%s) must be %s less: EXPECTED => %s // ACTUAL => %s",
                            ACCOUNT_BALANCE, quantity, EXPECTED, ACTUAL));
        }

        @ParameterizedTest(name="{displayName} => Test [{index}][{argumentsWithNames}]")
        @CsvFileSource(resources="/data-full.csv")
        @DisplayName("Test the current account debit operation (CSV File source more parameters)")
        void testAccountDebitFromFileSourceMoreParams(final String balance, final String quantity, final String expected) {
            System.out.println(String.format("BALANCE => %s // QUANTITY => %s // EXPECTED => %s", balance, quantity, expected));
            this.account.setBalance(new BigDecimal(balance));
            this.account.debit(new BigDecimal(quantity));
            final BigDecimal ACTUAL = this.account.getBalance();

            assertNotNull(ACTUAL, () -> "The account balance cant' be void");
            assertEquals(0, ACTUAL.compareTo(new BigDecimal(expected)),
                    () -> String.format("The account balance (%s) must be %s less: EXPECTED => %s // ACTUAL => %s",
                            balance, quantity, expected, ACTUAL));
        }

        private static List<String> getQuantityList(){
            return Arrays.asList("100", "200", "300", "500", "800", "1300", "2100", "3400", "4500");
        }

        @ParameterizedTest(name="{displayName} => Test [{index}][{argumentsWithNames}]")
        @MethodSource("getQuantityList")
        @DisplayName("Test the current account debit operation (Method source)")
        void testAccountDebitFromMethodSource(final String quantity) {
            System.out.println(String.format("QUANTITY => %s", quantity));
            final BigDecimal EXPECTED = this.account.getBalance().subtract(new BigDecimal(quantity));
            this.account.debit(new BigDecimal(quantity));
            final BigDecimal ACTUAL = this.account.getBalance();

            assertNotNull(ACTUAL, () -> "The account balance cant' be void");
            assertEquals(0, ACTUAL.compareTo(EXPECTED),
                    () -> String.format("The account balance (%s) must be %s less: EXPECTED => %s // ACTUAL => %s",
                            ACCOUNT_BALANCE, quantity, EXPECTED, ACTUAL));
        }
    }
}