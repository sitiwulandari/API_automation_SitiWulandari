Feature: Test the Create a User post comment functionality of GORest website


  @CreateUserPostComment
  Scenario: Test the Create a User post comment flow using Happy Path
    Given Creates a user post comment
    When User calls the "CreatePostComment" using "POST" http method
    Then The API call is sucessful with response code 201
    And User calls the "CreatePostComment" using "GET" http method