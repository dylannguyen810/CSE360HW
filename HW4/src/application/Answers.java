package application;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Manages answers for different questions in the application.
 */
public class Answers {
    private final Map<String, List<Answer>> answersMap; // Stores answers for questions
    public static int IDCounter = 1000;

    /**
     * Constructs an Answers object and initializes sample data.
     *
     * @param user The user initializing the answers (not currently used).
     */
    public Answers(String user) {
        answersMap = new HashMap<>();

        // Sample data
        addAnswer("1", "Java is a programming language.", "Alice");
        addAnswer("1", "Java is platform-independent.", "Bob");
        addAnswer("2", "Garbage collection reclaims unused memory.", "Charlie");
    }

    /**
     * Adds an answer to a specific question.
     *
     * @param questionID The ID of the question the answer belongs to.
     * @param answer The content of the answer.
     * @param user The user who posted the answer.
     * @return The newly created Answer object.
     */
    public Answer addAnswer(String questionID, String answer, String user) {
        Answer newAnswer = new Answer(answer, user);
        answersMap.computeIfAbsent(questionID, k -> new ArrayList<>()).add(newAnswer); // Add a new answer to the question
        return newAnswer;
    }
    
    /**
     * Removes all answers associated with a given question.
     *
     * @param question The ID of the question whose answers should be removed.
     * @param user The user requesting the removal.
     * @return A message indicating the success or failure of the removal.
     */
    public String removeAnswers(String question, String user) {
        if (answersMap.containsKey(question)) {
            answersMap.get(question).clear(); // Clears the list of answers for the given question
            return "Removed Answers";
        }
        return "User Match but Could not Find Answers";
    }

    /**
     * Retrieves all answers associated with a given question.
     *
     * @param questionID The ID of the question.
     * @return A list of answers for the question, or an empty list if no answers exist.
     */
    public List<Answer> getAnswers(String questionID) {
        return answersMap.getOrDefault(questionID, new ArrayList<>());
    }

    /**
     * Removes a specific answer from a given question if the user matches the answer's author.
     *
     * @param question The ID of the question containing the answer.
     * @param answerToRemove The answer to be removed.
     * @param user The user requesting the removal.
     * @return A message indicating the success or failure of the removal.
     */
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