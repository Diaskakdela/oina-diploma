package kz.oina.controller;

import kz.oina.exceptions.item.ItemNotFoundException;
import kz.oina.mapper.ReturnMapper;
import kz.oina.model.request.ReturnRequest;
import kz.oina.model.response.ApiResponse;
import kz.oina.model.response.SimpleInventoryItemResponse;
import kz.oina.service.ReturnService;
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
public class ReturnController {
    private final ReturnService returnService;
    private final ReturnMapper returnMapper;

    @PostMapping("return")
    public ResponseEntity<ApiResponse<SimpleInventoryItemResponse>> returnItem(@RequestBody ReturnRequest returnRequests) {
        try {
            var inventoryItem = returnService.returnItem(returnMapper.toReturnParams(returnRequests));
            return ResponseEntity.ok(ApiResponse.success(returnMapper.toSimpleInventoryItemResponse(inventoryItem)));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("return-all")
    public ResponseEntity<ApiResponse<Collection<SimpleInventoryItemResponse>>> returnAll(@RequestBody Collection<ReturnRequest> returnRequests) {
        try {
            var rentParams = returnRequests.stream()
                    .map(returnMapper::toReturnParams)
                    .toList();
            var rentedItems = returnService.returnItems(rentParams)
                    .stream()
                    .map(returnMapper::toSimpleInventoryItemResponse)
                    .toList();
            return ResponseEntity.ok(ApiResponse.success(rentedItems));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }
}
