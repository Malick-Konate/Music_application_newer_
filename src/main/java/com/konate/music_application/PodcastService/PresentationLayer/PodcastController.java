package com.konate.music_application.PodcastService.PresentationLayer;


import com.konate.music_application.PodcastService.BusinessLayer.Episode.EpisodeService;
import com.konate.music_application.PodcastService.BusinessLayer.Podcast.PodcastService;
import com.konate.music_application.PodcastService.PresentationLayer.Episode.EpisodeRequestModel;
import com.konate.music_application.PodcastService.PresentationLayer.Episode.EpisodeResponseModel;
import com.konate.music_application.PodcastService.PresentationLayer.Podcast.PodcastRequestModel;
import com.konate.music_application.PodcastService.PresentationLayer.Podcast.PodcastResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/podcasts")
public class PodcastController {
    private final PodcastService podcastService;
    private final EpisodeService episodeService;

    @Autowired
    public PodcastController(PodcastService podcastService, EpisodeService episodeService) {
        this.podcastService = podcastService;
        this.episodeService = episodeService;
    }

    @GetMapping
    public ResponseEntity<List<PodcastResponseModel>> getAllPodcast() {
        return new ResponseEntity<>(podcastService.getAllPodcast(), HttpStatus.OK);
    }

    @GetMapping("/{podcastId}")
    public ResponseEntity<PodcastResponseModel> getPodcastById(@PathVariable String podcastId) {
        return new ResponseEntity<>(podcastService.getPodcastById(podcastId), HttpStatus.OK);
    }

    @GetMapping("/{podcastId}/episodes")
    public ResponseEntity<List<EpisodeResponseModel>> getEpisodesByPodcastId(@PathVariable String podcastId) {
        return new ResponseEntity<>(episodeService.getEpisodesByPodcastId(podcastId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PodcastResponseModel> createPodcast(@RequestBody PodcastRequestModel requestModel) {
        return new ResponseEntity<>(podcastService.createPodcast(requestModel), HttpStatus.CREATED);
    }

    @PutMapping("/{podcastId}")
    public ResponseEntity<PodcastResponseModel> updatePodcast(@PathVariable String podcastId, @RequestBody PodcastRequestModel requestModel) {
        return new ResponseEntity<>(podcastService.updatePodcast(podcastId, requestModel), HttpStatus.OK);
    }

    @DeleteMapping("/{podcastId}")
    public ResponseEntity<Void> deletePodcast(@PathVariable String podcastId) {
        podcastService.deletePodcast(podcastId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/by-host/{hostname}")
    public ResponseEntity<List<PodcastResponseModel>> getPodcastByHostname(@PathVariable String hostname) {
        return new ResponseEntity<>(podcastService.getPodcastByHosname(hostname), HttpStatus.OK);
    }

    @GetMapping("/{podcastId}/episodes/{episodeId}")
    public ResponseEntity<EpisodeResponseModel> getEpisodeById(@PathVariable String podcastId, @PathVariable String episodeId) {
        return new ResponseEntity<>(episodeService.getEpisodeById(podcastId, episodeId), HttpStatus.OK);
    }

    @PostMapping("/{podcastId}/episodes")
    public ResponseEntity<EpisodeResponseModel> createEpisode(@PathVariable String podcastId, @RequestBody EpisodeRequestModel requestModel) {
        return new ResponseEntity<>(episodeService.createEpisode(podcastId, requestModel), HttpStatus.CREATED);
    }

    @PutMapping("/{podcastId}/episodes/{episodeId}")
    public ResponseEntity<EpisodeResponseModel> updateEpisode(@PathVariable String podcastId, @PathVariable String episodeId, @RequestBody EpisodeRequestModel requestModel) {
        return new ResponseEntity<>(episodeService.updateEpisode(podcastId, episodeId, requestModel), HttpStatus.OK);
    }

    @DeleteMapping("/{podcastId}/episodes/{episodeId}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable String episodeId, @PathVariable String podcastId) {
        episodeService.deleteEpisode(episodeId, podcastId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
