package fr.eletutour.kataSG.domain;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountOperationsTest {

    @Test
    public void making_deposit(){
        //Given an accountOperation
        AccountOperations accountOperations = new AccountOperations();

        //When I make a deposit of X euros
        accountOperations.addOperation(TypeOperation.DEPOSIT, new Money(BigDecimal.valueOf(100)));

        //Then I should have 1 operation in account operations
        assertThat(accountOperations.getOperations()).isNotEmpty();
        assertThat(accountOperations.getOperations().size()).isEqualTo(1);
        //Then I should have 1 statement line
        List<StatementLine> statementLines = accountOperations.getStatement();
        assertThat(statementLines).isNotEmpty();
        StatementLine statement = statementLines.get(0);
        assertThat(statement.getBalance().getAmount()).isEqualTo(BigDecimal.valueOf(100));
    }

    @Test
    public void making_withdrawal(){
        //Given an accountOperation
        AccountOperations accountOperations = new AccountOperations();

        //When I make a deposit of X euros
        accountOperations.addOperation(TypeOperation.WHITHDRAWAL, new Money(BigDecimal.valueOf(100)));

        //Then I should have 1 operation in account operations
        assertThat(accountOperations.getOperations()).isNotEmpty();
        assertThat(accountOperations.getOperations().size()).isEqualTo(1);
        //Then I should have 1 statement line
        List<StatementLine> statementLines = accountOperations.getStatement();
        assertThat(statementLines).isNotEmpty();
        StatementLine statement = statementLines.get(0);
        assertThat(statement.getBalance().getAmount()).isEqualTo(BigDecimal.valueOf(100));
    }
}
