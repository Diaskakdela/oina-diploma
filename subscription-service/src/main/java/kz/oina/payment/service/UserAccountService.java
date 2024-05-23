package kz.oina.payment.service;

import kz.oina.payment.entity.UserAccount;
import kz.oina.payment.model.UserAccountCreationParams;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountService {
    Optional<UserAccount> findByUserId(UUID userId);

    UserAccount save(UserAccountCreationParams creationParams);
}
