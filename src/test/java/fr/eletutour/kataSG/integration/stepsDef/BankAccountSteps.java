package fr.eletutour.kataSG.integration.stepsDef;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.eletutour.kataSG.domain.AccountOperations;
import fr.eletutour.kataSG.domain.BankAccount;
import fr.eletutour.kataSG.domain.Money;
import fr.eletutour.kataSG.domain.StatementPrinter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountSteps {

    private BankAccount account;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        AccountOperations accountOperations = new AccountOperations();
        account = new BankAccount(accountOperations);
        System.setOut(new PrintStream(out));
    }

    @Given("^I deposit (\\d+) euros$")
    public void i_deposit_euros(BigDecimal amount) throws Throwable {
        account.makeDeposit(new Money(amount));
    }

    @Given("^I withdraw (\\d+) euros$")
    public void i_withdraw_euros(BigDecimal amount) throws Throwable {
        account.makeWithdrawal(new Money(amount));
    }

    @When("^I ask the statement$")
    public void i_ask_the_statement() throws Throwable {
        out.reset();
        account.getAccountStatement(new StatementPrinter());
    }

    @Then("^My balance should be (-?\\d+)")
    public void my_balance_should_be(BigDecimal accountBalance) {
        assertThat(out.toString()).isNotEmpty().contains(String.valueOf(accountBalance));
    }
}
