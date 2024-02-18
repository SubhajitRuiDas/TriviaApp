package com.example.triviaapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.AppControllerr;
import com.example.triviaapp.model.Question;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    ArrayList<Question> questionArrayList = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    public List<Question> getQuestions(final AnswerListAsyncResponse callBack){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    Question question = new Question(response.getJSONArray(i).getBoolean(1),response.getJSONArray(i).get(0).toString());
                    // Add questions to ArrayList
                    questionArrayList.add(question);

                    Log.d("TAG","getQuestions "+ questionArrayList);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            if(callBack != null) callBack.processFinished(questionArrayList);
        }, error -> {

        });

        AppControllerr.getInstance().addToRequestQueue(jsonArrayRequest);
        return questionArrayList;
    }
}
