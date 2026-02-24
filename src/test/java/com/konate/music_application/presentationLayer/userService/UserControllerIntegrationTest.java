package com.konate.music_application.presentationLayer.userService;

import com.konate.music_application.UserService.PresentationLayer.UserRequestModel;
import com.konate.music_application.UserService.PresentationLayer.UserResponseModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Sql({"/data.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    private final String BASE_URI = "api/v1/users";
    private final String VALID_USER_ID = "user-007";
    private final String VALID_USER_NAME = "malick";
    private final String INVALID_USER_ID = "userId";

    @Test
    void whenUserExist_thenReturnAllUsers() {
        webTestClient.get()
                .uri(BASE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(UserResponseModel.class)
                .value(user -> {
                    assertNotNull(user);
                    assertFalse(user.isEmpty());
                });
    }

    @Test
    void whenValidRequest_thenCreateUser(){
        UserRequestModel user = buildSampleUser();

        webTestClient.post()
                .uri(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserResponseModel.class)
                .value(created ->{
                    assertNotNull(created);
                    assertEquals("username", created.getUsername());
                    assertEquals("email", created.getEmail());
                });
    }

    @Test
    void whenUserDeletet_thenReturnNoContext(){
        webTestClient.delete()
                .uri(BASE_URI + "/" + VALID_USER_NAME)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }

    private UserRequestModel buildSampleUser(){
        return UserRequestModel.builder()
                .username("username")
                .email("email")
                .password("password")
                .fullname("full name")
                .age(22)
                .country("country")
                .build();
    }
}
