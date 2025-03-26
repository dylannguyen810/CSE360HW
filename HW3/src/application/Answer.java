package application;

public class Answer {
	private String text;
	private String user;
	private boolean solution;
	//Constructor
	public Answer(String text, String user) {
		this.text = text;
		this.user = user;
	}
	//Get text function
	public String getText() {
		return text;
	}
	//Get user function
	public String getUser() {
		return user;
	}
	//Get update question
	public void update(String newQuestion) {
		if (user.equals(UserLoginPage.userNamePublic)) {
			text = newQuestion;
		}
	}
	//Set solution to question
	public void setSolution(boolean solution) {
		this.solution = solution;
	}
	
	@Override
	public String toString() {
		if (solution) {
			return text + " (Posted by: " + user + ") (Solution!)";
		}
		return text + " (Posted by: " + user + ")";
	}
}
