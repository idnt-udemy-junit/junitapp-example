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

        //Checks if the account name corresponds to the name that has been established
        assertEquals(EXPECTED, ACTUAL);
        assertTrue(ACTUAL.equals(EXPECTED));
    }

    @Test
    void testAccountBalance() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);
        final BigDecimal ACTUAL = account.getBalance();

        assertNotNull(ACTUAL);

        //Checks if the account balance corresponds to the balance that has been established
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

        //Checks if 1000 has been subtracted from the balance of the account.
        assertEquals("4000.00", ACTUAL.toPlainString());
        assertEquals(0, ACTUAL.compareTo(new BigDecimal("4000")));
    }

    @Test
    void testNotEnoughMoneyException() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE, null);

        //Checks if the "NoEnoughMoneyException" exception was thrown when subtracting an amount greater than the account balance.
        Exception exception = assertThrows(NotEnoughMoneyException.class, ()->{account.debit(new BigDecimal("6000"));});

        //Checks if the message of the "NoEnoughMoneyException" exception is equal to "No Enought Money !".
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

        //Checks if 1000 has been added to the balance of the account.
        assertEquals("6000.00", ACTUAL.toPlainString());
        assertEquals(0, ACTUAL.compareTo(new BigDecimal("6000")));
    }

    @Test
    void testTransferMoneyAccounts() {
        final Bank BANK = new Bank("TROBO");
        final Account SOURCE = new Account("Cuenta de Ángel", ACCOUNT_BALANCE, BANK);
        final Account TARGET = new Account("Cuenta de Patricia", ACCOUNT_BALANCE, BANK);
        BANK.addAccount(SOURCE);
        BANK.addAccount(TARGET);

        BANK.transfer(SOURCE, TARGET, new BigDecimal("1000"));

        //Checks if 1000 has been subtracted from the balance of the source account.
        assertEquals(0, SOURCE.getBalance().compareTo(new BigDecimal("4000")));

        //Checks if 1000 has been added to the balance of the target account.
        assertEquals(0, TARGET.getBalance().compareTo(new BigDecimal("6000")));
    }

    @Test
    void testRelationBankAccounts() {
        final Bank BANK = new Bank("TROBO");
        final Account ACCOUNT_1 = new Account("Cuenta de Ángel", ACCOUNT_BALANCE, BANK);
        final Account ACCOUNT_2 = new Account("Cuenta de Patricia", ACCOUNT_BALANCE, BANK);
        BANK.addAccount(ACCOUNT_1);
        BANK.addAccount(ACCOUNT_2);

        //Checks if account number 1 has the Bank class.
        assertEquals(BANK, ACCOUNT_1.getBank());

        //Checks if account number 2 has the Bank class.
        assertEquals(BANK, ACCOUNT_2.getBank());

        //Checks if the accounts of "Bank" class property is null
        assertNotNull(BANK.getAccounts());

        //Checks if the Bank has 2 accounts.
        assertEquals(2, BANK.getAccounts().size());

        //Checks if account number 1 is in the class "Bank".
        assertTrue(BANK.getAccounts().stream().anyMatch(account -> account.equals(ACCOUNT_1)));
        assertEquals(ACCOUNT_1, BANK.getAccounts().stream()
                .filter(account -> account.equals(ACCOUNT_1))
                .findFirst()
                .get());

        //Checks if account number 2 is in the class "Bank".
        assertTrue(BANK.getAccounts().stream().anyMatch(account -> account.equals(ACCOUNT_2)));
        assertEquals(ACCOUNT_2, BANK.getAccounts().stream()
                .filter(account -> account.equals(ACCOUNT_2))
                .findFirst()
                .get());
    }
}