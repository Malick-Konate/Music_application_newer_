package com.konate.music_application.AdService.PresentationLayer;

import com.konate.music_application.AdService.BusinessLayer.AdCampaignService;
import com.konate.music_application.AdService.DataLayer.AdCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ads")
public class AdCampaignController {

    private final AdCampaignService adCampaignService;

    @Autowired
    public AdCampaignController(AdCampaignService adCampaignService) {
        this.adCampaignService = adCampaignService;
    }

    @GetMapping
    public ResponseEntity<List<AdResponseModel>> getAllAdCampaigns() {
        return new ResponseEntity<>(adCampaignService.getAllAdCampaigns(), HttpStatus.OK);
    }

    @GetMapping("/{adId}")
    public ResponseEntity<AdResponseModel> getAdCampaign(@PathVariable String adId) {
        return new ResponseEntity<>(adCampaignService.getAdCampaign(adId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AdResponseModel> createCampaign(@RequestBody AdRequestModel adCampaign) {
        return new ResponseEntity<>(adCampaignService.createAdCampaign(adCampaign), HttpStatus.CREATED);
    }

    @PutMapping("/{adId}")
    public ResponseEntity<AdResponseModel> updateCampaign(@PathVariable String adId, @RequestBody AdRequestModel adCampaign) {
        return new ResponseEntity<>(adCampaignService.updateAdCampaign(adId, adCampaign), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{adId}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable String adId){
        adCampaignService.deleteAdCampaign(adId);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{adId}/activate")
    public ResponseEntity<Void> activate(@PathVariable String adId){
        adCampaignService.activateAdCampaign(adId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{adId}/pause")
    public ResponseEntity<Void> pause(@PathVariable String adId){
        adCampaignService.pauseAdCampaign(adId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{adId}/resume")
    public ResponseEntity<Void> resume(@PathVariable String adId){
        adCampaignService.resumeAdCampaign(adId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
