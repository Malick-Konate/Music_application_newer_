package com.konate.music_application.AdService.PresentationLayer;

import com.konate.music_application.AdService.DataLayer.AdCreative;
import com.konate.music_application.AdService.DataLayer.CampaignStatus;
import com.konate.music_application.AdService.DataLayer.TargetingRules;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdResponseModel extends RepresentationModel<AdResponseModel> {
    String ad_id;
    String name;
    BigDecimal budget;
    BigDecimal remainingSpend;
    CampaignStatus status;
    List<AdCreative> creatives;
    List<TargetingRules> targetingRules;

    //from the other services
    String userName;
//    String artistName;
    String targetName;
    String targetHostnameOrArtist;
}
