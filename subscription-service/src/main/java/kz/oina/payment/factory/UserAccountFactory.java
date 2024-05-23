package kz.oina.payment.factory;

import kz.oina.payment.entity.UserAccount;
import kz.oina.payment.model.UserAccountCreationParams;
import org.springframework.stereotype.Component;

@Component
public class UserAccountFactory {
    public UserAccount createUserAccount(UserAccountCreationParams creationParams) {
        return new UserAccount(creationParams.userId(),
                creationParams.account(),
                creationParams.fullName(),
                creationParams.expirationDate(),
                creationParams.cvvCode());
    }
}
