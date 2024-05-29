package kz.oina.subscription.controller;

import kz.oina.core.controller.model.ApiResponse;
import kz.oina.payment.exception.NoUserAccountDetailsException;
import kz.oina.subscription.exception.SubscriptionCreatingException;
import kz.oina.subscription.exception.SubscriptionTypeNotExistsException;
import kz.oina.subscription.model.SubscriptionCreationParams;
import kz.oina.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<ApiResponse<SubscriptionDTO>> subscribe(@RequestBody SubscriptionCreationParams subscriptionCreationParams) {
        try {
            var sub = subscriptionService.createSubscription(subscriptionCreationParams);
            return ResponseEntity.ok(ApiResponse.success(new SubscriptionDTO()
                    .setSubscriptionId(sub.getId())
                    .setStatus("SUCCESS")));
        } catch (NoUserAccountDetailsException e) {
            return ResponseEntity.ok(ApiResponse.error(new SubscriptionDTO()
                    .setStatus("NO_USER_ACCOUNT")));
        } catch (SubscriptionTypeNotExistsException e) {
            return ResponseEntity.ok(ApiResponse.error(new SubscriptionDTO()
                    .setStatus("SUBSCRIPTION_TYPE_NOT_EXISTS")));
        } catch (SubscriptionCreatingException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(new SubscriptionDTO()
                    .setStatus("SUBSCRIPTION_EXISTS")));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error(new SubscriptionDTO()
                    .setStatus("INTERNAL_SERVER_ERROR")));
        }
    }
}
