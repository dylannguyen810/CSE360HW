package application;

public class Question {
	private String text;
	private String user;
	private int questionId;
    private String solution; // This stores the solution answer
	private boolean resolved;

	public Question(String text, String user) {
		this.text = text;
		this.user = user;
		Questions.IDCounter++;
		this.questionId = Questions.IDCounter;
		this.resolved = false;

	}

	public String getQuestionId() {
		return String.valueOf(questionId);
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getText() {
		return text;
	}

	public String getUser() {
		return user;
	}
	
	public String getSolution() { 
		return solution; 
	}
	
    public boolean isResolved() { 
    	return resolved; 
    }

	public String update(String newQuestion, String user) {
		if (user.equals(this.user)) {
			text = newQuestion;
			return "Updated Question";
		}
		return "Users did not match! Did not update question!";
	}
	
	public void setSolution(String solution) {
        this.solution = solution;
        this.resolved = true;
    }
	
	public void removeSolution() {
        this.solution = null;
        this.resolved = false;
    }

	@Override
	public String toString() {
		if (resolved) {
			return text + " (Posted by: " + user + ") (Resolved!)";
		}
		return text + " (Posted by: " + user + ")";
	}
}
