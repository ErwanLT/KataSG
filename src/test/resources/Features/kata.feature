Feature: Testing the kata use case

  Scenario: Making a deposit and ask the statement
    Given I deposit 100 euros
    When I ask the statement
    Then My balance should be 100

  Scenario: Multiple Deposit / Withdraw and ask the statement balance
    Given I deposit 100 euros
    And I withdraw 50 euros
    When I ask the statement
    Then My balance should be 50

  Scenario: Making a withdrawal and ask the statement
    Given I withdraw 50 euros
    When I ask the statement
    Then My balance should be -50