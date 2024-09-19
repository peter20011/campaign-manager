package org.example.backend.service;

import org.example.backend.models.EmeraldAccount;
import org.example.backend.repository.EmeraldAccountRepository;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EmeraldAccountService {

    @Autowired
    private EmeraldAccountRepository emeraldAccountRepository;

    public EmeraldAccount createAccount(BigDecimal initBalance){
        EmeraldAccount emeraldAccount = new EmeraldAccount();
        return emeraldAccountRepository.save(emeraldAccount);
    }

    public EmeraldAccount getAccount(Long id){
        return emeraldAccountRepository.findById(id)
                .orElseThrow(()-> new ResourceClosedException("Account not found"));
    }

    public EmeraldAccount updateBalance(Long accountId, BigDecimal amount){
        EmeraldAccount account = getAccount(accountId);
        account.setBalance(account.getBalance().subtract(amount));
        return emeraldAccountRepository.save(account);
    }


}
