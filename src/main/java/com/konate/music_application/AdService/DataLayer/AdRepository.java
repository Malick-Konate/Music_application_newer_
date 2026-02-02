package com.konate.music_application.AdService.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<AdCampaign, Integer> {
    AdCampaign findAllByAdIdentifier_AdId(String adId);

    AdCampaign findAllByName(String name);
    List<AdCampaign> findAllByUserIdentifier_UserId(String userId);
//    AdCampaign findAllByArtistIdentifier_ArtistId(String artistId);
//    AdCampaign findAllByPodcastIdentifier_PodcastId(String podcastId);
}
