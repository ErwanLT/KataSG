package fr.eletutour.kataSG;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepDefsIntegrationTest extends SpringIntegrationTest {

    @When("^the client call /bank/toto$")
    public void the_client_call_bank_toto() throws Throwable {
        executeGet("http://localhost:8080/bank/toto");
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @When("^the client call /bank/operations/(\\d+)$")
    public void the_client_call_bank_operations(int arg1) throws Throwable {
        executeGet("http://localhost:8080/bank/operations/1111");
    }

    @When("^the client call /bank/operations/c(\\d+)a(\\d+)$")
    public void the_client_call_bank_operations_c_a(int arg1, int arg2) throws Throwable {
        executeGet("http://localhost:8080/bank/operations/c0a80116");
    }

    @Then("^the client get a list of operation$")
    public void the_client_get_a_list_of_operation() throws Throwable {
        String responseBody = latestResponse.getBody();
        Assertions.assertThat(responseBody).isNotNull().isNotEmpty();
    }

    @When("^the client call bank/make/WHITHDRAWAL/account/c(\\d+)a(\\d+)\\?amount=(\\d+)$")
    public void the_client_call_bank_make_WITHDRAWAL_account_c_a_amount(int arg1, int arg2, int arg3) throws Throwable {
        executePut("http://localhost:8080/bank/make/WHITHDRAWAL/account/c0a80116?amount="+String.valueOf(arg3));
    }

}