package com.example.triviaapp.model;

public class Question {
    private boolean answerTrue;
    private String answer;

    public Question() {
    }

    public Question(boolean answerTrue, String answer) {
        this.answerTrue = answerTrue;
        this.answer = answer;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "answerTrue=" + answerTrue +
                ", answer='" + answer + '\'' +
                '}';
    }
}
