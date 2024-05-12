package kz.oina.controller;

import kz.oina.entity.InventoryItem;
import kz.oina.exceptions.item.ItemNotFoundException;
import kz.oina.mapper.RentMapper;
import kz.oina.model.request.RentRequest;
import kz.oina.model.response.ApiResponse;
import kz.oina.model.response.SimpleInventoryItemResponse;
import kz.oina.service.RentService;
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
public class RentController {

    private final RentService rentService;
    private final RentMapper rentMapper;

    @PostMapping("rent")
    public ResponseEntity<ApiResponse<SimpleInventoryItemResponse>> rent(@RequestBody RentRequest rentRequest) {
        try {
            InventoryItem rent = rentService.rent(rentMapper.toRentParams(rentRequest));
            return ResponseEntity.ok(ApiResponse.success(rentMapper.toSimpleInventoryItemResponse(rent)));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("rent-all")
    public ResponseEntity<ApiResponse<Collection<SimpleInventoryItemResponse>>> rentAll(@RequestBody Collection<RentRequest> rentRequest) {
        try {
            var rentParams = rentRequest.stream()
                    .map(rentMapper::toRentParams)
                    .toList();
            var rentedItems = rentService.rent(rentParams)
                    .stream()
                    .map(rentMapper::toSimpleInventoryItemResponse)
                    .toList();
            return ResponseEntity.ok(ApiResponse.success(rentedItems));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }
}
