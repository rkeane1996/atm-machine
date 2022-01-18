package com.atm.repository;

import com.atm.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "Select opening_balance FROM ACCOUNT WHERE account_number = ?1",
            nativeQuery = true)
    int getAccountBalance(int accNum);

    @Query(value = "Select * FROM ACCOUNT WHERE account_number = ?1",
            nativeQuery = true)
    Account getAccountByAccountNumber(int accNum);
}
