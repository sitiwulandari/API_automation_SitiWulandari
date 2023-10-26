Feature: Test the Create a User post functionality of GORest website


  @CreateUserPost
  Scenario: Test the Create a User post flow using Happy Path
    Given Creates a user post
    When User calls the "CreateUserPost" using "POST" http method
    Then The API call is sucessful with response code 201
    And User calls the "CreateUserPost" using "GET" http method