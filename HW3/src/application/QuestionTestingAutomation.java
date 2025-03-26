package application;
import java.util.HashSet;

public class QuestionTestingAutomation {
    public static void main(String[] args) {
        // Set to track existing question titles
        HashSet<String> questionTitles = new HashSet<>();

        // Creating question by Alice
        TestQuestion question1 = new TestQuestion("What is Java?", "Alice");
        questionTitles.add(question1.getTitle()); // Adding the title to the set of question titles

        // Creating question by Charlie
        TestQuestion question2 = new TestQuestion("How does garbage collection work?", "Charlie");
        questionTitles.add(question2.getTitle()); // Adding the title to the set of question titles

        // Creating Answers for Alice's question
        TestAnswers AliceAnswers = new TestAnswers("Alice");

        // Test Case 1: Trying to update Alice's question as Charlie - this will fail
        System.out.println("Test Case 1: " + question1.editQuestionTitle("Is this an example question?", "Charlie"));

        // Test Case 2: Trying to update Alice's question as Alice - this will succeed
        System.out.println("Test Case 2: " + question1.editQuestionTitle("Is this an example question?", "Alice"));

        // Test Case 3: Trying to update Charlie's question as Alice - this will fail
        System.out.println("Test Case 3: " + question2.editQuestionTitle("This is not a question!", "Alice"));

        // Test Case 4: Trying to update Charlie's question as Charlie - this will succeed
        System.out.println("Test Case 4: " + question2.editQuestionTitle("This is not a question!", "Charlie"));

        // Creating an answer to Alice's question as Charlie
        TestAnswer CharlieAnswer = AliceAnswers.submitAnswer("Is this an example question?", "Java is a programming language!", "Charlie");

        // Test Case 5: Trying to remove Charlie's answer as Alex to Alice's question - this will fail
        System.out.println("Test Case 5: " + AliceAnswers.deleteAnswer("Is this an example question?", CharlieAnswer, "Alex"));

        // Test Case 6: Trying to remove Charlie's answer as Charlie to Alice's question - this will succeed
        System.out.println("Test Case 6: " + AliceAnswers.deleteAnswer("Is this an example question?", CharlieAnswer, "Charlie"));

        // Test Case 7: Creating a new answer to Alice's question as Alice
        TestAnswer AliceNewAnswer = AliceAnswers.submitAnswer("Is this an example question?", "Java is widely used!", "Alice");
        System.out.println("Test Case 7: " + AliceAnswers.deleteAnswer("Is this an example question?", AliceNewAnswer, "Alice"));

        // Test Case 8: Detecting duplicate question titles before creation
        String duplicateTitle = "What is Java?";
        boolean isDuplicate = questionTitles.contains(duplicateTitle);

        if (!isDuplicate) {
            // Test Case 8: Allow creation of duplicate question (this part will not run, as duplicate is detected)
            TestQuestion duplicateQuestion = new TestQuestion(duplicateTitle, "Charlie");
            questionTitles.add(duplicateQuestion.getTitle()); // Adding the title to the set if duplicate is allowed
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

// Renamed class to "TestQuestion"
class TestQuestion {
    private String title;
    private String author;

    // Constructor for the TestQuestion class
    public TestQuestion(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Getter for the title of the question
    public String getTitle() {
        return title;
    }

    // Renamed method to edit question title
    public boolean editQuestionTitle(String newTitle, String requester) {
        if (this.author.equals(requester)) {
            this.title = newTitle;
            return true;
        }
        return false;
    }
}

// Renamed class to "TestAnswers"
class TestAnswers {
    private String owner;

    // Constructor for TestAnswers class
    public TestAnswers(String owner) {
        this.owner = owner;
    }

    // Renamed method to submit answer
    public TestAnswer submitAnswer(String questionTitle, String answerText, String answerer) {
        return new TestAnswer(questionTitle, answerText, answerer);
    }

    // Renamed method to delete answer
    public boolean deleteAnswer(String questionTitle, TestAnswer answer, String remover) {
        if (answer.getAnswerer().equals(remover)) {
            return true;
        }
        return false;
    }
}

// Renamed class to "TestAnswer"
class TestAnswer {
    private String questionTitle;
    private String answerText;
    private String answerer;

    // Constructor for TestAnswer class
    public TestAnswer(String questionTitle, String answerText, String answerer) {
        this.questionTitle = questionTitle;
        this.answerText = answerText;
        this.answerer = answerer;
    }

    // Getter for the answerer of the answer
    public String getAnswerer() {
        return answerer;
    }
}