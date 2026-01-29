package com.konate.music_application.OrderService.MappingLayer;

//import org.mapstruct.Mapper;
import com.konate.music_application.OrderService.DataLayer.Order;
import com.konate.music_application.OrderService.PresentationLayer.OrderController;
import com.konate.music_application.OrderService.PresentationLayer.OrderResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {
    @Mappings({
            @Mapping(source = "orderIdentifier.orderId", target = "orderId"),
            @Mapping(source = "orderStatus", target = "orderStatus"),
            @Mapping(source = "orderItems", target = "orderItems"),
            @Mapping(source = "payments", target = "payments")


    })
    OrderResponseModel toRespondModel(Order order);

    List<OrderResponseModel> entityListToRespondModel (List<Order> orders);

    @AfterMapping
    default void selfLinks (OrderResponseModel responseModel, @MappingTarget Order order){
        Link selfLink = linkTo(methodOn(OrderController.class)
                .getOrderById(order.getOrderIdentifier()
                        .getOrderId())).withSelfRel();

        responseModel.add(selfLink);
    }

}
