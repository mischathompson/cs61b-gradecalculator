package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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

    private Button calculateGradeButton, calculateNecessaryFinalGrade, examSupersessionButton;
    private FullGradeCalculator fullGradeCalculator;
    private NecessaryFinalGradeCalculator necessaryFinalGradeCalculator;
    private TextField hw, vitamins, projects, mt1Score, mt2Score, finScore, extraCredit, goldPoints, desiredGrade;
    private Text calculation;

    private static HashMap<String, Integer> gradeThresholds = new HashMap<>(13);
    private static HashMap<String, Integer> categoryThresholds = new HashMap<>(5);
    private static HashMap<String, Integer> userValues = new HashMap<>(5);

    private static final String[] categories = new String[]
    {"Homework/Labs", "Vitamins", "Projects", "Midterm 1", "Midterm 2", "Final", "Extra Credit", "Gold Points"};

    private static final String[] grades =
            new String[]{"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};

    /*
     * Initializes the thresholds for each grade as well
     * as number of points in each grading category,
     * using Spring 2018 values.
     */
    static {
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

        //add Spring 2018's category thresholds
        categoryThresholds.put("Homework/Labs", 256);
        categoryThresholds.put("Vitamins", 48);
        categoryThresholds.put("Projects", 480);
        categoryThresholds.put("Midterm 1", 160);
        categoryThresholds.put("Midterm 2", 240);
        categoryThresholds.put("Final", 400);
        categoryThresholds.put("Extra Credit", 32);
        categoryThresholds.put("Gold Points", 100);

        //initializes user's grade values to 0
        userValues.put("Homework/Labs", 0);
        userValues.put("Vitamins", 0);
        userValues.put("Projects", 0);
        userValues.put("Midterm 1", 0);
        userValues.put("Midterm 2", 0);
        userValues.put("Final", 0);
        userValues.put("Extra Credit", 0);
        userValues.put("Gold Points", 0);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CS61B Grade Calculator");

        initCalcGradeButton();
        initCalcNecessaryButton();
        initExamSupersessionButton();
        initTextFields();
        initLayout();

        AnchorPane layout = initLayout();
        Scene mainScene = new Scene(layout, 600, 450);
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Initializes the layout, adding the buttons, text, and text entry fields.
     */
    private AnchorPane initLayout() {
        //initialize the input grid and set gaps + padding
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(5);
        inputGrid.setVgap(5);
        inputGrid.setPadding(new Insets(0, 10, 0, 10));

        //add the categories to the grid
        Text title = new Text("Scores");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        inputGrid.add(title, 0, 0);

        Text hw = new Text("HW/Labs: ");
        inputGrid.add(hw, 1, 1);
        inputGrid.add(this.hw, 2, 1);
        inputGrid.add(new Text("/ " + categoryThresholds.get("Homework/Labs")), 3, 1);

        Text vitamins = new Text("Vitamins: ");
        inputGrid.add(vitamins, 1,  2);
        inputGrid.add(this.vitamins, 2, 2);
        inputGrid.add(new Text("/ " + categoryThresholds.get("Vitamins")), 3, 2);

        Text projects = new Text("Projects: ");
        inputGrid.add(projects, 1, 3);
        inputGrid.add(this.projects, 2, 3);
        inputGrid.add(new Text("/ " + categoryThresholds.get("Projects")), 3, 3);

        Text midterm1 = new Text("Midterm 1: ");
        inputGrid.add(midterm1, 1, 4);
        inputGrid.add(this.mt1Score, 2, 4);
        inputGrid.add(new Text("/ " + categoryThresholds.get("Midterm 1")), 3, 4);

        Text midterm2 = new Text("Midterm 2: ");
        inputGrid.add(midterm2, 1, 5);
        inputGrid.add(this.mt2Score, 2, 5);
        inputGrid.add(new Text("/ " + categoryThresholds.get("Midterm 2")), 3, 5);

        Text fin = new Text("Final: ");
        inputGrid.add(fin, 1, 6);
        inputGrid.add(this.finScore, 2, 6);
        inputGrid.add(new Text("/ " + categoryThresholds.get("Final")), 3, 6);

        Text extraCredit = new Text("Extra Credit: ");
        inputGrid.add(extraCredit, 1, 7);
        inputGrid.add(this.extraCredit, 2, 7);
        inputGrid.add(new Text("/ " + categoryThresholds.get("Extra Credit")), 3, 7);

        Text goldPoints = new Text("Gold Points: ");
        inputGrid.add(goldPoints, 1, 8);
        inputGrid.add(this.goldPoints, 2, 8);

        AnchorPane anchorpane = new AnchorPane();

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(5);
        buttonGrid.setVgap(5);
        buttonGrid.setPadding(new Insets(0, 10, 0, 10));

        buttonGrid.add(calculateGradeButton, 0, 0);
        buttonGrid.add(calculateNecessaryFinalGrade, 0, 1);
        buttonGrid.add(new Text(" to get a(n) "), 1, 1);
        buttonGrid.add(desiredGrade, 2, 1);

        calculation = new Text();
        calculation.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        anchorpane.getChildren().addAll(inputGrid, buttonGrid, examSupersessionButton, calculation);
        AnchorPane.setBottomAnchor(buttonGrid, 8.0);
        AnchorPane.setLeftAnchor(buttonGrid, 5.0);
        AnchorPane.setBottomAnchor(examSupersessionButton, 8.0);
        AnchorPane.setRightAnchor(examSupersessionButton, 5.0);
        AnchorPane.setTopAnchor(inputGrid, 10.0);
        AnchorPane.setBottomAnchor(calculation, 130.0);
        AnchorPane.setLeftAnchor(calculation, 183.0);

        return anchorpane;
    }

    /**
     * Initializes the TextFields for user input.
     * Sets the prompt text for each field and adds listeners
     * to ensure that only valid inputs can be entered.
     */
    private void initTextFields() {
        hw = new TextField();
        hw.setPromptText("Enter HW/Labs score");
        hw.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                hw.setText(newValue.replaceAll("\\D+", ""));
            } else {
                if (toInt(newValue, 0) > categoryThresholds.get("Homework/Labs")) {
                    hw.setStyle("-fx-background-color: #ff2349, #ff96a8;");
                } else {
                    hw.setStyle("");
                }
            }
            calculation.setText("");
        });

        vitamins = new TextField();
        vitamins.setPromptText("Enter Vitamins Score");
        vitamins.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                vitamins.setText(newValue.replaceAll("\\D+", ""));
            } else {
                if (toInt(newValue, 0) > categoryThresholds.get("Vitamins")) {
                    vitamins.setStyle("-fx-background-color: #ff2349, #ff96a8;");
                } else {
                    vitamins.setStyle("");
                }
            }
            calculation.setText("");
        });

        projects = new TextField();
        projects.setPromptText("Enter Projects Score");
        projects.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                projects.setText(newValue.replaceAll("\\D+", ""));
            } else {
                if (toInt(newValue, 0) > categoryThresholds.get("Projects")) {
                    projects.setStyle("-fx-background-color: #ff2349, #ff96a8;");
                } else {
                    projects.setStyle("");
                }
            }
            calculation.setText("");
        });

        mt1Score = new TextField();
        mt1Score.setPromptText("Enter MT1 Score");
        mt1Score.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                mt1Score.setText(newValue.replaceAll("\\D+", ""));
            } else {
                if (toInt(newValue, 0) > categoryThresholds.get("Midterm 1")) {
                    mt1Score.setStyle("-fx-background-color: #ff2349, #ff96a8;");
                } else {
                    mt1Score.setStyle("");
                }
            }
            calculation.setText("");
        });

        mt2Score = new TextField();
        mt2Score.setPromptText("Enter MT2 Score");
        mt2Score.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                mt2Score.setText(newValue.replaceAll("\\D+", ""));
            } else {
                if (toInt(newValue, 0) > categoryThresholds.get("Midterm 2")) {
                    mt2Score.setStyle("-fx-background-color: #ff2349, #ff96a8;");
                } else {
                    mt2Score.setStyle("");
                }
            }
            calculation.setText("");
        });

        finScore = new TextField();
        finScore.setPromptText("Enter Final Score");
        finScore.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                finScore.setText(newValue.replaceAll("\\D+", ""));
            } else {
                if (toInt(newValue, 0) > categoryThresholds.get("Final")) {
                    finScore.setStyle("-fx-background-color: #ff2349, #ff96a8;");
                } else {
                    finScore.setStyle("");
                }
            }
            calculation.setText("");
        });

        extraCredit = new TextField();
        extraCredit.setPromptText("Enter Extra Credit");
        extraCredit.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                extraCredit.setText(newValue.replaceAll("\\D+", ""));
            } else {
                if (toInt(newValue, 0) > categoryThresholds.get("Extra Credit")) {
                    extraCredit.setStyle("-fx-background-color: #ff2349, #ff96a8;");
                } else {
                    extraCredit.setStyle("");
                }
            }
            calculation.setText("");
        });

        goldPoints = new TextField();
        goldPoints.setPromptText("Enter Gold Points");
        goldPoints.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                goldPoints.setText(newValue.replaceAll("\\D+", ""));
            }
            calculation.setText("");
        });

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
     * Initializes the examSupersessionButton to open a new window that users can use to
     * test different exam scores and how exam supersession might affect the score.
     */
    private void initExamSupersessionButton() {
        examSupersessionButton = new Button("Exam Supersession");
        examSupersessionButton.setOnAction(e -> new ExamSupersession().displayExamSupersessionWindow());
        examSupersessionButton.setTooltip(new Tooltip("Open a new window to test how exam supersession " +
                "might affect certain exam scores."));
    }

    private void updateAllValues() {
        updateHW(toInt(hw.getText(), 0));
        updateVitamins(toInt(vitamins.getText(), 0));
        updateProjects(toInt(projects.getText(), 0));
        updateMidterm1(toInt(mt1Score.getText(), 0));
        updateMidterm2(toInt(mt2Score.getText(), 0));
        updateFinal(toInt(finScore.getText(), 0));
        updateExtraCredit(toInt(extraCredit.getText(), 0));
        updateGoldPoints(toInt(goldPoints.getText(), 0));
    }

    private void updateHW(Integer newVal) {
        Integer threshold = categoryThresholds.get("Homework/Labs");

        //check whether user entered valid point value. If not,
        //sets the value to be the max of the HW category
        if (newVal > threshold) {
            newVal = threshold;
        }

        userValues.put("Homework/Labs", newVal);
    }

    private void updateVitamins(Integer newVal) {
        Integer threshold = categoryThresholds.get("Vitamins");

        //check whether user entered valid point value. If not
        //sets the value to be the max of the Vitamins category
        if (newVal > threshold) {
            newVal = threshold;
        }

        userValues.put("Vitamins", newVal);
    }

    private void updateProjects(Integer newVal) {
        Integer threshold = categoryThresholds.get("Projects");

        //check whether user entered valid point value. If not
        //sets the value to be the max of the Projects category
        if (newVal > threshold) {
            newVal = threshold;
        }

        userValues.put("Projects", newVal);
    }

    private void updateMidterm1(Integer newVal) {
        Integer threshold = categoryThresholds.get("Midterm 1");

        //check whether user entered valid point value. If not
        //sets the value to be the max of the Midterm 1 category
        if (newVal > threshold) {
            newVal = threshold;
        }

        userValues.put("Midterm 1", newVal);
    }

    private void updateMidterm2(Integer newVal) {
        Integer threshold = categoryThresholds.get("Midterm 2");

        //check whether user entered valid point value. If not
        //sets the value to be the max of the Midterm 2 category
        if (newVal > threshold) {
            newVal = threshold;
        }

        userValues.put("Midterm 2", newVal);
    }

    private void updateFinal(Integer newVal) {
        Integer threshold = categoryThresholds.get("Final");

        //check whether user entered valid point value. If not
        //sets the value to be the max of the HW category
        if (newVal > threshold) {
            newVal = threshold;
        }

        userValues.put("Final", newVal);
    }

    private void updateExtraCredit(Integer newVal) {
        Integer threshold = categoryThresholds.get("Extra Credit");

        //check whether user entered valid point value. If not
        //sets the value to be the max of the HW category
        if (newVal > threshold) {
            newVal = threshold;
        }

        userValues.put("Extra Credit", newVal);
    }

    private void updateGoldPoints(Integer newVal) {
        Integer threshold = categoryThresholds.get("Gold Points");

        //check whether user entered valid point value. If not
        //sets the value to be the max of the HW category
        if (newVal > threshold) {
            newVal = threshold;
        }

        userValues.put("Gold Points", newVal);
    }

    private int calculateTotalScore() {
        int totalPoints = 0;
        int examScore = 0;
        int goldPoints = 0;
        int totalExamThreshold = categoryThresholds.get("Midterm 1") + categoryThresholds.get("Midterm 2")
                + categoryThresholds.get("Final");

        for (String curCategory: categories) {
            Integer userValue = userValues.get(curCategory);

            if (curCategory.equals("Midterm 1") || curCategory.equals("Midterm 2")
                    || curCategory.equals("Final")) {
                examScore += userValue;
            }
            if (curCategory.equals("Gold Points")) {
                goldPoints = userValue;
            } else {
                totalPoints += userValue;
            }
        }
        double goldPointsBoost = 2 * (goldPoints - (goldPoints * ((double) examScore / totalExamThreshold)));
        totalPoints += Math.round(goldPointsBoost);
        return totalPoints;
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

    class FullGradeCalculator implements EventHandler<ActionEvent> {

        /**
         * Calculates the user's final grade, using the values provided.
         * Any values not provided, or that are invalid, default to 0.
         * @param event The event received from clicking the 'Calculate Grade' button
         */
        @Override
        public void handle(ActionEvent event) {
            updateAllValues();
            calculateGrade();
        }

        /**
         * Calculates the user's grade based on values provided for each category
         * (these are initialized to 0).
         * @return The letter grade the user will receive
         */
        String calculateGrade() {
            int totalPoints = calculateTotalScore();

            String calculatedGrade = "?";
            //using total points, find correct grade to return
            for (String grade: grades) {
                if (totalPoints >= gradeThresholds.get(grade)) {
                    calculatedGrade = grade;
                    break;
                }
            }
            calculation.setText("Total points: " + totalPoints + "\nGrade: " + calculatedGrade);

            return calculatedGrade;
        }

    }

    class ExamSupersession {
        private TextField mt1Score, mt1Mean, mt1StdDev,
                mt2Score, mt2Mean, mt2StdDev,
                finScore, finMean, finStdDev;
        Text examSupersessionResult;
        Button closeButton, calculateButton;


        /**
         * Opens a new window for the user to be able to experiment with exam
         * supersession. Allows the user to input their score, the mean, and
         * the standard deviation for both midterms and the final, and will display
         * whether exam supersession will improve their overall score and by how much.
         */
        void displayExamSupersessionWindow() {
            Stage window = new Stage();

            AnchorPane anchorPane = initLayout(window);

            window.setTitle("CS61B Grade Calculator Exam Supersession");
            Scene scene = new Scene(anchorPane, 500, 550);
            window.setScene(scene);
            window.setResizable(false);
            window.show();
        }

        /**
         * Calculates the results of exam supersession, updating the
         * Text object's text to reflect the results.
         */
        void calculateExamSupersession() {
            double F, FSEM1, FSEM2, scoreM1Replaced, scoreM2Replaced, scoreNoReplacements;
            double m1Score = toInt(mt1Score.getText(), 0);
            double m1Mean = toInt(mt1Mean.getText(), 0);
            double m1Stddev = toInt(mt1StdDev.getText(), 0);

            double m2Score = toInt(mt2Score.getText(), 0);
            double m2Mean = toInt(mt2Mean.getText(), 0);
            double m2Stddev = toInt(mt2StdDev.getText(), 0);

            double finalScore = toInt(finScore.getText(), 0);
            double finalMean = toInt(finMean.getText(), 0);
            double finalStddev = toInt(finStdDev.getText(), 0);

            F = (finalScore - finalMean) / finalStddev;

            FSEM1 = m1Stddev * F + m1Mean;
            FSEM2 = m2Stddev * F + m2Mean;
            if (FSEM1 > 160) {
                FSEM1 = 160;
            }
            if (FSEM2 > 240) {
                FSEM2 = 240;
            }

            scoreM1Replaced = FSEM1 + m2Score + finalScore;
            scoreM2Replaced = m1Score + FSEM2 + finalScore;
            scoreNoReplacements = m1Score + m2Score + finalScore;

            if (scoreM1Replaced > scoreM2Replaced && scoreM1Replaced > scoreNoReplacements) {
                examSupersessionResult.setText("Total score maximized with Midterm 1\nScore (" +
                        (int) m1Score + ") replaced by FSE of (" + (int) FSEM1
                        + ").\nThis leads to an increase of (" + (int) (scoreM1Replaced - scoreNoReplacements) + ") pts.");
            } else if (scoreM2Replaced > scoreM1Replaced && scoreM2Replaced > scoreNoReplacements) {
                examSupersessionResult.setText("Total score maximized with Midterm 2\nScore (" +
                        (int) m2Score + ") replaced by FSE of (" + (int) FSEM2
                        + ").\nThis leads to an increase of (" + (int) (scoreM2Replaced - scoreNoReplacements) + ") pts.");
            } else {
                examSupersessionResult.setText("Exam Supersession will not improve final score.");
            }
        }

        /**
         * Initializes the ExamSupersession calculator's layout.
         * Sets the calculate and close buttons as well as all text
         * fields and their listeners.
         *
         * @param window The window that the close button will close.
         * @return The layout, with all buttons and text fields added.
         */
        AnchorPane initLayout(Stage window) {
            closeButton = new Button("Close");
            closeButton.setOnAction(e -> window.close());

            calculateButton = new Button("Calculate Supersession");
            calculateButton.setStyle("-fx-base: #3d9fe5;");
            calculateButton.setOnAction(e -> calculateExamSupersession());

            mt1Score = new TextField();
            mt1Score.setPromptText("Enter Midterm 1 Score");
            mt1Score.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    mt1Score.setText(newValue.replaceAll("\\D+", ""));
                }
                examSupersessionResult.setText("");
            });

            mt1Mean = new TextField();
            mt1Mean.setPromptText("Enter Midterm 1 Mean");
            mt1Mean.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    mt1Mean.setText(newValue.replaceAll("\\D+", ""));
                }
                examSupersessionResult.setText("");
            });

            mt1StdDev = new TextField();
            mt1StdDev.setPromptText("Enter Midterm 1 Std Dev");
            mt1StdDev.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    mt1StdDev.setText(newValue.replaceAll("\\D+", ""));
                }
                examSupersessionResult.setText("");
            });

            mt2Score = new TextField();
            mt2Score.setPromptText("Enter Midterm 2 Score");
            mt2Score.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    mt2Score.setText(newValue.replaceAll("\\D+", ""));
                }
                examSupersessionResult.setText("");
            });

            mt2Mean = new TextField();
            mt2Mean.setPromptText("Enter Midterm 2 Mean");
            mt2Mean.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    mt2Mean.setText(newValue.replaceAll("\\D+", ""));
                }
                examSupersessionResult.setText("");
            });

            mt2StdDev = new TextField();
            mt2StdDev.setPromptText("Enter Midterm 2 Std Dev");
            mt2StdDev.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    mt2StdDev.setText(newValue.replaceAll("\\D+", ""));
                }
                examSupersessionResult.setText("");
            });

            finScore = new TextField();
            finScore.setPromptText("Enter Final Exam Score");
            finScore.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    finScore.setText(newValue.replaceAll("\\D+", ""));
                }
                examSupersessionResult.setText("");
            });

            finMean = new TextField();
            finMean.setPromptText("Enter Final Exam Mean");
            finMean.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    finMean.setText(newValue.replaceAll("\\D+", ""));
                }
                examSupersessionResult.setText("");
            });

            finStdDev = new TextField();
            finStdDev.setPromptText("Enter Final Exam Std Dev");
            finStdDev.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    finStdDev.setText(newValue.replaceAll("\\D+", ""));
                }
                examSupersessionResult.setText("");
            });

            GridPane inputGrid = new GridPane();
            inputGrid.setHgap(10);
            inputGrid.setVgap(10);
            inputGrid.setPadding(new Insets(0, 10, 0, 10));

            Text title = new Text("Scores");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            inputGrid.add(title, 0, 0);

            Text mt1ScoreIn = new Text("Midterm 1 Score: ");
            Text mt1MeanIn = new Text("Midterm 1 Mean: ");
            Text mt1StdIn = new Text("Midterm 1 Std Dev: ");
            inputGrid.add(mt1ScoreIn, 1, 1);
            inputGrid.add(mt1MeanIn, 1, 2);
            inputGrid.add(mt1StdIn, 1, 3);
            inputGrid.add(mt1Score, 2, 1);
            inputGrid.add(mt1Mean, 2, 2);
            inputGrid.add(mt1StdDev, 2, 3);

            Text mt2ScoreIn = new Text("Midterm 2 Score: ");
            Text mt2MeanIn = new Text("Midterm 2 Mean: ");
            Text mt2StdIn = new Text("Midterm 2 Std Dev: ");
            inputGrid.add(mt2ScoreIn, 1, 5);
            inputGrid.add(mt2MeanIn, 1, 6);
            inputGrid.add(mt2StdIn, 1, 7);
            inputGrid.add(mt2Score, 2, 5);
            inputGrid.add(mt2Mean, 2, 6);
            inputGrid.add(mt2StdDev, 2, 7);

            Text finScoreIn = new Text("Final Exam Score: ");
            Text finMeanIn = new Text("Final Exam Mean: ");
            Text finStdIn = new Text("Final Exam Std Dev: ");
            inputGrid.add(finScoreIn, 1, 9);
            inputGrid.add(finMeanIn, 1, 10);
            inputGrid.add(finStdIn, 1, 11);
            inputGrid.add(finScore, 2, 9);
            inputGrid.add(finMean, 2, 10);
            inputGrid.add(finStdDev, 2, 11);


            AnchorPane anchorPane = new AnchorPane();

            examSupersessionResult = new Text();
            examSupersessionResult.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            anchorPane.getChildren().addAll(closeButton, inputGrid, calculateButton, examSupersessionResult);

            AnchorPane.setBottomAnchor(closeButton, 8.0);
            AnchorPane.setRightAnchor(closeButton, 5.0);
            AnchorPane.setTopAnchor(inputGrid, 8.0);
            AnchorPane.setLeftAnchor(inputGrid, 5.0);
            AnchorPane.setBottomAnchor(calculateButton, 8.0);
            AnchorPane.setLeftAnchor(calculateButton, 5.0);
            AnchorPane.setBottomAnchor(examSupersessionResult, 100.0);
            AnchorPane.setLeftAnchor(examSupersessionResult, 100.0);


            return anchorPane;
        }
    }

    class NecessaryFinalGradeCalculator implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            updateAllValues();
            finalScoreNecessary();
        }

        boolean desiredGradeChoiceValid() {
            String dG = desiredGrade.getText();
            if (dG.isEmpty()) {
                return false;
            }
            dG = dG.toUpperCase();
            for (String grade: grades) {
                if (dG.equals(grade)) {
                    return true;
                }
            }
            return false;
        }

        double finalScoreNecessary() {
            if (!desiredGradeChoiceValid()) {
                return -1;
            }
            String gradeText = desiredGrade.getText().toUpperCase();
            int desiredGradeThreshold = gradeThresholds.get(gradeText);
            double maxExamScore = 0;
            double mt1Score, mt2Score, projectsScore, hwScore, vitaminsScore, goldPoints, extraCredit;
            mt1Score = userValues.get("Midterm 1");
            mt2Score = userValues.get("Midterm 2");
            projectsScore = userValues.get("Projects");
            hwScore = userValues.get("Homework/Labs");
            vitaminsScore = userValues.get("Vitamins");
            goldPoints = userValues.get("Gold Points");
            extraCredit = userValues.get("Extra Credit");

            for (String curCategory: categories) {
                Integer maxValue = categoryThresholds.get(curCategory);

                if (curCategory.equals("Midterm 1") || curCategory.equals("Midterm 2")
                        || curCategory.equals("Final")) {
                    maxExamScore += maxValue;
                }
            }
            double numerator = maxExamScore * (desiredGradeThreshold - 2 * goldPoints
                    - hwScore - projectsScore - extraCredit - mt1Score - mt2Score - vitaminsScore)
                    + 2 * goldPoints * (mt1Score + mt2Score);
            double denom = maxExamScore - 2 * goldPoints;
            double necessaryFinalScore = numerator / denom;
            if (necessaryFinalScore <= 0) {
                calculation.setText("Congratulations, you are already\npast the " + gradeText + " threshold!");
            } else {
                int ceilingScore = (int) Math.ceil(necessaryFinalScore);
                calculation.setText("You need to get at least (" + ceilingScore + ")\npts on the " +
                        "Final to get a(n) " + gradeText);
            }

            return numerator / denom;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
