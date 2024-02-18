package com.example.triviaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.AppControllerr;
import com.example.triviaapp.data.AnswerListAsyncResponse;
import com.example.triviaapp.data.Repository;
import com.example.triviaapp.databinding.ActivityMainBinding;
import com.example.triviaapp.model.Question;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        questionList = new Repository().getQuestions(questionArrayList -> {
                     binding.questionTextView.setText(questionArrayList.get(currentQuestionIndex)
                            .getAnswer());
                    updateQuestionNo(questionArrayList);
                }
        );
        binding.nextButton.setOnClickListener(v -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
            updateQuestion();
        });
        binding.trueButton.setOnClickListener(v -> {
            checkAnswer(true);
            updateQuestion();
        });
        binding.falseButton.setOnClickListener(v -> {
            checkAnswer(false);
            updateQuestion();
        });
        binding.previousButton.setOnClickListener(v -> {
            if(currentQuestionIndex>0){
                currentQuestionIndex = (currentQuestionIndex - 1) % questionList.size();
                updateQuestion();
            }
        });

    }

    private void checkAnswer(boolean b) {
        boolean originalAns = questionList.get(currentQuestionIndex).isAnswerTrue();
        int snackMessageId = 0;
        if(b == originalAns){
            snackMessageId = R.string.correct_answer;
            fadeAnimation();
        }else{
            snackMessageId = R.string.incorrect_answer;
            shakeAnimation();
        }
        Snackbar.make(binding.cardView,snackMessageId, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    private void updateQuestionNo(ArrayList<Question> questionArrayList) {
        binding.questionNoView.setText(String.format(getString(R.string.text_formatted), currentQuestionIndex, questionArrayList.size()));
    }

    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        binding.questionTextView.setText(question);
        updateQuestionNo((ArrayList<Question>) questionList);
    }

    private void fadeAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(400);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        binding.cardView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void shakeAnimation(){
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake_animation);
        binding.cardView.setAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}