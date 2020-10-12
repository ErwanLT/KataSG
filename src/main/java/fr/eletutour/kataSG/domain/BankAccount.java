package fr.eletutour.kataSG.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class BankAccount {

    int accountBalance;

    private List<Operation> operations;

    public BankAccount(int accountBalance){
        this.accountBalance = accountBalance;
        operations = new ArrayList<Operation>();
    }

    public void makeWithdrawal(int amount){
        this.operations.add(new Operation(new Date(), TypeOperation.WHITHDRAWAL, -amount));
        this.accountBalance = accountBalance - amount;
    }

    public void makeDeposit(int amount){
        this.operations.add(new Operation(new Date(), TypeOperation.DEPOSIT, amount));
        this.accountBalance = accountBalance + amount;
    }

    public void getAccountStatement(){
        System.out.println("DATE\t|Type Operation\t|amount");
        for (Operation op:this.operations) {
            System.out.println(op.getCreatedOn().toString()+"\t|"+op.getTypeOperation()+"\t|"+op.getAmount());
        }
        System.out.println("-------------------------");
        System.out.println("Account balance :"+ this.accountBalance);
    }
}
