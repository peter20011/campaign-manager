package org.example.backend.repository;

import org.example.backend.models.EmeraldAccount;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for performing CRUD operations on the {@link EmeraldAccount} entity.
 * Extends {@link JpaRepository} to provide standard database operations.
 */
public interface EmeraldAccountRepository extends JpaRepository<EmeraldAccount,Long> {
}
