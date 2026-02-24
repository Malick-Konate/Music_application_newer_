package com.konate.music_application.UserService.PresentationLayer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestModel {
    String username;
    String email;
    String password;
    String fullname;
    int age;
    String country;
//    String region;
}
