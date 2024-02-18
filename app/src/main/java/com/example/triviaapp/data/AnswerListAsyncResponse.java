package com.example.triviaapp.data;

import com.example.triviaapp.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList); // means who ever will call the getQuestion method will verified parameterlized by interface which have a method which don't have any body but also have to recieve finished questionArrayList
}
