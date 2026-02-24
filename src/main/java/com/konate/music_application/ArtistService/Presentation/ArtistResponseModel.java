package com.konate.music_application.ArtistService.Presentation;

import com.konate.music_application.ArtistService.dataLayer.Genre;
import com.konate.music_application.ArtistService.dataLayer.SocialMediaLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistResponseModel extends RepresentationModel<ArtistResponseModel> {
    private String artistIdentifier;
    private String firstName;
    private String lastName;
    private List<Genre> genres;
    private List<SocialMediaLink> socialMediaLinks;
    private String biography;
}
