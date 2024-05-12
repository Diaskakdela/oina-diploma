package kz.oina.order.web;


import kz.oina.core.web.ApiResponse;
import kz.oina.order.exceptions.OrderNotFoundException;
import kz.oina.order.model.OrderMapper;
import kz.oina.order.service.OrderService;
import kz.oina.order.web.request.OrderCreationRequest;
import kz.oina.order.web.request.PayOrderRequest;
import kz.oina.order.web.request.RentOrderRequest;
import kz.oina.order.web.response.OrderDTO;
import kz.oina.order.web.response.OrderWithOrderItemsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderWithOrderItemsDTO>> createOrder(@RequestBody OrderCreationRequest orderCreationRequest) {
        try {
            var orderWithOrderItems = orderService.createOrder(orderMapper.toOrderCreationParam(orderCreationRequest));
            var orderWithOrderItemsDTO = orderMapper.mapOrderWithItems(orderWithOrderItems);
            return ResponseEntity.ok(ApiResponse.success(orderWithOrderItemsDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("pay")
    public ResponseEntity<ApiResponse<OrderDTO>> payForOrder(@RequestBody PayOrderRequest payOrderRequest) {
        try {
            var order = orderService.payOrder(payOrderRequest.orderId());
            return ResponseEntity.ok(ApiResponse.success(OrderDTO.from(order)));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("rent")
    public ResponseEntity<ApiResponse<OrderDTO>> rentOrder(@RequestBody RentOrderRequest rentOrderRequest) {
        try {
            var order = orderService.rentOrder(rentOrderRequest.orderId());
            return ResponseEntity.ok(ApiResponse.success(OrderDTO.from(order)));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("cancel")
    public ResponseEntity<ApiResponse<OrderDTO>> cancelOrder(@RequestBody RentOrderRequest cancelOrderRequest) {
        try {
            var order = orderService.cancelOrder(cancelOrderRequest.orderId());
            return ResponseEntity.ok(ApiResponse.success(OrderDTO.from(order)));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error(e.getMessage()));
        }
    }
}
