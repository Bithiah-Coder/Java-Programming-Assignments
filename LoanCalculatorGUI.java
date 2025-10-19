// LoanCalculatorGUI.java
// JavaFX GUI for loan calculation using Loan.java logic

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoanCalculatorGUI extends Application {

    private TextField tfLoanAmount = new TextField();
    private TextField tfInterestRate = new TextField();
    private TextField tfNumYears = new TextField();
    private TextField tfMonthlyPayment = new TextField();
    private TextField tfTotalPayment = new TextField();
    private Button btnCompute = new Button("Compute Loan Payment");

    @Override
    public void start(Stage primaryStage) {

        GridPane root = new GridPane();
        root.setPadding(new Insets(12));
        root.setHgap(10);
        root.setVgap(10);

        // Input fields
        root.add(new Label("Loan Amount:"), 0, 0);
        root.add(tfLoanAmount, 1, 0);

        root.add(new Label("Annual Interest Rate (%):"), 0, 1);
        root.add(tfInterestRate, 1, 1);

        root.add(new Label("Number of Years:"), 0, 2);
        root.add(tfNumYears, 1, 2);

        // Output fields
        root.add(new Label("Monthly Payment:"), 0, 3);
        tfMonthlyPayment.setEditable(false);
        root.add(tfMonthlyPayment, 1, 3);

        root.add(new Label("Total Payment:"), 0, 4);
        tfTotalPayment.setEditable(false);
        root.add(tfTotalPayment, 1, 4);

        // Compute button
        root.add(btnCompute, 1, 5);

        // Event handler
        btnCompute.setOnAction(e -> computeLoan());

        Scene scene = new Scene(root, 400, 250);
        primaryStage.setTitle("Loan Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void computeLoan() {
        try {
            double amount = Double.parseDouble(tfLoanAmount.getText());
            double annualRate = Double.parseDouble(tfInterestRate.getText());
            int years = Integer.parseInt(tfNumYears.getText());

            Loan loan = new Loan(amount, annualRate, years);

            tfMonthlyPayment.setText(String.format("%.2f", loan.getMonthlyPayment()));
            tfTotalPayment.setText(String.format("%.2f", loan.getTotalPayment()));

        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid numbers.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// Loan.java (from Listing 10.2, included for completeness)
class Loan {
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;

    public Loan(double loanAmount, double annualInterestRate, int numberOfYears) {
        this.loanAmount = loanAmount;
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
    }

    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        int months = numberOfYears * 12;
        return loanAmount * monthlyInterestRate /
                (1 - Math.pow(1 + monthlyInterestRate, -months));
    }

    public double getTotalPayment() {
        return getMonthlyPayment() * numberOfYears * 12;
    }
}
