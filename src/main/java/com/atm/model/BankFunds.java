package com.atm.model;

import com.atm.exception.InsufficientFundsException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@Data
public class BankFunds {

    private int totalFunds = 1500;
    private int NumFiftyEuroNotes = 10;
    private int NumTwentyEuroNotes = 30;
    private int NumTenEuroNotes = 30;
    private int NumFiveEuroNotes = 20;


    public void withdraw50(){
        this.totalFunds -= 50;
        this.NumFiftyEuroNotes --;
    }

    public void withdraw20(){
        this.totalFunds -= 20;
        this.NumTwentyEuroNotes --;
    }

    public void withdraw10(){
        this.totalFunds -= 10;
        this.NumTenEuroNotes --;
    }

    public void withdraw5(){
        this.totalFunds -= 5;
        this.NumFiveEuroNotes --;
    }


    public Map<Money, Integer> withdrawMoney(int amount) throws InsufficientFundsException {
        assertTrue(amount < this.totalFunds);
        Map<Money, Integer> notesDispensed = new HashMap<>();
        int amountOfFifties = 1, amountOfTwenties = 1, amountOfTens = 1, amountOfFives = 1;
        do {
            if (amount >= Money.FIFTY_EURO_NOTES.getAmount() && this.NumFiftyEuroNotes != 0) {
                withdraw50();
                notesDispensed.put(Money.FIFTY_EURO_NOTES, amountOfFifties++);
                amount -= Money.FIFTY_EURO_NOTES.getAmount();
            } else if (amount >= Money.TWENTY_EURO_NOTES.getAmount() && this.NumTwentyEuroNotes != 0) {
                withdraw20();
                notesDispensed.put(Money.TWENTY_EURO_NOTES, amountOfTwenties++);
                amount -= Money.TWENTY_EURO_NOTES.getAmount();
            } else if (amount >= Money.TEN_EURO_NOTES.getAmount() && this.NumTenEuroNotes != 0) {
                withdraw10();
                notesDispensed.put(Money.TEN_EURO_NOTES, amountOfTens++);
                amount -= Money.TEN_EURO_NOTES.getAmount();
            } else if (amount >= Money.FIVE_EURO_NOTES.getAmount() && this.NumFiveEuroNotes != 0) {
               withdraw5();
                notesDispensed.put(Money.FIVE_EURO_NOTES, amountOfFives++);
                amount -= Money.FIVE_EURO_NOTES.getAmount();
            } else {
                throw new InsufficientFundsException("The ATM has Insufficient Funds to complete this request");
            }
        }
        while (amount > 0);
        return notesDispensed;
    }
}
