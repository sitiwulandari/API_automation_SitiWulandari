Feature: Test the Create a User Todo API functionality of GORest website

  @CreateUserTodo
  Scenario: Test the Create a User todo flow using Happy Path
    Given Create a User Todo API Information
    When User calls the "createUserTodo" using "POST" http method
    Then The API call is sucessful with response code 201
    And User calls the "createUserTodo" using "GET" http method

	
	