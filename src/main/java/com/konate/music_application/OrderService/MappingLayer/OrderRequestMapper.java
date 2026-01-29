package com.konate.music_application.OrderService.MappingLayer;
import com.konate.music_application.OrderService.DataLayer.Order;
import com.konate.music_application.OrderService.DataLayer.OrderIdentifier;
import com.konate.music_application.OrderService.PresentationLayer.OrderRequestModel;
import com.konate.music_application.UserService.DataLayer.UserIdentifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(orderIdentifier)", target = "orderIdentifier"),
            @Mapping(expression = "java(userIdentifier)", target = "userIdentifier"),
            @Mapping(source = "requestModel.orderStatus", target = "orderStatus"),
            @Mapping(source = "requestModel.orderItems", target = "orderItems"),
            @Mapping(source = "requestModel.payments", target = "payments")

    })
    Order toOder(OrderRequestModel requestModel,
                 OrderIdentifier orderIdentifier,
                 UserIdentifier userIdentifier);
}

