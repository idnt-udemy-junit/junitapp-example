package org.idnt.udemy.junitapp.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.idnt.udemy.junitapp.example.exception.NotEnoughMoneyException;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Account {
    private String name;
    private BigDecimal balance;
    private static final String ERROR_MSG_NOT_ENOUGHT_MONEY = "No Enought Money !";

    /**
     * Method that makes an expense in the account.
     *
     * @param quantity The amount to be subtracted from the account balance.
     */
    public void debit(BigDecimal quantity){
        //The value returned by the operation must be assigned, since the modifications made by BigDecimal are not by reference.
        BigDecimal newBalance = this.balance.subtract(quantity);

        if(newBalance.compareTo(BigDecimal.ZERO)<0){
            throw new NotEnoughMoneyException(ERROR_MSG_NOT_ENOUGHT_MONEY);
        }

        this.setBalance(newBalance);
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
