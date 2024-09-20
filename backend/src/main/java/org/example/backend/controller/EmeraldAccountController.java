package org.example.backend.controller;

import org.example.backend.dto.AccountRequest;
import org.example.backend.models.EmeraldAccount;
import org.example.backend.service.EmeraldAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * REST controller for managing the EmeraldAccount resource. It provides
 * endpoints to retrieve the current account details and update the balance.
 */
@RestController
@RequestMapping("/api/account")
public class EmeraldAccountController {

    @Autowired
    private EmeraldAccountService emeraldAccountService;
    /**
     * Retrieves the current EmeraldAccount details.
     *
     * @return the current {@link EmeraldAccount} object
     */
    @GetMapping
    public ResponseEntity<EmeraldAccount> getAccount() {
        EmeraldAccount account = emeraldAccountService.getAccount();
        return ResponseEntity.ok(account);
    }
    /**
     * Updates the balance of the pre-created EmeraldAccount.
     *
     * @param accountRequest the request containing the new balance
     * @return the updated {@link EmeraldAccount} object
     */
    @PostMapping
    public ResponseEntity<EmeraldAccount> updateAccount(@RequestBody AccountRequest accountRequest) {
        EmeraldAccount updatedAccount = emeraldAccountService.updateAccount(accountRequest.initialBalance());
        return ResponseEntity.ok(updatedAccount);
    }
}
