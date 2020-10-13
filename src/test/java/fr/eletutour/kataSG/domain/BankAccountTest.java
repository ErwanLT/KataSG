package fr.eletutour.kataSG.domain;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountTest {

    @Test
    public void making_deposit(){
        //Given an account
        BankAccount bankAccount = new BankAccount(new AccountOperations());

        // When i make a deposit of X euros
        bankAccount.makeDeposit(new Money(BigDecimal.valueOf(100)));

        //Then I should have 1 operation in account operations
        List<Operation> operations = bankAccount.getAccountOperations().getOperations();
        assertThat(operations).isNotEmpty();
        assertThat(operations.size()).isEqualTo(1);
        Operation operation = operations.get(0);
        assertThat(operation.getTypeOperation()).isEqualTo(TypeOperation.DEPOSIT);
    }

    @Test
    public void making_withdrawal(){
        //Given an account
        BankAccount bankAccount = new BankAccount(new AccountOperations());

        // When i make a withdrawal of X euros
        bankAccount.makeWithdrawal(new Money(BigDecimal.valueOf(100)));

        //Then I should have 1 operation in account operations
        List<Operation> operations = bankAccount.getAccountOperations().getOperations();
        assertThat(operations).isNotEmpty();
        assertThat(operations.size()).isEqualTo(1);
        Operation operation = operations.get(0);
        assertThat(operation.getTypeOperation()).isEqualTo(TypeOperation.WHITHDRAWAL);
    }
}
