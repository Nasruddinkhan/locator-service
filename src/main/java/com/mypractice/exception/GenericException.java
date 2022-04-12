package com.mypractice.exception;

public class GenericException  extends RuntimeException{
    public GenericException(String msg) {
        super(msg);
    }
    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
