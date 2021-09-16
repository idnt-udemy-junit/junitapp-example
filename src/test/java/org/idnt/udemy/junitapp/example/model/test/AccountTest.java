package org.idnt.udemy.junitapp.example.model.test;

import org.idnt.udemy.junitapp.example.model.Account;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private static final String ACCOUNT_NAME =  "Personal Account";
    private static final BigDecimal ACCOUNT_BALANCE =  new BigDecimal("5000.00");

    @Test
    void testAccountName() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE);

        final String EXPECTED = ACCOUNT_NAME;
        final String ACTUAL = account.getName();

        assertEquals(EXPECTED, ACTUAL);
        assertTrue(ACTUAL.equals(EXPECTED));
    }

    @Test
    void testAccountBalance() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE);
        final BigDecimal ACTUAL = account.getBalance();

        assertTrue(ACTUAL.compareTo(ACCOUNT_BALANCE) == 0);
        assertFalse(ACTUAL.compareTo(BigDecimal.ZERO) == -1);
    }

    @Test
    void testAccountReference() {
        Account account1 = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE);
        Account account2 = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE);

        //If the equals method has been implemented in Account class, assertNotEquals return false, else return true.
//        assertNotEquals(account1, account2);

        //If the equals method has been implemented in Account class, asserEquals return true, else return false.
        assertEquals(account1, account2);
    }
}