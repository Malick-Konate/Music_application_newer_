package com.konate.music_application.UserService.PresentationLayer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel {
    String username;
    String email;
    String password;
    String fullname;
    String age;
    String country;
//    String region;
}
