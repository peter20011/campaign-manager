package org.example.backend.service;

import org.example.backend.models.EmeraldAccount;
import org.example.backend.repository.EmeraldAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmeraldAccountService {

    @Autowired
    private EmeraldAccountRepository emeraldAccountRepository;

    private static final Long DEFAULT_ACCOUNT_ID = 1L; // Assuming the account created has ID 1

    // Method to update the balance for the single existing account
    public EmeraldAccount updateAccount(Double initBalance) {
        EmeraldAccount emeraldAccount = emeraldAccountRepository.findById(DEFAULT_ACCOUNT_ID)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        emeraldAccount.setBalance(initBalance);

        return emeraldAccountRepository.save(emeraldAccount);
    }


    public EmeraldAccount getAccount() {
        return emeraldAccountRepository.findById(DEFAULT_ACCOUNT_ID)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }
}
