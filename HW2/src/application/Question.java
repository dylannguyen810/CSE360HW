package application;

public class Question {
	private int ID;
	private String text;
	private int userID;
	
		public Question(int ID, String text, int userID) {		//instantiate Constructor for Question 
			this.ID = ID;			//Create unique Question ID
			this.text = text;		//Assigns text
			this.userID = userID;	//Assigns userID to object's field
		}
		//Getters and Setters
		public int getID() {
			return ID;
		}
		
		public String getText() {
			return text;
		}
		
		public void setText(String text) {
			this.text = text;
		}
		
		public int getUserID() {
			return userID;
		}
		
		@Override
		public String toString() {			//Display the Question in desired format
			return "Question: " + text + " (Asked by: " + userID + ")";
		}
}
