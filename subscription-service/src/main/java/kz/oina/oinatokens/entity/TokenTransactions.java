package kz.oina.oinatokens.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Getter
@Entity
public class TokenTransactions {
    @Id
    private UUID id;
    private UUID userId;
    private Integer tokenAmount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private LocalDateTime createdAt;

    public TokenTransactions(UUID userId, Integer tokenAmount, TransactionType transactionType) {
        id = UUID.randomUUID();
        this.userId = userId;
        this.tokenAmount = tokenAmount;
        this.transactionType = transactionType;
        this.createdAt = LocalDateTime.now();
    }

    public boolean isDebit() {
        return transactionType == TransactionType.DEBIT;
    }

    public boolean isCredit() {
        return transactionType == TransactionType.CREDIT;
    }

    public TokenTransactions cancelTransaction() {
        if (ChronoUnit.DAYS.between(this.createdAt, LocalDateTime.now()) > 2) {
            throw new IllegalStateException("Transaction cannot be cancelled after 2 days");
        }
        if (this.isDebit()) {
            return new TokenTransactions(userId, tokenAmount, TransactionType.CREDIT);
        } else if (this.isCredit()) {
            return new TokenTransactions(userId, tokenAmount, TransactionType.DEBIT);
        }
        throw new IllegalStateException("Transaction cannot be cancelled");
    }

    @Deprecated
    public TokenTransactions() {

    }
}
