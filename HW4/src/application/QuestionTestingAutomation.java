package application;

public class QuestionTestingAutomation {
	public static void main(String[] args) {
		// Creating question by Alice
		Question question1 = new Question("What is Java?", "Alice");
		// Creating question by Charlie
		Question question2 = new Question("How does garbage collection work?", "Charlie");
		// Creating Answers for Alice's question
		Answers AliceAnswers = new Answers("Alice");
		// Trying to update Alice's question as Charlie - this will fail
		System.out.println("Test Case 1: " + question1.update("Is this an example question?", "Charlie"));
		// Trying to update Alice's question as Alice - this will succeed
		System.out.println("Test Case 2: " + question1.update("Is this an example question?", "Alice"));
		// Trying to update Charlies question as Alice - this will fail
		System.out.println("Test Case 3: " + question2.update("This is not a question!", "Alice"));
		// Trying to update Charlies question as Charlies - this will succeed
		System.out.println("Test Case 4: " + question2.update("This is not a question!", "Charlie"));
		// Creating an answer to Alice's question as Charlie
		Answer CharlieAnswer = AliceAnswers.addAnswer("Is this an example question?", "Java is a programming language!",
				"Charlie");
		// Trying to remove Charlie's answer as Alex to Alice's question - this will fail
		System.out.println(
				"Test Case 5: " + AliceAnswers.removeAnswer("Is this an example question?", CharlieAnswer, "Alex"));
		// Trying to remove Charlie's answer as Charlie to Alice's question - this will succeed
		System.out.println(
				"Test Case 6: " + AliceAnswers.removeAnswer("Is this an example question?", CharlieAnswer, "Charlie"));
	}
}
