package kz.oina.oinatokens.service;

import kz.oina.oinatokens.entity.TokenTransactions;
import kz.oina.oinatokens.model.TransactionDetails;

import java.util.UUID;

public interface TransactionService {
    TokenTransactions createTransactionOnSubscribe(UUID userId, Integer amount);

    TokenTransactions createTransaction(TransactionDetails transactionDetails);

    TokenTransactions cancelTransaction(UUID transactionId);
}
