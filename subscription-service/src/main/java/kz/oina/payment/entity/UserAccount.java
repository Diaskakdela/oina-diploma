package kz.oina.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class UserAccount {
    @Id
    private UUID id;
    private UUID userId;
    private String accountNumber;
    private String fullName;
    private String expirationDate;
    private String cvvCode;

    public UserAccount(UUID userId, String accountNumber, String fullName, String expirationDate, String cvvCode) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.expirationDate = expirationDate;
        this.cvvCode = cvvCode;
    }
}
