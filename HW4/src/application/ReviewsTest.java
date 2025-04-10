package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Reviews Test JUnit JavaDoc
 * This JUnit test cases tests the Reviews class and the methods within it.
 * testAddReview1: Pass,
 * testAddReview2: Pass,
 * testAddReview3: Pass,
 * testAddReview4: Pass,
 * testRemoveReviews1: Pass, 
 * testRemoveReviews2: Pass,
 * testRemoveReview1: Pass,
 * testRemoveReview2: Pass,
 * testGetReviews: Pass,
 * testGetReviewsByAuthor: Pass
 */
public class ReviewsTest {
	/**
	 * This case tests if a user with the Role "Reviewer" can add a review to a question
	 */
	@Test
	public void testAddReview1() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		UserLoginPage.userRole = "Reviewer"; // Sets user's role to "Reviewer"
		Reviews reviewsTest = new Reviews(UserLoginPage.userNamePublic); // Creates Reviews class for test user
		Question questionToReview = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		Review review = null;
		// Review created by "TestUser1" on test question to review
		review = reviewsTest.addReview(questionToReview.getQuestionId(), "This is a test review!", UserLoginPage.userNamePublic);
		assertTrue(review != null); // If the review is not null (was returned) then a review was successfully created
	}
	/**
	 * This case tests if a user with the Role "Student" can add a review to a question
	 */
	@Test
	public void testAddReview2() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		UserLoginPage.userRole = "Student"; // Sets user's role to "Student"
		Reviews reviewsTest = new Reviews(UserLoginPage.userNamePublic); // Creates Reviews class for test user
		Question questionToReview = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		Review review = null;
		// Review created by "TestUser1" on test question to review
		review = reviewsTest.addReview(questionToReview.getQuestionId(), "This is a test review!", UserLoginPage.userNamePublic);
		assertTrue(review == null); // If the review is null (was not returned) then a review was not created
	}
	/**
	 * This case tests if a user with the Role "Reviewer" can add a review to an answer
	 */
	@Test
	public void testAddReview3() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		UserLoginPage.userRole = "Reviewer"; // Sets user's role to "Reviewer"
		Reviews reviewsTest = new Reviews(UserLoginPage.userNamePublic); // Creates Reviews class for test user
		Answers answersToQuestion = new Answers(UserLoginPage.userNamePublic);
		Question questionToAnswer = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		Answer answerToReview = answersToQuestion.addAnswer(questionToAnswer.getQuestionId(), "This is a test answer!", "Andrew");
		Review review = null;
		// Review created by "TestUser1" on test question to review
		review = reviewsTest.addReview(answerToReview.getAnswerId(), "This is a test review!", UserLoginPage.userNamePublic);
		assertTrue(review != null); // If the review is not null (was returned) then a review was successfully created
	}
	/**
	 * This case tests if a user with the Role "Student" can add a review to an answer
	 */
	@Test
	public void testAddReview4() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		UserLoginPage.userRole = "Student"; // Sets user's role to "Reviewer"
		Reviews reviewsTest = new Reviews(UserLoginPage.userNamePublic); // Creates Reviews class for test user
		Answers answersToQuestion = new Answers(UserLoginPage.userNamePublic);
		Question questionToAnswer = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		Answer answerToReview = answersToQuestion.addAnswer(questionToAnswer.getQuestionId(), "This is a test answer!", "Andrew");
		Review review = null;
		// Review created by "TestUser1" on test question to review
		review = reviewsTest.addReview(answerToReview.getAnswerId(), "This is a test review!", UserLoginPage.userNamePublic);
		assertTrue(review == null); // If the review is null (was not returned) then a review was not created
	}
	/**
	 * This case tests if a reviewer can remove all their reviews from a question.
	 */
	@Test
	public void testRemoveReviews1() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		UserLoginPage.userRole = "Reviewer"; // Sets user's role to "Reviewer"
		Reviews reviewsTest = new Reviews(UserLoginPage.userNamePublic); // Creates Reviews class for test user
		Question questionToReview = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		// Review created by "TestUser1" on test question to review
		Review review = reviewsTest.addReview(questionToReview.getQuestionId(), "This is a test review!", UserLoginPage.userNamePublic);
		// Removes reviews made by "TestUser1" on test question made by "Emily"
		String removedReviews = reviewsTest.removeReviews(questionToReview.getQuestionId(), UserLoginPage.userNamePublic);
		assertTrue(removedReviews.equals("Removed Reviews"));
	}
	/**
	 * This case tests if a different user from the original author can remove all the reviews from a question
	 */
	@Test
	public void testRemoveReviews2() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		UserLoginPage.userRole = "Reviewer"; // Sets user's role to "Reviewer"
		Reviews reviewsTest = new Reviews(UserLoginPage.userNamePublic); // Creates Reviews class for test user
		Question questionToReview = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		// Review created by "TestUser1" on test question to review
		Review review = reviewsTest.addReview(questionToReview.getQuestionId(), "This is a test review!", UserLoginPage.userNamePublic);
		// Removes reviews made by "TestUser1" on test question made by "Emily"
		String removedReviews = reviewsTest.removeReviews(questionToReview.getQuestionId(), "Emily");
		assertTrue(removedReviews.equals("Users did not match! Did not Remove Reviews!"));
	}
	/**
	 * This case tests if the original author of a review can remove it
	 */
	@Test
	public void testRemoveReview1() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		UserLoginPage.userRole = "Reviewer"; // Sets user's role to "Reviewer"
		Reviews reviewsTest = new Reviews(UserLoginPage.userNamePublic); // Creates Reviews class for test user
		Question questionToReview = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		// Review created by "TestUser1" on test question to review
		Review review = reviewsTest.addReview(questionToReview.getQuestionId(), "This is a test review!", UserLoginPage.userNamePublic);
		String removedReview = reviewsTest.removeReview(questionToReview.getQuestionId(), review, UserLoginPage.userNamePublic); // Removes review created by "TestUser1"
		assertTrue(removedReview.equals("Removed Review"));
	}
	/**
	 * This case tests if a different user from the original author can remove a review
	 */
	@Test
	public void testRemoveReview2() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		UserLoginPage.userRole = "Reviewer"; // Sets user's role to "Reviewer"
		Reviews reviewsTest = new Reviews(UserLoginPage.userNamePublic); // Creates Reviews class for test user
		Question questionToReview = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		// Review created by "TestUser1" on test question to review
		Review review = reviewsTest.addReview(questionToReview.getQuestionId(), "This is a test review!", UserLoginPage.userNamePublic);
		String removedReview = reviewsTest.removeReview(questionToReview.getQuestionId(), review, "Emily"); // Removes review created by "TestUser1"
		assertTrue(removedReview.equals("Users did not match! Did not Remove Review!"));
	}
	/**
	 * This case tests retrieving the reviews related to a question
	 */
	@Test
	public void testGetReviews() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		UserLoginPage.userRole = "Reviewer"; // Sets user's role to "Reviewer"
		Reviews reviewsTest = new Reviews(UserLoginPage.userNamePublic); // Creates Reviews class for test user
		Question questionToReview = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		// Review created by "TestUser1" on test question to review
		Review review = reviewsTest.addReview(questionToReview.getQuestionId(), "This is a test review!", UserLoginPage.userNamePublic);
		List<Review> reviewList = null;
		reviewList = reviewsTest.getReviews(questionToReview.getQuestionId());
		assertTrue(reviewList != null); // If the reviewList is not null (was returned) then a reviewList was successfully returned
	}
	/**
	 * This case tests retrieving all the reviews posted by a user
	 */
	@Test
	public void testGetReviewsByAuthor() {
		UserLoginPage.userNamePublic = "TestUser1"; // Sets user to "TestUser1"
		UserLoginPage.userRole = "Reviewer"; // Sets user's role to "Reviewer"
		Reviews reviewsTest = new Reviews(UserLoginPage.userNamePublic); // Creates Reviews class for test user
		Question questionToReview = new Question("This is a test question!", "Emily"); // A test question made by "Emily"
		// Review created by "TestUser1" on test question to review
		Review review = reviewsTest.addReview(questionToReview.getQuestionId(), "This is a test review!", UserLoginPage.userNamePublic);
		List<Review> reviewList = null;
		reviewList = reviewsTest.getReviewsByAuthor(UserLoginPage.userNamePublic);
		assertTrue(reviewList != null); // If the reviewList is not null (was returned) then a reviewList was successfully returned
	}

}
