package org.example.backend.controller;


import jakarta.validation.Valid;
import org.example.backend.models.Campaign;
import org.example.backend.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;


    @GetMapping ("/getAll")
    public ResponseEntity<List<Campaign>> getAllCampaings(){
        List<Campaign> campaigns = campaignService.findAll();
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/id")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id){
        Campaign campaign = campaignService.findById(id);
        return ResponseEntity.ok(campaign);
    }


    @PostMapping("/{accountId}")
    public ResponseEntity<Campaign> createCampaign(@PathVariable Long accountId, @Valid @RequestBody Campaign campaign){
        Campaign newCampaign = campaignService.save(accountId,campaign);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCampaign);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable Long id, @Valid @RequestBody Campaign campaignDetails){
        Campaign updatedCampaign = campaignService.update(id,campaignDetails);
        return ResponseEntity.ok(updatedCampaign);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id){
        campaignService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
