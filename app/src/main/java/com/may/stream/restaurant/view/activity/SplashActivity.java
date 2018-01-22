package com.may.stream.restaurant.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.helper.DatabaseHelper;
import com.may.stream.restaurant.model.TblMember;
import com.may.stream.restaurant.until.ApplicationController;
import com.may.stream.restaurant.until.TaskController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may on 12/29/2017.
 */

public class SplashActivity extends AppCompatActivity{

    private Handler handler;
    private TextView textView;
    private long startTime, currentTime, finishedTime = 0L;
    private int duration = 10000 / 4;
    private int endTime = 0;
    private Activity _activity;
    private DatabaseHelper databaseHelper = null;
    private TaskController taskController;
    private List<TblMember> members;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(R.string.app_name);
        _activity = SplashActivity.this;
        taskController = new TaskController();
        ApplicationController.setAppActivity(_activity);
        databaseHelper = DatabaseHelper.getHelper(_activity);

        handler = new Handler();
        startTime = Long.valueOf(System.currentTimeMillis());
        currentTime = startTime;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                currentTime = Long.valueOf(System.currentTimeMillis());
                finishedTime = Long.valueOf(currentTime)
                        - Long.valueOf(startTime);

                if (finishedTime >= duration + 30) {
                    members = new ArrayList<TblMember>();
                    members = taskController.getAllMember();
                    if(members.size()>0){
                        Intent intent = new Intent(SplashActivity.this, BaseActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    endTime = (int) (finishedTime / 250);
                    Spannable spannableString = new SpannableString(textView
                            .getText());
                    spannableString.setSpan(new ForegroundColorSpan(
                                    Color.BLACK), 0, endTime,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    textView.setText(spannableString);
                    handler.postDelayed(this, 10);
                }
            }
        }, 10);
    }
}
