package com.bignerdranch.android.geoquiz;

public class Question {

private int mTextResId;
private boolean mAnswerTrue;
private int mQuestionNum;

Question(int textResId, boolean answerTrue, int questionNum) {

    mTextResId = textResId;
    mAnswerTrue = answerTrue;
    mQuestionNum = questionNum;
}

    int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public int getQuestionNum() {
        return mQuestionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.mQuestionNum = questionNum;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
