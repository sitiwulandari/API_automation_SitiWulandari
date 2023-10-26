package pojo;

public class CreateUserTodo {
	private int userId;
	private String title;
	private String dueOn;
	private String status;

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setDueOn(String dueOn){
		this.dueOn = dueOn;
	}

	public String getDueOn(){
		return dueOn;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}
