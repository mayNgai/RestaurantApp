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
import com.may.stream.restaurant.model.TblCompany;
import com.may.stream.restaurant.model.TblMember;
import com.may.stream.restaurant.presenters.RegisterPresenter;
import com.may.stream.restaurant.service.ApiService;

/**
 * Created by may on 1/9/2018.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_login,btn_register,btn_submit;
    private EditText edt_company,edt_first_name,edt_last_name,edt_tel,edt_email,edt_username,edt_password,edt_repeat_password;
    private TextView txt_company_dup;
    private ImageView imv_edit;
    private String str_company,str_first_name,str_last_name,str_tel,str_email,str_username,str_password,str_repeat_password;
    private ApiService mApiService;
    private RegisterPresenter mRegisterPresenter;
    public static TblCompany tblCompany;
    public TblMember tblMember;
    private boolean flagEdit = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        initListeners();
    }

    private void init(){
        try {
            btn_login = (Button)findViewById(R.id.btn_login);
            btn_register = (Button)findViewById(R.id.btn_register);
            btn_submit = (Button)findViewById(R.id.btn_submit);
            edt_company = (EditText)findViewById(R.id.edt_company);
            edt_first_name = (EditText)findViewById(R.id.edt_first_name);
            edt_last_name = (EditText)findViewById(R.id.edt_last_name);
            edt_tel = (EditText)findViewById(R.id.edt_tel);
            edt_email = (EditText)findViewById(R.id.edt_email);
            edt_username = (EditText)findViewById(R.id.edt_username);
            edt_password = (EditText)findViewById(R.id.edt_password);
            edt_repeat_password = (EditText)findViewById(R.id.edt_repeat_password);
            txt_company_dup = (TextView)findViewById(R.id.txt_company_dup);
            imv_edit = (ImageView)findViewById(R.id.imv_edit);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initListeners(){
        try {
            btn_login.setOnClickListener(this);
            btn_register.setOnClickListener(this);
            btn_submit.setOnClickListener(this);
            imv_edit.setOnClickListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void enableEditText(boolean flagCompany){
        try {
            edt_company.setEnabled(flagCompany);
            edt_first_name.setEnabled(!flagCompany);
            edt_last_name.setEnabled(!flagCompany);
            edt_tel.setEnabled(!flagCompany);
            edt_email.setEnabled(!flagCompany);
            edt_username.setEnabled(!flagCompany);
            edt_password.setEnabled(!flagCompany);
            edt_repeat_password.setEnabled(!flagCompany);
            btn_register.setEnabled(!flagCompany);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        intentPage("login");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                intentPage("login");
                break;
            case R.id.btn_register:
                validateRegister();
                break;
            case R.id.btn_submit:
                validateCompany();
                break;
            case R.id.imv_edit:
                flagEdit = true;
                enableEditText(true);
                btn_submit.setVisibility(View.VISIBLE);
                imv_edit.setVisibility(View.GONE);
                break;

        }
    }

    private void validateCompany(){
        try {
            boolean cancel = false;
            View focusView = null;
            str_company = edt_company.getText().toString();
            if(!GlobalVar.isTextValid(str_company)){
                edt_company.setError(getString(R.string.error_invalid_company_name));
                focusView = edt_company;
                cancel = true;
            }
            if(cancel) {
                focusView.requestFocus();
            }else {
                if(flagEdit){
                    if(str_company.equals(tblCompany.getCompany_name())){
                        flagEdit = false;
                        enableEditText(false);
                        btn_submit.setVisibility(View.GONE);
                        imv_edit.setVisibility(View.VISIBLE);
                        txt_company_dup.setVisibility(View.GONE);
                    }else {
                        tblCompany.setCompany_name(str_company);
                        callService("company");
                    }
                }else {
                    tblCompany = new TblCompany();
                    tblCompany.setCompany_name(str_company);
                    callService("company");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void validateRegister(){
        try {
            boolean cancel = false;
            View focusView = null;
            str_first_name = edt_first_name.getText().toString();
            str_last_name = edt_last_name.getText().toString();
            str_tel = edt_tel.getText().toString();
            str_email = edt_email.getText().toString();
            str_username = edt_username.getText().toString();
            str_password = edt_password.getText().toString();
            str_repeat_password = edt_repeat_password.getText().toString();
            if(!GlobalVar.isTextValid(str_first_name)){
                edt_first_name.setError(getString(R.string.error_invalid_first_name));
                focusView = edt_first_name;
                cancel = true;
            }else if(!GlobalVar.isTextValid(str_last_name)){
                edt_last_name.setError(getString(R.string.error_invalid_last_name));
                focusView = edt_last_name;
                cancel = true;
            }else if(!GlobalVar.isPhoneNumberValid(str_tel)){
                edt_tel.setError(getString(R.string.error_invalid_tel));
                focusView = edt_tel;
                cancel = true;
            }else if(!GlobalVar.isEmailValid(str_email)){
                edt_email.setError(getString(R.string.error_invalid_email));
                focusView = edt_email;
                cancel = true;
            }else if(!GlobalVar.isTextValid(str_username)){
                edt_username.setError(getString(R.string.error_invalid_user_name));
                focusView = edt_username;
                cancel = true;
            }else if(!GlobalVar.isPassWordValid(str_password)){
                edt_password.setError(getString(R.string.error_invalid_pass));
                focusView = edt_password;
                cancel = true;
            }else if(!GlobalVar.isConfirmPassWordValid(str_password,str_repeat_password)){
                edt_repeat_password.setError(getString(R.string.error_invalid_pass));
                focusView = edt_repeat_password;
                cancel = true;
            }
            if(cancel) {
                focusView.requestFocus();
            }else {
                tblMember = new TblMember();
                tblMember.setCompany_id(Integer.parseInt(tblCompany.getCompany_id()));
                tblMember.setFirst_name(str_first_name);
                tblMember.setLast_name(str_last_name);
                tblMember.setTel(str_tel);
                tblMember.setEmail(str_email);
                tblMember.setUser_name(str_username);
                tblMember.setPassword(str_password);
                tblMember.setAuthen("admin");
                callService("register");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void callService(String action){
        try {
            mApiService = new ApiService();
            mRegisterPresenter = new RegisterPresenter(this,mApiService);
            if(action.equalsIgnoreCase("company")){
                if(!flagEdit)
                    mRegisterPresenter.loadCreateCompany();
                else
                    mRegisterPresenter.updateCompany();
            }else {
                mRegisterPresenter.loadRegister();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateCompany(TblCompany company){
        try {
            if(company.getSuccess().equalsIgnoreCase("1")){
                flagEdit = false;
                enableEditText(false);
                btn_submit.setVisibility(View.GONE);
                imv_edit.setVisibility(View.VISIBLE);
                txt_company_dup.setVisibility(View.GONE);
                tblCompany.setCompany_id(company.getCompany_id());
            }else if(company.getSuccess().equalsIgnoreCase("0")){
                txt_company_dup.setVisibility(View.VISIBLE);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void intentPage(String name){
        try {
            Intent intent = null;
            if(name.equalsIgnoreCase("login")){
                intent = new Intent(this,LoginActivity.class);
                intent.putExtra("action","Admin");
            }
            startActivity(intent);
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
