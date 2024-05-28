package kz.oina.core.controller;

import kz.oina.core.controller.model.ApiResponse;
import kz.oina.core.controller.model.PayAvailabilityResponse;
import kz.oina.core.controller.model.PayAvailabilityStatus;
import kz.oina.core.service.PayAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("pay-availability")
@RequiredArgsConstructor
public class PayAvailabilityController {

    private final PayAvailabilityService payAvailabilityService;

    @GetMapping
    public ResponseEntity<ApiResponse<PayAvailabilityResponse>> isAvailable(@RequestParam UUID renterId, @RequestParam BigDecimal price) {
        try {
            var response = payAvailabilityService.findAvailability(renterId, price);
            if (response.status().equals(PayAvailabilityStatus.AVAILABLE.getName())) {
                return ResponseEntity.ok(ApiResponse.success(response));
            } else {
                return ResponseEntity.ok(ApiResponse.error(response));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }

}
