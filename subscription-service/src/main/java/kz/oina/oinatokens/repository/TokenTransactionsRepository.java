package kz.oina.oinatokens.repository;

import kz.oina.oinatokens.entity.TokenTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TokenTransactionsRepository extends JpaRepository<TokenTransactions, UUID> {
}
