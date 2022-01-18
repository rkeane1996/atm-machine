package com.atm.model;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Data
public class Response {

    private String message;
    private Map<Money,Integer> noteDetails;
    private int balance;

    public Response(){}

    public Response(String message){
        this.message = message;
    }

    public Response(String message, Map<Money,Integer> noteDetails, int balance){
        this.message = message;
        this.noteDetails = noteDetails;
        this.balance = balance;
    }
}
