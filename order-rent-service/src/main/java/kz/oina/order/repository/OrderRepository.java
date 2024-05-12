package kz.oina.order.repository;

import kz.oina.order.entity.Order;
import kz.oina.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {


    @Query("SELECT o FROM Order o WHERE o.status = :status AND o.updatedAt <= :cutoff")
    List<Order> findOrdersByStatusAndUpdatedBefore(OrderStatus status, LocalDateTime cutoff);
}
