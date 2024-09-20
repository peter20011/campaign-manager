package org.example.backend.service;

import org.example.backend.models.EmeraldAccount;
import org.example.backend.repository.EmeraldAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service class for managing the EmeraldAccount. Provides methods to update
 * and retrieve the single pre-created account.
 */
@Service
public class EmeraldAccountService {

    @Autowired
    private EmeraldAccountRepository emeraldAccountRepository;

    private static final Long DEFAULT_ACCOUNT_ID = 1L;
    /**
     * Updates the balance of the EmeraldAccount with a predefined ID.
     *
     * @param initBalance the new balance to set for the account
     * @return the updated {@link EmeraldAccount}
     * @throws IllegalArgumentException if the account with the predefined ID is not found
     */
    public EmeraldAccount updateAccount(Double initBalance) {
        EmeraldAccount emeraldAccount = emeraldAccountRepository.findById(DEFAULT_ACCOUNT_ID)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        emeraldAccount.setBalance(initBalance);

        return emeraldAccountRepository.save(emeraldAccount);
    }

    /**
     * Retrieves the EmeraldAccount with a predefined ID.
     *
     * @return the {@link EmeraldAccount}
     * @throws IllegalArgumentException if the account with the predefined ID is not found
     */
    public EmeraldAccount getAccount() {
        return emeraldAccountRepository.findById(DEFAULT_ACCOUNT_ID)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }
}
