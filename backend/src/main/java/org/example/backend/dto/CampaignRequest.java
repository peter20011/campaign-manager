package org.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.aspectj.weaver.ast.Not;

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