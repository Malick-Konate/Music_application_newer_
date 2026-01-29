package com.konate.music_application.OrderService.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
public class Payment {
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "currency")
    private String currency;

    public Payment(BigDecimal amount, LocalDateTime paidAt, PaymentMethod method, PaymentStatus paymentStatus, String currency) {
        this.amount = amount;
        this.currency = currency;
        this.paidAt = paidAt;
        this.method = method;
        this.paymentStatus = paymentStatus;
    }
}
