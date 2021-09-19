package org.idnt.udemy.junitapp.example.model.test;

import org.idnt.udemy.junitapp.example.exception.NotEnoughMoneyException;
import org.idnt.udemy.junitapp.example.model.Account;
import org.idnt.udemy.junitapp.example.model.Bank;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private static final String ACCOUNT_NAME =  "Personal Account";
    private static final BigDecimal ACCOUNT_BALANCE =  new BigDecimal("5000.00");

    @Test
    @DisplayName("Testing the current account name")
    void testAccountName() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);

        final String EXPECTED = ACCOUNT_NAME;
        final String ACTUAL = account.getName();

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
    @DisplayName("Testing the current account balance")
    void testAccountBalance() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        final BigDecimal ACTUAL = account.getBalance();

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
    @DisplayName("Testing the current account reference")
    void testAccountReference() {
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

    /**
     * <p>TDD (Test Driven Development)</p>
     * <p>First develop the tests, then develop the methods.</p>
     */
    @Test
    @DisplayName("Testing the current account debit operation")
    void testAccountDebit() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        account.debit(new BigDecimal("1000"));
        final BigDecimal ACTUAL = account.getBalance();

        assertNotNull(ACTUAL, () -> "The account balance cant' be void");

        //Checks if 1000 has been subtracted from the balance of the account.
        assertEquals("4000.00", ACTUAL.toPlainString(),
                () -> String.format("The account balance must be 1000 less than before: BEFORE: %s", ACCOUNT_BALANCE));
        assertEquals(0, ACTUAL.compareTo(new BigDecimal("4000")),
                () -> String.format("The account balance must be 1000 less than before: BEFORE: %s", ACCOUNT_BALANCE));
    }

    @Test
    @DisplayName("Testing that it isn't possible to withdraw more money than is available in the current account")
    void testNotEnoughMoneyException() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        final String EXPECTED_MSG = "No Enought Money !";
        final BigDecimal MONEY_TO_BE_SUBSTRACT = new BigDecimal("6000");

        //Checks if the "NoEnoughMoneyException" exception was thrown when subtracting an amount greater than the account balance.
        Exception exception = assertThrows(NotEnoughMoneyException.class,
                () -> account.debit(MONEY_TO_BE_SUBSTRACT),
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
    @DisplayName("Testing the current account credit operation")
    void testAccountCredit() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        account.credit(new BigDecimal("1000"));
        final BigDecimal ACTUAL = account.getBalance();

        assertNotNull(ACTUAL, () -> "The account balance cant' be void");

        //Checks if 1000 has been added to the balance of the account.
        assertEquals("6000.00", ACTUAL.toPlainString(),
                () -> String.format("The account balance must be 1000 more than before: BEFORE: %s", ACCOUNT_BALANCE));
        assertEquals(0, ACTUAL.compareTo(new BigDecimal("6000")),
                () -> String.format("The account balance must be 1000 more than before: BEFORE: %s", ACCOUNT_BALANCE));
    }

    @Test
    @DisplayName("Testing the money transfer operation between 2 accounts")
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
    @DisplayName("Testing the relationship between the bank and the accounts")
    void testRelationBankAccounts() {
        final Bank BANK = new Bank("TROBO");
        final Account ACCOUNT_1 = new Account("Cuenta de Ángel", ACCOUNT_BALANCE, BANK);
        final Account ACCOUNT_2 = new Account("Cuenta de Patricia", ACCOUNT_BALANCE, BANK);
        final Account ACCOUNT_3 = new Account("Cuenta de Patriciazzzx", ACCOUNT_BALANCE, null);
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