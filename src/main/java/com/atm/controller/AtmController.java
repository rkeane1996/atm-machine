package com.atm.controller;

import com.atm.exception.IncorrectPinException;
import com.atm.exception.InsufficientFundsException;
import com.atm.model.Request;
import com.atm.model.Response;
import com.atm.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmController {

    @Autowired
    private AtmService atmService;

    @GetMapping(value = "/checkBalance")
    public int checkAccountBalance(@RequestBody Request request) throws IncorrectPinException {
        if(!atmService.assertPinIsCorrect(request)) throw new IncorrectPinException("Incorrect pin");
        return atmService.checkAccountBalance(request.getAccountNumber());

    }

    @PutMapping(value = "/withdraw")
    public Response accountWithdrawal(@RequestBody Request request) throws InsufficientFundsException, IncorrectPinException {
        if(!atmService.assertPinIsCorrect(request)) throw new IncorrectPinException("Incorrect pin");
        return atmService.withdrawMoney(request);
    }
}