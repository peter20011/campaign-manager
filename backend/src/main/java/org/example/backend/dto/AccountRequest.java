package org.example.backend.dto;

import jakarta.validation.constraints.NotNull;

public record AccountRequest(@NotNull Double initialBalance) {
}
