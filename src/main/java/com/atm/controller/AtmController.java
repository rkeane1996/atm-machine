package com.atm.controller;

import com.atm.model.Request;
import com.atm.model.Response;
import com.atm.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmController {

    @Autowired
    private AtmService atmService;

    @GetMapping(value = "/checkBalance")
    public Response checkAccountBalance(@RequestBody Request request) {
        return atmService.assertPinIsCorrect(request) ? atmService.checkAccountBalance(request.getAccountNumber()) : new Response("Incorrect Pin");
    }

    @GetMapping(value = "/withdraw")
    public Response accountWithdrawal(@RequestBody Request request){
        return atmService.assertPinIsCorrect(request) ? atmService.withdrawMoney(request) : new Response("Incorrect Pin");
    }
}