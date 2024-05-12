package kz.oina.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "toy_id")
    private UUID toyId;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InventoryStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public InventoryItem(UUID toyId, String location) {
        this.id = UUID.randomUUID();
        this.toyId = toyId;
        this.location = location;
        this.status = InventoryStatus.AVAILABLE;
    }

    public void rentItem() {
        if (!(this.status == InventoryStatus.RESERVED || this.status == InventoryStatus.AVAILABLE))
            throw new IllegalArgumentException("You can only rent reserved items");
        this.status = InventoryStatus.RENTED;
    }

    public void reserveItem() {
        if (this.status != InventoryStatus.AVAILABLE)
            throw new IllegalArgumentException("You can only reserve available items");
        this.status = InventoryStatus.RESERVED;
    }

    public void cancelReservation() {
        if (this.status != InventoryStatus.RESERVED)
            throw new IllegalArgumentException("You can only cancel reserved items");
        this.status = InventoryStatus.AVAILABLE;
    }


    public void returnItem() {
        if (!InventoryStatus.UNAVAILABLE_STATUSES.contains(this.status)) {
            throw new IllegalArgumentException("You can only return items in unavailable statuses");
        }
        this.status = InventoryStatus.AVAILABLE;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
