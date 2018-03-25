package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Currency;
import java.util.HashMap;

public class FullGradeCalculator implements EventHandler<ActionEvent> {
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
     * If any
     * @param event The event received from clicking the 'Calculate Grade' button
     */
    @Override
    public void handle(ActionEvent event) {

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
            Integer threshold = categoryThresholds.get(curCategory);

            totalPoints += userValue;
        }

        //using total points, find correct grade to return
        for (String grade: grades) {
            if (totalPoints >= gradeThresholds.get(grade)) {
                return grade;
            }
        }

        return null;
    }

}
