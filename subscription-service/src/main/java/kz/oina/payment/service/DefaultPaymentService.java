package kz.oina.payment.service;

import kz.oina.payment.entity.PaymentMethod;
import kz.oina.payment.entity.Provider;
import kz.oina.payment.entity.UserAccount;
import kz.oina.payment.exception.NoUserAccountDetailsException;
import kz.oina.payment.exception.PaymentException;
import kz.oina.payment.model.PaymentCreationParams;
import kz.oina.payment.model.PaymentIntegrationParams;
import kz.oina.payment.model.PaymentStatus;
import kz.oina.payment.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DefaultPaymentService implements PaymentService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentIntegrationService paymentIntegrationService;
    private final UserAccountService userAccountService;

    @Override
    public PaymentMethod makePayment(PaymentCreationParams params) throws NoUserAccountDetailsException, PaymentException {
        var userAccount = userAccountService.findByUserId(params.userId())
                .orElseThrow(NoUserAccountDetailsException::new);
        try {
            var payment = paymentIntegrationService.makePayment(createPaymentParams(userAccount, params.amount()));
            if (PaymentStatus.SUCCESS.equals(payment.status())) {
                return paymentMethodRepository.save(new PaymentMethod(params.userId(), Provider.MOCK, payment.details()));
            } else {
                throw new PaymentException("Payment failed");
            }
        } catch (Exception e) {
            throw new PaymentException("Error while creating payment for " + params.userId(), e);
        }
    }

    private PaymentIntegrationParams createPaymentParams(UserAccount userAccount, BigDecimal amount) {
        return new PaymentIntegrationParams(userAccount.getAccountNumber(),
                userAccount.getFullName(),
                userAccount.getExpirationDate(),
                userAccount.getCvvCode(),
                amount);
    }
}
