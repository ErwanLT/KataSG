Feature: Testing the kata use case

  Scenario: Testing an invalid endpoint
    When the client call /bank/toto
    Then the client receives status code of 404

  Scenario: Trying to get operations of nonexistent account
    When the client call /bank/operations/1111
    Then the client receives status code of 500

  Scenario: Getting operations of valid account
    When the client call /bank/operations/c0a80116
    Then the client receives status code of 200
    Then the client get a list of operation

  Scenario: Making an operation that will make the account balance below 0
    When the client call bank/make/WHITHDRAWAL/account/c0a80116?amount=2000
    Then the client receives status code of 500

  Scenario: Making a valid WHITHDRAWAL operation on an account
    When the client call bank/make/WHITHDRAWAL/account/c0a80116?amount=15
    Then the client receives status code of 200