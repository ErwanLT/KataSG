package fr.eletutour.kataSG.exceptions;

/**
 * Exception thrown when bank account not found in database
 */
public class BankAccountNotFoundException extends RuntimeException {

    public BankAccountNotFoundException(String s){
        super(s);
    }
}
