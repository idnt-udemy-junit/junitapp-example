package org.idnt.udemy.junitapp.example.model.test;

import org.idnt.udemy.junitapp.example.exception.NotEnoughMoneyException;
import org.idnt.udemy.junitapp.example.model.Account;
import org.idnt.udemy.junitapp.example.model.Bank;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private static final String ACCOUNT_NAME =  "Personal Account";
    private static final BigDecimal ACCOUNT_BALANCE =  new BigDecimal("5000.00");

    @Test
    void testAccountName() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);

        final String EXPECTED = ACCOUNT_NAME;
        final String ACTUAL = account.getName();

        assertNotNull(ACTUAL);
        assertEquals(EXPECTED, ACTUAL);
        assertTrue(ACTUAL.equals(EXPECTED));
    }

    @Test
    void testAccountBalance() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        final BigDecimal ACTUAL = account.getBalance();

        assertNotNull(ACTUAL);
        assertTrue(ACTUAL.compareTo(ACCOUNT_BALANCE) == 0);
        assertFalse(ACTUAL.compareTo(BigDecimal.ZERO) == -1);
    }

    @Test
    void testAccountReference() {
        Account account1 = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        Account account2 = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);

        //If the equals method has been implemented in Account class, assertNotEquals return false, else return true.
//        assertNotEquals(account1, account2);

        //If the equals method has been implemented in Account class, asserEquals return true, else return false.
        assertEquals(account1, account2);
    }

    /**
     * <p>TDD (Test Driven Development)</p>
     * <p>First develop the tests, then develop the methods.</p>
     */
    @Test
    void testAccountDebit() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        account.debit(new BigDecimal("1000"));
        final BigDecimal ACTUAL = account.getBalance();

        assertNotNull(ACTUAL);
        assertEquals("4000.00", ACTUAL.toPlainString());
        assertEquals(0, ACTUAL.compareTo(new BigDecimal("4000")));
    }

    @Test
    void testNotEnoughMoneyException() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);

        Exception exception = assertThrows(NotEnoughMoneyException.class, ()->{account.debit(new BigDecimal("6000"));});
        final String ACTUAL = exception.getMessage();
        assertEquals("No Enought Money !", ACTUAL);
    }

    /**
     * <p>TDD (Test Driven Development)</p>
     * <p>First develop the tests, then develop the methods.</p>
     */
    @Test
    void testAccountCredit() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        account.credit(new BigDecimal("1000"));
        final BigDecimal ACTUAL = account.getBalance();

        assertNotNull(ACTUAL);
        assertEquals("6000.00", ACTUAL.toPlainString());
        assertEquals(0, ACTUAL.compareTo(new BigDecimal("6000")));
    }

    @Test
    void testTransferMoneyAccounts() {
        final Bank BANK = new Bank("TROBO");
        final Account SOURCE = new Account("´Cuenta de Ángel", ACCOUNT_BALANCE, BANK);
        final Account TARGET = new Account("Cuenta de Patricia", ACCOUNT_BALANCE, BANK);
        BANK.addAccount(SOURCE);
        BANK.addAccount(TARGET);

        BANK.transfer(SOURCE, TARGET, new BigDecimal("1000"));

        assertEquals(0, SOURCE.getBalance().compareTo(new BigDecimal("4000")));
        assertEquals(0, TARGET.getBalance().compareTo(new BigDecimal("6000")));
        assertEquals(2, BANK.getAccounts().size());
    }
}