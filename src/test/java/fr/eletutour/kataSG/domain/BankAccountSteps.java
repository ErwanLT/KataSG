package fr.eletutour.kataSG.domain;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountSteps {

    private BankAccount bankAccount;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void init(){
        System.setOut(new PrintStream(out));
    }

    @Given("^I have a bank account with (\\d+) euros$")
    public void i_have_a_bank_account_with_euros(int accountBalance) throws Throwable {
        bankAccount = new BankAccount(accountBalance);
    }

    @When("^I make a deposit of (\\d+) euros$")
    public void i_make_a_deposit_of_euros(int depositAmount) throws Throwable {
        bankAccount.makeDeposit(depositAmount);
    }

    @When("^i make a withdrawal of (\\d+) euros$")
    public void i_make_a_withdrawal_of_euros(int withdrawalAmount) throws Throwable {
        bankAccount.makeWithdrawal(withdrawalAmount);
    }

    @Then("^my list of operation shouldnt be empty$")
    public void my_list_of_operation_shouldnt_be_empty() throws Throwable {
        assertThat(bankAccount.getOperations()).isNotEmpty();
    }

    @Then("^my balance should be (\\d+) euros$")
    public void my_balance_should_be_euros(int accountBalance) throws Throwable {
        assertThat(bankAccount.getAccountBalance()).isEqualTo(accountBalance);
    }

    @Then("^my list of operation should have (\\d+) operations in$")
    public void my_list_of_operation_should_have_operations_in(int numberOfOperations) throws Throwable {
        assertThat(bankAccount.getOperations()).isNotEmpty();
        assertThat(bankAccount.getOperations().size()).isEqualTo(numberOfOperations);
    }

    @Then("^i demand the statement$")
    public void i_demand_the_statement() throws Throwable {
        out.reset();
        bankAccount.getAccountStatement();
        assertThat(out).isNotNull();
        String statement = out.toString();
        assertThat(statement).contains("DATE\t|Type Operation\t|amount");
        assertThat(statement).contains("Account balance :2080");
    }
}
