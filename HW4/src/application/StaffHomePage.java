package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * JavaFX GUI for the Staff user.
 */
public class StaffHomePage extends Application {

    private StaffController controller = new StaffController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Staff Dashboard");

        Label title = new Label("Staff Dashboard");
        ListView<String> contentList = new ListView<>();
        contentList.getItems().addAll("Question 1", "Answer 2", "Feedback 3");

        Button flagButton = new Button("Flag Selected");
        Button deleteButton = new Button("Delete Selected");

        flagButton.setOnAction(e -> {
            String selected = contentList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                controller.flagContent(selected);
            }
        });

        deleteButton.setOnAction(e -> {
            String selected = contentList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                controller.deleteContent(selected);
            }
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20;");
        layout.getChildren().addAll(title, contentList, flagButton, deleteButton);

        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}