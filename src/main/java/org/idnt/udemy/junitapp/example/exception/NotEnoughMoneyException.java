package org.idnt.udemy.junitapp.example.exception;

public class NotEnoughMoneyException extends RuntimeException{

    public NotEnoughMoneyException(String msg){
        super(msg);
    }
}
