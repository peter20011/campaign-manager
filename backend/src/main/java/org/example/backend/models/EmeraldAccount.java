package org.example.backend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "emerald_accounts")
@Setter
@Getter
public class EmeraldAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Balance is mandatory")
    @Column(nullable = false)
    private Double balance;

    public EmeraldAccount() {}

}
