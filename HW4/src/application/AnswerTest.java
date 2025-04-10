package application;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Answer Test JUnit JavaDoc
 * This JUnit test cases tests the Answer class and the methods within it.
 * testGetText: Pass,
 * testGetUser: Pass,
 * testUpdate: Pass,
 * testSetSolution: Pass,
 * testGetAnswerId: Pass,
 */
public class AnswerTest {
	/**
	 * This case tests getting the text of the answer posted
	 */
	@Test
	public void testGetText() {
		Answer answerToTest = new Answer("This is a test answer!", "Emily"); // Test answer created by "Emily"
		assertTrue(answerToTest.getText().equals("This is a test answer!"));
	}
	/**
	 * This case tests getting the author of the answer posted
	 */
	@Test
	public void testGetUser() {
		Answer answerToTest = new Answer("This is a test answer!", "Emily"); // Test answer created by "Emily"
		assertTrue(answerToTest.getUser().equals("Emily"));
	}
	/**
	 * This case tests updating an answer as the original author
	 */
	@Test
	public void testUpdate() {
		UserLoginPage.userNamePublic = "Emily";
		Answer answerToTest = new Answer("This is a test answer!", "Emily"); // Test answer created by "Emily"
		String updatedAnswer = answerToTest.update("This is an updated test!");
		assertTrue(updatedAnswer.equals("Updated Answer!"));
	}
	/**
	 * This case tests setting an answer as a solution
	 */
	@Test
	public void testSetSolution() {
		Answer answerToTest = new Answer("This is a test answer!", "Emily"); // Test answer created by "Emily"
		answerToTest.setSolution(true);
		assertTrue(answerToTest.getSolution() == true);
	}
	/**
	 * This case tests getting unique ID tied to the answer
	 */
	@Test
	public void testGetAnswerId() {
		Answer answerToTest = new Answer("This is a test answer!", "Emily"); // Test answer created by "Emily"
		String id = null;
		id = answerToTest.getAnswerId();
		assertTrue(id != null);
	}

}
