package org.example.backend.repository;

import org.example.backend.models.EmeraldAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmeraldAccountRepository extends JpaRepository<EmeraldAccount,Long> {
}
