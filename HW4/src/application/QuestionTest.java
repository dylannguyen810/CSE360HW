package application;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Question Test JUnit JavaDoc
 * This JUnit test cases tests the Question class and the methods within it.
 * testGetQuestionId: Pass,
 * testSetQuestionId: Pass,
 * testGetText: Pass,
 * testGetUser: Pass,
 * testGetSolution: Pass, 
 * testIsResolved: Pass,
 * testUpdate1: Pass,
 * testUpdate2: Pass,
 * testSetSolution: Pass,
 * testRemoveSolution: Pass
 */
public class QuestionTest {
	/**
	 * This case tests retrieving the unique ID tied to a question
	 */
	@Test
	public void testGetQuestionId() {
		Question questionToTest = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		String id = null;
		id = questionToTest.getQuestionId();
		assertTrue(id != null); // if the id is not null then we successfully retrieved the question id
	}
	/**
	 * This case tests setting the unique ID tied to a question
	 */
	@Test
	public void testSetQuestionId() {
		Question questionToTest = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		questionToTest.setQuestionId(0);
		assertTrue(questionToTest.getQuestionId().equals("0"));
	}
	/**
	 * This case tests getting the question posted by the author
	 */
	@Test
	public void testGetText() {
		Question questionToTest = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		assertTrue(questionToTest.getText() == "This is a test question!");
	}
	/**
	 * This case tests getting the author of the question
	 */
	@Test
	public void testGetUser() {
		Question questionToTest = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		assertTrue(questionToTest.getUser() == "Emily");
	}
	/**
	 * This case tests getting the solution to the question 
	 */
	@Test
	public void testGetSolution() {
		Question questionToAnswer = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		Answers answersToQuestion = new Answers(UserLoginPage.userNamePublic);
		Answer answerToReview = answersToQuestion.addAnswer(questionToAnswer.getQuestionId(), "This is a test answer!", "Andrew");
		questionToAnswer.setSolution(answerToReview.getText());
		assertTrue(questionToAnswer.getSolution() == "This is a test answer!");
	}
	/**
	 * This case tests checking the status of whether or not a question is resolved
	 */
	@Test
	public void testIsResolved() {
		Question questionToAnswer = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		Answers answersToQuestion = new Answers(UserLoginPage.userNamePublic);
		Answer answerToReview = answersToQuestion.addAnswer(questionToAnswer.getQuestionId(), "This is a test answer!", "Andrew");
		questionToAnswer.setSolution(answerToReview.getText());
		assertTrue(questionToAnswer.isResolved() == true);
	}
	/**
	 * This case tests updating the question posted by the author as the original author
	 */
	@Test
	public void testUpdate1() {
		Question questionToAnswer = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		String updatedQuestion = questionToAnswer.update("This is a new test question!", "Emily");
		assertTrue(updatedQuestion.equals("Updated Question"));
	}
	/**
	 * This case tests updating the question posted by the author as a different user
	 */
	@Test
	public void testUpdate2() {
		Question questionToAnswer = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		String updatedQuestion = questionToAnswer.update("This is a new test question!", "Derek");
		assertTrue(updatedQuestion.equals("Users did not match! Did not update question!"));
	}
	/**
	 * This case tests setting the solution to a question
	 */
	@Test
	public void testSetSolution() {
		Question questionToAnswer = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		Answers answersToQuestion = new Answers(UserLoginPage.userNamePublic);
		Answer answerToReview = answersToQuestion.addAnswer(questionToAnswer.getQuestionId(), "This is a test answer!", "Andrew");
		questionToAnswer.setSolution(answerToReview.getText());
		assertTrue(questionToAnswer.getSolution() == "This is a test answer!");
	}
	/**
	 * This case tests removing the solution to a question
	 */
	@Test
	public void testRemoveSolution() {
		Question questionToAnswer = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		Answers answersToQuestion = new Answers(UserLoginPage.userNamePublic);
		Answer answerToReview = answersToQuestion.addAnswer(questionToAnswer.getQuestionId(), "This is a test answer!", "Andrew");
		questionToAnswer.setSolution(answerToReview.getText());
		questionToAnswer.removeSolution();
		assertTrue(questionToAnswer.isResolved() == false);
	}

}
