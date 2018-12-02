package com.example.jilson.quizapp;

/**
 * Created by Jilson on 30-12-2017.
 */

class Question {
    private int questionId;                //id of the question
    private int correctAnswerOptionNo;     //Option number of  correct answer is stored in this variable
    private int selectedAnswerOptionNo;    //Option number of the answer selected by the user is stored in this variable
    private boolean attended;              //true when question is attended false when not attended
    private boolean questionResult;        //stores the result after evaluation



    /** Constructor initialises correct answer at the time of object creation and sets user selected option as 0
     *@param questionId Id of the question
     *@param correctAnswerOptionNo stores the correct answer option no
     */

    public Question(int questionId,int correctAnswerOptionNo) {
        this.questionId = questionId;
        this.correctAnswerOptionNo = correctAnswerOptionNo;
        this.selectedAnswerOptionNo = 0;
        this.attended = false;
        this.questionResult =false;
    }

    /**function to set the option number the user has selected to selectedAnswerOptionNo variable
    * @param selectedAnswerOptionNo accepts the option no selected by user
    */
    public void setSelectedAnswerOptionNo(int selectedAnswerOptionNo) {
        this.selectedAnswerOptionNo = selectedAnswerOptionNo;
    }

    /**
     * function to set whether question is attended or not
     * @param  attended true if question is attended else false
     * */
    public void setAttented(boolean attended) {
        this.attended = attended;
    }

    /** function to return questionId
    * @return questionId integer type
    * */
    public final int getQuestionId() {
        return questionId;
    }

    /**
     * function to get correct answer option
     * @return returns correct answer
     * */
    public int getCorrectAnswerOptionNo() {
        return correctAnswerOptionNo;
    }

    /**
     *function to get the result of the question
     * @return returns true if question is right else false
     * */
    public boolean isQuestionResult() {
        return questionResult;
    }

    /**
     * function to get status whether question is attented or not
     *
     * */
    public boolean isAttended() {
        return attended;
    }



    /**
     * function to evaluate whether user selected option is right
     * sets the result to result variable
     */
    public void evaluateAnswer(){
        if(this.correctAnswerOptionNo == this.selectedAnswerOptionNo){
            this.questionResult = true;
        }else{
            this.questionResult = false;
        }
    }

    /**
     * function to reset the question
     * */
    public void resetQuestion(){
        selectedAnswerOptionNo = 0;
        attended = false;
        questionResult = false;

    }
}
