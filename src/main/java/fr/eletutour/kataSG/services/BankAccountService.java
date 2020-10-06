package fr.eletutour.kataSG.services;

import fr.eletutour.kataSG.domain.BankAccount;
import fr.eletutour.kataSG.domain.BankAccountRepository;
import fr.eletutour.kataSG.domain.Operation;
import fr.eletutour.kataSG.domain.TypeOperation;
import fr.eletutour.kataSG.exceptions.BankAccountNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    BankAccountService(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository = bankAccountRepository;
    }


    public BankAccount createAccount(String name, int amount) {
        return bankAccountRepository.save(new BankAccount(new Date().toInstant(), name, amount));
    }

    public List<Operation> getAccountOperation(String accountId) {
        BankAccount bankAccount = retrieveAccount(accountId);
        return bankAccount.getOperations();
    }

    public void makeOperation(String typeOperation, String accountId, int amount) {
        BankAccount bankAccount = retrieveAccount(accountId);
        bankAccount.addOperation(new Date().toInstant(), TypeOperation.valueOf(typeOperation), amount);
        bankAccountRepository.save(bankAccount);
    }

    private BankAccount retrieveAccount(String accountId){
        BankAccount bankAccount = bankAccountRepository.findOneById(accountId);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("No account found for the id : " + accountId);
        }
        return bankAccount;
    }
}
