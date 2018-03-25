package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private Button calculateGradeButton, calculateNecessaryFinalGrade;
    TextField hw, vitamins, projects, midterms, fin, extraCredit, goldPoints, desiredGrade;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CS61B Grade Calculator");

        initCalcGradeButton();
        initCalcNecessaryButton();
        initTextFields();
        initLayout();

        AnchorPane layout = initLayout();

        primaryStage.setScene(new Scene(layout, 600, 550));
        primaryStage.show();
    }

    /**
     * Initializes the layout, adding the buttons and text entry fields.
     */
    private AnchorPane initLayout() {
        //initialize the input grid and set gaps + padding
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(0, 10, 0, 10));

        //add the categories to the grid
        Text title = new Text("Scores");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        inputGrid.add(title, 0, 0);

        Text hw = new Text("Homework/Labs: ");
        inputGrid.add(hw, 1, 1);
        inputGrid.add(this.hw, 2, 1);

        Text vitamins = new Text("Vitamins: ");
        inputGrid.add(vitamins, 1,  2);
        inputGrid.add(this.vitamins, 2, 2);

        Text projects = new Text("Projects: ");
        inputGrid.add(projects, 1, 3);
        inputGrid.add(this.projects, 2, 3);

        Text midterms = new Text("Midterms: ");
        inputGrid.add(midterms, 1, 4);
        inputGrid.add(this.midterms, 2, 4);

        Text fin = new Text("Final: ");
        inputGrid.add(fin, 1, 5);
        inputGrid.add(this.fin, 2, 5);

        Text extraCredit = new Text("Extra Credit: ");
        inputGrid.add(extraCredit, 1, 6);
        inputGrid.add(this.extraCredit, 2, 6);

        Text goldPoints = new Text("Gold Points: ");
        inputGrid.add(goldPoints, 1, 7);
        inputGrid.add(this.goldPoints, 2, 7);

        AnchorPane anchorpane = new AnchorPane();

        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(calculateGradeButton, calculateNecessaryFinalGrade, desiredGrade);

        anchorpane.getChildren().addAll(inputGrid, hb);
        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 5.0);
        AnchorPane.setTopAnchor(inputGrid, 10.0);

        return anchorpane;
    }

    /**
     * Initializes the TextFields for user input.
     */
    private void initTextFields() {
        hw = new TextField();
        hw.setPromptText("Enter HW/Labs score");

        vitamins = new TextField();
        vitamins.setPromptText("Enter Vitamins Score");

        projects = new TextField();
        projects.setPromptText("Enter Projects Score");

        midterms = new TextField();
        midterms.setPromptText("Enter Midterms Score");

        fin = new TextField();
        fin.setPromptText("Enter Final Exam Score");

        extraCredit = new TextField();
        extraCredit.setPromptText("Enter Extra Credit");

        goldPoints = new TextField();
        goldPoints.setPromptText("Enter Gold Points");

        desiredGrade = new TextField();
        desiredGrade.setPromptText("Enter Desired Grade");
    }

    /**
     * Initializes the button that calculates the user's grade based on input values.
     */
    private void initCalcGradeButton() {
        FullGradeCalculator fullGradeCalculator = new FullGradeCalculator();

        calculateGradeButton = new Button("Calculate Grade");
        calculateGradeButton.setOnAction(fullGradeCalculator);
        calculateGradeButton.setTooltip(new Tooltip("Calculate final grade if all values have been provided."));
    }

    /**
     * Initializes the button that calculates the required grade on the final exam to get the desired grade.
     */
    private void initCalcNecessaryButton() {
        NecessaryFinalGradeCalculator necessaryFinalGradeCalculator = new NecessaryFinalGradeCalculator();

        calculateNecessaryFinalGrade = new Button("Necessary Score on Final to Get ");
        calculateNecessaryFinalGrade.setOnAction(necessaryFinalGradeCalculator);
        calculateNecessaryFinalGrade.setTooltip(new Tooltip("Calculate the necessary score on the final " +
                "exam to get the input grade"));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
