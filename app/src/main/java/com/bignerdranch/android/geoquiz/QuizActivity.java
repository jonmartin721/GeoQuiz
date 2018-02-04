package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    //keys
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    //global variables
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true, 0),
            new Question(R.string.question_oceans, true, 1),
            new Question(R.string.question_mideast, false, 2),
            new Question(R.string.question_africa, false, 3),
            new Question(R.string.question_americas, true, 4),
            new Question(R.string.question_asia, true, 5),
    };
    private int mCurrentIndex = 0;
    private int numCorrect = 0;
    private int[] answeredArray = {0,0,0,0,0,0};
    private int numAnswered = 0;
    private TextView mQuestionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button mTrueButton;
        Button mFalseButton;
        Button mNextButton;


        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        //checking for the value to hold the mCurrentIndex from the savedInstanceState bundle
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = findViewById(R.id.question_text_view);


        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });


        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();
    }

    @Override
    public  void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    //this updates the questions and buttons when the next button is pressed
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        Button mTrueButton = findViewById(R.id.true_button);
        Button mFalseButton = findViewById(R.id.false_button);

        //if the question has already been answered, disable the true and false buttons
        if (answeredArray[mCurrentIndex] == 1) {
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(false);
        } else {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        numAnswered ++;

        //if the user has answered the question, disable the buttons
        Button mTrueButton = findViewById(R.id.true_button);
        Button mFalseButton = findViewById(R.id.false_button);
        mTrueButton.setEnabled(false);
        mFalseButton.setEnabled(false);


        int messageResId;
        answeredArray[mCurrentIndex] = 1;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            numCorrect ++;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
        if (numAnswered == 6) {
            float percentCorrect = ((float) numCorrect / 6) * 100;
            String percentMessage = "You got " + numCorrect + " right, that's " + percentCorrect + "% correct!";
            Toast.makeText(this, percentMessage, Toast.LENGTH_LONG).show();
        }
    }




}
