Feature: Test the Get User API functionality of GORest website
	
	@GetUser @HappyPath
	Scenario: Test the Get User API flow as Happy Path to fetch female users in active status
	Given Get User API Information filtering a female active user
	When User calls the "GetUser_QP_API" using "GET" http method
	Then The API call is sucessful with response code 200
	And The value "gender" in response is shown as "[female]"
	And The value "status" in response is shown as "[active]"	
	
	@GetUser @HappyPath
	Scenario: Test the Get User API flow as Happy Path to fetch male users in inactive status
	Given Get User API Information filtering a male inactive user
	When User calls the "GetUser_QP_API" using "GET" http method
	Then The API call is sucessful with response code 200
	And The value "gender" in response is shown as "[male]"
	And The value "status" in response is shown as "[inactive]"	
	
	@GetUser @HappyPath
	Scenario: Test the Get User API flow as Happy Path to fetch users whose email ends with .test
	Given Get User API Information filtering a user whose email ends with .test
	When User calls the "GetUser_QP_API" using "GET" http method
	Then The API call is sucessful with response code 200
	And The value "email" in response ends with ".test"
	
	@GetUser @HappyPath
	Scenario: Test the Get User API flow as Happy Path to fetch a list of users and validate all the five fields in the response
	Given Get User API Information with a filter criteria to fetch a list of users
	When User calls the "GetUser_QP_API" using "GET" http method
	Then The API call is sucessful with response code 200
	And Validate if the correct number of users are returned and all the five fields for each returned user is present in the response
	
	@GetUser @ErrorPath
	Scenario: Test the Get User API flow as Error Path using an Incorrect User Id
	Given Get User API Information and Incorrect User Id
	When User calls the "GetUser_PP_API" using "GET" http method
	Then The API call is sucessful with response code 404
	And The value "message" in response is shown as "Resource not found"		
	
	