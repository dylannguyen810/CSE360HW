package application;

import java.util.ArrayList;
import java.util.List;

public class Questions {
	private List<Question> questionList = new ArrayList<>();	//declare new list to store questions
	private int nextID = 1;		//incrementing counter for Questions
	
	
	public void addQuestion(String text, int userID) {
		if (text.isEmpty()) {
			System.out.println("Error: Question cannot be empty.");	//Error for empty text
			return;
		}
		questionList.add(new Question(nextID++, text, userID));		//add new question to the list
		System.out.println("Question successfully added.");
	}
	
	public void updateQuestion(int ID, String newText, int userID) {
		for (Question q : questionList) {
			if (q.getID() == ID) {
				if(q.getUserID() != userID) {		//if userID does not match Question's userID
					System.out.println("Error: Can only edit your own questions");	//Display Error
					return;
				}
				q.setText(newText);
				System.out.println("Question updated.");
				return;
			}
		}
		System.out.println("Error: Question ID is not found.");
	}
	
	public void deleteQuestion(int ID, int userID) {
		for (Question q: questionList) {
			if (q.getID() == ID) {
				if(q.getUserID() != userID) {		//if userID does not match Question's userID
					System.out.println("Error: Can only delete your own questions.");	//Display Error
					return;
				}
				questionList.remove(q);
				System.out.println("Question successfully deleted.");
				return;
			}
		}
		System.out.println("Error: Question ID is not found.");
	}
	
	//Display the list of all questions
	public void QuestionList() {
		if (questionList.isEmpty()) {
			System.out.println("No Questions found.");
			return;
		}
		for (Question q : questionList) {
			System.out.println(q);
		}
	}
	
	//Retrieve specific question by its ID
	public Question getQuestion(int ID) {
		for (Question q : questionList) {
			if (q.getID() == ID) 
				return q;
		}
		return null;
	}
	
	
}
