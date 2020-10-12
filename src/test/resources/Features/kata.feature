Feature: Testing the kata use case
  Scenario: Making a deposit
    Given I have a bank account with 1000 euros
    When I make a deposit of 100 euros
    Then my list of operation shouldnt be empty
    Then my balance should be 1100 euros

  Scenario: Making a withdrawal
    Given I have a bank account with 500 euros
    When i make a withdrawal of 20 euros
    Then my list of operation shouldnt be empty
    Then my balance should be 480 euros

  Scenario: Getting operation statement
    Given I have a bank account with 2000 euros
    When I make a deposit of 100 euros
    And i make a withdrawal of 20 euros
    Then my list of operation should have 2 operations in
    Then my balance should be 2080 euros
    Then i demand the statement