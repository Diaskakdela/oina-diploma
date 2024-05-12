package kz.oina.orderitem.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Getter
    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    @Column(name = "rent_start_date")
    private LocalDateTime rentStartDate;

    @Column(name = "actual_return_date")
    private LocalDateTime actualReturnDate;

    @PrePersist
    protected void onCreate() {
        rentStartDate = LocalDateTime.now();
    }

    public Rental() {
        this.id = UUID.randomUUID();
        this.status = RentalStatus.PENDING;
    }

    public void startRental() {
        this.status = RentalStatus.ACTIVE;
        this.rentStartDate = LocalDateTime.now();
    }

    public void returnRental() {
        this.status = RentalStatus.COMPLETED;
        this.actualReturnDate = LocalDateTime.now();
    }

    public void cancelRental() {
        this.status = RentalStatus.CANCELED;
    }

}
