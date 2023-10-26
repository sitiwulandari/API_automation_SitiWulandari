package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import io.restassured.specification.*;
import testComponents.APIResources;
import testComponents.TestDataBuilder;
import testComponents.Utils;
import io.restassured.builder.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import org.junit.Assert;

public class StepDefinitions extends Utils{
	
	RequestSpecification request;
	Response response;
	TestDataBuilder testdata = new TestDataBuilder();
		
	@Given("Create User API Information")
	public void create_user_api_information() throws IOException {	
	request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").
			  body(testdata.createUserPayload());
	}
	
	@When("User calls the {string} using {string} http method")
	public void user_calls_the_using_http_method(String resource, String method) {
	APIResources resourceAPI = APIResources.valueOf(resource);
	if(method.equalsIgnoreCase("GET")) {
		response = request.when().get(resourceAPI.getResource());
	}
	else if(method.equalsIgnoreCase("POST")) {
		response = request.when().post(resourceAPI.getResource());
	}
	
	else if(method.equalsIgnoreCase("PUT")) {
		response = request.when().put(resourceAPI.getResource());
	}
	
	else if(method.equalsIgnoreCase("DELETE")) {
		response = request.when().delete(resourceAPI.getResource());
	}
	   
	}
	@Then("The API call is sucessful with response code {int}")
	public void the_api_call_is_sucessful_with_response_code(Integer statusCode) {
	if(statusCode.equals(200))
	Assert.assertEquals(response.getStatusCode(), 200);
	
	else if(statusCode.equals(201))
	Assert.assertEquals(response.getStatusCode(), 201);
	
	else if(statusCode.equals(204))
	Assert.assertEquals(response.getStatusCode(), 204);	 	
	
	else if(statusCode.equals(404))
	Assert.assertEquals(response.getStatusCode(), 404);	    
	
	
	else if(statusCode.equals(422))
	Assert.assertEquals(response.getStatusCode(), 422);	    
	}
	
	@Then("The value {string} in response is shown as {string}")
	public void the_value_in_response_is_shown_as(String key, String expectedValue) {
	Assert.assertEquals(parseJSON(response, key), expectedValue);	    
	}
	
	@Then("The Userid and Useremail are stored for furture utilization")
	public void the_userid_and_useremail_are_stored_for_furture_utilization() throws IOException {
	String userId = parseJSON(response,"id");	
	String email = parseJSON(response,"email");	
	
	Properties prop2 = new Properties();
	FileInputStream fis2 = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//DynamicValues.properties");
	prop2.load(fis2);
	prop2.setProperty("userId", userId);
	prop2.setProperty("email", email);
	
	BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"//src//test//resources//DynamicValues.properties"));
	prop2.store(writer, "userId and email");
	writer.close();		
	}
	
	@Given("Create User API Information along with an existing user")
	public void create_user_api_information_along_with_an_existing_user() throws IOException {
	request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").
			  body(testdata.existingUserPayload()); 
	}
	
	@Given("Get User API Information filtering a female active user")
	public void get_user_api_information_filtering_a_female_active_user() throws IOException {
	request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").
			  queryParam("gender", "female").queryParam("status", "active").queryParam("per_page", 1);	    
	}	

	@Given("Get User API Information filtering a male inactive user")
	public void get_user_api_information_filtering_a_male_inactive_user() throws IOException {
	request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").
			  queryParam("gender", "male").queryParam("status", "inactive").queryParam("per_page", 1);	
	}
	
	@Given("Get User API Information filtering a user whose email ends with .test")
	public void get_user_api_information_filtering_a_user_whose_email_ends_with_test() throws IOException {
	request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").
			  queryParam("email", ".test").queryParam("per_page", 1);	
	}	
	
	@Given("Get User API Information with a filter criteria to fetch a list of users")
	public void get_user_api_information_with_a_filter_criteria_to_fetch_a_list_of_users() throws IOException {
	request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").
			  queryParam("gender", "female").queryParam("email", ".test").queryParam("per_page", "3");
			      
	}
	
	@Given("Get User API Information and Incorrect User Id")
	public void get_user_api_information_and_incorrect_user_id() throws IOException {
	request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").
			  pathParam("userId", getGlobalValue("userId"));   
	}
	
	@Then("Validate if the correct number of users are returned and all the five fields for each returned user is present in the response")
	public void validate_if_the_correct_number_of_users_are_returned_and_all_the_five_fields_for_each_returned_user_is_present_in_the_response() {
	String UserListstg = response.asString();
	JsonPath js = new JsonPath(UserListstg);	
	int objectCount = js.getInt("$.size()");
	System.out.println("Total Number of JSON objects in the response is "+objectCount);
	
	Object jsonObject = js.get("[0]");
	int numberOfFields = jsonObject.getClass().getDeclaredFields().length;
	
	System.out.println("Total Number of fields in the JSONObject is "+numberOfFields);
	
	List<HashMap> aList = js.getList("$");
	for(HashMap obj: aList){
		if (!obj.containsKey("id") || !obj.containsKey("name")|| !obj.containsKey("email")|| !obj.containsKey("gender")|| !obj.containsKey("status")){
        	System.out.println("Object {" + obj.toString() + "} does NOT contain the required keys and values");
        	Assert.fail("User List does NOT contain the required keys and values");
        }
		else
        {
			System.out.println("Object {" + obj.toString() + "} contains the required keys and values");
        }        
        
    }
	
    }	
		
	@Then("The value {string} in response ends with {string}")
	public void the_value_in_response_ends_with(String key, String expectedValue) {
	Assert.assertTrue(parseJSON(response, key).contains(expectedValue));
	}
	
	@Given("Update User API Information")
	public void update_user_api_information() throws IOException {
	request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").
			  pathParam("userId", getDynamicValue("userId")).body(testdata.updateUserPayload());  			    
	}

	
	@Given("Delete User API Information and User to be deleted")
	public void delete_user_api_information_and_user_to_be_deleted() throws IOException {
	request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").pathParam("userId", getDynamicValue("userId"));
	}
	
	@Given("Delete User API Information and Incorrect User to be deleted")
	public void delete_user_api_information_and_incorrect_user_to_be_deleted() throws IOException {
	request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").pathParam("userId", getGlobalValue("userId"));
	}


	@Given("Creates a user post")
	public void createsAUserPost() throws IOException {
		request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").
				pathParam("userId", getDynamicValue("userId")).body(testdata.createUserPostPayload());
	}

	@Given("Creates a user post comment")
	public void createsAUserPostComment() throws IOException{
		request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").
				pathParam("userId", getDynamicValue("userId")).body(testdata.createPostCommentPayload());

	}
	@Given("Create a User Todo API Information")
	public void createAUserTodoAPIInformation() throws IOException{
		request = given().spec(requestSpecification()).auth().oauth2("8e864ebe0435372bd5e1ff3a1c85dedf12a020ebf10e53f5bb975cdc429f9b63").
				pathParam("userId", getDynamicValue("userId")).body(testdata.createUserTodoPayload());

	}
}
