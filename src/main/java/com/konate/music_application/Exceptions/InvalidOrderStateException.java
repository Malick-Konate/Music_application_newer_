package com.konate.music_application.Exceptions;

import com.konate.music_application.OrderService.DataLayer.OrderStatus;

public class InvalidOrderStateException extends RuntimeException {
    public InvalidOrderStateException(String orderId, OrderStatus status) {
        super("Order " + orderId + " cannot be modified because it is in state: " + status);
    }

    public InvalidOrderStateException(String message) {
        super(message);
    }
}
