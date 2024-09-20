package org.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.aspectj.weaver.ast.Not;
/**
 * Record representing a request to create or update a Campaign.
 *
 * @param campaignName the name of the campaign, must not be blank
 * @param keywords the keywords for the campaign, must not be blank
 * @param bidAmount the bid amount for the campaign, must be greater than 0
 * @param campaignFund the fund allocated for the campaign, must be greater than 0
 * @param status the status of the campaign (true for active, false for inactive)
 * @param town the town for the campaign, must not be blank
 * @param radius the radius for the campaign in kilometers, must be greater than 0
 */
public record CampaignRequest(
        @NotBlank(message = "Campaign name is mandatory")
        String campaignName,

        @NotBlank(message = "Keywords are mandatory")
        String keywords,

        @NotNull(message = "Bid amount is mandatory")
        @Positive(message = "Bid amount must be greater than 0")
        Double bidAmount,

        @NotNull(message = "Campaign fund is mandatory")
        @Positive(message = "Campaign fund must be greater than 0")
        Double campaignFund,

        @NotNull(message = "Status is mandatory")
        Boolean status,

        @NotBlank(message = "Town is mandatory")
        String town,

        @NotNull(message = "Radius is mandatory")
        @Positive(message = "Radius must be greater than 0")
        Integer radius
) {}