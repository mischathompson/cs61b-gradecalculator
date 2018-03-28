# cs61b-gradecalculator
This is a grade calculator for UC Berkeley's CS61B.

A few slightly complicated Grading policies can make calculating your exact grade somewhat difficult.

For example, gold points are calculated using the following formula:
![gold points equation](https://latex.codecogs.com/gif.latex?2%20*%20%28goldPoints%20-%20goldPoints%20*%20%5Cfrac%7ByourTotalExamScore%7D%7BmaxTotalExamScore%7D%29)

It's an interesting mechanic, but isn't exactly easy to factor in when considering what you need on the final to get a particular grade.

This started out as a project to learn JavaFX, but the idea was to create something functional and useful.

# Requirements
All you need is Java 9.

If you get something that looks like this:
>Exception in thread "main" java.lang.UnsupportedClassVersionError: sample/Main : Unsupported major.minor version 53.0

It's most likely caused by running the .jar file with a Java version lower than 9.

# How to use
In order to use the grade calculator, first download the calc.jar file.

To run it, go into a terminal, navigate to the directory that has calc.jar and run it with:
>**java -jar calc.jar**

## Calculate your grade
Input scores for each category (anything left blank will be considered a 0) and click the 'Calculate Grade' button. This will display your total points and grade, including properly calculated gold points!

## Determine score needed on the Final exam to get a certain grade
Just like calculating your grade, input scores for each category and then enter a desired grade. Click the 'Req Final Score' button. This will display the score necessary on the Final Exam to get your desired grade. This also accounts for how gold points change as exam scores change.

>Note: If you entered something in the 'Final' category, it is ignored for the purposes of calculation

## Exam Supersession
Click on the 'Exam Supersession' button on the bottom right. This will open a new window with text fields to enter your score, the class mean, and standard deviation for Midterm 1, Midterm 2, and the Final Exam.

After inputting all of these values, click on the 'Calculate Supersession' button and it will display whether Exam Supersession would improve your score and by how much.

# Have fun calculating your grade, and good luck in CS61B!
