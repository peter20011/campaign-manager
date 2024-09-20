package org.example.backend.dto;

import jakarta.validation.constraints.NotNull;
/**
 * Record representing a request to update the balance of an EmeraldAccount.
 *
 * @param initialBalance the new balance to be set for the account, must not be null
 */
public record AccountRequest(@NotNull Double initialBalance) {
}
