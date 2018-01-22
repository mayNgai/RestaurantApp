package com.may.stream.restaurant.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.may.stream.restaurant.R;

/**
 * Created by may on 1/4/2018.
 */

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_admin,btn_staff,btn_cook,btn_customer;
    private String action="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);
        init();
        initListeners();
    }

    private void init(){
        try {
            btn_admin = (Button)findViewById(R.id.btn_admin);
            btn_staff = (Button)findViewById(R.id.btn_staff);
            btn_cook = (Button)findViewById(R.id.btn_cook);
            btn_customer = (Button)findViewById(R.id.btn_customer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initListeners(){
        try {
            btn_admin.setOnClickListener(this);
            btn_staff.setOnClickListener(this);
            btn_cook.setOnClickListener(this);
            btn_customer.setOnClickListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_admin:
                action = "Admin";
                intentPage(action);
                break;
            case R.id.btn_staff:
                action = "Staff";
                intentPage(action);
                break;
            case R.id.btn_cook:
                action = "Cook";
                intentPage(action);
                break;
            case R.id.btn_customer:
                action = "Customer";
                intentPage(action);
                break;
        }
    }

    private void intentPage(String name){
        try {
            Intent intent = null;
            if(name.equalsIgnoreCase("Customer")) {
                intent = new Intent(this, BaseActivity.class);
            }else {
                intent = new Intent(this,LoginActivity.class);
                intent.putExtra("action",action);
            }
            startActivity(intent);
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
