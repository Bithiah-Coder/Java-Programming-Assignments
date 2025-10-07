// ChangeCircleColorOnMouse.java
// JavaFX program that changes the color of a circle when the mouse is pressed and released.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ChangeCircleColorOnMouse extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a pane to hold the circle
        Pane pane = new Pane();

        // Create a circle centered in the pane
        Circle circle = new Circle(200, 150, 50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        // Add the circle to the pane
        pane.getChildren().add(circle);

        // Mouse press event: change circle to black
        circle.setOnMousePressed(e -> circle.setFill(Color.BLACK));

        // Mouse release event: change circle back to white
        circle.setOnMouseReleased(e -> circle.setFill(Color.WHITE));

        // Create a scene and add the pane
        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setTitle("Change Circle Color on Mouse Press");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
