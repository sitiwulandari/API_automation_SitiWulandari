package testComponents;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
		public static RequestSpecification baserequest;
	
		public RequestSpecification requestSpecification() throws IOException {	
		if(baserequest==null) {
		PrintStream logs = new PrintStream(new FileOutputStream("Complete_Logs.txt"));
		baserequest = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).
										   setContentType("application/json").
										   addFilter(RequestLoggingFilter.logRequestTo(logs)).
										   addFilter(ResponseLoggingFilter.logResponseTo(logs)).build();
		return baserequest;
		}
		return baserequest;
		}
		
		public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//GlobalValues.properties");
		prop.load(fis);
		return prop.getProperty(key);
		}
		
		public String getDynamicValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//DynamicValues.properties");
		prop.load(fis);
		return prop.getProperty(key);
		}
		
		public String parseJSON(Response response, String key) {
		String responsestg = response.asString();
		JsonPath js = new JsonPath(responsestg);
		return js.getString(key);			
		}
		
}
