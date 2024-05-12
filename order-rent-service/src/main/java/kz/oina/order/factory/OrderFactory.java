package kz.oina.order.factory;

import kz.oina.order.entity.Order;
import kz.oina.order.model.OrderDetails;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {

    public Order createOrder(OrderDetails orderDetails) {
        return new Order(orderDetails.userId());
    }
}
