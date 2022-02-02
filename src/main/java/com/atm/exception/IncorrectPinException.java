package com.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class IncorrectPinException extends Exception{

    public IncorrectPinException(String message) {
        super(message);
    }
}
