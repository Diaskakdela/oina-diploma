package kz.oina.oinatokens.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kz.oina.oinatokens.exception.NotEnoughTokensException;
import kz.oina.oinatokens.exception.WrongCreditTokenAmountException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
public class UserTokens {

    @Id
    private UUID id;
    private UUID userId;
    @Column(nullable = false)
    private Integer tokenAmount;
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    public void creditToken(int amount) {
        if (amount <= 0)
            throw new WrongCreditTokenAmountException();
        tokenAmount += amount;
        lastUpdated = LocalDateTime.now();
    }

    public void debitToken(int amount) {
        if (amount <= 0)
            throw new WrongCreditTokenAmountException();
        if (amount > tokenAmount)
            throw new NotEnoughTokensException();
        tokenAmount -= amount;
        lastUpdated = LocalDateTime.now();
    }

    public UserTokens(UUID userId) {
        id = UUID.randomUUID();
        this.userId = userId;
        tokenAmount = 0;
        lastUpdated = LocalDateTime.now();
    }

    @Deprecated
    public UserTokens() {

    }
}
