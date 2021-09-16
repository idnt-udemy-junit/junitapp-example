package org.idnt.udemy.junitapp.example.model.test;

import org.idnt.udemy.junitapp.example.model.Account;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testAccountName() {
        final String ACCOUNT_NAME =  "Personal Account";
        final BigDecimal ACCOUNT_BALANCE =  new BigDecimal("5000.00");

        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE);

        final String EXPECTED = ACCOUNT_NAME;
        final String ACTUAL = account.getName();

        assertEquals(EXPECTED, ACTUAL);
        assertTrue(ACTUAL.equals(EXPECTED));
    }
}