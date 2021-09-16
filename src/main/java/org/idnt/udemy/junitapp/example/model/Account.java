package org.idnt.udemy.junitapp.example.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account{
    private String person;
    private BigDecimal balance;
}
