package org.example.backend.repository;



import org.example.backend.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for performing CRUD operations on the {@link Campaign} entity.
 * Extends {@link JpaRepository} to provide standard database operations.
 */
@Repository
public interface CampaignRepository  extends JpaRepository<Campaign, Long> {
}
