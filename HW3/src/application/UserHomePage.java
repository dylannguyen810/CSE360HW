package application;

import databasePart1.DatabaseHelper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This page displays a simple welcome message for the user.
 */

public class UserHomePage {
	
	private boolean loggedOut = false;

    public void show(Stage primaryStage, DatabaseHelper databaseHelper) {
    	VBox layout = new VBox();
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // Label to display Hello user
	    Label userLabel = new Label("Hello, " + UserLoginPage.userNamePublic);
	    userLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    
	    // Button to navigate to Questions Page
        Button questionsButton = new Button("View Questions");
        questionsButton.setOnAction(e -> {
            Questions questionsPage = new Questions();
            questionsPage.show(primaryStage, databaseHelper);
        });
        questionsButton.setVisible(false);
        
        // Logout button to close the GUI
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
        	primaryStage.close();
        	UserLoginPage loginPage = new UserLoginPage(databaseHelper);
        	loginPage.show(primaryStage);
        });
        
    	if (UserLoginPage.userRole.equals("Student")) {
    		questionsButton.setVisible(true);
    	}
    	
	    if (UserLoginPage.userRole.equals("Student") && loggedOut == false) {
	    	Questions questionsPage = new Questions();
	        questionsPage.show(primaryStage, databaseHelper);
        }
	    else {
	    	layout.getChildren().addAll(userLabel, questionsButton, logoutButton);
		    Scene userScene = new Scene(layout, 800, 400);

		    // Set the scene to primary stage
		    primaryStage.setScene(userScene);
		    primaryStage.setTitle("User Page");
	    }
    }
    
    public void logout() {
    	loggedOut = true;
    }
}