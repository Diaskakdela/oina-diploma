package kz.oina.core.service;

import kz.oina.core.controller.model.PayAvailabilityResponse;
import kz.oina.core.controller.model.PayAvailabilityStatus;
import kz.oina.oinatokens.service.UserTokensService;
import kz.oina.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PayAvailabilityService {
    private final SubscriptionService subscriptionService;
    private final UserTokensService userTokensService;

    public PayAvailabilityResponse findAvailability(UUID renterId, BigDecimal amount) {
        if (subscriptionService.findByUserId(renterId).isEmpty()) {
            return new PayAvailabilityResponse(PayAvailabilityStatus.NO_SUBSCRIPTION.getName());
        }
        var userTokens = userTokensService.findByUserId(renterId)
                .orElseThrow(() -> new RuntimeException("User tokens not found"));
        BigDecimal tokensAmount = BigDecimal.valueOf(userTokens.getTokenAmount());

        if (tokensAmount.compareTo(amount) < 0) {
            return new PayAvailabilityResponse(PayAvailabilityStatus.NOT_ENOUGH_TOKENS.getName());
        }

        return new PayAvailabilityResponse(PayAvailabilityStatus.AVAILABLE.getName());
    }
}
