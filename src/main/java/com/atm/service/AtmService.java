package com.atm.service;

import com.atm.exception.InsufficientFundsException;
import com.atm.model.*;
import com.atm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AtmService {

    @Autowired
    private AccountRepository accountRepo;

    BankFunds bankFunds = new BankFunds();

    public int checkAccountBalance(int accountNumber) {
        return accountRepo.getAccountBalance(accountNumber);
    }

    public Response withdrawMoney(Request request) throws InsufficientFundsException {
        int originalAmount = request.getWithdrawAmount();
        Account account = getAccount(request.getAccountNumber());
        if (account.getOpeningBalance() + account.getOverdraft() > request.getWithdrawAmount()) {
            Map<Money, Integer> response = bankFunds.withdrawMoney(request.getWithdrawAmount());
            account.reduceBalance(originalAmount);
            updateAccount(account);
            return new Response("Successful Request, please take your money", response, account.getOpeningBalance());
        } else {
            throw new InsufficientFundsException("Your Account has Insufficient Funds to complete this request");
        }
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
}
