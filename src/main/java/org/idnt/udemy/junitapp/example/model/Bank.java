package org.idnt.udemy.junitapp.example.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class Bank {
    private String name;
    private Set<Account> accounts;

    public Bank(String name) {
        this.name = name;
    }

    public void transfer(Account source, Account target, BigDecimal quantity){
        source.debit(quantity);
        target.credit(quantity);
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }
}
