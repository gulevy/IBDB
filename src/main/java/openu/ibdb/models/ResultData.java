package openu.ibdb.models;

//{ success: false, message: 'Username or password is incorrect' };
public class ResultData {
	private boolean success;
	private String message;
	
	public ResultData(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
