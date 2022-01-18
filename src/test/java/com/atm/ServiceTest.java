package com.atm;

import com.atm.model.Account;
import com.atm.model.Response;
import com.atm.repository.AccountRepository;
import com.atm.service.AtmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @InjectMocks
    AtmService atmService;

    @Mock
    AccountRepository accountRepository;

    @Test
    public void checkBalanceTest(){
        Mockito.when(accountRepository.getAccountBalance(123456789))
                .thenReturn(200);
        Response balance = atmService.checkAccountBalance(123456789);
        assertEquals(200, balance.getBalance());
    }

    @Test
    public void getAccountTest(){
        Mockito.when(accountRepository.getAccountByAccountNumber(123456789))
                .thenReturn(new Account(123456789,9876,550,100));
        Account account = atmService.getAccount(123456789);
        assertEquals(account.getAccountNumber(), 123456789);
    }

    @Test
    public void updateAccountTest(){
        Account acc = new Account(12345,8765,300,100);
        Mockito.when(accountRepository.save(acc))
                .thenReturn(new Account(12345,8765,300,100));
        Account account = atmService.updateAccount(acc);
        assertEquals(account.getAccountNumber(), 123456789);
    }
}
