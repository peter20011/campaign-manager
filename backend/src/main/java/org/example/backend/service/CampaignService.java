package org.example.backend.service;

import org.example.backend.dto.CampaignRequest;
import org.example.backend.exceptions.ResourceNotFoundException;
import org.example.backend.models.Campaign;
import org.example.backend.models.EmeraldAccount;
import org.example.backend.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private EmeraldAccountService emeraldAccountService;

    // Save a new campaign
    public Campaign save(CampaignRequest campaignRequest) {
        EmeraldAccount account = emeraldAccountService.getAccount();

        // Check if there are sufficient funds
        if (account.getBalance() < campaignRequest.campaignFund()) {
            throw new IllegalArgumentException("Insufficient funds in the account");
        }

        // Deduct campaign fund from account balance
        emeraldAccountService.updateAccount(account.getBalance() - campaignRequest.campaignFund());

        Campaign campaign = new Campaign();
        campaign.setCampaignName(campaignRequest.campaignName());
        campaign.setKeywords(campaignRequest.keywords());
        campaign.setBidAmount(campaignRequest.bidAmount());
        campaign.setCampaignFund(campaignRequest.campaignFund());
        campaign.setStatus(campaignRequest.status());
        campaign.setTown(campaignRequest.town());
        campaign.setRadius(campaignRequest.radius());

        return campaignRepository.save(campaign);
    }

    // Get all campaigns
    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    // Find a campaign by ID
    public Campaign findById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + id));
    }

    // Delete a campaign
    public void delete(Long id) {
        Campaign campaign = findById(id);
        campaignRepository.delete(campaign);
    }

    // Update an existing campaign
    public Campaign update(Long id, CampaignRequest campaignDetails) {
        Campaign existingCampaign = findById(id);
        Double oldFund = existingCampaign.getCampaignFund();
        Double newFund = campaignDetails.campaignFund();

        EmeraldAccount account = emeraldAccountService.getAccount();

        // Calculate the difference between the old and new campaign funds
        Double difference = newFund - oldFund;

        // If the new fund is greater than the old fund (i.e., increasing the fund)
        if (difference > 0) {
            if (account.getBalance() < difference) {
                throw new IllegalArgumentException("Insufficient funds for updating the campaign.");
            }
            // Deduct the difference from the account balance
            emeraldAccountService.updateAccount(account.getBalance() - difference);

        } else if (difference < 0) {
            // If the new fund is smaller than the old fund (i.e., decreasing the fund)
            // Add the difference back to the account balance (since the difference will be negative, this adds the absolute value)
            emeraldAccountService.updateAccount(account.getBalance() + Math.abs(difference));
        }

        // Update campaign fields
        existingCampaign.setCampaignName(campaignDetails.campaignName());
        existingCampaign.setKeywords(campaignDetails.keywords());
        existingCampaign.setBidAmount(campaignDetails.bidAmount());
        existingCampaign.setCampaignFund(newFund);
        existingCampaign.setStatus(campaignDetails.status());
        existingCampaign.setTown(campaignDetails.town());
        existingCampaign.setRadius(campaignDetails.radius());

        return campaignRepository.save(existingCampaign);
    }
}
