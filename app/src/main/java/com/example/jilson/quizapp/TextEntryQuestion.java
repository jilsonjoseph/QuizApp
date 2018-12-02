package com.example.jilson.quizapp;

/**
 * Created by Jilson on 02-01-2018.
 */

public class TextEntryQuestion {

    private int questionId;           // stores id of Edit Text of question
    private String correctAnswer;     //correct answers is stored in this variable
    private String selectedAnswer;    //answers selected by the user is stored in this variable
    private boolean attended;              //true when question is attended false when not attended
    private boolean questionResult;        //stores the result of the question

    /**
     * constructor
     * @param questionId id of question
     * @param correctAnswer correct answer to the question
     * */
    public TextEntryQuestion(int questionId, String correctAnswer) {
        this.questionId = questionId;
        this.correctAnswer = correctAnswer;
    }


    //getters

    public int getQuestionId() {
        return questionId;
    }

    public boolean isAttended() {
        return attended;
    }

    public boolean isQuestionResult() {
        return questionResult;
    }


    //setters
    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    /**
     * function to evaluate whether answer entered by user is right
     */
    public void evaluateAnswer(){
        this.questionResult = correctAnswer.equals(selectedAnswer);
    }

    /**
     * function to reset the question to its default values
     * */
    public void resetQuestion(){
        selectedAnswer = "";
        attended = false;
        questionResult = false;

    }

}


