package kz.oina.oinatokens.service;

import kz.oina.oinatokens.entity.TokenTransactions;
import kz.oina.oinatokens.entity.UserTokens;
import kz.oina.oinatokens.exception.UserAccountAbsenceException;
import kz.oina.oinatokens.repository.UserTokensRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUserTokensService implements UserTokensService {

    private final UserTokensRepository userTokensRepository;

    @Override
    public Optional<UserTokens> findByUserId(UUID userId) {
        return userTokensRepository.findByUserId(userId);
    }

    @Override
    public UserTokens save(UserTokens userTokens) {
        return userTokensRepository.save(userTokens);
    }

    public UserTokens processTokensOnSubscribe(TokenTransactions tokenTransactions) {
        var userId = tokenTransactions.getUserId();
        var userTokens = userTokensRepository.findByUserId(userId)
                .orElseGet(() -> new UserTokens(userId));
        processTokensAndSave(userTokens, tokenTransactions);
        return userTokensRepository.save(userTokens);
    }

    @Override
    public UserTokens processTokensAndSave(TokenTransactions tokenTransactions) {
        var userTokens = userTokensRepository.findByUserId(tokenTransactions.getUserId())
                .orElseThrow(UserAccountAbsenceException::new);
        return processTokensAndSave(userTokens, tokenTransactions);
    }

    private UserTokens processTokensAndSave(UserTokens userTokens, TokenTransactions tokenTransactions) {
        if (tokenTransactions.isCredit()) {
            userTokens.creditToken(tokenTransactions.getTokenAmount());
        } else if (tokenTransactions.isDebit()) {
            userTokens.debitToken(tokenTransactions.getTokenAmount());
        }
        return userTokensRepository.save(userTokens);
    }
}
