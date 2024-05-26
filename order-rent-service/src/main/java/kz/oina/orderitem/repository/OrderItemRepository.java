package kz.oina.orderitem.repository;

import kz.oina.orderitem.entity.OrderItem;
import kz.oina.orderitem.entity.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    Collection<OrderItem> findByOrderId(UUID orderId);

    List<OrderItem> findAllByOrderId(UUID orderId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.renterId = :renterId AND oi.rental.status = :status")
    List<OrderItem> findAllByRenterIdAndRentalStatus(@Param("renterId") UUID renterId, @Param("status") RentalStatus status);
}
