package application;

public class Answer {
	private int ID;
	private String text;
	private int userID;
	
	public Answer(int ID, String text, int userID) {		//instantiate Constructor for Question
		this.ID = ID;				//Create unique Answer ID 
		this.text = text;			//Assign desired content for answer
		this.userID = userID;		//Assign userID 
	}
	
	//Setters and Getters
	public int getID() {
		return ID;
	}
	
	public String getText() {
		return text;
	}
	
	public int getUserID() {
		return userID;
	}
	
	@Override						//Display the format for desired answer
	public String toString() {
		return "Answer: " + text + " (By: " + userID + ")";
	}
}
