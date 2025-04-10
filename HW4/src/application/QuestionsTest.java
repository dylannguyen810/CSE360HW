package application;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Questions Test JUnit JavaDoc
 * This JUnit test cases tests the Questions class and the methods within it.
 * testRemoveQuestion1: Pass,
 * testRemoveQuestion2: Pass,
 */
public class QuestionsTest {
	/**
	 * This case tests removing a question as the original author
	 */
	@Test
	public void testRemoveQuestion1() {
		UserLoginPage.userNamePublic = "TestUser1";
		Questions testQuestions = new Questions();
		Question newQuestion = new Question("This is a test question!", UserLoginPage.userNamePublic);
		testQuestions.addQuestion(newQuestion);
		String removedQuestion = testQuestions.removeQuestion(newQuestion, UserLoginPage.userNamePublic);
		assertTrue(removedQuestion.equals("Question Removed"));
	}
	/**
	 * This case tests removing a question as a different user from the original author
	 */
	@Test
	public void testRemoveQuestion2() {
		UserLoginPage.userNamePublic = "TestUser1";
		Questions testQuestions = new Questions();
		Question newQuestion = new Question("This is a test question!", UserLoginPage.userNamePublic);
		testQuestions.addQuestion(newQuestion);
		String removedQuestion = testQuestions.removeQuestion(newQuestion, "Shane");
		assertTrue(removedQuestion.equals("Users did not match! Did not Remove question!"));
	}
}
