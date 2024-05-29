package kz.oina.subscription.controller;

import kz.oina.core.controller.model.ApiResponse;
import kz.oina.subscription.service.SubscriptionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("subscription-types")
public class SubscriptionTypeController {
    private final SubscriptionTypeService subscriptionTypeService;
    private final SubTypeMapper mapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TypesDTO>>> getAll() {
        var dto = subscriptionTypeService.findAllSubscriptionTypes()
                .stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(dto));
    }
}
