package fr.eletutour.kataSG.repository;

import fr.eletutour.kataSG.model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for bank account
 */
@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {

    BankAccount findOneById(int idAccount);
}
