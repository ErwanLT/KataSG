package fr.eletutour.kataSG.exceptions;

/**
 * Exception thrown when the account balance will be negative after a withdraw operation
 */
public class NegativeBalanceException extends RuntimeException {

    public NegativeBalanceException(String s){
        super(s);
    }
}
