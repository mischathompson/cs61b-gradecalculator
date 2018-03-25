package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
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

import java.util.HashMap;

public class Main extends Application {

    private Button calculateGradeButton, calculateNecessaryFinalGrade, settings;
    private FullGradeCalculator fullGradeCalculator;
    private NecessaryFinalGradeCalculator necessaryFinalGradeCalculator;
    private TextField hw, vitamins, projects, midterms, fin, extraCredit, goldPoints, desiredGrade;
    private Label calculatedGrade;
    private boolean emulatingExamSupersession;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CS61B Grade Calculator");

        initCalcGradeButton();
        initCalcNecessaryButton();
        initSettingsButton();
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

        anchorpane.getChildren().addAll(inputGrid, buttonGrid, settings);
        AnchorPane.setBottomAnchor(buttonGrid, 8.0);
        AnchorPane.setLeftAnchor(buttonGrid, 5.0);
        AnchorPane.setBottomAnchor(settings, 8.0);
        AnchorPane.setRightAnchor(settings, 5.0);
        AnchorPane.setTopAnchor(inputGrid, 10.0);

        return anchorpane;
    }

    /**
     * Initializes the TextFields for user input.
     * Sets the prompt text for each field.
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
     * Sets the text, EventHandler, tooltip, and color of the button.
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
     * Sets the text, EventHandler, tooltip, and color of the button.
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
     * Initializes the settings button to open a new settings window when clicked.
     */
    private void initSettingsButton() {
        settings = new Button("Settings");
        settings.setOnAction(e -> new Settings().displaySettingsWindow());
    }

    /**
     * Parse the input string to return the integer inside, or the default value if the input is invalid.
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

    class FullGradeCalculator implements EventHandler<ActionEvent> {
        private HashMap<String, Integer> gradeThresholds;
        private HashMap<String, Integer> categoryThresholds;
        private String[] categories;
        private String[] grades;

        private HashMap<String, Integer> userValues;

        /**
         * Initializes the thresholds for each grade as well
         * as number of points in each grading category,
         * using Spring 2018 values.
         */
        FullGradeCalculator() {
            gradeThresholds = new HashMap<>(13);
            categoryThresholds = new HashMap<>(5);
            userValues = new HashMap<>(5);

            categories = new String[]
                    {"Homework/Labs", "Vitamins", "Projects", "Midterms", "Final", "Extra Credit", "Gold Points"};

            grades = new String[]{"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};

            //add Spring 2018's grade thresholds
            gradeThresholds.put("A+", 1550);
            gradeThresholds.put("A", 1474);
            gradeThresholds.put("A-", 1393);
            gradeThresholds.put("B+", 1290);
            gradeThresholds.put("B", 1195);
            gradeThresholds.put("B-", 1145);
            gradeThresholds.put("C+", 1050);
            gradeThresholds.put("C", 925);
            gradeThresholds.put("C-", 744);
            gradeThresholds.put("D+", 646);
            gradeThresholds.put("D", 544);
            gradeThresholds.put("D-", 400);
            gradeThresholds.put("F", 0);

            //add Spring 2018's category values
            categoryThresholds.put("Homework/Labs", 256);
            categoryThresholds.put("Vitamins", 48);
            categoryThresholds.put("Projects", 480);
            categoryThresholds.put("Midterms", 400);
            categoryThresholds.put("Final", 400);
            categoryThresholds.put("Extra Credit", 32);
            categoryThresholds.put("Gold Points", 100);

            //initializes user's grade values to 0
            userValues.put("Homework/Labs", 0);
            userValues.put("Vitamins", 0);
            userValues.put("Projects", 0);
            userValues.put("Midterms", 0);
            userValues.put("Final", 0);
            userValues.put("Extra Credit", 0);
            userValues.put("Gold Points", 0);
        }

        void updateHW(Integer newVal) {
            Integer threshold = categoryThresholds.get("Homework/Labs");

            //check whether user entered valid point value. If not,
            //sets the value to be the max of the HW category
            if (newVal > threshold) {
                newVal = threshold;
            }

            userValues.put("Homework/Labs", newVal);
        }

        void updateVitamins(Integer newVal) {
            Integer threshold = categoryThresholds.get("Vitamins");

            //check whether user entered valid point value. If not
            //sets the value to be the max of the HW category
            if (newVal > threshold) {
                newVal = threshold;
            }

            userValues.put("Vitamins", newVal);
        }

        void updateProjects(Integer newVal) {
            Integer threshold = categoryThresholds.get("Projects");

            //check whether user entered valid point value. If not
            //sets the value to be the max of the HW category
            if (newVal > threshold) {
                newVal = threshold;
            }

            userValues.put("Projects", newVal);
        }

        void updateMidterms(Integer newVal) {
            Integer threshold = categoryThresholds.get("Midterms");

            //check whether user entered valid point value. If not
            //sets the value to be the max of the HW category
            if (newVal > threshold) {
                newVal = threshold;
            }

            userValues.put("Midterms", newVal);
        }

        void updateFinal(Integer newVal) {
            Integer threshold = categoryThresholds.get("Final");

            //check whether user entered valid point value. If not
            //sets the value to be the max of the HW category
            if (newVal > threshold) {
                newVal = threshold;
            }

            userValues.put("Final", newVal);
        }

        void updateExtraCredit(Integer newVal) {
            Integer threshold = categoryThresholds.get("Extra Credit");

            //check whether user entered valid point value. If not
            //sets the value to be the max of the HW category
            if (newVal > threshold) {
                newVal = threshold;
            }

            userValues.put("Extra Credit", newVal);
        }

        void updateGoldPoints(Integer newVal) {
            Integer threshold = categoryThresholds.get("Gold Points");

            //check whether user entered valid point value. If not
            //sets the value to be the max of the HW category
            if (newVal > threshold) {
                newVal = threshold;
            }

            userValues.put("Gold Points", newVal);
        }

        /**
         * Calculates the user's final grade, using the values provided.
         * Any values not provided, or that are invalid, default to 0.
         * @param event The event received from clicking the 'Calculate Grade' button
         */
        @Override
        public void handle(ActionEvent event) {
            updateHW(toInt(hw.getText(), 0));
            updateVitamins(toInt(vitamins.getText(), 0));
            updateProjects(toInt(projects.getText(), 0));
            updateMidterms(toInt(midterms.getText(), 0));
            updateFinal(toInt(fin.getText(), 0));
            updateExtraCredit(toInt(extraCredit.getText(), 0));
            updateGoldPoints(toInt(goldPoints.getText(), 0));
            System.out.println(calculateGrade());
        }

        /**
         * Calculates the user's grade based on values provided for each category
         * (these are initialized to 0).
         * @return The letter grade the user will receive
         */
        String calculateGrade() {
            int totalPoints = 0;

            for (String curCategory: categories) {
                Integer userValue = userValues.get(curCategory);

                totalPoints += userValue;
            }
            System.out.println(totalPoints);

            //using total points, find correct grade to return
            for (String grade: grades) {
                if (totalPoints >= gradeThresholds.get(grade)) {
                    return grade;
                }
            }

            return null;
        }

    }

    class Settings {

        void displaySettingsWindow() {
            Stage window = new Stage();
            window.initModality(Modality.NONE);

            Button closeButton = new Button("Save Settings and Close");
            closeButton.setOnAction(e -> window.close());

            CheckBox examSuperSessionCheckBox = new CheckBox("Emulate Exam Supersession");
            examSuperSessionCheckBox.setSelected(emulatingExamSupersession);
            examSuperSessionCheckBox.setOnAction(e -> emulatingExamSupersession = !emulatingExamSupersession);

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.getChildren().addAll(closeButton, examSuperSessionCheckBox);

            AnchorPane.setBottomAnchor(closeButton, 8.0);
            AnchorPane.setRightAnchor(closeButton, 5.0);
            AnchorPane.setTopAnchor(examSuperSessionCheckBox, 8.0);
            AnchorPane.setLeftAnchor(examSuperSessionCheckBox, 5.0);

            window.setTitle("CS61B Grade Calculator Settings");
            window.setMinHeight(250);
            window.setMinWidth(250);
            Scene scene = new Scene(anchorPane, 250, 250);
            window.setScene(scene);
            window.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
