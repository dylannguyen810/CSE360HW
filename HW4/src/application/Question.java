package application;

/**
 * Represents a question in the application, containing text, author information,
 * a unique identifier, and a solution status.
 */
public class Question {
    private String text;
    private String user;
    private int questionId;
    private String solution; // This stores the solution answer
    private boolean resolved;

    /**
     * Constructs a Question object with the given text and user.
     *
     * @param text The content of the question.
     * @param user The user who posted the question.
     */
    public Question(String text, String user) {
        this.text = text;
        this.user = user;
        Questions.IDCounter++;
        this.questionId = Questions.IDCounter;
        this.resolved = false;
    }

    /**
     * Gets the question ID.
     *
     * @return The question ID as a string.
     */
    public String getQuestionId() {
        return String.valueOf(questionId);
    }

    /**
     * Sets the question ID.
     *
     * @param questionId The new question ID.
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    /**
     * Gets the text of the question.
     *
     * @return The question text.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the author of the question.
     *
     * @return The username of the user who posted the question.
     */
    public String getUser() {
        return user;
    }

    /**
     * Gets the solution to the question, if one exists.
     *
     * @return The solution text, or null if no solution has been set.
     */
    public String getSolution() {
        return solution;
    }

    /**
     * Checks if the question has been resolved.
     *
     * @return True if resolved, otherwise false.
     */
    public boolean isResolved() {
        return resolved;
    }

    /**
     * Updates the question text if the requesting user is the original author.
     *
     * @param newQuestion The new text for the question.
     * @param user The user attempting to update the question.
     * @return A message indicating success or failure.
     */
    public String update(String newQuestion, String user) {
        if (user.equals(this.user)) {
            text = newQuestion;
            return "Updated Question";
        }
        return "Users did not match! Did not update question!";
    }

    /**
     * Sets the solution for the question and marks it as resolved.
     *
     * @param solution The solution text.
     */
    public void setSolution(String solution) {
        this.solution = solution;
        this.resolved = true;
    }

    /**
     * Removes the solution from the question and marks it as unresolved.
     */
    public void removeSolution() {
        this.solution = null;
        this.resolved = false;
    }

    /**
     * Returns a string representation of the question, including its resolution status.
     *
     * @return A formatted string containing the question text, author, and resolution status.
     */
    @Override
    public String toString() {
        if (resolved) {
            return text + " (Posted by: " + user + ") (Resolved!)";
        }
        return text + " (Posted by: " + user + ")";
    }
}