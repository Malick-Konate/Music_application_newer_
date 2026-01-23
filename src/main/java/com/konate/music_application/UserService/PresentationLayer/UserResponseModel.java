package com.konate.music_application.UserService.PresentationLayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel extends RepresentationModel<UserResponseModel> {
    String userId;
    String username;
    String email;
    String fullname;
    String age;
    String country;
//    String region;
}
