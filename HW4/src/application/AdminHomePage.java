package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import databasePart1.DatabaseHelper;
import java.sql.*;

public class AdminHomePage {
    private DatabaseHelper dbHelper = new DatabaseHelper();

    /**
     * Displays the admin page in the provided primary stage.
     * @param primaryStage The primary stage where the scene will be displayed.
     */
    public void show(Stage primaryStage) {
        VBox layout = new VBox();
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
        
        // Label to display the welcome message for the admin
        Label adminLabel = new Label("Hello, Admin!");
        adminLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        // Button to load all users
        Button loadUsersButton = new Button("Load Users");
        loadUsersButton.setStyle("-fx-font-size: 14px;");
        loadUsersButton.setOnAction(e -> loadUsers(layout));

        layout.getChildren().addAll(adminLabel, loadUsersButton);
        
        Scene adminScene = new Scene(layout, 800, 400);
        
        // Set the scene to the primary stage
        primaryStage.setScene(adminScene);
        primaryStage.setTitle("Admin Page");
    }

    /**
     * Loads all users from the database and displays them as buttons.
     * @param layout The layout to add the user buttons to.
     */
    private void loadUsers(VBox layout) {
        try {
            // Clear previous user buttons
            layout.getChildren().clear();
            layout.getChildren().add(new Label("List of Users:"));
            
            // Connect to the database and get the user data
            dbHelper.connectToDatabase();
            String query = "SELECT userName FROM cse360users";
            ResultSet rs = dbHelper.getStatement().executeQuery(query);
            
            // Create a button for each user in the database
            while (rs.next()) {
                String userName = rs.getString("userName");
                Button userButton = new Button(userName);
                userButton.setStyle("-fx-font-size: 14px;");
                
                // Set the action for the button
                userButton.setOnAction(e -> showUserOptions(userName, layout));
                
                layout.getChildren().add(userButton);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Displays options to delete, change role, or reset password for a specific user.
     * @param userName The name of the user for whom the options will be displayed.
     * @param layout The layout to add the options to.
     */
    private void showUserOptions(String userName, VBox layout) {
        // Clear previous buttons and show options
        layout.getChildren().clear();
        layout.getChildren().add(new Label("Options for " + userName));
        
        // Button to delete user
        Button deleteButton = new Button("Delete User");
        deleteButton.setStyle("-fx-font-size: 14px;");
        deleteButton.setOnAction(e -> deleteUser(userName, layout));
        
        // Button to change user's role
        Button changeRoleButton = new Button("Change Role");
        changeRoleButton.setStyle("-fx-font-size: 14px;");
        changeRoleButton.setOnAction(e -> changeRole(userName, layout));
        
        // Button to reset user's password
        Button resetPasswordButton = new Button("Reset Password");
        resetPasswordButton.setStyle("-fx-font-size: 14px;");
        resetPasswordButton.setOnAction(e -> resetPassword(userName, layout));
        
        // Add the option buttons to the layout
        layout.getChildren().addAll(deleteButton, changeRoleButton, resetPasswordButton);
    }

    /**
     * Deletes a user from the database.
     * @param userName The name of the user to delete.
     * @param layout The layout to update after deletion.
     */
    private void deleteUser(String userName, VBox layout) {
        try {
            String query = "DELETE FROM cse360users WHERE userName = ?";
            PreparedStatement pstmt = dbHelper.getConnection().prepareStatement(query);
            pstmt.setString(1, userName);
            pstmt.executeUpdate();
            layout.getChildren().clear();
            layout.getChildren().add(new Label("User " + userName + " deleted successfully."));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Changes a user's role in the database.
     * @param userName The name of the user whose role will be changed.
     * @param layout The layout to display the role change form.
     */
    private void changeRole(String userName, VBox layout) {
        // Ask for the new role
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Change Role");
        dialog.setHeaderText("Enter the new role for " + userName);
        dialog.showAndWait().ifPresent(newRole -> {
            try {
                String query = "UPDATE cse360users SET role = ? WHERE userName = ?";
                PreparedStatement pstmt = dbHelper.getConnection().prepareStatement(query);
                pstmt.setString(1, newRole);
                pstmt.setString(2, userName);
                pstmt.executeUpdate();
                layout.getChildren().clear();
                layout.getChildren().add(new Label("User " + userName + "'s role changed to " + newRole));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Resets a user's password in the database.
     * @param userName The name of the user whose password will be reset.
     * @param layout The layout to display the password reset form.
     */
    private void resetPassword(String userName, VBox layout) {
        // Ask for the new password
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Reset Password");
        dialog.setHeaderText("Enter the new password for " + userName);
        dialog.showAndWait().ifPresent(newPassword -> {
            try {
                String query = "UPDATE cse360users SET password = ? WHERE userName = ?";
                PreparedStatement pstmt = dbHelper.getConnection().prepareStatement(query);
                pstmt.setString(1, newPassword);
                pstmt.setString(2, userName);
                pstmt.executeUpdate();
                layout.getChildren().clear();
                layout.getChildren().add(new Label("User " + userName + "'s password reset successfully."));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
