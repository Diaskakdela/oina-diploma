package kz.oina.payment.service;

import kz.oina.payment.entity.UserAccount;
import kz.oina.payment.exception.UserAccountException;
import kz.oina.payment.factory.UserAccountFactory;
import kz.oina.payment.model.UserAccountCreationParams;
import kz.oina.payment.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUserAccountService implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountFactory userAccountFactory;

    @Override
    public Optional<UserAccount> findByUserId(UUID userId) {
        return userAccountRepository.findById(userId);
    }

    @Override
    public UserAccount save(UserAccountCreationParams creationParams) {
        if (userAccountRepository.existsByUserId(creationParams.userId())) {
            throw new UserAccountException("User account already exists");
        }
        var userAccount = userAccountFactory.createUserAccount(creationParams);
        return userAccountRepository.save(userAccount);
    }
}
