package org.example.backend.service;


import jakarta.transaction.Transactional;
import org.example.backend.exceptions.ResourceNotFoundException;
import org.example.backend.models.Campaign;
import org.example.backend.models.EmeraldAccount;
import org.example.backend.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@Transactional
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private EmeraldAccountService emeraldAccountService;


    public Campaign save(Long accountId, Campaign campaign){
        EmeraldAccount account = emeraldAccountService.getAccount(accountId);

        if(account.getBalance().compareTo(campaign.getCampaignFund()) < 0){
            throw new IllegalArgumentException("Insufficient funds");
        }

        emeraldAccountService.updateBalance(accountId, campaign.getCampaignFund());

        campaign.setEmeraldAccount(account);


        return campaignRepository.save(campaign);
    }


    public List<Campaign> findAll(){
        return campaignRepository.findAll();
    }


    public Campaign findById( Long id){
        return campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Campaign not found with id: " + id));
    }


    public void delete(Long id){
        Campaign campaign = findById(id);
        campaignRepository.delete(campaign);
    }


    public Campaign update(Long id,Campaign campaignDetails){
        Campaign existingCampaign = findById(id);
        EmeraldAccount account = existingCampaign.getEmeraldAccount();

        BigDecimal oldFund = existingCampaign.getCampaignFund();
        BigDecimal newFund = campaignDetails.getCampaignFund();

        if (newFund.compareTo(oldFund) > 0) {
            BigDecimal difference = newFund.subtract(oldFund);

            if (account.getBalance().compareTo(difference) < 0) {
                throw new IllegalArgumentException("Insufficient funds on Emerald account for the update.");
            }

            emeraldAccountService.updateBalance(account.getId(), difference);
        } else if (newFund.compareTo(oldFund) < 0) {
            BigDecimal difference = oldFund.subtract(newFund);
            account.setBalance(account.getBalance().add(difference));
            emeraldAccountService.updateBalance(account.getId(), BigDecimal.ZERO); // Aktualizacja salda konta
        }

        existingCampaign.setCampaignName(campaignDetails.getCampaignName());
        existingCampaign.setKeywords(campaignDetails.getKeywords());
        existingCampaign.setBidAmount(campaignDetails.getBidAmount());
        existingCampaign.setCampaignFund(newFund);
        existingCampaign.setStatus(campaignDetails.isStatus());
        existingCampaign.setTown(campaignDetails.getTown());
        existingCampaign.setRadius(campaignDetails.getRadius());

        return campaignRepository.save(existingCampaign);
    }
}
