package kz.oina.controller;

import kz.oina.mapper.AddMapper;
import kz.oina.model.request.AddRequest;
import kz.oina.model.response.ApiResponse;
import kz.oina.model.response.SimpleInventoryItemResponse;
import kz.oina.service.AddService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("inventory/admin")
@RequiredArgsConstructor
public class AddController {
    private final AddService addService;
    private final AddMapper addMapper;

    @PostMapping("add")
    @PreAuthorize("hasAuthority('ADMIN') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Collection<SimpleInventoryItemResponse>>> add(@RequestBody AddRequest addRequest) {
        var addResponse = addService.addItem(addMapper.toCreationParams(addRequest)).stream()
                .map(addMapper::toSimpleInventoryItemResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(addResponse));
    }
}
