package com.example.jilson.quizapp;

import java.util.Arrays;

/**
 * Created by Jilson on 02-01-2018.
 */

public class MultiCorrectQuestion {
    private int questionId;                                       //Id of question
    private boolean[] correctAnswerOptionNo = new boolean[4];     //correct answers is stored in this variable
    private boolean[] selectedAnswerOptionNo = new boolean[4];    //answers selected by the user is stored in this variable
    private boolean attended;                                    //true when question is attended false when not attended
    private boolean questionResult;                               // stores the result of the question

    /** Constructor initialises correct answer at the time of object creation and sets user selected option as 0
     *@param questionId Id of the question
     *@param correctAnswerOptionNo stores the correct answer option no
     */

    public MultiCorrectQuestion(int questionId, boolean[] correctAnswerOptionNo) {
        this.questionId = questionId;
        this.correctAnswerOptionNo = correctAnswerOptionNo;
        this.attended = false;
        this.questionResult =false;
    }

    /**
     * Constructor
     * @param selectedAnswerOptionNo the answer which is selected by user
     * */
    public void setSelectedAnswerOptionNo(boolean[] selectedAnswerOptionNo) {
        this.selectedAnswerOptionNo = selectedAnswerOptionNo;
    }

    //getter

    public int getQuestionId() {
        return questionId;
    }

    public boolean[] getCorrectAnswerOptionNo() {
        return correctAnswerOptionNo;
    }

    public boolean isAttended() {
        return attended;
    }

    public boolean isQuestionResult() {
        return questionResult;
    }


    //setter

    public void setAttended(boolean attended) {
        this.attended = attended;
    }


    /**
     * function to evaluate whether user selected option is right
     */
    public void evaluateAnswer(){
        this.questionResult = Arrays.equals(correctAnswerOptionNo,selectedAnswerOptionNo);
    }

    /**
     * function to reset the question
     * */
    public void resetQuestion(){
        Arrays.fill(selectedAnswerOptionNo,false);
        attended = false;
        questionResult = false;

    }
}


