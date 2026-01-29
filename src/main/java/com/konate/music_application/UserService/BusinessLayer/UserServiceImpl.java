package com.konate.music_application.UserService.BusinessLayer;

import com.konate.music_application.UserService.DataLayer.User;
import com.konate.music_application.UserService.DataLayer.UserIdentifier;
import com.konate.music_application.UserService.DataLayer.UserRepository;
import com.konate.music_application.Exceptions.NotFoundException;
import com.konate.music_application.Exceptions.UserFound;
import com.konate.music_application.UserService.MappingLayer.UserRequestMapper;
import com.konate.music_application.UserService.MappingLayer.UserResponseMapper;
import com.konate.music_application.UserService.PresentationLayer.UserRequestModel;
import com.konate.music_application.UserService.PresentationLayer.UserResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final UserRequestMapper userRequestMapper;

    public UserServiceImpl(UserRepository userRepository, UserResponseMapper userResponseMapper, UserRequestMapper userRequestMapper) {
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
        this.userRequestMapper = userRequestMapper;
    }

    @Override
    public UserResponseModel registerUser(UserRequestModel userRequestModel) {
        User existingUser = userRepository.findByUsername(userRequestModel.getUsername());
        if (existingUser != null) {
            throw new UserFound("User already exists: " + userRequestModel.getUsername());

        }

        User newUser = userRequestMapper.toUser(userRequestModel, new UserIdentifier());
        // Save the new user entity to the repository
        User savedUser = userRepository.save(newUser);
        // Map the saved user entity to the response model

        return userResponseMapper.toUserResponseModel(savedUser);
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        // Check if the user exists
        if (user != null) {
            // Delete the user from the repository
            userRepository.delete(user);
        } else {
            // Handle the case where the user does not exist, e.g., throw an exception or log a message
            throw new RuntimeException("User not found: " + username);
        }
    }

    @Override
    public UserResponseModel updateUser(String username, UserRequestModel userRequestModel) {
        User existingUser = userRepository.findByUsername(username);
        // Check if the user exists
        if (existingUser == null)
            throw new NotFoundException("User not found: " + username);

        // Map the request model to the user entity
        User updatedUser = userRequestMapper.toUser(userRequestModel, new UserIdentifier(existingUser.getUserIdentifier().getUserId()));
        // Set the ID of the existing user to ensure it updates the correct record
        updatedUser.setId(existingUser.getId());
        // Save the updated user entity to the repository
        User savedUser = userRepository.save(updatedUser);
        // Map the saved user entity to the response model
        UserResponseModel responseModel = userResponseMapper.toUserResponseModel(savedUser);
        // Add links to the response model
//            return userResponseMapper.addLinks(responseModel);

        // Handle the case where the user does not exist, e.g., throw an exception or log a message

        return responseModel;
    }

    @Override
    public List<UserResponseModel> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userResponseMapper.entityListResponseModels(users);
    }

    @Override
    public UserResponseModel getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        // Check if the user exists
        if (user == null)
            throw new NotFoundException("User not found: " + username);

        // Map the user entity to the response model
        // Add links to the response model
        return userResponseMapper.toUserResponseModel(user);
    }

    @Override
    public UserResponseModel changeUserPassword(String username, UserRequestModel model) {
        User user = userRepository.findByUsername(username);
        String newPassword = model.getPassword();
        // Check if the user exists
        if (user != null) {
            // Update the user's password
            user.setPassword(newPassword);
            // Save the updated user entity to the repository
            userRepository.save(user);
            return userResponseMapper.toUserResponseModel(user);
        } else {
            // Handle the case where the user does not exist, e.g., throw an exception or log a message
            throw new NotFoundException("User not found: " + username);
        }    }

    @Override
    public UserResponseModel getUserByEmail(String email) {
        if (email == null || email.isEmpty())
            throw new NotFoundException("Email cannot be null");
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new NotFoundException("User not found: " + email);
        return userResponseMapper.toUserResponseModel(user);
    }

    @Override
    public UserResponseModel getUserById(String id) {
        User user = userRepository.findAllByUserIdentifier_UserId(id);
        if (user == null)
            throw new NotFoundException("User not found: " + id);
        return userResponseMapper.toUserResponseModel(user);
    }
}
