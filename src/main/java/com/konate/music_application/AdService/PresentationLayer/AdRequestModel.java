package com.konate.music_application.AdService.PresentationLayer;

import com.konate.music_application.AdService.DataLayer.AdCreative;
import com.konate.music_application.AdService.DataLayer.AdTarget;
import com.konate.music_application.AdService.DataLayer.CampaignStatus;
import com.konate.music_application.AdService.DataLayer.TargetingRules;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdRequestModel {
    String name;
    BigDecimal budget;
    BigDecimal remainingSpend;
    CampaignStatus status;
    List<AdCreative> creatives;
    List<TargetingRules> targetingRules;

    //from the other services
    String userEmail;
//    String artistLastName;
//    String podcastTitle;

    AdTarget adTarget;
}
