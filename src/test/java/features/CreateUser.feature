Feature: Test the Create User API functionality of GORest website

	@CreateUser @HappyPath
	Scenario: Test the Create User flow using Happy Path
	Given Create User API Information
	When User calls the "CreateUserAPI" using "POST" http method
	Then The API call is sucessful with response code 201	
	And The value "email" in response is shown as "damararya222@gmail.com"
	And The value "status" in response is shown as "active"
	And The Userid and Useremail are stored for furture utilization	
	
	@CreateUser @ErrorPath
	Scenario: Test the Create User flow using Error Path to validate the Duplicate User Check
	Given Create User API Information along with an existing user
	When User calls the "CreateUserAPI" using "POST" http method
	Then The API call is sucessful with response code 422
	And The value "field" in response is shown as "[email]"
	And The value "message" in response is shown as "[has already been taken]"
	
	