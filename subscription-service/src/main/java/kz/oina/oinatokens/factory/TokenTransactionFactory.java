package kz.oina.oinatokens.factory;

import kz.oina.oinatokens.entity.TokenTransactions;
import kz.oina.oinatokens.model.TransactionDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenTransactionFactory {
    public TokenTransactions createTokenTransactions(TransactionDetails transactionDetails) {
        return new TokenTransactions(transactionDetails.userId(),
                transactionDetails.tokenAmount(),
                transactionDetails.transactionType());
    }
}
