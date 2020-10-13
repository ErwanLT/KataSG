package fr.eletutour.kataSG.domain;

public class BankAccount {

    private AccountOperations accountOperations;

    public BankAccount(AccountOperations accountOperations) {
        this.accountOperations = accountOperations;
    }

    public void makeDeposit(Money money){
        this.accountOperations.addOperation(TypeOperation.DEPOSIT, money);
    }

    public void makeWithdrawal(Money money){
        this.accountOperations.addOperation(TypeOperation.WHITHDRAWAL, money.negate());
    }

    public void getAccountStatement(StatementPrinter printer){
        printer.print(accountOperations.getStatement());
    }

    public AccountOperations getAccountOperations(){
        return this.accountOperations;
    }
}
