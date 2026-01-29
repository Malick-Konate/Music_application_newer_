package com.konate.music_application.OrderService.DataLayer;

import com.konate.music_application.UserService.DataLayer.UserIdentifier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Embedded
    private OrderIdentifier orderIdentifier;

    @Embedded
    private UserIdentifier userIdentifier;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    )
    List<OrderItem> orderItems;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "payment",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    )
    List<Payment> payments;
}
