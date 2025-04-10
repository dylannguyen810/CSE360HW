package application;

/**
 * Represents an answer to a question in the application.
 */
public class Answer {
    private String text;
    private String user;
    private int answerId;
    private boolean solution;

    /**
     * Constructs an Answer with the given text and user.
     *
     * @param text The content of the answer.
     * @param user The user who posted the answer.
     */
    public Answer(String text, String user) {
        this.text = text;
        this.user = user;
        Answers.IDCounter++;
        this.answerId = Answers.IDCounter;
    }

    /**
     * Retrieves the text of the answer.
     *
     * @return The answer text.
     */
    public String getText() {
        return text;
    }

    /**
     * Retrieves the user who posted the answer.
     *
     * @return The username of the answer's author.
     */
    public String getUser() {
        return user;
    }

    /**
     * Updates the text of the answer if the current user matches the author.
     *
     * @param newQuestion The new text to update the answer with.
     * @return A message indicating success or failure of the update.
     */
    public String update(String newQuestion) {
        if (user.equals(UserLoginPage.userNamePublic)) {
            text = newQuestion;
            return "Updated Answer!";
        }
        return "Users did not match! Did not update Answer!";
    }

    /**
     * Marks this answer as a solution to the question.
     *
     * @param solution {@code true} if the answer is a solution, {@code false} otherwise.
     */
    public void setSolution(boolean solution) {
        this.solution = solution;
    }
    
    /**
     * Checks if this answer is marked as a solution.
     *
     * @return {@code true} if the answer is a solution, {@code false} otherwise.
     */
    public boolean getSolution() {
        return solution;
    }

    /**
     * Retrieves the unique identifier of the answer.
     *
     * @return The answer ID as a string.
     */
    public String getAnswerId() {
        return String.valueOf(answerId);
    }

    /**
     * Returns a string representation of the answer, indicating the author and whether it is a solution.
     *
     * @return A formatted string representing the answer.
     */
    @Override
    public String toString() {
        if (solution) {
            return text + " (Posted by: " + user + ") (Solution!)";
        }
        return text + " (Posted by: " + user + ")";
    }
}
