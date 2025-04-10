package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import databasePart1.DatabaseHelper;

/**
 * The Reviews class manages reviews for different questions. It allows adding, removing, and retrieving reviews.
 * The class maintains mappings for reviews by question ID and by the author of the review.
 */
public class Reviews {

    private final Map<String, List<Review>> reviewsMap; // Stores reviews indexed by question IDs
    private final Map<String, List<Review>> reviewsMapByAuthor; // Stores reviews indexed by reviewer usernames
    private DatabaseHelper databaseHelper;

    /**
     * Constructs a new Reviews object for a given user. Initializes the review maps and adds some sample reviews.
     *
     * @param user The username of the current logged-in user.
     */
    public Reviews(String user) {
        reviewsMap = new HashMap<>();
        reviewsMapByAuthor = new HashMap<>();

        // Sample reviews added for the user during initialization
        Review reviewToAdd1 = new Review("This is a manually added review!", UserLoginPage.userNamePublic);
        Review reviewToAdd2 = new Review("This is another review!", UserLoginPage.userNamePublic);
        reviewsMapByAuthor.computeIfAbsent(UserLoginPage.userNamePublic, k -> new ArrayList<>()).add(reviewToAdd1);
        reviewsMapByAuthor.computeIfAbsent(UserLoginPage.userNamePublic, k -> new ArrayList<>()).add(reviewToAdd2);
    }

    /**
     * Adds a new review for a given question if the current user has the "Reviewer" role.
     *
     * @param questionID The ID of the question being reviewed.
     * @param review The content of the review.
     * @param user The username of the person adding the review.
     * @return The new Review object if added successfully, otherwise null if the user is not authorized.
     */
    public Review addReview(String questionID, String review, String user) {
        if (UserLoginPage.userRole.equals("Reviewer")) {
            Review newReview = new Review(review, user);
            reviewsMap.computeIfAbsent(questionID, k -> new ArrayList<>()).add(newReview); // Add the review to the question
            reviewsMapByAuthor.computeIfAbsent(user, k -> new ArrayList<>()).add(newReview); // Add the review to the author
            return newReview;
        }
        return null; // Return null if the user does not have the "Reviewer" role
    }

    /**
     * Removes all reviews for a given question if the user is the logged-in user.
     *
     * @param question The ID of the question whose reviews are to be removed.
     * @param user The username of the person attempting to remove the reviews.
     * @return A message indicating the result of the operation (success or failure).
     */
    public String removeReviews(String question, String user) {
        if (UserLoginPage.userNamePublic.equals(user)) {
            if (reviewsMap.containsKey(question)) {
                reviewsMap.get(question).clear(); // Clear all reviews for the given question
                return "Removed Reviews";
            }
            return "User Match but Could not Find Reviews";
        }
        return "Users did not match! Did not Remove Reviews!";
    }

    /**
     * Removes a specific review from a question if the review belongs to the given user.
     *
     * @param questionID The ID of the question from which the review is to be removed.
     * @param reviewToRemove The review to be removed.
     * @param user The username of the person attempting to remove the review.
     * @return A message indicating the result of the operation (success or failure).
     */
    public String removeReview(String questionID, Review reviewToRemove, String user) {
        if (reviewToRemove.getUser().equals(user)) {
            List<Review> reviewList = reviewsMap.get(questionID);
            List<Review> reviewListByAuthor = reviewsMap.get(user);
            boolean removed = false;

            if (reviewListByAuthor != null) {
                reviewListByAuthor.remove(reviewToRemove);
                removed = true;
            }
            if (reviewList != null) {
                reviewList.remove(reviewToRemove); // Remove the review from the list
                removed = true;
            }
            if (removed) {
                return "Removed Review";
            }
            return "User Match but Could not Find Review";
        }
        return "Users did not match! Did not Remove Review!";
    }

    /**
     * Retrieves all reviews for a given question.
     *
     * @param questionID The ID of the question whose reviews are to be retrieved.
     * @return A list of reviews for the specified question.
     */
    public List<Review> getReviews(String questionID) {
        return reviewsMap.getOrDefault(questionID, new ArrayList<>());
    }

    /**
     * Retrieves all reviews by a specific author.
     *
     * @param user The username of the author whose reviews are to be retrieved.
     * @return A list of reviews written by the specified author.
     */
    public List<Review> getReviewsByAuthor(String user) {
        return reviewsMapByAuthor.getOrDefault(user, new ArrayList<>());
    }

    /**
     * Displays the user's reviews in a new window using a JavaFX stage.
     *
     * @param primaryStage The primary stage to display the scene.
     * @param databaseHelper The database helper used for database interactions (not utilized in this method).
     */
    public void show(Stage primaryStage, DatabaseHelper databaseHelper) {

        this.databaseHelper = databaseHelper;

        primaryStage = new Stage();
        primaryStage.setTitle("My Reviews");

        Label titleLabel = new Label("My Reviews");
        ListView<Review> reviewsListView = new ListView<>();

        // Fetch and display only the reviews by the logged-in reviewer
        List<Review> userReviews = this.getReviewsByAuthor(UserLoginPage.userNamePublic);
        reviewsListView.getItems().addAll(userReviews);

        // Layout setup
        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, reviewsListView);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
