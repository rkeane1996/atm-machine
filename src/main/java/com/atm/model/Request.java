package com.atm.model;

import lombok.Data;

@Data
public class Request {

    private Integer accountNumber;
    private Integer pin;
    private Integer withdrawAmount;

    public Request(){}

    public Request(int accountNumber, int pin, int withdrawAmount){
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.withdrawAmount = withdrawAmount;
    }
}
