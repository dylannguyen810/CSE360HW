package application;

/**
 * The Review class represents a review written by a reviewer. It contains the review text and the name of the reviewer.
 * This class provides functionality to retrieve review details, update the review text, and convert the review to a string.
 */
public class Review {

    private String text;     // The text content of the review
    private String reviewer; // The name of the reviewer

    /**
     * Constructs a new Review object with the given text and reviewer name.
     *
     * @param text The content of the review.
     * @param reviewer The name of the person who wrote the review.
     */
    public Review(String text, String reviewer) {
        this.text = text;
        this.reviewer = reviewer;
    }

    /**
     * Gets the text content of the review.
     *
     * @return The review text.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the name of the reviewer who wrote the review.
     *
     * @return The name of the reviewer.
     */
    public String getUser() {
        return reviewer;
    }

    /**
     * Updates the text of the review if the user attempting to update the review matches the current logged-in user.
     * 
     * If the user is authorized (i.e., the user matches the logged-in user's name), the review text is updated.
     * Otherwise, an error code is returned indicating that the update failed.
     *
     * @param newText The new review text.
     * @param user The user attempting to update the review.
     * @return 0 if the update was successful, -1 if the user is not authorized to update the review.
     */
    public int update(String newText, String user) {
        if (user.equals(UserLoginPage.userNamePublic)) {
            this.text = newText;
            return 0; // Successful update
        }
        return -1; // Unauthorized user
    }

    /**
     * Returns a string representation of the review, consisting of the reviewer's name followed by the review text.
     *
     * @return A string representation of the review in the format "reviewer: text".
     */
    @Override
    public String toString() {
        return reviewer + ": " + text;
    }
}
