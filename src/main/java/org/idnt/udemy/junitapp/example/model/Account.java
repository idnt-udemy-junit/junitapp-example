package org.idnt.udemy.junitapp.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Account {
    private String name;
    private BigDecimal balance;

    /**
     * Method that makes an expense in the account.
     *
     * @param quantity The amount to be subtracted from the account balance.
     */
    public void debit(BigDecimal quantity){
        //The value returned by the operation must be assigned, since the modifications made by BigDecimal are not by reference.
        this.setBalance(this.balance.subtract(quantity));
    }

    /**
     * Method that makes a deposit into the account.
     *
     * @param quantity The amount to be added from the account balance
     */
    public void credit(BigDecimal quantity){
        //The value returned by the operation must be assigned, since the modifications made by BigDecimal are not by reference.
        this.setBalance(this.balance.add(quantity));
    }
}
