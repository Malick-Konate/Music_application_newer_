package com.konate.music_application.UserService.MappingLayer;

//import org.mapstruct.Mapper;

import com.konate.music_application.UserService.DataLayer.User;
import com.konate.music_application.UserService.PresentationLayer.UserController;
import com.konate.music_application.UserService.PresentationLayer.UserResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    @Mappings(
            {
                    @Mapping(source = "userIdentifier.userId", target = "userId"),
                    @Mapping(source = "user.username", target = "username"),
                    @Mapping(source = "user.email", target = "email"),
                    @Mapping(source = "user.fullname", target = "fullname")
            }
    )
    UserResponseModel toUserResponseModel(User user);

    List<UserResponseModel> entityListResponseModels(List<User> users);


    @AfterMapping
    default void addLinks(@MappingTarget UserResponseModel userResponseModel) {
        Link selfLink = linkTo(methodOn(UserController.class)
                .getUserByUsername(userResponseModel.getUsername()))
                .withSelfRel();
        userResponseModel.add(selfLink);
    }

}
