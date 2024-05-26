package kz.oina.orderitem.web;

import kz.oina.core.web.ApiResponse;
import kz.oina.orderitem.exception.OrderItemNotFoundException;
import kz.oina.orderitem.mapper.OrderItemMapper;
import kz.oina.orderitem.service.OrderItemService;
import kz.oina.orderitem.web.request.OrderItemAddRequest;
import kz.oina.orderitem.web.request.OrderItemReturnRequest;
import kz.oina.orderitem.web.response.OrderItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order-item")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderItemMapper orderItemMapper;

    @PostMapping("return")
    public ResponseEntity<ApiResponse<OrderItemDTO>> returnOrderItem(@RequestBody OrderItemReturnRequest orderItemReturnRequest) {
        try {
            var orderItem = orderItemService.returnOrderItem(orderItemReturnRequest.orderItemId());
            return ResponseEntity.ok(ApiResponse.success(orderItemMapper.toDto(orderItem)));
        } catch (OrderItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<List<OrderItemDTO>>> addItem(@RequestBody OrderItemAddRequest itemAddRequest) {
        try {
            var orderItems = orderItemService.createOrderItem(orderItemMapper.toCreateParams(itemAddRequest))
                    .stream()
                    .map(orderItemMapper::toDto)
                    .toList();
            return ResponseEntity.ok(ApiResponse.success(orderItems));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(e.getMessage()));
        }
    }
}
