Feature: Test the Delete User API functionality of GORest website

	@DeleteUser @HappyPath
	Scenario: Test the Delete User API flow as Happy Path
	Given Delete User API Information and User to be deleted
	When User calls the "DeleteUserAPI" using "DELETE" http method
	Then The API call is sucessful with response code 204
	
	@DeleteUser @ErrorPath
	Scenario: Test the Delete User API flow as ErrorPath to validate the Incorrect User Deletion Check					  
	Given Delete User API Information and Incorrect User to be deleted
	When User calls the "DeleteUserAPI" using "DELETE" http method
	Then The API call is sucessful with response code 404
	And The value "message" in response is shown as "Resource not found"
	