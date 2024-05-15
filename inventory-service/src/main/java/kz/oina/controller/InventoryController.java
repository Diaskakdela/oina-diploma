package kz.oina.controller;

import kz.oina.exceptions.toy.ItemNotInInventoryException;
import kz.oina.mapper.InventoryMapper;
import kz.oina.model.response.ApiResponse;
import kz.oina.model.response.ToyAvailabilityResponse;
import kz.oina.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("inventory/info")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    private final InventoryMapper inventoryMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<Collection<ToyAvailabilityResponse>>> info() {
        try {
            var toyAvailabilityResponses = inventoryService.findAvailableToReserveToys().stream()
                    .map(inventoryMapper::toyAvailabilityResponse)
                    .toList();
            return ResponseEntity.ok(ApiResponse.success(toyAvailabilityResponses));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("{toyId}")
    public ResponseEntity<ApiResponse<ToyAvailabilityResponse>> concreteToyInfo(@PathVariable UUID toyId) {
        try {
            ToyAvailabilityResponse toyAvailabilityResponse = inventoryMapper.toyAvailabilityResponse(inventoryService.findToyAvailability(toyId));
            return ResponseEntity.ok(ApiResponse.success(toyAvailabilityResponse));
        } catch (ItemNotInInventoryException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error(e.getMessage()));
        }
    }
}
