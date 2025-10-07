// ColorAdjusterWithScrollBars.java
// Liang Chapter 16 - Programming Exercise 16.17
// Use ScrollBars or Sliders to adjust RGB and opacity of displayed text.

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorAdjusterWithScrollBars extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the main text
        Text text = new Text("Show Colors");
        text.setFont(Font.font("Arial", 36));
        text.setFill(Color.BLUE);

        // Create four scrollbars for RGB + Opacity
        ScrollBar sbRed = new ScrollBar();
        ScrollBar sbGreen = new ScrollBar();
        ScrollBar sbBlue = new ScrollBar();
        ScrollBar sbOpacity = new ScrollBar();

        // Set scrollbar properties
        sbRed.setMin(0);
        sbRed.setMax(255);
        sbGreen.setMin(0);
        sbGreen.setMax(255);
        sbBlue.setMin(0);
        sbBlue.setMax(255);
        sbOpacity.setMin(0);
        sbOpacity.setMax(1);
        sbOpacity.setBlockIncrement(0.01);

        // Set initial values
        sbRed.setValue(0);
        sbGreen.setValue(0);
        sbBlue.setValue(255);
        sbOpacity.setValue(1);

        // Create labels for scrollbars
        Label lblRed = new Label("Red");
        Label lblGreen = new Label("Green");
        Label lblBlue = new Label("Blue");
        Label lblOpacity = new Label("Opacity");

        // Layout for scrollbars and labels
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);

        grid.add(lblRed, 0, 0);
        grid.add(sbRed, 1, 0);
        grid.add(lblGreen, 0, 1);
        grid.add(sbGreen, 1, 1);
        grid.add(lblBlue, 0, 2);
        grid.add(sbBlue, 1, 2);
        grid.add(lblOpacity, 0, 3);
        grid.add(sbOpacity, 1, 3);

        // Place the text and scrollbars in a BorderPane
        BorderPane pane = new BorderPane();
        pane.setCenter(text);
        BorderPane.setAlignment(text, Pos.CENTER);
        pane.setBottom(grid);

        // Update the text color when any scrollbar value changes
        sbRed.valueProperty().addListener(e -> updateColor(text, sbRed, sbGreen, sbBlue, sbOpacity));
        sbGreen.valueProperty().addListener(e -> updateColor(text, sbRed, sbGreen, sbBlue, sbOpacity));
        sbBlue.valueProperty().addListener(e -> updateColor(text, sbRed, sbGreen, sbBlue, sbOpacity));
        sbOpacity.valueProperty().addListener(e -> updateColor(text, sbRed, sbGreen, sbBlue, sbOpacity));

        // Create and display the scene
        Scene scene = new Scene(pane, 500, 300);
        primaryStage.setTitle("Exercise16_17 - Show Colors");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to update text color dynamically
    private void updateColor(Text text, ScrollBar r, ScrollBar g, ScrollBar b, ScrollBar o) {
        Color color = Color.rgb(
            (int) r.getValue(),
            (int) g.getValue(),
            (int) b.getValue(),
            o.getValue()
        );
        text.setFill(color);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
