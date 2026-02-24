package com.konate.music_application.presentationLayer.ArtistService;

import com.konate.music_application.ArtistService.Presentation.ArtistRequestModel;
import com.konate.music_application.ArtistService.Presentation.ArtistResponseModel;
import com.konate.music_application.ArtistService.dataLayer.Genre;
import com.konate.music_application.ArtistService.dataLayer.SocialMediaLink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Sql({"/data.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ArtistControllerIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    private final String BASE_URI = "/api/v1/artists";
    private final String VALID_ARTIST_ID = "ART-006";
    private String INVALID_ARTIST_ID = "artist";

    @Test
    void whenArtistExist_thenReturnAllArtist() {
        webTestClient.get()
                .uri(BASE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ArtistResponseModel.class)
                .value(artist -> {
                    assertNotNull(artist);
                    assertFalse(artist.isEmpty());
                });
    }

    @Test
    void whenValidRequest_thenCreateArtist() {
        ArtistRequestModel artist = buildSampleArtist();

        webTestClient.post()
                .uri(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(artist)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ArtistResponseModel.class)
                .value(created -> {
                    assertNotNull(created);
                    assertEquals("Firstname", created.getFirstName());
                    assertEquals("Lastname", created.getLastName());
                });
    }

    @Test
    void whenArtistDeleted_thenReturnNoContext() {
        webTestClient.delete()
                .uri(BASE_URI + "/" + VALID_ARTIST_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void whenValidArtistId_thenReturnArtist() {
        webTestClient.get()
                .uri(BASE_URI + "/" + VALID_ARTIST_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ArtistResponseModel.class)
                .value(artist ->{
                    assertNotNull(artist);
//                    assertEquals("Firstname", artist.getFirstName());
                    assertEquals(VALID_ARTIST_ID, artist.getArtistIdentifier());
                });


    }

    @Test
    void whenUpdatedArtist_thenReturnUpdatedArtist(){
        ArtistRequestModel updatedArtist = buildSampleArtist();

        webTestClient.put()
                .uri(BASE_URI + "/" + VALID_ARTIST_ID)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(updatedArtist)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ArtistResponseModel.class)
                .value(response -> assertEquals("Firstname", response.getFirstName()));


    }
//    @Test
//    void whenUpdatedInvalid_thenReturnNotFound(){
//        ArtistRequestModel updatedArtist = buildSampleArtist();
//        webTestClient.put()
//                .uri(BASE_URI + "/" + INVALID_ARTIST_ID)
//                .accept(MediaType.APPLICATION_JSON)
//                .bodyValue(updatedArtist)
//                .exchange()
//                .expectStatus().isNotFound();
//
//
//    }


    private ArtistRequestModel buildSampleArtist() {
        return ArtistRequestModel.builder()
                .firstName("Firstname")
                .lastName("Lastname")
                .biography("this is the biography")
                .socialMediaLinks(List.of(
                        new SocialMediaLink("platform", "URI")
                ))
                .genres(List.of(
                        new Genre("Genre")
                ))
                .build();
    }

}
