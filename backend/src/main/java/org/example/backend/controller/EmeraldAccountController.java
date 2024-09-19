package org.example.backend.controller;


import org.example.backend.models.EmeraldAccount;
import org.example.backend.service.EmeraldAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class EmeraldAccountController {

    @Autowired
    private EmeraldAccountService emeraldAccountService;

    @PostMapping
    public ResponseEntity<EmeraldAccount> createAccount(@RequestBody BigDecimal initialBalance) {
        EmeraldAccount account = emeraldAccountService.createAccount(initialBalance);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmeraldAccount> getAccount(@PathVariable Long id) {
        EmeraldAccount account = emeraldAccountService.getAccount(id);
        return ResponseEntity.ok(account);
    }
}
