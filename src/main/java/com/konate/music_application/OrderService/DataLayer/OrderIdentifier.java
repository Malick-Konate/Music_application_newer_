package com.konate.music_application.OrderService.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import static java.util.UUID.randomUUID;

@Embeddable
@Getter
public class OrderIdentifier {
    @Column(name = "order_id")
    private String orderId;

    public OrderIdentifier(){
        this.orderId = "order_"+ randomUUID().toString();
    }
    public OrderIdentifier(String orderId){
        this.orderId = orderId;
    }
}
