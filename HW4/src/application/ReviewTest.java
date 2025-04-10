package application;

import static org.junit.Assert.*;
import org.junit.Test;
/**
 * Review Test JUnit JavaDoc
 * This JUnit test cases tests the Review class and the methods within it.
 * testReview: Pass,
 * testUpdate1: Pass,
 * testUpdate2: Pass
 */
public class ReviewTest {
	
	/**
	 * This JUnit test case tests if "TestUser1" can create a review
	 */
	@Test
	public void testReview() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		Review testReview = new Review("This is a test review", UserLoginPage.userNamePublic);
	}
	/**
	 * This JUnit test case tests if "TestUser1" can update the review they made. This test case will pass because the user
	 * is the original author
	 */
	@Test
	public void testUpdate1() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		Review testReview = new Review("This is a test review", UserLoginPage.userNamePublic); // Creates review as set user
		int result = testReview.update("This is an updated review!", UserLoginPage.userNamePublic); // Updates review as set user
		assertTrue(result == 0);
	}
	/**
	 * This JUnit test case tests if "TestUser2" can update the review "TestUser1" made. This test case will fail because the user
	 * is NOT the original author
	 */
	@Test
	public void testUpdate2() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		Review testReview = new Review("This is a test review", UserLoginPage.userNamePublic); // Creates review as set user
		int result = testReview.update("This is an updated review!", "TestUser2"); // Updates review as "TestUser2"
		assertTrue(result == 0);
	}

}
