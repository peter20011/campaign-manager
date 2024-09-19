package org.example.backend.repository;



import org.example.backend.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository  extends JpaRepository<Campaign, Long> {
}
