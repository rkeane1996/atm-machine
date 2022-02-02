package com.atm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Account {

    @Id
    private int accountNumber;

    private int pin;
    private int openingBalance;
    private int overdraft;

    public Account(int accountNumber, int pin, int openingBalance, int overdraft) {
        super();
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.openingBalance = openingBalance;
        this.overdraft = overdraft;
    }

    public void reduceBalance(int amount){
        this.openingBalance -= amount;
    }
}
