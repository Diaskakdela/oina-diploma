package kz.oina.orderitem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "inventory_item_id", nullable = false)
    private UUID inventoryItemId;

    @Column(name = "toy_id", nullable = false)
    private UUID toyId;

    @Column(name = "renter_id", nullable = false)
    private UUID renterId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;

    public OrderItem(UUID orderId, UUID inventoryItemId, UUID renterId, UUID toyId) {
        this.id = UUID.randomUUID();
        this.orderId = orderId;
        this.inventoryItemId = inventoryItemId;
        this.renterId = renterId;
        this.toyId = toyId;
        this.rental = new Rental();
    }

    public void cancelOrderItemRental() {
        rental.cancelRental();
    }

    public void startOrderItemRental() {
        rental.startRental();
    }

    public void endOrderItemRental() {
        rental.returnRental();
    }

    public String getRentalStatus() {
        return rental.getStatus().name();
    }
}
