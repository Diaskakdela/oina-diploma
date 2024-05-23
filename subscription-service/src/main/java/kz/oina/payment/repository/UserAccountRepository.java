package kz.oina.payment.repository;

import kz.oina.payment.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    Optional<UserAccount> findByUserId(UUID userId);
    boolean existsByUserId(UUID userId);
}
