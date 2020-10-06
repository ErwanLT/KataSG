package fr.eletutour.kataSG.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findOneById(String accountId);
}
