package com.may.stream.restaurant.presenters;

import android.app.ProgressDialog;
import android.util.Log;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.model.TblCompany;
import com.may.stream.restaurant.model.TblDataLogin;
import com.may.stream.restaurant.model.TblMember;
import com.may.stream.restaurant.service.ApiService;
import com.may.stream.restaurant.until.DialogController;
import com.may.stream.restaurant.until.NetworkUtils;
import com.may.stream.restaurant.until.TaskController;
import com.may.stream.restaurant.view.activity.LoginActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by may on 1/9/2018.
 */

public class LoginPresenter {
    private ProgressDialog dialog;
    private DialogController dialogController;
    private TaskController taskController;
    private ApiService mForum;
    private LoginActivity mView;

    public LoginPresenter(LoginActivity view, ApiService forum){
        mForum = forum;
        mView = view;
        dialogController = new DialogController();
        taskController = new TaskController();
    }

    public void loadLogin(){
        try {
            if(!NetworkUtils.isConnected(mView)){
                dialogController.dialogNolmal(mView,mView.getString(R.string.txtWarning),mView.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(mView, mView.getString(R.string.txtWait), mView.getString(R.string.txt_loading));
                mForum.getApi()
                        .getLogin(mView.member)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TblMember>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("userLoginCall Error", e.getMessage());
                                dialogController.dialogNolmal(mView,mView.getString(R.string.txtWarning),e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(TblMember member) {
                                updateLogin(member);
                                dialog.dismiss();
                            }
                        });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void updateLogin(TblMember member){
        try {
            if(member.getSuccess().equalsIgnoreCase("1")){
                taskController.createMember(member);
                TblCompany company = new TblCompany();
                company.setCompany_id(String.valueOf(member.getCompany_id()));
                taskController.createCompany(company);
                loadDataLogin(company,member.getAuthen());

            }else {
                dialogController.dialogNolmal(mView,mView.getString(R.string.txtWarning),member.getMessage());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadDataLogin(final TblCompany company,final String authen){
        try {
            if(!NetworkUtils.isConnected(mView)){
                dialogController.dialogNolmal(mView,mView.getString(R.string.txtWarning),mView.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(mView, mView.getString(R.string.txtWait), mView.getString(R.string.txt_loading));
                mForum.getApi()
                        .getDataLogin(company)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TblDataLogin>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("loadDataLogin Error", e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(TblDataLogin dataLogin) {
                                if(dataLogin.getStores().size()>0)
                                    taskController.createStore(dataLogin.getStores());
                                if(dataLogin.getProducts().size()>0)
                                    taskController.createProduct(dataLogin.getProducts());
                                if(dataLogin.getProductTypes().size()>0)
                                    taskController.createProductType(dataLogin.getProductTypes());
                                if(dataLogin.getDiscounts().size()>0)
                                    taskController.createDiscount(dataLogin.getDiscounts());
                                if(dataLogin.getTables().size()>0)
                                    taskController.createTable(dataLogin.getTables());

                                mView.intentPage(authen);
                                dialog.dismiss();
                            }
                        });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
