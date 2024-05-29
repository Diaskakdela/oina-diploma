package kz.oina.payment.web;

import kz.oina.core.controller.model.ApiResponse;
import kz.oina.payment.exception.UserAccountException;
import kz.oina.payment.model.UserAccountCreationParams;
import kz.oina.payment.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user-account")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;

    @GetMapping("/exists/{renterId}")
    public ResponseEntity<ApiResponse<Boolean>> getUserAccount(@PathVariable UUID renterId) {
        var isUserAccountPresent = userAccountService.findByUserId(renterId).isPresent();
        if (isUserAccountPresent) {
            return ResponseEntity.ok(ApiResponse.success(true));
        } else {
            return ResponseEntity.ok(ApiResponse.error(false));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserAccountCreationResponse>> save(@RequestBody UserAccountCreationRequest creationRequest) {
        var params = new UserAccountCreationParams(creationRequest.renterId(),
                creationRequest.account(),
                creationRequest.fullName(),
                creationRequest.expirationDate(),
                creationRequest.cvvCode());
        try {
            var userAccount = userAccountService.save(params);
            return ResponseEntity.ok(ApiResponse.success(new UserAccountCreationResponse(userAccount.getId())));
        } catch (UserAccountException e) {
            return ResponseEntity.ok(ApiResponse.error("User account creation failed. " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error(e.getMessage()));
        }
    }
}
