package com.example.storeelectronic.demo.exception;

public class BadClassException extends RuntimeException{

    public BadClassException(String msg){
        super(msg);
    }
   public  BadClassException(){
        super("bad req");
    }
}
