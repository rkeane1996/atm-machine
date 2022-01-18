package com.atm.model;

public enum Money {

    FIFTY_EURO_NOTES(50),
    TWENTY_EURO_NOTES(20),
    TEN_EURO_NOTES(10),
    FIVE_EURO_NOTES(5);

    private int amount;

    Money(int amount) {
        this.amount = amount;
    }

    public int getAmount(){return amount;}
}
