package com.may.stream.restaurant.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.helper.GlobalVar;
import com.may.stream.restaurant.model.TblMember;
import com.may.stream.restaurant.presenters.LoginPresenter;
import com.may.stream.restaurant.service.ApiService;

/**
 * Created by may on 1/4/2018.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imv_authen;
    private Button btn_login,btn_register;
    private TextView txt_authen;
    private EditText edt_username,edt_password;
    private Intent extra;
    public String action = "",str_user_name,str_password;
    private ApiService mApiService;
    private LoginPresenter mLoginPresenter;
    public static TblMember member;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getIntentExtra();
        init();
        setAction();
        initListeners();
    }

    private void getIntentExtra(){
        try {
            extra = getIntent();
            if (extra != null) {
                if(extra.getStringExtra("action")!=null)
                    action = extra.getStringExtra("action");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init(){
        try {
            imv_authen = (ImageView)findViewById(R.id.imv_authen);
            btn_login = (Button)findViewById(R.id.btn_login);
            btn_register = (Button)findViewById(R.id.btn_register);
            txt_authen = (TextView)findViewById(R.id.txt_authen);
            edt_username = (EditText)findViewById(R.id.edt_username);
            edt_password = (EditText)findViewById(R.id.edt_password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initListeners(){
        try {
            btn_login.setOnClickListener(this);
            btn_register.setOnClickListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setAction(){
        txt_authen.setText(action);
        if(action.equalsIgnoreCase("admin")) {
            imv_authen.setImageResource(R.drawable.ic_menu_admin);
            btn_register.setVisibility(View.VISIBLE);
        }else if(action.equalsIgnoreCase("staff")){
            imv_authen.setImageResource(R.drawable.ic_menu_staff);
            btn_register.setVisibility(View.GONE);
        }else if(action.equalsIgnoreCase("cook")){
            imv_authen.setImageResource(R.drawable.ic_menu_cooking);
            btn_register.setVisibility(View.GONE);
        }else if(action.equalsIgnoreCase("customer")){
            imv_authen.setImageResource(R.drawable.ic_menu_customer);
            btn_register.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                validate();
                break;
            case R.id.btn_register:
                intentPage("register");
                break;
        }
    }

    public void intentPage(String name){
        try {
            Intent intent = null;
            if(name.equalsIgnoreCase("register")){
                intent = new Intent(this,RegisterActivity.class);
            }else {
                intent = new Intent(this,BaseActivity.class);
            }
            startActivity(intent);
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void validate(){
        try {
            boolean cancel = false;
            View focusView = null;
            str_user_name = edt_username.getText().toString().trim();
            str_password = edt_password.getText().toString().trim();
            if(!GlobalVar.isTextValid(str_user_name)){
                edt_username.setError(getString(R.string.error_invalid_user_name));
                focusView = edt_username;
                cancel = true;
            }else if(!GlobalVar.isTextValid(str_password)){
                edt_username.setError(getString(R.string.error_invalid_pass));
                focusView = edt_username;
                cancel = true;
            }

            if(cancel) {
                focusView.requestFocus();
            }else {
                member = new TblMember();
                member.setUser_name(str_user_name);
                member.setPassword(str_password);
                member.setAuthen(action.toLowerCase());
                callService();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void callService(){
        try {
            mApiService = new ApiService();
            mLoginPresenter = new LoginPresenter(this,mApiService);
            mLoginPresenter.loadLogin();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
