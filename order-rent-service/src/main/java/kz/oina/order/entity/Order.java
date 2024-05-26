package kz.oina.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "renter_id", nullable = false)
    private UUID renterId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    public Order(UUID renterId) {
        this.id = UUID.randomUUID();
        this.renterId = renterId;
        this.status = OrderStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void cancelOrder() {
        if (this.status != OrderStatus.PENDING) {
            throw new IllegalStateException("Cannot cancel order because status is not PENDING");
        }
        this.status = OrderStatus.CANCELED;
        this.updatedAt = LocalDateTime.now();
    }


    public void payOrder() {
        if (this.status != OrderStatus.PENDING) {
            throw new IllegalStateException("Cannot pay order because status is not PENDING");
        }
        this.status = OrderStatus.PAID;
        this.updatedAt = LocalDateTime.now();
    }

    public void rentOrder() {
        if (this.status != OrderStatus.PAID) {
            throw new IllegalStateException("Cannot rent order because status is not PAID");
        }
        this.status = OrderStatus.RENTED;
        this.updatedAt = LocalDateTime.now();
    }
}
