package com.konate.music_application.OrderService.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Embeddable
@Getter
@NoArgsConstructor
public class OrderItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    ProductType productType;

    @Column(name = "display_name")
    String displayName;  // Will be Album Title, Podcast Title, or Artist Name

    @Column(name = "price")
    BigDecimal price;    // The price at that exact second

    @Column(name = "quantity")
    Integer quantity;

    // Optional: Reference to the Artist ID for all types
    @Column(name = "artist_name")
    String artistName;

    //Product Type	Typical Price (USD/CAD)
    //ALBUM_PURCHASE	$9.99 – $14.99
    //PODCAST_SUBSCRIPTION	$4.99 – $9.99
    //SINGLE_EPISODE	$0.99 – $1.99
    //ARTIST_DONATION	$1.00 – ∞	This is the "Pay-What-You-Want" model


    public OrderItem(ProductType productType, String displayName, String artistName,
                     BigDecimal price, Integer quantity) {
        this.productType = productType;
        this.displayName = displayName;
        this.artistName = artistName;
        this.price = price;
        this.quantity = quantity;
    }

    // Helper method to calculate subtotal for this item
    public BigDecimal getSubTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }


}
