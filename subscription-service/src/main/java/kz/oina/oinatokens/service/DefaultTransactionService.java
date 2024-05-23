package kz.oina.oinatokens.service;

import kz.oina.oinatokens.entity.TokenTransactions;
import kz.oina.oinatokens.exception.TransactionNotFoundException;
import kz.oina.oinatokens.factory.TokenTransactionFactory;
import kz.oina.oinatokens.model.TransactionDetails;
import kz.oina.oinatokens.repository.TokenTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultTransactionService implements TransactionService {
    private final TokenTransactionsRepository tokenTransactionsRepository;
    private final UserTokensService userTokensService;
    private final TokenTransactionFactory tokenTransactionFactory;

    @Transactional
    @Override
    public TokenTransactions createTransactionOnSubscribe(UUID userId, Integer amount) {
        var transactionDetails = TransactionDetails.creditTransaction(userId, amount);
        var tokenTransaction = tokenTransactionFactory.createTokenTransactions(transactionDetails);
        userTokensService.processTokensOnSubscribe(tokenTransaction);
        return tokenTransactionsRepository.save(tokenTransaction);
    }

    @Transactional
    @Override
    public TokenTransactions createTransaction(TransactionDetails transactionDetails) {
        var tokenTransaction = tokenTransactionFactory.createTokenTransactions(transactionDetails);
        userTokensService.processTokensAndSave(tokenTransaction);
        return tokenTransactionsRepository.save(tokenTransaction);
    }

    @Transactional
    @Override
    public TokenTransactions cancelTransaction(UUID transactionId) {
        var tokenTransaction = tokenTransactionsRepository.findById(transactionId)
                .orElseThrow(TransactionNotFoundException::new);
        var compensatoryTransaction = tokenTransaction.cancelTransaction();
        userTokensService.processTokensAndSave(compensatoryTransaction);

        return tokenTransactionsRepository.save(tokenTransaction);
    }
}
