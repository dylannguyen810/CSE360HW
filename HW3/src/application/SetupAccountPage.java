package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

import databasePart1.*;

/**
 * SetupAccountPage class handles the account setup process for new users. Users
 * provide their userName, password, and a valid invitation code to register.
 */
public class SetupAccountPage {

	private final DatabaseHelper databaseHelper;

	// DatabaseHelper to handle database operations.
	public SetupAccountPage(DatabaseHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	/**
	 * Displays the Setup Account page in the provided stage.
	 * 
	 * @param primaryStage The primary stage where the scene will be displayed.
	 */
	public void show(Stage primaryStage) {
		// Input fields for userName, password, and invitation code
		TextField userNameField = new TextField();
		userNameField.setPromptText("Enter userName");
		userNameField.setMaxWidth(250);

		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Enter Password");
		passwordField.setMaxWidth(250);

		TextField inviteCodeField = new TextField();
		inviteCodeField.setPromptText("Enter InvitationCode");
		inviteCodeField.setMaxWidth(250);

		// Label to display error messages for invalid input or registration issues
		Label errorLabel = new Label();
		errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");

		Button setupButton = new Button("Setup");

		setupButton.setOnAction(a -> {
			// Retrieve user input
			String userName = userNameField.getText();
			String password = passwordField.getText();
			String code = inviteCodeField.getText();

			String userResult = UserNameRecognizer.checkForValidUserName(userName); // Check if the user name is valid
			String passResult = PasswordEvaluator.evaluatePassword(password);  // Check if the password is valid

			System.out.println(userResult);

			if (userResult == "") {
				if (passResult == "") {
					try {
						// Check if the user already exists
						if (!databaseHelper.doesUserExist(userName)) {

							// Validate the invitation code
							String role = databaseHelper.validateInvitationCode(code);
							if (role != null) {

								// Create a new user and register them in the database
								User user = new User(userName, password, "user");
								databaseHelper.register(user, code);

								// Navigate to the Welcome Login Page
								new WelcomeLoginPage(databaseHelper).show(primaryStage, user);
								UserLoginPage.userNamePublic = userName; // sets name of user for welcome page
				                UserLoginPage.userRole = role; // sets role of user for welcome page
				                
							} else {
								errorLabel.setText("Please enter a valid invitation code");
							}
						} else {
							errorLabel.setText("This useruserName is taken!!.. Please use another to setup an account");
						}

					} catch (SQLException e) {
						System.err.println("Database error: " + e.getMessage());
						e.printStackTrace();
					}
				} else {
					errorLabel.setText(passResult);
				}
			} else {
				errorLabel.setText(userResult);
			}
		});

		VBox layout = new VBox(10);
		layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
		layout.getChildren().addAll(userNameField, passwordField, inviteCodeField, setupButton, errorLabel);

		primaryStage.setScene(new Scene(layout, 800, 400));
		primaryStage.setTitle("Account Setup");
		primaryStage.show();
	}
}
