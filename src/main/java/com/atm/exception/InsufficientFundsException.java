package com.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class InsufficientFundsException extends Exception{

    public InsufficientFundsException(String message){
        super(message);
    }
}
