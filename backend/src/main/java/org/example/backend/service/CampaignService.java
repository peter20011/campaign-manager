package org.example.backend.service;

import org.example.backend.dto.CampaignRequest;
import org.example.backend.exceptions.ResourceNotFoundException;
import org.example.backend.models.Campaign;
import org.example.backend.models.EmeraldAccount;
import org.example.backend.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Service class for managing campaigns. Provides methods for creating, updating,
 * finding, and deleting campaigns, as well as interacting with the
 * {@link EmeraldAccountService} to ensure sufficient funds are available for
 * campaign creation and updates.
 */
@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private EmeraldAccountService emeraldAccountService;
    /**
     * Saves a new campaign. It checks if there are sufficient funds in the
     * EmeraldAccount before creating the campaign.
     *
     * @param campaignRequest the request containing campaign details
     * @return the saved {@link Campaign}
     * @throws IllegalArgumentException if there are insufficient funds
     */
    public Campaign save(CampaignRequest campaignRequest) {
        EmeraldAccount account = emeraldAccountService.getAccount();


        if (account.getBalance() < campaignRequest.campaignFund()) {
            throw new IllegalArgumentException("Insufficient funds in the account");
        }

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
    /**
     * Retrieves all campaigns.
     *
     * @return a list of {@link Campaign} objects
     */
    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }
    /**
     * Finds a campaign by its ID.
     *
     * @param id the ID of the campaign to find
     * @return the {@link Campaign} if found
     * @throws ResourceNotFoundException if no campaign is found with the given ID
     */
    public Campaign findById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + id));
    }
    /**
     * Deletes a campaign by its ID.
     *
     * @param id the ID of the campaign to delete
     */
    public void delete(Long id) {
        Campaign campaign = findById(id);
        campaignRepository.delete(campaign);
    }
    /**
     * Updates an existing campaign. It adjusts the EmeraldAccount balance if
     * the campaign fund is modified.
     *
     * @param id the ID of the campaign to update
     * @param campaignDetails the request containing updated campaign details
     * @return the updated {@link Campaign}
     * @throws IllegalArgumentException if there are insufficient funds for the update
     */
    public Campaign update(Long id, CampaignRequest campaignDetails) {
        Campaign existingCampaign = findById(id);
        Double oldFund = existingCampaign.getCampaignFund();
        Double newFund = campaignDetails.campaignFund();

        EmeraldAccount account = emeraldAccountService.getAccount();

        Double difference = newFund - oldFund;

        if (difference > 0) {
            if (account.getBalance() < difference) {
                throw new IllegalArgumentException("Insufficient funds for updating the campaign.");
            }
            emeraldAccountService.updateAccount(account.getBalance() - difference);

        } else if (difference < 0) {

            emeraldAccountService.updateAccount(account.getBalance() + Math.abs(difference));
        }

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
