package testComponents;

public enum APIResources {
	
	CreateUserAPI("/public/v2/users"),
	GetUser_PP_API("/public/v2/users/{userId}"),
	GetUser_QP_API("/public/v2/users"),
	UpdateUserAPI("/public/v2/users/{userId}"),
	DeleteUserAPI("/public/v2/users/{userId}");
	
	private String resource;
	
	APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}	

}
