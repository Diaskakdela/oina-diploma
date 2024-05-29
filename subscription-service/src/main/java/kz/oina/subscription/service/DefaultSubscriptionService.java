package kz.oina.subscription.service;

import kz.oina.oinatokens.service.TransactionService;
import kz.oina.payment.exception.NoUserAccountDetailsException;
import kz.oina.payment.model.PaymentCreationParams;
import kz.oina.payment.service.PaymentService;
import kz.oina.subscription.entity.Subscription;
import kz.oina.subscription.entity.SubscriptionStatus;
import kz.oina.subscription.exception.SubscriptionCreatingException;
import kz.oina.subscription.exception.SubscriptionTypeNotExistsException;
import kz.oina.subscription.factory.SubscriptionFactory;
import kz.oina.subscription.model.SubscriptionCreationParams;
import kz.oina.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultSubscriptionService implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionTypeService subscriptionTypeService;
    private final SubscriptionFactory subscriptionFactory;
    private final TransactionService tranactionService;
    private final PaymentService paymentService;

    @Override
    public Optional<Subscription> findByUserId(UUID userId) {
        return subscriptionRepository.findByUserIdAndStatus(userId, SubscriptionStatus.ACTIVE);
    }

    @Transactional
    @Override
    public Subscription createSubscription(SubscriptionCreationParams creationParams) throws SubscriptionCreatingException, NoUserAccountDetailsException {
        if (findByUserId(creationParams.renterId()).isPresent()) {
            throw new SubscriptionCreatingException("Subscription with id " + creationParams.renterId() + " already exists");
        }
        var userId = creationParams.renterId();
        var subscriptionType = subscriptionTypeService.findSubscriptionTypeById(creationParams.subscriptionTypeId())
                .orElseThrow(SubscriptionTypeNotExistsException::new);

        var payment = paymentService.makePayment(new PaymentCreationParams(userId, subscriptionType.getPrice()));

        var subscription = subscriptionFactory.createSubscription(creationParams);
        var transaction = tranactionService.createTransactionOnSubscribe(userId, subscriptionType.getTokensProvided());
        return subscriptionRepository.save(subscription);
    }
}
