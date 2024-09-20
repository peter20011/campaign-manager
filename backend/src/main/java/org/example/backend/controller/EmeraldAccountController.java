package org.example.backend.controller;

import org.example.backend.dto.AccountRequest;
import org.example.backend.models.EmeraldAccount;
import org.example.backend.service.EmeraldAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class EmeraldAccountController {

    @Autowired
    private EmeraldAccountService emeraldAccountService;

    // Get the current EmeraldAccount details
    @GetMapping
    public ResponseEntity<EmeraldAccount> getAccount() {
        EmeraldAccount account = emeraldAccountService.getAccount();
        return ResponseEntity.ok(account);
    }

    // Update the balance of the specific pre-created account
    @PostMapping
    public ResponseEntity<EmeraldAccount> updateAccount(@RequestBody AccountRequest accountRequest) {
        EmeraldAccount updatedAccount = emeraldAccountService.updateAccount(accountRequest.initialBalance());
        return ResponseEntity.ok(updatedAccount);
    }
}
