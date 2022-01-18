package com.atm.service;

import com.atm.model.*;
import com.atm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AtmService {

    @Autowired
    private AccountRepository accountRepo;

    BankFunds bankFunds = new BankFunds();

    public Response checkAccountBalance(int accountNumber) {
        Response response = new Response();
        response.setBalance(accountRepo.getAccountBalance(accountNumber));
        response.setMessage("Your account balance is shown below");
        return response;
    }

    public Response withdrawMoney(Request request) {
        int originalAmount = request.getWithdrawAmount();
        Map<Money, Integer> noteDetails = new HashMap<>();
        int amountOfFifties = 1, amountOfTwenties = 1, amountOfTens = 1, amountOfFives = 1;
        Account account = getAccount(request.getAccountNumber());

        if (request.getWithdrawAmount() < bankFunds.getTotalFunds()) {
            if (account.getOpeningBalance() + account.getOverdraft() > request.getWithdrawAmount()) {
                do {
                    if (request.getWithdrawAmount() >= Money.FIFTY_EURO_NOTES.getAmount() && bankFunds.getNumFiftyEuroNotes() != 0) {
                        alterMoneyTotals(request, Money.FIFTY_EURO_NOTES, amountOfFifties, noteDetails);
                        bankFunds.setNumFiftyEuroNotes(bankFunds.getNumFiftyEuroNotes() - 1);
                    } else if (request.getWithdrawAmount() >= Money.TWENTY_EURO_NOTES.getAmount() && bankFunds.getNumTwentyEuroNotes() != 0) {
                        alterMoneyTotals(request, Money.TWENTY_EURO_NOTES, amountOfTwenties, noteDetails);
                        bankFunds.setNumTwentyEuroNotes(bankFunds.getNumTwentyEuroNotes() - 1);
                    } else if (request.getWithdrawAmount() >= Money.TEN_EURO_NOTES.getAmount() && bankFunds.getNumTenEuroNotes() != 0) {
                        alterMoneyTotals(request, Money.TEN_EURO_NOTES, amountOfTens, noteDetails);
                        bankFunds.setNumTenEuroNotes(bankFunds.getNumTenEuroNotes() - 1);
                    } else if (request.getWithdrawAmount() >= Money.FIVE_EURO_NOTES.getAmount() && bankFunds.getNumFiveEuroNotes() != 0) {
                        alterMoneyTotals(request, Money.FIVE_EURO_NOTES, amountOfFives, noteDetails);
                        bankFunds.setNumFiveEuroNotes(bankFunds.getNumFiveEuroNotes() - 1);
                    } else {
                        return new Response("Atm does not have the funds to complete your request");
                    }
                }
                while (request.getWithdrawAmount() > 0);
            } else {
                return new Response("Your Account has Insufficient Funds to complete this request");
            }
        } else {
            return new Response("ATM does not have sufficient fund to complete your request");
        }
        bankFunds.setTotalFunds(bankFunds.getTotalFunds() - originalAmount);
        account.setOpeningBalance(account.getOpeningBalance() - originalAmount);
        updateAccount(account);
        return new Response("Success Request, please take your money", noteDetails, account.getOpeningBalance());
    }

    public Account getAccount(int accountNumber) {
        return accountRepo.getAccountByAccountNumber(accountNumber);
    }

    public Account updateAccount(Account account) {
        return accountRepo.save(account);
    }

    public boolean assertPinIsCorrect(Request request) {
        Account account = accountRepo.getAccountByAccountNumber(request.getAccountNumber());
        if (account.getPin() == request.getPin()) {
            return true;
        }
        return false;
    }

    public void alterMoneyTotals(Request request, Money notes, int numOfNotes, Map<Money, Integer> noteDetails) {
        request.setWithdrawAmount(request.getWithdrawAmount() - notes.getAmount());
        noteDetails.put(notes, numOfNotes++);
    }
}
