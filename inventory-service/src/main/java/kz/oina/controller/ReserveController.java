package kz.oina.controller;

import kz.oina.exceptions.item.InsufficientInventoryException;
import kz.oina.exceptions.item.ItemNotFoundException;
import kz.oina.mapper.ReserveMapper;
import kz.oina.model.request.CancelReserveRequest;
import kz.oina.model.request.ReserveRequest;
import kz.oina.model.response.ApiResponse;
import kz.oina.model.response.SimpleInventoryItemResponse;
import kz.oina.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/inventory/service")
@RequiredArgsConstructor
public class ReserveController {
    private final ReserveService reserveService;
    private final ReserveMapper reserveMapper;

    @PostMapping("reserve")
    public ResponseEntity<ApiResponse<Collection<SimpleInventoryItemResponse>>> reserve(@RequestBody ReserveRequest reserveRequest) {
        try {
            var reservedItems = reserveService.reserve(reserveMapper.toReserveParams(reserveRequest))
                    .stream()
                    .map(reserveMapper::toSimpleInventoryItemResponse)
                    .toList();

            return ResponseEntity.ok(ApiResponse.success(reservedItems));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        } catch (InsufficientInventoryException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("cancel-reserve")
    public ResponseEntity<ApiResponse<SimpleInventoryItemResponse>> cancelReserve(@RequestBody CancelReserveRequest cancelReserveRequest) {
        try {
            var canceledReservedItem = reserveService.cancelReserve(reserveMapper.toCancelReserveParams(cancelReserveRequest));

            return ResponseEntity.ok(ApiResponse.success(reserveMapper.toSimpleInventoryItemResponse(canceledReservedItem)));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("cancel-reserve-all")
    public ResponseEntity<ApiResponse<Collection<SimpleInventoryItemResponse>>> cancelReserveList(@RequestBody Collection<CancelReserveRequest> cancelReserveRequest) {
        try {
            var cancelReserveParams = cancelReserveRequest.stream()
                    .map(reserveMapper::toCancelReserveParams)
                    .toList();
            var inventoryItems = reserveService.cancelReserve(cancelReserveParams)
                    .stream()
                    .map(reserveMapper::toSimpleInventoryItemResponse)
                    .toList();
            return ResponseEntity.ok(ApiResponse.success(inventoryItems));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }
}
