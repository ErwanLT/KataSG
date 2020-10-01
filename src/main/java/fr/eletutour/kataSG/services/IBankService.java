package fr.eletutour.kataSG.services;

import fr.eletutour.kataSG.dto.BankAccountDTO;
import fr.eletutour.kataSG.dto.OperationDto;
import fr.eletutour.kataSG.exceptions.BankAccountNotFoundException;
import fr.eletutour.kataSG.exceptions.NegativeBalanceException;

import java.util.List;

/**
 * Bank service
 */
public interface IBankService {

    /**
     * create account methode
     * @param bankAccountDTO the account to be created
     * @return the created account
     */
    BankAccountDTO createAccount(BankAccountDTO bankAccountDTO);

    /**
     * methode to withdraw an amount from an account
     * @param idAccount the account id
     * @param amount the amount to withdraw
     * @return the bank account
     * @throws BankAccountNotFoundException if the specified bank account dont exist
     * @throws NegativeBalanceException if the account balance will be negative
     */
    BankAccountDTO withdraw(int idAccount, int amount) throws BankAccountNotFoundException, NegativeBalanceException;

    /**
     * methode to save an amount to an account
     * @param idAccount the account id
     * @param amount the amount to save
     * @return the bank account
     * @throws BankAccountNotFoundException if the specified bank account dont exist
     */
    BankAccountDTO deposit(int idAccount, int amount) throws BankAccountNotFoundException;

    /**
     * methode to view all the operations of an account
     * @param idAccount the account id
     * @return the list of operations
     * @throws BankAccountNotFoundException if the specified bank account dont exist
     */
    List<OperationDto> getAccountOperation(int idAccount) throws BankAccountNotFoundException;

}
