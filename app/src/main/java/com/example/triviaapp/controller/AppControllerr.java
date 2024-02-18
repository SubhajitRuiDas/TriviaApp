package com.example.triviaapp.controller;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppControllerr extends Application {
    private static AppControllerr instance;
    private RequestQueue requestQueue;

    public static synchronized AppControllerr getInstance(){
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
