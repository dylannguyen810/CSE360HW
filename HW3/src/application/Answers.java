package application;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Answers {
	//private String user;

    private final Map<String, List<Answer>> answersMap; //Stored answers for questions
    //Constructor
    public Answers(String user) {
        answersMap = new HashMap<>();
        
        //this.user = user;

        // Sample data
        addAnswer("1", "Java is a programming language.", "Alice");
        addAnswer("1", "Java is platform-independent.", "Bob");
        addAnswer("2", "Garbage collection reclaims unused memory.", "Charlie");
    }

    // Add an answer to a question
    public Answer addAnswer(String questionID, String answer, String user) {
    	Answer newAnswer = new Answer(answer, user);
        answersMap.computeIfAbsent(questionID, k -> new ArrayList<>()).add(newAnswer); // Add a new answer to the question
        return newAnswer;
    }
    
    // Remove answers for a question
    public String removeAnswers(String question, String user) {
	    	if (answersMap.containsKey(question)) {
	            answersMap.get(question).clear(); // Clears the list of answers for the given question
				return "Removed Answers";
	        }
	    	return "User Match but Could not Find Answers";
    }

    // Get all answers for a question
    public List<Answer> getAnswers(String questionID) {
        return answersMap.getOrDefault(questionID, new ArrayList<>());
    }

	public String removeAnswer(String question, Answer answerToRemove, String user) {
		if (answerToRemove.getUser().equals(user)) {
			List<Answer> answerList = answersMap.get(question);
	        if (answerList != null) {
	            answerList.remove(answerToRemove); // Removes the specific answer from the list
				return "Removed Answer";
	        }
	    	return "User Match but Could not Find Answer";
		}
		return "Users did not match! Did not Remove Answer!";
	}
}
