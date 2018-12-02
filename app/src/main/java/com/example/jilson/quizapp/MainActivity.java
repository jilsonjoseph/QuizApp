package com.example.jilson.quizapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    /* Delcaration of Question objects with id of corresponding view, and correct answer of each question*/

    Question question1 = new Question(R.id.question_1_radio_group,R.id.option_4_question_1_radio_button);
    Question question2 = new Question(R.id.question_2_radio_group,R.id.option_2_question_2_radio_button);
    Question question3 = new Question(R.id.question_3_radio_group,R.id.option_4_question_3_radio_button);
    Question question4 = new Question(R.id.question_4_radio_group,R.id.option_2_question_4_radio_button);
    Question question5 = new Question(R.id.question_5_radio_group,R.id.option_3_question_5_radio_button);

    boolean[] question6RightAnswer = {false,true,false,true};    // right answer for question 6 is set to this variable, so that it can be passed
    MultiCorrectQuestion question6 = new MultiCorrectQuestion(R.id.question_6_linear_layout,question6RightAnswer);
    TextEntryQuestion question7 = new TextEntryQuestion(R.id.question_7_edit_text,"radius");

    boolean submitFlag = true;                                  // Flag to track whether submit button is pressed or not
    boolean minimumOneQuestionFlag = false;                     // Flag to check if minimum one question is selected or not

    int noOfQuestionsAttended = 0;                              // tracks the number of questions attended
    int noOfQuestionsCorrect = 0;                               // tracks no of correct answers

    boolean[] question6SelectedAnswer = new boolean[4];         // stores answer selected by user



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /**
     * function for option selection, used to set the answer selected by the user  in Question class objects
     * @param view RadioButton which is selected
     * */
    public void onOptionSelection(View view){
        minimumOneQuestionFlag = true;                                               // as an option is selected minimumOneQuestionFlag is set to true
        boolean checked = ((RadioButton) view).isChecked();
        if(checked){
            RadioButton optionButton = (RadioButton)view;
            RadioGroup questionId = (RadioGroup) optionButton.getParent();           //questionId is assigned the id of RadioGroup from which the option was selected
            switch(questionId.getId()){                                              // matching the RadioGroup id with corresponding question objects
                case  R.id.question_1_radio_group:
                    question1.setSelectedAnswerOptionNo(optionButton.getId());       // setting the option selected by user in object
                    question1.setAttented(true);                                     // marking the question as attended
                    question1.evaluateAnswer();                                      // evaluation of answer
                    break;

                case  R.id.question_2_radio_group:
                    question2.setSelectedAnswerOptionNo(optionButton.getId());
                    question2.setAttented(true);
                    question2.evaluateAnswer();
                    break;

                case  R.id.question_3_radio_group:
                    question3.setSelectedAnswerOptionNo(optionButton.getId());
                    question3.setAttented(true);
                    question3.evaluateAnswer();
                    break;

                case  R.id.question_4_radio_group:
                    question4.setSelectedAnswerOptionNo(optionButton.getId());
                    question4.setAttented(true);
                    question4.evaluateAnswer();
                    break;

                case  R.id.question_5_radio_group:
                    question5.setSelectedAnswerOptionNo(optionButton.getId());
                    question5.setAttented(true);
                    question5.evaluateAnswer();
                    break;
            }

        }

    }

    /**
     * This method is called when any of the check boxes are selected
     * @param view view which is checked
     * */
    public void onCheckboxSelection(View view){
        minimumOneQuestionFlag = true;                                  //sets at least one question is attended
        question6.setAttended(true);                                    // sets the question6 as attended
    }

    /**
     * function to find which all checkboxes are selected
     * and it is updated in question6SelectedAnswer boolean array
     * */
    public void findCheckboxSelections(){

            if(((CheckBox)findViewById(R.id.option_1_question_6_checkbox)).isChecked()) {                  // checking whether checkbox 1 is selected
                question6SelectedAnswer[0] = true;                                                         // if checkbox 1 is selected first variable of boolean array is set to true
            } else {
                question6SelectedAnswer[0] = false;                                                        // if not selected it is set to false
            }

            if(((CheckBox)findViewById(R.id.option_2_question_6_checkbox)).isChecked()) {                  // checking whether checkbox 2 is selected
                question6SelectedAnswer[1] = true;                                                         // if checkbox 2 is selected second variable of boolean array is set to true
            } else {
                question6SelectedAnswer[1] = false;                                                        // if not selected it is set to false
            }

            if(((CheckBox)findViewById(R.id.option_3_question_6_checkbox)).isChecked()) {                  // checking whether checkbox 3 is selected
                question6SelectedAnswer[2] = true;
            } else {
                question6SelectedAnswer[2] = false;
            }

            if(((CheckBox)findViewById(R.id.option_5_question_6_checkbox)).isChecked()) {
                question6SelectedAnswer[3] = true;
            } else {
                question6SelectedAnswer[3] = false;
            }
            question6.setSelectedAnswerOptionNo(question6SelectedAnswer);                                  // the boolean array is passed, its value is set as selectedAnswerOptionNo
            question6.evaluateAnswer();                                                                    // function to evaluate answer is called
    }

    /**
     * function called when edit text is input
     * @param  view view passed from edit text
     * */
    public void onEditTextSelection(View view){
        minimumOneQuestionFlag = true;                                              // sets at least one question answered
        question7.setAttended(true);                                                // sets question seven as attended
    }

    /**
     * function to update user selected answer
     * */
    public void findEnteredText(){
        EditText editText = findViewById(question7.getQuestionId());
        question7.setSelectedAnswer(editText.getText().toString().toLowerCase());   // user input is formated to lower case and set to SelectedAnswer
        question7.evaluateAnswer();                                                 // function to evaluate answer is called
    }

    /**
     * function to handle button press
     * if the submitFlag is true, that is if submit button is not pressed once then submit() method is called else tryAgain() method is called
     * */
    public void multiButton(View view){

        if(submitFlag){
            if(minimumOneQuestionFlag) {                    //checking whether at least one question is answered using using minimumOneQuestionFlag
                submit();
            }else{
                Toast toast = Toast.makeText(this,"Answer atleast one Question",Toast.LENGTH_SHORT);       // displays tost message to answer atleast one question
                toast.show();
            }
        }else {
            tryAgain();
        }
    }

    /**
     * function to evaluate question when submit button is pressed
     * If question is attended then this method changes the background of question, green for true, red for false
     * if answer selected by user is false then correct answer is highlighted using green background
     * updates number of number of questions attended and number of correct answers
     * */
    public void submit(){
        findCheckboxSelections();                                             //function to set user selected answer in checkboxes is called
        findEnteredText();                                                    //function to set user entered answer in edit text answer is called
        if(question1.isAttended()){                                           //checks whether question is attended
            noOfQuestionsAttended++;
            if(question1.isQuestionResult()){
                noOfQuestionsCorrect++;
                RadioGroup q1 = findViewById(question1.getQuestionId());
                LinearLayout ll = (LinearLayout) q1.getParent();                 //getting the id of linear layout containing the question
                ll.setBackgroundColor(Color.parseColor("#B9F6CA"));     //setting the background of question as green
            }else{
                RadioGroup q1 = findViewById(question1.getQuestionId());
                LinearLayout ll = (LinearLayout) q1.getParent();                  //getting the id of linear layout containing the question
                ll.setBackgroundColor(Color.parseColor("#FF8A80"));      //setting the background of question as red as the question is false
                RadioButton b1 = findViewById(question1.getCorrectAnswerOptionNo());                //RadioButton for the correct answer is set to b1
                b1.setBackgroundColor(Color.parseColor("#B9F6CA"));                        //setting background color of the correct answer to green
            }
        }

        if(question2.isAttended()){
            noOfQuestionsAttended++;
            if(question2.isQuestionResult()){
                noOfQuestionsCorrect++;
                RadioGroup q2 = findViewById(question2.getQuestionId());
                LinearLayout ll = (LinearLayout) q2.getParent();
                ll.setBackgroundColor(Color.parseColor("#B9F6CA"));
            }else{
                RadioGroup q2 = findViewById(question2.getQuestionId());
                LinearLayout ll = (LinearLayout) q2.getParent();
                ll.setBackgroundColor(Color.parseColor("#FF8A80"));
                RadioButton b2 = findViewById(question2.getCorrectAnswerOptionNo());
                b2.setBackgroundColor(Color.parseColor("#B9F6CA"));
            }
        }

        if(question3.isAttended()){
            noOfQuestionsAttended++;
            if(question3.isQuestionResult()){
                noOfQuestionsCorrect++;
                RadioGroup q3 = findViewById(question3.getQuestionId());
                LinearLayout ll = (LinearLayout) q3.getParent();
                ll.setBackgroundColor(Color.parseColor("#B9F6CA"));
            }else{
                RadioGroup q3 = findViewById(question3.getQuestionId());
                LinearLayout ll = (LinearLayout) q3.getParent();
                ll.setBackgroundColor(Color.parseColor("#FF8A80"));
                RadioButton b3 = findViewById(question3.getCorrectAnswerOptionNo());
                b3.setBackgroundColor(Color.parseColor("#B9F6CA"));
            }
        }

        if(question4.isAttended()){
            noOfQuestionsAttended++;
            if(question4.isQuestionResult()){
                noOfQuestionsCorrect++;
                RadioGroup q4 = findViewById(question4.getQuestionId());
                LinearLayout ll = (LinearLayout) q4.getParent();
                ll.setBackgroundColor(Color.parseColor("#B9F6CA"));
            }else{
                RadioGroup q4 = findViewById(question4.getQuestionId());
                LinearLayout ll = (LinearLayout) q4.getParent();
                ll.setBackgroundColor(Color.parseColor("#FF8A80"));
                RadioButton b4 = findViewById(question4.getCorrectAnswerOptionNo());
                b4.setBackgroundColor(Color.parseColor("#B9F6CA"));
            }
        }

        if(question5.isAttended()){
            noOfQuestionsAttended++;
            if(question5.isQuestionResult()){
                noOfQuestionsCorrect++;
                RadioGroup q5 = findViewById(question5.getQuestionId());
                LinearLayout ll = (LinearLayout) q5.getParent();
                ll.setBackgroundColor(Color.parseColor("#B9F6CA"));
            }else{
                RadioGroup q5 = findViewById(question5.getQuestionId());
                LinearLayout ll = (LinearLayout) q5.getParent();
                ll.setBackgroundColor(Color.parseColor("#FF8A80"));
                RadioButton b5 = findViewById(question5.getCorrectAnswerOptionNo());
                b5.setBackgroundColor(Color.parseColor("#B9F6CA"));
            }
        }

        if(question6.isAttended()){
            noOfQuestionsAttended++;
            if(question6.isQuestionResult()){
                noOfQuestionsCorrect++;
                LinearLayout ll = findViewById(question6.getQuestionId());
                ll.setBackgroundColor(Color.parseColor("#B9F6CA"));
            }else{
                LinearLayout ll = findViewById(question6.getQuestionId());
                ll.setBackgroundColor(Color.parseColor("#FF8A80"));
                boolean[] temp = question6.getCorrectAnswerOptionNo();                               // correct answer boolean array is assigned to temp
                for(int i=0;i<4;i++){
                    switch (i){

                        case 0: if(temp[i]){                                                         // when true background of checkbox is set to green
                            CheckBox box6Option1 = findViewById(R.id.option_1_question_6_checkbox);  // as it is one of the correct answers
                            box6Option1.setBackgroundColor(Color.parseColor("#B9F6CA"));
                            break;
                        }

                        case 1: if(temp[i]){
                            CheckBox box6Option2 = findViewById(R.id.option_2_question_6_checkbox);
                            box6Option2.setBackgroundColor(Color.parseColor("#B9F6CA"));
                            break;
                        }

                        case 2: if(temp[i]){
                            CheckBox box6Option3 = findViewById(R.id.option_3_question_6_checkbox);
                            box6Option3.setBackgroundColor(Color.parseColor("#B9F6CA"));
                            break;
                        }

                        case 3: if(temp[i]){
                            CheckBox box6Option5 = findViewById(R.id.option_5_question_6_checkbox);
                            box6Option5.setBackgroundColor(Color.parseColor("#B9F6CA"));
                            break;
                        }
                    }

                }

            }
        }

        if(question7.isAttended()){
            noOfQuestionsAttended++;
            if(question7.isQuestionResult()){
                noOfQuestionsCorrect++;
                LinearLayout ll = (LinearLayout) findViewById(question7.getQuestionId()).getParent();
                ll.setBackgroundColor(Color.parseColor("#B9F6CA"));                            // changing background color to green when answer is right
            }else{
                LinearLayout ll = (LinearLayout) findViewById(question7.getQuestionId()).getParent();
                ll.setBackgroundColor(Color.parseColor("#FF8A80"));                            // changing background color to red when answer is wrong
                ((EditText)findViewById(question7.getQuestionId())).setText("Answer is Radius");
            }
        }

        Toast toast = Toast.makeText(this,"Questions Attended: "+ noOfQuestionsAttended +"\nCorrect Answer: "+noOfQuestionsCorrect, Toast.LENGTH_SHORT);
        toast.show();
        submitFlag = false;
        ((Button)findViewById(R.id.submit_button)).setText(R.string.Try_again);               //as submit button is pressed text in the button is changed to TRY AGAIN
    }



    /**
     * function to reset the app by setting all variables to the initial state
     * questionId and correctAnswerOptionNo in question objects are not reset
     * */
    public void tryAgain(){

        noOfQuestionsAttended = 0;
        noOfQuestionsCorrect = 0;

        submitFlag = true;
        minimumOneQuestionFlag = false;

        /* Clearing all Radio Button selections*/

        ((RadioGroup)findViewById(question1.getQuestionId())).clearCheck();
        ((RadioGroup)findViewById(question2.getQuestionId())).clearCheck();
        ((RadioGroup)findViewById(question3.getQuestionId())).clearCheck();
        ((RadioGroup)findViewById(question4.getQuestionId())).clearCheck();
        ((RadioGroup)findViewById(question5.getQuestionId())).clearCheck();

        /* Clearing all check box selections*/

        ((CheckBox)findViewById(R.id.option_1_question_6_checkbox)).setChecked(false);
        ((CheckBox)findViewById(R.id.option_2_question_6_checkbox)).setChecked(false);
        ((CheckBox)findViewById(R.id.option_3_question_6_checkbox)).setChecked(false);
        ((CheckBox)findViewById(R.id.option_5_question_6_checkbox)).setChecked(false);

        ((EditText)findViewById(question7.getQuestionId())).getText().clear();               //clearing edit text

        ((Button)findViewById(R.id.submit_button)).setText(R.string.Submit);                        //changing the text in button back to submit


        /*
        * if question is attended by the user then its background is changed back
        */
        if(question1.isAttended()) {
            ((LinearLayout) findViewById(question1.getQuestionId()).getParent()).setBackgroundColor(Color.TRANSPARENT);
            if (!question1.isQuestionResult())
                findViewById(question1.getCorrectAnswerOptionNo()).setBackgroundColor(Color.TRANSPARENT);
        }

        if(question2.isAttended()) {
            ((LinearLayout) findViewById(question2.getQuestionId()).getParent()).setBackgroundColor(Color.TRANSPARENT);
            if (!question2.isQuestionResult())
                findViewById(question2.getCorrectAnswerOptionNo()).setBackgroundColor(Color.TRANSPARENT);
        }

        if(question3.isAttended()) {
            ((LinearLayout) findViewById(question3.getQuestionId()).getParent()).setBackgroundColor(Color.TRANSPARENT);
            if (!question3.isQuestionResult())
                findViewById(question3.getCorrectAnswerOptionNo()).setBackgroundColor(Color.TRANSPARENT);
        }

        if(question4.isAttended()) {
            ((LinearLayout) findViewById(question4.getQuestionId()).getParent()).setBackgroundColor(Color.TRANSPARENT);
            if (!question4.isQuestionResult())
                findViewById(question4.getCorrectAnswerOptionNo()).setBackgroundColor(Color.TRANSPARENT);
        }

        if(question5.isAttended()) {
            ((LinearLayout) findViewById(question5.getQuestionId()).getParent()).setBackgroundColor(Color.TRANSPARENT);
            if (!question5.isQuestionResult())
                findViewById(question5.getCorrectAnswerOptionNo()).setBackgroundColor(Color.TRANSPARENT);
        }

        if(question6.isAttended()) {
            ((LinearLayout) findViewById(question6.getQuestionId())).setBackgroundColor(Color.TRANSPARENT);
            findViewById(R.id.option_1_question_6_checkbox).setBackgroundColor(Color.TRANSPARENT);
            findViewById(R.id.option_2_question_6_checkbox).setBackgroundColor(Color.TRANSPARENT);
            findViewById(R.id.option_3_question_6_checkbox).setBackgroundColor(Color.TRANSPARENT);
            findViewById(R.id.option_5_question_6_checkbox).setBackgroundColor(Color.TRANSPARENT);
        }

        if(question7.isAttended()) {
            ((LinearLayout) findViewById(question7.getQuestionId()).getParent()).setBackgroundColor(Color.TRANSPARENT);
        }

        /* Resetting question objects*/

        question1.resetQuestion();
        question2.resetQuestion();
        question3.resetQuestion();
        question4.resetQuestion();
        question5.resetQuestion();

        question6.resetQuestion();

        question7.resetQuestion();
    }
}
