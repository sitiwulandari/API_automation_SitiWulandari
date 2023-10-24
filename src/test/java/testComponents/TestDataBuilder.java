package testComponents;

import pojo.CreateUser;
import pojo.UpdateUser;

public class TestDataBuilder {
	
		public CreateUser createUserPayload() {			
		CreateUser cu1 = new CreateUser();
		cu1.setId("707");
		cu1.setEmail("test7007@gmail.com");
		cu1.setGender("male");
		cu1.setName("Shiva S Bharadwaj");
		cu1.setStatus("active");
		return cu1;		
		}
		
		public CreateUser existingUserPayload() {			
		CreateUser cu2 = new CreateUser();
		cu2.setId("707");
		cu2.setEmail("test10005.test@gmail.com");
		cu2.setGender("male");
		cu2.setName("Shiva Shankar Bharadwaj");
		cu2.setStatus("active");
		return cu2;		
		}
		
		public UpdateUser updateUserPayload() {			
		UpdateUser uu = new UpdateUser();
		uu.setId("707");
		uu.setEmail("test7007@gmail.com");
		uu.setGender("male");
		uu.setName("Shiva S Bharadwaj");
		uu.setStatus("inactive");
		return uu;		
		}
		


}
