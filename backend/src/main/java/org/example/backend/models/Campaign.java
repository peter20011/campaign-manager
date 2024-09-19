package org.example.backend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;


@Entity
@Table(name = "campaigns")
@Setter
@Getter
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campaign name is mandatory")
    @Size(min = 2, max = 255, message = "Campaign name should be between 2 and 255 characters")
    @Column(nullable = false)
    private String campaignName;

    @NotNull(message = "Keywords are mandatory")
    @Column(nullable = false)
    private String keywords;

    @NotNull(message = "Bid amount is mandatory")
    @DecimalMin(value = "0.01", message = "Bid amount must be greater than 0.01")
    @Column(nullable = false)
    private BigDecimal bidAmount;

    @NotNull(message = "Campaign fund is mandatory")
    @DecimalMin(value = "0.01", message = "Campaign fund must be greater than 0.01")
    @Column(nullable = false)
    private BigDecimal campaignFund;

    @NotNull(message = "Status is mandatory")
    @Column(nullable = false)
    private boolean status;

    @NotNull(message = "Town is mandatory")
    @Column(nullable = false)
    private String town;

    @Min(value = 1, message = "Radius must be at least 1 km")
    @Column(nullable = false)
    private int radius;

    @ManyToOne
    @JoinColumn(name = "emerald_account_id", nullable = false)
    private EmeraldAccount emeraldAccount;


    public Campaign() {}

    public Campaign(String campaignName, String keywords, BigDecimal bidAmount, BigDecimal campaignFund, boolean status, String town, int radius) {
        this.campaignName = campaignName;
        this.keywords = keywords;
        this.bidAmount = bidAmount;
        this.campaignFund = campaignFund;
        this.status = status;
        this.town = town;
        this.radius = radius;
    }
}
