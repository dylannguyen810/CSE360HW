package application;

import java.util.ArrayList;
import java.util.List;

public class Answers {
	private List<Answer> answerList = new ArrayList<>();	//declare new list to store Answers
	private int nextID = 1;		//incrementing counter for Answers
	

	public void addAnswer(int ID, String text, int userID) {
		if(text.isEmpty()) {
			System.out.println("Error: Answer cannot be empty.");	//Error for empty text
			return;
		}
		answerList.add(new Answer(nextID++, text, userID));		//add new answer to the list
		System.out.println("Answer successfully added");
	}
	
	//Display the list of all Answers
	public void AnswerList(int ID) {
		boolean founded = false;
		for (Answer a : answerList) {
			if (a.getID() == ID) {
				System.out.println(a);
				founded = true;
			}
		}
		if(!founded) {
			System.out.println("No Answers were found for this question.");
		}
	}
	
	public void updateAnswer(int ID, String newText, int userID) {
		for (Answer a : answerList) {
			if (a.getID() == ID) {
				if (a.getID() != userID) {		//if userID does not match Answers's userID
					System.out.println("Error: Can only edit your own answers.");	//Display Error
					return;
				}
				a = new Answer(ID, newText, userID);
				System.out.println("Answer successfully updated");
				return;
			}
		}
		System.out.println("Error: Answer ID is not found.");
	}
	
	public void deleteAnswer(int ID, int userID) {
		for (Answer a : answerList) {
			if (a.getID() == ID) {
				if (a.getID() != userID) {		//if userID does not match Answers's userID
					System.out.println("Error: Can only delete your own answers.");	//Display Error
					return;
				}
				answerList.remove(a);
				System.out.println("Answer successfully deleted.");
				return;
			}
		}
		System.out.println("Error: Answer ID is not found");
	}
}
