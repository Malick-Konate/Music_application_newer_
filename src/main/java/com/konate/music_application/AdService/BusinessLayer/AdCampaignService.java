package com.konate.music_application.AdService.BusinessLayer;

import com.konate.music_application.AdService.PresentationLayer.AdRequestModel;
import com.konate.music_application.AdService.PresentationLayer.AdResponseModel;

import java.util.List;

public interface AdCampaignService {
    AdResponseModel createAdCampaign(AdRequestModel adRequestModel);

    AdResponseModel updateAdCampaign(String adId,AdRequestModel adRequestModel);

    void deleteAdCampaign(String adId);

    AdResponseModel getAdCampaign(String adId);

    List<AdResponseModel> getAllAdCampaigns();

    AdResponseModel getAdCampaignsByUser(String userEmail);

//    List<AdResponseModel> getAdCampaignsByArtist(String artistId);
//
//    List<AdResponseModel> getAdCampaignsByPodcast(String podcastId);

    void activateAdCampaign(String adId);

    void pauseAdCampaign(String adId);

    void resumeAdCampaign(String adId);
}
