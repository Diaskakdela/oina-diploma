package kz.oina.oinatokens.service;

import kz.oina.oinatokens.entity.TokenTransactions;
import kz.oina.oinatokens.entity.UserTokens;

import java.util.Optional;
import java.util.UUID;

public interface UserTokensService {

    Optional<UserTokens> findByUserId(UUID userId);

    UserTokens save(UserTokens userTokens);

    UserTokens processTokensOnSubscribe(TokenTransactions tokenTransactions);

    UserTokens processTokensAndSave(TokenTransactions tokenTransactions);

}
