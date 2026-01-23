package com.konate.music_application.UserService.MappingLayer;

import com.konate.music_application.UserService.DataLayer.User;
import com.konate.music_application.UserService.DataLayer.UserIdentifier;
import com.konate.music_application.UserService.PresentationLayer.UserRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    @Mappings(
            {
                    @Mapping(target = "id", ignore = true),
                    @Mapping(source = "requestModel.username", target = "username"),
                    @Mapping(source = "requestModel.email", target = "email"),
                    @Mapping(source = "requestModel.password", target = "password"),
                    @Mapping(source = "requestModel.fullname", target = "fullname"),
                    @Mapping(source = "requestModel.age", target = "age"),
                    @Mapping(source = "requestModel.country", target = "country"),
//                    @Mapping(source = "user.region", target = "region"),

                    @Mapping(expression = "java(userIdentifier)", target = "userIdentifier")
            }
    )
    User toUser(UserRequestModel requestModel, UserIdentifier userIdentifier);


}
