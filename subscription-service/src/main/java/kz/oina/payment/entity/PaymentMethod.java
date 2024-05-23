package kz.oina.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class PaymentMethod {
    @Id
    private UUID id;
    private UUID userId;
    @Enumerated(EnumType.STRING)
    private Provider provider;
    private String details;

    public PaymentMethod(UUID userId, Provider provider, String details) {
        id = UUID.randomUUID();
        this.userId = userId;
        this.provider = provider;
        this.details = details;
    }
}
