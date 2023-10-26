package testComponents;

import pojo.*;

public class TestDataBuilder {
	
		public CreateUser createUserPayload() {			
		CreateUser cu1 = new CreateUser();
		cu1.setId("5489062");
		cu1.setEmail("wulaninf48@mailinator.com");
		cu1.setGender("female");
		cu1.setName("wulan");
		cu1.setStatus("active");
		return cu1;		
		}
		
		public CreateUser existingUserPayload() {			
		CreateUser cu2 = new CreateUser();
		cu2.setId("5489062");
		cu2.setEmail("wulaninf48@mailinator.com");
		cu2.setGender("female");
		cu2.setName("wulan");
		cu2.setStatus("active");
		return cu2;		
		}
		
		public UpdateUser updateUserPayload() {			
		UpdateUser uu = new UpdateUser();
		uu.setId("5489062");
		uu.setEmail("wulaninf48@mailinator.com");
		uu.setGender("female");
		uu.setName("wulan");
		uu.setStatus("inactive");
		return uu;		
		}

		public CreateUserPost createUserPostPayload(){
			CreateUserPost us = new CreateUserPost();
			us.setTitle("Wulandari");
			us.setBody("auda blanditiis colloco. Arcus consequatur sapiente. Spoliatio quam comparo.");
			return us;
		}

		public CreateUserTodo createUserTodoPayload(){
			CreateUserTodo pc = new CreateUserTodo();
			pc.setUserId(5507773);
			pc.setTitle("anto universe aperte damno vallum - Test by Siti Wulandari");
			pc.setDueOn("2023-11-15T00:00:00.000+05:30");
			pc.setStatus("pending");
			return pc;
		}

		public CreatePostComment createPostCommentPayload(){
			CreatePostComment co = new CreatePostComment();
			co.setPostId(80699);
			co.setName("Siti Wulandari");
			co.setEmail("sitiiwulandari@gmail.com");
			co.setBody("Et rem exercitationem. Et amet aperiam. Delectus libero nam. Rerum fugit nesciunt.");
			return co;
		}


}
