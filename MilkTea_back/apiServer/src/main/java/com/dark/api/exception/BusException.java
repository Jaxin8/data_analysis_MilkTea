package com.dark.api.exception;

public class BusException extends RuntimeException{
    private String simpleMsg;

    public BusException(String message){
        super(message);
    }

    public BusException(String message,String simpleMsg){
        super(message);
        this.simpleMsg = simpleMsg;
    }
}
