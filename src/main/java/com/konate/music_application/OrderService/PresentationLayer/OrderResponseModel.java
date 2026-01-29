package com.konate.music_application.OrderService.PresentationLayer;

import com.konate.music_application.ArtistService.Presentation.ArtistResponseModel;
import com.konate.music_application.OrderService.DataLayer.OrderItem;
import com.konate.music_application.OrderService.DataLayer.OrderStatus;
import com.konate.music_application.OrderService.DataLayer.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseModel extends RepresentationModel<ArtistResponseModel> {

    String orderId;

    //from user service
    String email;
    String fullname;
    String country;

    //enum
    OrderStatus orderStatus;

    //from the embedded objects
    List<OrderItem> orderItems;
    List<Payment> payments;


}
