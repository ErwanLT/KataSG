package fr.eletutour.kataSG.domain;

/**
 * TypeOperation enum
 */
public enum TypeOperation {
    WHITHDRAWAL("withdrawal"),DEPOSIT("deposit");

    private String name;

    TypeOperation(String name){
        this.name = name;
    }
}
