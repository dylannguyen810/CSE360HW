package application;

import java.util.HashSet;

/**
 * {@code QuestionTestingAutomation} class contains automated test cases
 * that validate the functionality of the question and answer platform.
 * It will ensure the security of the platform, provide proper system validation, and data integrity of the system.
 * 
 * <p>Test cases include checking permissions, input validation, and system restrictions
 * to prevent unauthorized modifications within the system.</p>
 * 
 * @author Dylan Nguyen
 * @version 1.0
 */
public class QuestionTestingAutomation {
    /**
     * Main method executes the series of automated tests chosen for this HW.
     *
     * @param args Arguments not used.
     */
    public static void main(String[] args) {
        // Set to track existing question titles
        HashSet<String> questionTitles = new HashSet<>();

        // Creating question by Alice
        TestQuestion question1 = new TestQuestion("What is Java?", "Alice");
        questionTitles.add(question1.getTitle()); // Adding title to set

        // Creating question by Charlie
        TestQuestion question2 = new TestQuestion("How does garbage collection work?", "Charlie");
        questionTitles.add(question2.getTitle()); // Adding title to set

        // Creating Answers for Alice's question
        TestAnswers AliceAnswers = new TestAnswers("Alice");

        // Test Case 1: Unauthorized question update - should fail
        System.out.println("Test Case 1: " + question1.editQuestionTitle("Is this an example question?", "Charlie"));

        // Test Case 2: Authorized question update - should succeed
        System.out.println("Test Case 2: " + question1.editQuestionTitle("Is this an example question?", "Alice"));

        // Test Case 3: Unauthorized update on another user's question - should fail
        System.out.println("Test Case 3: " + question2.editQuestionTitle("This is not a question!", "Alice"));

        // Test Case 4: Authorized update - should succeed
        System.out.println("Test Case 4: " + question2.editQuestionTitle("This is not a question!", "Charlie"));

        // Test Case 5: Unauthorized answer removal - should fail
        TestAnswer CharlieAnswer = AliceAnswers.submitAnswer("Is this an example question?", "Java is a programming language!", "Charlie");
        System.out.println("Test Case 5: " + AliceAnswers.deleteAnswer("Is this an example question?", CharlieAnswer, "Alex"));

        // Test Case 6: Authorized answer removal - should succeed
        System.out.println("Test Case 6: " + AliceAnswers.deleteAnswer("Is this an example question?", CharlieAnswer, "Charlie"));

        // Test Case 7: Creating and removing an answer by the same user - should succeed
        TestAnswer AliceNewAnswer = AliceAnswers.submitAnswer("Is this an example question?", "Java is widely used!", "Alice");
        System.out.println("Test Case 7: " + AliceAnswers.deleteAnswer("Is this an example question?", AliceNewAnswer, "Alice"));

        // Test Case 8: Detecting duplicate question titles before creation
        String duplicateTitle = "What is Java?";
        boolean isDuplicate = questionTitles.contains(duplicateTitle);

        if (!isDuplicate) {
            // This part will not run since a duplicate is detected
            TestQuestion duplicateQuestion = new TestQuestion(duplicateTitle, "Charlie");
            questionTitles.add(duplicateQuestion.getTitle()); // Adding to the set if allowed
            System.out.println("Test Case 8: Duplicate question added successfully.");
        } else {
            System.out.println("Test Case 8: Duplicate question detected, creation prevented.");
        }

        // Printing the titles of all questions to demonstrate getTitle() functionality
        System.out.println("\nTitles of all questions:");
        System.out.println("Question 1 Title: " + question1.getTitle());
        System.out.println("Question 2 Title: " + question2.getTitle());
    }
}

/**
 * {@code TestQuestion} class represents a question with a title and author.
 * This allows authorized users to modify their own questions.
 */
class TestQuestion {
    private String title;
    private String author;

    /**
     * Constructs a new {@code TestQuestion}.
     *
     * @param title  Title of the question.
     * @param author Author who created the question.
     */
    public TestQuestion(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * This will retrieve the title of the question.
     *
     * @return Title of the question.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Updates the question title if author is also requester.
     *
     * @param newTitle  New title for the question.
     * @param requester User attempting the update.
     * @return {@code true} if the update is successful, else {@code false}.
     */
    public boolean editQuestionTitle(String newTitle, String requester) {
        if (this.author.equals(requester)) {
            this.title = newTitle;
            return true;
        }
        return false;
    }
}

/**
 * {@code TestAnswers} class manages all answers submitted by users.
 * Allows answer submission and removal by authorized users.
 */
class TestAnswers {
    private String owner;

    /**
     * Constructs a new {@code TestAnswers} object for specific owners.
     *
     * @param owner Owner of the answers.
     */
    public TestAnswers(String owner) {
        this.owner = owner;
    }

    /**
     * Submits an answer to a question.
     *
     * @param questionTitle Title of the question being answered.
     * @param answerText    Text of the answer.
     * @param answerer      User submitting the answer.
     * @return A {@code TestAnswer} object representing the answer submitted.
     */
    public TestAnswer submitAnswer(String questionTitle, String answerText, String answerer) {
        return new TestAnswer(questionTitle, answerText, answerer);
    }

    /**
     * Deletes an answer if requester is the original answerer.
     *
     * @param questionTitle Title of the question related to the answer.
     * @param answer        Answer being deleted.
     * @param remover       User attempting to remove the answer.
     * @return {@code true} if removal is successful, else {@code false}.
     */
    public boolean deleteAnswer(String questionTitle, TestAnswer answer, String remover) {
        return answer.getAnswerer().equals(remover);
    }
}

/**
 * {@code TestAnswer} class represents an answer submitted to desired question.
 */
class TestAnswer {
    private String questionTitle;
    private String answerText;
    private String answerer;

    /**
     * Construct a new {@code TestAnswer}.
     *
     * @param questionTitle Title of the question being answered.
     * @param answerText    Content of the answer.
     * @param answerer      User who submitted the answer.
     */
    public TestAnswer(String questionTitle, String answerText, String answerer) {
        this.questionTitle = questionTitle;
        this.answerText = answerText;
        this.answerer = answerer;
    }

    /**
     * Retrieve the username who submitted the answer.
     *
     * @return The answerer's username.
     */
    public String getAnswerer() {
        return answerer;
    }
}