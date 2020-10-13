package fr.eletutour.kataSG.domain;

public class StatementLine {
    private Operation operation;
    private Money balance;

    public StatementLine(Operation operation, Money balance) {
        this.operation = operation;
        this.balance = balance;
    }

    public Operation getOperation() {
        return operation;
    }

    public Money getBalance() {
        return balance;
    }
}
