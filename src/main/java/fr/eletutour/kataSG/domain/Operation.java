package fr.eletutour.kataSG.domain;

import java.util.Date;

public class Operation {

    private Date createdOn;

    private TypeOperation typeOperation;

    private Money amount;

    public Operation(Date date, TypeOperation typeOperation, Money amount) {
        this.createdOn = date;
        this.typeOperation = typeOperation;
        this.amount = amount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public Money getAmount() {
        return amount;
    }
}
