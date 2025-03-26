package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import databasePart1.DatabaseHelper;

public class Questions {
	private final List<Question> questions; // Questions posted by users
	private final Answers answers; // Instance of Answers class, this is the answers to the questions
	private Question questionSelected;
	public static int IDCounter = 0;
	private DatabaseHelper databaseHelper;

	public Questions() {
		this.questions = new ArrayList<>();
		this.answers = new Answers(UserLoginPage.userNamePublic);
		// Sample questions
		Question q1 = new Question("What is Java?", "Alice");
		questions.add(q1);
		questions.add(new Question("How does garbage collection work?", "Charlie"));
	}

	public String removeQuestion(Question question, String user) {
		if (question != null && question.getUser().equals(user)) {
			questions.remove(question);
			return "Question Removed";
		}
		return "Users did not match! Did not Remove question!";
	}

	public void show(Stage primaryStage, DatabaseHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
		VBox layout = new VBox(10);
		layout.setStyle("-fx-alignment: center; -fx-padding: 5;");

		HBox questionActions = new HBox(10);
		questionActions.setStyle("-fx-alignment: center; -fx-padding: 5;");

		HBox answerActions = new HBox(10);
		answerActions.setStyle("-fx-alignment: center; -fx-padding: 5;");

		Label titleLabel = new Label("Questions");
		titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

		ListView<Question> questionListView = new ListView<>();
		questionListView.getItems().addAll(questions);

		Label answersLabel = new Label("Answers:");
		answersLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
		ListView<Answer> answersListView = new ListView<>();

		Button postQuestionButton = new Button("Post a Question");
		Button answerQuestionButton = new Button("Answer Question");
		answerQuestionButton.setDisable(true); // Disabled until a question is selected

		Button removeQuestionButton = new Button("Remove Question");
		removeQuestionButton.setDisable(true); // Disabled until a question is selected

		Button removeAnswerButton = new Button("Remove Answer");
		removeAnswerButton.setDisable(true); // Disabled until an answer is selected

		Button updateQuestionButton = new Button("Update Question");
		updateQuestionButton.setDisable(true); // Disabled until a question is selected

		Button updateAnswerButton = new Button("Update Answer");
		updateAnswerButton.setDisable(true); // Disabled until an answer is selected

		Button markAsSolutionButton = new Button("Mark as Solution");
		markAsSolutionButton.setDisable(true); // Disabled until a question and answer are selected

		// Event listener: When a question is selected, show its answers
		questionListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			markAsSolutionButton.setDisable(true); // Disable the marks as solution button
			if (newVal != null) {
                questionSelected = newVal;
                answersListView.getItems().setAll(answers.getAnswers(newVal.getQuestionId()));
                answerQuestionButton.setDisable(false); // Enable the answer button when a question is selected
                removeQuestionButton.setDisable(!newVal.getUser().equals(UserLoginPage.userNamePublic)); //Enabled remove question button
                updateQuestionButton.setDisable(!newVal.getUser().equals(UserLoginPage.userNamePublic)); // enabled update question button
            }
            else {
                questionSelected = null;
                answerQuestionButton.setDisable(true);
                removeQuestionButton.setDisable(true);
                updateQuestionButton.setDisable(true);
            }
		});

		// Event listener: When an answer is selected, enable the remove answer button
		answersListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != null) {
				removeAnswerButton.setDisable(newVal == null || !newVal.getUser().equals(UserLoginPage.userNamePublic)); // Enable remove answer button
				updateAnswerButton.setDisable(newVal == null || !newVal.getUser().equals(UserLoginPage.userNamePublic)); // Enabled update answer button
				if (questionSelected != null && questionSelected.getUser().equals(UserLoginPage.userNamePublic)) {
					markAsSolutionButton.setDisable(false); // Enable if an answer is selected
				}
			}
		});

		// Button action for marking an answer as the solution
		markAsSolutionButton.setOnAction(e -> {
			Question selectedQuestion = questionListView.getSelectionModel().getSelectedItem();
			Answer selectedAnswer = answersListView.getSelectionModel().getSelectedItem();
			if (selectedQuestion != null && selectedAnswer != null
					&& selectedQuestion.getUser().equals(UserLoginPage.userNamePublic)) {
				// Mark the selected answer as the solution
				selectedQuestion.setSolution(selectedAnswer.getText());
				for (Answer answer : answersListView.getItems()) {
					answer.setSolution(false); // Call the setSolution method on each Answer to false to update new
												// Solution
				}
				selectedAnswer.setSolution(true); // Update new solution
				questionListView.refresh(); // Refresh to show the "Resolved" label
				answersListView.refresh(); // Refresh to show the "Solution" label
			}
		});

		// Button action for posting a new question
		postQuestionButton.setOnAction(e -> {
			TextInputDialog questionDialog = new TextInputDialog();
			questionDialog.setTitle("Post a Question");
			questionDialog.setHeaderText("Enter your question:");
			questionDialog.setContentText("Question:");

			questionDialog.showAndWait().ifPresent(questionText -> {
				if (!questionText.trim().isEmpty()) {
					Question newQuestion = new Question(questionText, UserLoginPage.userNamePublic);
					questions.add(newQuestion);
					questionListView.getItems().add(newQuestion);
				}
			});
		});

		// Button action for answering a selected question
		answerQuestionButton.setOnAction(e -> {
			Question selectedQuestion = questionListView.getSelectionModel().getSelectedItem();
			if (selectedQuestion == null)
				return;

			TextInputDialog answerDialog = new TextInputDialog();
			answerDialog.setTitle("Post an Answer");
			answerDialog.setHeaderText("Enter your answer:");
			answerDialog.setContentText("Answer:");

			answerDialog.showAndWait().ifPresent(answerText -> {
				if (!answerText.trim().isEmpty()) {
					answers.addAnswer(selectedQuestion.getQuestionId(), answerText, UserLoginPage.userNamePublic);
					answersListView.getItems().setAll(answers.getAnswers(selectedQuestion.getQuestionId())); // Refresh
					// answers
				}
			});
		});

		// Button action for removing a selected question
		removeQuestionButton.setOnAction(e -> {
			Question selectedQuestion = questionListView.getSelectionModel().getSelectedItem();
			if (selectedQuestion != null && selectedQuestion.getUser().equals(UserLoginPage.userNamePublic)) {
				questions.remove(selectedQuestion);
				questionListView.getItems().remove(selectedQuestion);
				answers.removeAnswers(selectedQuestion.getQuestionId(), UserLoginPage.userNamePublic); // Remove associated answers
			}
		});

		// Button action for updating a selected question
		updateQuestionButton.setOnAction(e -> {
			Question selectedQuestion = questionListView.getSelectionModel().getSelectedItem();
			if (selectedQuestion != null && selectedQuestion.getUser().equals(UserLoginPage.userNamePublic)) {
				TextInputDialog questionDialog = new TextInputDialog();
				questionDialog.setTitle("Update Question");
				questionDialog.setHeaderText("Enter your updated question:");
				questionDialog.setContentText("Question:");

				questionDialog.showAndWait().ifPresent(questionText -> {
					if (!questionText.trim().isEmpty()) {
						selectedQuestion.update(questionText, UserLoginPage.userNamePublic);
						questionListView.refresh();
					}
				});
			}
		});

		// Button action for removing a selected answer
		removeAnswerButton.setOnAction(e -> {
			Question selectedQuestion = questionListView.getSelectionModel().getSelectedItem();
			Answer selectedAnswer = answersListView.getSelectionModel().getSelectedItem();
			if (selectedAnswer != null && selectedAnswer.getUser().equals(UserLoginPage.userNamePublic)) {
				answers.removeAnswer(selectedQuestion.getQuestionId(), selectedAnswer, UserLoginPage.userNamePublic);
				answersListView.getItems().remove(selectedAnswer);
			}
		});

		// Button action for updating a selected answer
		updateAnswerButton.setOnAction(e -> {
			Answer selectedAnswer = answersListView.getSelectionModel().getSelectedItem();
			if (selectedAnswer != null && selectedAnswer.getUser().equals(UserLoginPage.userNamePublic)) {
				TextInputDialog questionDialog = new TextInputDialog();
				questionDialog.setTitle("Update Answer");
				questionDialog.setHeaderText("Enter your updated answer:");
				questionDialog.setContentText("Answer:");

				questionDialog.showAndWait().ifPresent(answerText -> {
					if (!answerText.trim().isEmpty()) {
						selectedAnswer.update(answerText);
						answersListView.refresh();
					}
				});
			}
		});

		Button backButton = new Button("Back to Home"); // Button to go back to the home page
		backButton.setOnAction(e -> {
			UserHomePage homePage = new UserHomePage();
			homePage.logout();
			homePage.show(primaryStage, databaseHelper);
		});

		questionActions.getChildren().addAll(removeQuestionButton, updateQuestionButton, markAsSolutionButton);
		answerActions.getChildren().addAll(removeAnswerButton, updateAnswerButton);

		layout.getChildren().addAll(titleLabel, questionListView, postQuestionButton, questionActions, answersLabel,
				answersListView, answerQuestionButton, answerActions, backButton);
		Scene questionScene = new Scene(layout, 800, 500);

		primaryStage.setScene(questionScene);
		primaryStage.setTitle("Questions Page");
	}
}
