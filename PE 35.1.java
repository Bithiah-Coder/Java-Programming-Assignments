// BatchUpdatePerformance.java
// JavaFX program for Programming Exercise 35.1
// Compare batch vs non-batch inserts into MySQL database

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.Random;

public class BatchUpdatePerformance extends Application {

    private Connection connection;
    private TextField tfHost = new TextField("localhost");
    private TextField tfPort = new TextField("3306");
    private TextField tfDatabase = new TextField("test");
    private TextField tfUsername = new TextField("root");
    private PasswordField pfPassword = new PasswordField();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button btConnect = new Button("Connect to Database");
        Button btInsertNoBatch = new Button("Insert Without Batch");
        Button btInsertBatch = new Button("Insert With Batch");

        btInsertNoBatch.setDisable(true);
        btInsertBatch.setDisable(true);

        btConnect.setOnAction(e -> showConnectionDialog(btInsertNoBatch, btInsertBatch));
        btInsertNoBatch.setOnAction(e -> insertWithoutBatch());
        btInsertBatch.setOnAction(e -> insertWithBatch());

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setVgap(10);
        pane.setHgap(10);
        pane.add(btConnect, 0, 0);
        pane.add(btInsertNoBatch, 0, 1);
        pane.add(btInsertBatch, 0, 2);

        Scene scene = new Scene(pane, 350, 200);
        primaryStage.setTitle("Programming Exercise 35.1 - Batch Update Performance");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showConnectionDialog(Button btInsertNoBatch, Button btInsertBatch) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Database Connection");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        pfPassword.setPromptText("Enter password (if any)");

        grid.add(new Label("Host:"), 0, 0);
        grid.add(tfHost, 1, 0);
        grid.add(new Label("Port:"), 0, 1);
        grid.add(tfPort, 1, 1);
        grid.add(new Label("Database:"), 0, 2);
        grid.add(tfDatabase, 1, 2);
        grid.add(new Label("Username:"), 0, 3);
        grid.add(tfUsername, 1, 3);
        grid.add(new Label("Password:"), 0, 4);
        grid.add(pfPassword, 1, 4);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                connectToDatabase(btInsertNoBatch, btInsertBatch);
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void connectToDatabase(Button btInsertNoBatch, Button btInsertBatch) {
        String url = "jdbc:mysql://" + tfHost.getText() + ":" + tfPort.getText() + "/" + tfDatabase.getText() + "?useSSL=false";
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            createTableIfNotExists();
            showAlert(Alert.AlertType.INFORMATION, "Connected", "Successfully connected to database.");
            btInsertNoBatch.setDisable(false);
            btInsertBatch.setDisable(false);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Connection Failed", e.getMessage());
        }
    }

    private void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Temp (num1 DOUBLE, num2 DOUBLE, num3 DOUBLE)";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    private void insertWithoutBatch() {
        String sql = "INSERT INTO Temp (num1, num2, num3) VALUES (?, ?, ?)";
        long startTime = System.currentTimeMillis();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                pstmt.setDouble(1, random.nextDouble());
                pstmt.setDouble(2, random.nextDouble());
                pstmt.setDouble(3, random.nextDouble());
                pstmt.executeUpdate();
            }
            long endTime = System.currentTimeMillis();
            showAlert(Alert.AlertType.INFORMATION, "Insert Without Batch", "Time: " + (endTime - startTime) + " ms");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void insertWithBatch() {
        String sql = "INSERT INTO Temp (num1, num2, num3) VALUES (?, ?, ?)";
        long startTime = System.currentTimeMillis();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                pstmt.setDouble(1, random.nextDouble());
                pstmt.setDouble(2, random.nextDouble());
                pstmt.setDouble(3, random.nextDouble());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            long endTime = System.currentTimeMillis();
            showAlert(Alert.AlertType.INFORMATION, "Insert With Batch", "Time: " + (endTime - startTime) + " ms");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
