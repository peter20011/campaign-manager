package org.example.backend.controller;

import jakarta.validation.Valid;
import org.example.backend.dto.CampaignRequest;
import org.example.backend.models.Campaign;
import org.example.backend.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller for managing campaign resources. It provides endpoints to
 * create, read, update, and delete campaigns.
 */
@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;
    /**
     * Retrieves a list of all campaigns.
     *
     * @return a list of {@link Campaign} objects
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        List<Campaign> campaigns = campaignService.findAll();
        return ResponseEntity.ok(campaigns);
    }
    /**
     * Retrieves a specific campaign by its ID.
     *
     * @param id the ID of the campaign to retrieve
     * @return the {@link Campaign} object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id) {
        Campaign campaign = campaignService.findById(id);
        return ResponseEntity.ok(campaign);
    }
    /**
     * Creates a new campaign.
     *
     * @param campaignRequest the request body containing campaign details
     * @return the created {@link Campaign} object
     */
    @PostMapping
    public ResponseEntity<Campaign> createCampaign(
            @Valid @RequestBody CampaignRequest campaignRequest) {

        Campaign newCampaign = campaignService.save(campaignRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCampaign);
    }
    /**
     * Updates an existing campaign.
     *
     * @param id the ID of the campaign to update
     * @param campaignRequest the request body with updated campaign details
     * @return the updated {@link Campaign} object
     */
    @PutMapping("/{id}")
    public ResponseEntity<Campaign> updateCampaign(
            @PathVariable Long id,
            @Valid @RequestBody CampaignRequest campaignRequest) {

        Campaign updatedCampaign = campaignService.update(id, campaignRequest);
        return ResponseEntity.ok(updatedCampaign);
    }
    /**
     * Deletes a campaign by its ID.
     *
     * @param id the ID of the campaign to delete
     * @return an empty response indicating success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        campaignService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
