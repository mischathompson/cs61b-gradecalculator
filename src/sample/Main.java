package sample;

import org.junit.Test;
import static org.junit.Assert.*;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private Button calculateGradeButton, calculateNecessaryFinalGrade;
    private FullGradeCalculator fullGradeCalculator;
    private NecessaryFinalGradeCalculator necessaryFinalGradeCalculator;
    TextField hw, vitamins, projects, midterms, fin, extraCredit, goldPoints, desiredGrade;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CS61B Grade Calculator");

        initCalcGradeButton();
        initCalcNecessaryButton();
        initTextFields();
        initLayout();

        AnchorPane layout = initLayout();

        primaryStage.setScene(new Scene(layout, 600, 400));
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

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setPadding(new Insets(0, 10, 0, 10));

        buttonGrid.add(calculateGradeButton, 0, 0);
        buttonGrid.add(calculateNecessaryFinalGrade, 0, 1);
        buttonGrid.add(desiredGrade, 1, 1);

        anchorpane.getChildren().addAll(inputGrid, buttonGrid);
        AnchorPane.setBottomAnchor(buttonGrid, 8.0);
        AnchorPane.setLeftAnchor(buttonGrid, 5.0);
        AnchorPane.setTopAnchor(inputGrid, 10.0);

        return anchorpane;
    }

    /**
     * Initializes the TextFields for user input.
     */
    private void initTextFields() {
        hw = new TextField();
        hw.setPromptText("Enter HW/Labs score");
        hw.setOnMouseClicked(e -> fullGradeCalculator.updateHW(toInt(hw.getText(), 0)));
        //hw.setOnMouseExited(e -> fullGradeCalculator.updateHW(toInt(hw.getText(), 0)));

        vitamins = new TextField();
        vitamins.setPromptText("Enter Vitamins Score");
        vitamins.setOnAction(e -> fullGradeCalculator.updateVitamins(toInt(vitamins.getText(), 0)));
        //vitamins.setOnMouseExited(e -> fullGradeCalculator.updateVitamins(toInt(vitamins.getText(), 0)));

        projects = new TextField();
        projects.setPromptText("Enter Projects Score");
        projects.setOnAction(e -> fullGradeCalculator.updateProjects(toInt(projects.getText(), 0)));
        //projects.setOnMouseExited(e -> fullGradeCalculator.updateProjects(toInt(projects.getText(), 0)));

        midterms = new TextField();
        midterms.setPromptText("Enter Midterms Score");
        midterms.setOnAction(e -> fullGradeCalculator.updateMidterms(toInt(midterms.getText(), 0)));
        //midterms.setOnMouseExited(e -> fullGradeCalculator.updateMidterms(toInt(midterms.getText(), 0)));

        fin = new TextField();
        fin.setPromptText("Enter Final Exam Score");
        fin.setOnAction(e -> fullGradeCalculator.updateFinal(toInt(fin.getText(), 0)));
        //fin.setOnMouseExited(e -> fullGradeCalculator.updateMidterms(toInt(fin.getText(), 0)));

        extraCredit = new TextField();
        extraCredit.setPromptText("Enter Extra Credit");
        extraCredit.setOnAction(e -> fullGradeCalculator.updateExtraCredit(toInt(extraCredit.getText(), 0)));
        //extraCredit.setOnMouseExited(e -> fullGradeCalculator.updateExtraCredit(toInt(extraCredit.getText(), 0)));

        goldPoints = new TextField();
        goldPoints.setPromptText("Enter Gold Points");
        goldPoints.setOnAction(e -> fullGradeCalculator.updateGoldPoints(toInt(goldPoints.getText(), 0)));
        //goldPoints.setOnMouseExited(e -> fullGradeCalculator.updateGoldPoints(toInt(goldPoints.getText(), 0)));

        desiredGrade = new TextField();
        desiredGrade.setPromptText("Enter Desired Grade");
    }

    /**
     * Initializes the button that calculates the user's grade based on input values.
     */
    private void initCalcGradeButton() {
        fullGradeCalculator = new FullGradeCalculator();

        calculateGradeButton = new Button("Calculate Grade");
        calculateGradeButton.setOnAction(fullGradeCalculator);
        calculateGradeButton.setTooltip(new Tooltip("Calculate final grade. If there is no " +
                "input, value defaults to 0."));
        calculateGradeButton.setStyle("-fx-base: #3d9fe5;");
    }

    /**
     * Initializes the button that calculates the required grade on the final exam to get the desired grade.
     */
    private void initCalcNecessaryButton() {
        necessaryFinalGradeCalculator = new NecessaryFinalGradeCalculator();

        calculateNecessaryFinalGrade = new Button("Req Final Score ");
        calculateNecessaryFinalGrade.setOnAction(necessaryFinalGradeCalculator);
        calculateNecessaryFinalGrade.setTooltip(new Tooltip("Calculate the necessary score on the final " +
                "exam to get the input grade. Ignores any input for Final score."));
        calculateNecessaryFinalGrade.setStyle("-fx-base: #3d9fe5;");
    }

    /**
     * Parse the input string to return the integer inside, or the default value if the input is invalid
     * @param input Input string to parse
     * @param defaultVal Default value to return if input is invalid
     * @return defaultVal if input is invalid, the integer in input otherwise
     */
    private int toInt(String input, int defaultVal) {
        if (input == null || input.isEmpty()) {
            return defaultVal;
        }
        return Integer.parseInt("0" + input.replaceAll("\\D+",""));
    }

    @Test
    public void toIntNull() {
        String in = null;
        assertEquals(1, toInt(in, 1));
        assertEquals(0, toInt(in, 0));
    }

    @Test
    public void toIntEmpty() {
        String in = "";
        assertEquals(1, toInt(in, 1));
        assertEquals(0, toInt(in, 0));
    }

    @Test
    public void toIntTest() {
        String in = "5";
        assertEquals(5, toInt(in, 0));

        in = "asdf10";
        assertEquals(10, toInt(in, 0));

        in = "15asdf]";
        assertEquals(15, toInt(in, 0));

        in = "asfd20pqoweri";
        assertEquals(20, toInt(in, 0));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
