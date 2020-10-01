package fr.eletutour.kataSG.services.impl;

import fr.eletutour.kataSG.dto.BankAccountDTO;
import fr.eletutour.kataSG.dto.OperationDto;
import fr.eletutour.kataSG.enums.TypeOperation;
import fr.eletutour.kataSG.exceptions.BankAccountNotFoundException;
import fr.eletutour.kataSG.exceptions.NegativeBalanceException;
import fr.eletutour.kataSG.model.BankAccount;
import fr.eletutour.kataSG.model.Operation;
import fr.eletutour.kataSG.repository.BankAccountRepository;
import fr.eletutour.kataSG.services.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * implementation for {@link IBankService}
 */
@Service
public class BankService implements IBankService {

    private BankAccountRepository bankAccountRepository;


    @Autowired
    public BankService(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccountDTO createAccount(BankAccountDTO bankAccountDTO) {

        BankAccount bankAccount = new BankAccount(bankAccountDTO);
        bankAccountRepository.save(bankAccount);


        return bankAccount.toDTO();
    }

    @Override
    public BankAccountDTO withdraw(int idAccount, int amount) throws BankAccountNotFoundException, NegativeBalanceException {
        BankAccount bankAccount = bankAccountRepository.findOneById(idAccount);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("Bank account not found");
        }

        if(bankAccount.getAmount() - amount < 0){
            throw new NegativeBalanceException("The balance of your account will be negative");
        }


        Operation operation = createOperation(TypeOperation.WHITHDRAWAL, amount, bankAccount);

        if(CollectionUtils.isEmpty(bankAccount.getOperations())){
            List<Operation> operations = new ArrayList<>();
            operations.add(operation);
            bankAccount.setOperations(operations);
        } else {
            bankAccount.getOperations().add(operation);
        }

        bankAccount.setAmount(bankAccount.getAmount() - amount);

        bankAccount = bankAccountRepository.save(bankAccount);

        return bankAccount.toDTO();

    }

    @Override
    public BankAccountDTO deposit(int idAccount, int amount) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findOneById(idAccount);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("Bank account not found");
        }

        Operation operation = createOperation(TypeOperation.DEPOSIT, amount, bankAccount);

        if(CollectionUtils.isEmpty(bankAccount.getOperations())){
            List<Operation> operations = new ArrayList<>();
            operations.add(operation);
            bankAccount.setOperations(operations);
        } else {
            bankAccount.getOperations().add(operation);
        }

        bankAccount.setAmount(bankAccount.getAmount() + amount);

        bankAccount = bankAccountRepository.save(bankAccount);

        return bankAccount.toDTO();
    }

    @Override
    public List<OperationDto> getAccountOperation(int idAccount) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findOneById(idAccount);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("Bank account not found");
        }

        if(CollectionUtils.isEmpty(bankAccount.toDTO().getOperationsDTO())){
            return new ArrayList<>();
        } else {
            return bankAccount.toDTO().getOperationsDTO();
        }
    }

    private static Operation createOperation(TypeOperation typeOperation, int amount, BankAccount bankAccount){
        Operation operation = new Operation();
        operation.setDateOperation(new Date());
        operation.setAmount(amount);
        operation.setTypeOperation(typeOperation);
        operation.setBankAccount(bankAccount);

        return operation;
    }
}
