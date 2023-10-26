package testComponents;

public enum APIResources {
	
	CreateUserAPI("/public/v1/users"),
	GetUser_PP_API("/public/v1/users/{userId}"),
	GetUser_QP_API("/public/v1/users"),
	UpdateUserAPI("/public/v1/users/{userId}"),
	DeleteUserAPI("/public/v1/users/{userId}"),
	CreateUserPost("/public/v1/users/{userId}/posts"),
	CreatePostComment("/public/v1/posts/{userId}/comments"),
	CreateUserTodo("/public/v1/users/{userId}/todos");

	
	private String resource;
	
	APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}	

}
