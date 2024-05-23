package kz.oina.oinatokens.model;

import kz.oina.oinatokens.entity.TransactionType;

import java.util.UUID;

public record TransactionDetails(UUID userId,
                                 Integer tokenAmount,
                                 TransactionType transactionType) {

    public static TransactionDetails creditTransaction(UUID userId, Integer tokenAmount) {
        return new TransactionDetails(userId, tokenAmount, TransactionType.CREDIT);
    }

    public static TransactionDetails debitTransaction(UUID userId, Integer tokenAmount) {
        return new TransactionDetails(userId, tokenAmount, TransactionType.DEBIT);
    }
}
