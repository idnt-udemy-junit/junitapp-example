package org.idnt.udemy.junitapp.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Account {
    private String name;
    private BigDecimal balance;
}
