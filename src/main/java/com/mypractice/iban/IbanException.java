package com.mypractice.iban;

public class IbanException extends RuntimeException{
    public IbanException(String message){
        super(message);
    }
}
