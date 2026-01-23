package com.konate.music_application.UserService.PresentationLayer;

import com.konate.music_application.UserService.BusinessLayer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    // Add methods to handle user-related requests, e.g., create, update, delete, get users
    // Example:
    @PostMapping
    public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userRequestModel) {
        UserResponseModel createdUser = userService.registerUser(userRequestModel);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseModel>> getAllUsers() {
        List<UserResponseModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseModel> getUserByUsername(@PathVariable String username) {
        UserResponseModel user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponseModel> updateUser(@PathVariable String username, @RequestBody UserRequestModel userRequestModel) {
        UserResponseModel updatedUser = userService.updateUser(username, userRequestModel);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/{username}/roles/{role}")
//    public ResponseEntity<Void> assignRoleToUser(@PathVariable String username, @PathVariable String role) {
//        userService.assignRoleToUser(username, role);
//        return ResponseEntity.noContent().build();
//    }

    @PostMapping("/{username}/password")
    public ResponseEntity<Void> changeUserPassword(@PathVariable String username, UserRequestModel requestModel) {
        userService.changeUserPassword(username, requestModel);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseModel> getUserByEmail(@PathVariable String email) {
        UserResponseModel users = userService.getUserByEmail(email);
        return ResponseEntity.ok(users);
    }

}
