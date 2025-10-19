import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    private Connection connection;
    private TextField tfId = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfFirstName = new TextField();
    private TextField tfMI = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfState = new TextField();
    private TextField tfTelephone = new TextField();
    private TextField tfEmail = new TextField();
    private Label lblStatus = new Label();

    @Override
    public void start(Stage primaryStage) {
        // Initialize database
        connectToDatabase();
        createTableIfNeeded();

        // UI Layout
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(5);
        pane.setVgap(5);

        pane.add(new Label("ID:"), 0, 0);
        pane.add(tfId, 1, 0);

        pane.add(new Label("Last Name:"), 0, 1);
        pane.add(tfLastName, 1, 1);

        pane.add(new Label("First Name:"), 0, 2);
        pane.add(tfFirstName, 1, 2);

        pane.add(new Label("MI:"), 0, 3);
        pane.add(tfMI, 1, 3);

        pane.add(new Label("Address:"), 0, 4);
        pane.add(tfAddress, 1, 4);

        pane.add(new Label("City:"), 0, 5);
        pane.add(tfCity, 1, 5);

        pane.add(new Label("State:"), 0, 6);
        pane.add(tfState, 1, 6);

        pane.add(new Label("Telephone:"), 0, 7);
        pane.add(tfTelephone, 1, 7);

        pane.add(new Label("Email:"), 0, 8);
        pane.add(tfEmail, 1, 8);

        Button btnView = new Button("View");
        Button btnInsert = new Button("Insert");
        Button btnUpdate = new Button("Update");
        pane.add(btnView, 0, 9);
        pane.add(btnInsert, 1, 9);
        pane.add(btnUpdate, 2, 9);
        pane.add(lblStatus, 1, 10, 2, 1);

        // Button Actions
        btnView.setOnAction(e -> viewRecord());
        btnInsert.setOnAction(e -> insertRecord());
        btnUpdate.setOnAction(e -> updateRecord());

        // Scene and Stage Setup
        Scene scene = new Scene(pane, 500, 400);
        primaryStage.setTitle("Staff Database");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void connectToDatabase() {
        try {
            // SQLite local database file
            connection = DriverManager.getConnection("jdbc:sqlite:staff.db");
            lblStatus.setText("Connected to database");
        } catch (SQLException e) {
            lblStatus.setText("Failed to connect to database: " + e.getMessage());
        }
    }

    private void createTableIfNeeded() {
        String sql = "CREATE TABLE IF NOT EXISTS Staff (" +
                "id CHAR(9) PRIMARY KEY NOT NULL, " +
                "lastName VARCHAR(15), " +
                "firstName VARCHAR(15), " +
                "mi CHAR(1), " +
                "address VARCHAR(20), " +
                "city VARCHAR(20), " +
                "state CHAR(2), " +
                "telephone CHAR(10), " +
                "email VARCHAR(40))";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            lblStatus.setText("Error creating table: " + e.getMessage());
        }
    }

    private void viewRecord() {
        String sql = "SELECT * FROM Staff WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tfId.getText().trim());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tfLastName.setText(rs.getString("lastName"));
                tfFirstName.setText(rs.getString("firstName"));
                tfMI.setText(rs.getString("mi"));
                tfAddress.setText(rs.getString("address"));
                tfCity.setText(rs.getString("city"));
                tfState.setText(rs.getString("state"));
                tfTelephone.setText(rs.getString("telephone"));
                tfEmail.setText(rs.getString("email"));
                lblStatus.setText("Record found");
            } else {
                lblStatus.setText("No record with that ID");
            }
        } catch (SQLException e) {
            lblStatus.setText("Error viewing: " + e.getMessage());
        }
    }

    private void insertRecord() {
        String sql = "INSERT INTO Staff VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tfId.getText().trim());
            pstmt.setString(2, tfLastName.getText().trim());
            pstmt.setString(3, tfFirstName.getText().trim());
            pstmt.setString(4, tfMI.getText().trim());
            pstmt.setString(5, tfAddress.getText().trim());
            pstmt.setString(6, tfCity.getText().trim());
            pstmt.setString(7, tfState.getText().trim());
            pstmt.setString(8, tfTelephone.getText().trim());
            pstmt.setString(9, tfEmail.getText().trim());
            pstmt.executeUpdate();
            lblStatus.setText("Insert successful");
        } catch (SQLException e) {
            lblStatus.setText("Error inserting: " + e.getMessage());
        }
    }

    private void updateRecord() {
        String sql = "UPDATE Staff SET lastName=?, firstName=?, mi=?, address=?, city=?, state=?, telephone=?, email=? WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tfLastName.getText().trim());
            pstmt.setString(2, tfFirstName.getText().trim());
            pstmt.setString(3, tfMI.getText().trim());
            pstmt.setString(4, tfAddress.getText().trim());
            pstmt.setString(5, tfCity.getText().trim());
            pstmt.setString(6, tfState.getText().trim());
            pstmt.setString(7, tfTelephone.getText().trim());
            pstmt.setString(8, tfEmail.getText().trim());
            pstmt.setString(9, tfId.getText().trim());
            pstmt.executeUpdate();
            lblStatus.setText("Update successful");
        } catch (SQLException e) {
            lblStatus.setText("Error updating: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
