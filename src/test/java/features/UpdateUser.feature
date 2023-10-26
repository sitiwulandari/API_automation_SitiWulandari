Feature: Test the Update User API functionality of GORest website

	@UpdateUser @HappyPath
	Scenario: Test the Update User API flow as Happy Path
	Given Update User API Information
	When User calls the "UpdateUserAPI" using "PUT" http method
	Then The API call is sucessful with response code 200
	And The value "email" in response is shown as "wulaninf48@mailinator.com"
	And The value "status" in response is shown as "inactive" 	
	
	