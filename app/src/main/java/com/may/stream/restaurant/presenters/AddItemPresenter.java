package com.may.stream.restaurant.presenters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.model.TblDiscount;
import com.may.stream.restaurant.model.TblProductTypes;
import com.may.stream.restaurant.model.TblProducts;
import com.may.stream.restaurant.model.TblTables;
import com.may.stream.restaurant.service.ApiService;
import com.may.stream.restaurant.until.ApplicationController;
import com.may.stream.restaurant.until.DialogController;
import com.may.stream.restaurant.until.NetworkUtils;
import com.may.stream.restaurant.until.TaskController;
import com.may.stream.restaurant.view.activity.AddItemActivity;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by may on 1/11/2018.
 */

public class AddItemPresenter {
    private ProgressDialog dialog;
    private DialogController dialogController;
    private TaskController taskController;
    private ApiService mForum;
    private static AddItemActivity mView;
    private static Activity activity;

    public AddItemPresenter(AddItemActivity view,ApiService forum){
        mForum = forum;
        mView = view;
        dialogController = new DialogController();
        taskController = new TaskController();
        activity = ApplicationController.getAppActivity();
    }

    public void loadCreateProduct(){
        try {
            if(!NetworkUtils.isConnected(activity)){
                dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),activity.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(activity, activity.getString(R.string.txtWait), activity.getString(R.string.txt_loading));
                mForum.getApi()
                        .createProduct(mView.tblProducts)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TblProducts>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("loadCreateProduct Error", e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(TblProducts products) {
                                if(products.getSuccess().equalsIgnoreCase("1")){
                                    mView.tblProducts.setProduct_id(products.getProduct_id());
                                    taskController.createProduct(mView.tblProducts);
                                    mView.updateProduct();
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtSuccess),products.getMessage());
                                }else if(products.getSuccess().equalsIgnoreCase("0")){
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),products.getMessage());
                                }
                                dialog.dismiss();
                            }
                        });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadCreateProductType(){
        try {
            if(!NetworkUtils.isConnected(activity)){
                dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),activity.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(activity, activity.getString(R.string.txtWait), activity.getString(R.string.txt_loading));
                mForum.getApi()
                        .createProductType(mView.tblProductTypes)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TblProductTypes>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @SuppressLint("LongLogTag")
                            @Override
                            public void onError(Throwable e) {
                                Log.e("loadCreateProductType Error", e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(TblProductTypes productTypes) {
                                //updateLogin(member);
                                if(productTypes.getSuccess().equalsIgnoreCase("1")){
                                    mView.tblProductTypes.setProduct_type_id(productTypes.getProduct_type_id());
                                    taskController.createProductType(mView.tblProductTypes);
                                    mView.updateProductType();
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtSuccess),productTypes.getMessage());
                                }else if(productTypes.getSuccess().equalsIgnoreCase("0")){
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),productTypes.getMessage());
                                }
                                dialog.dismiss();
                            }
                        });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadCreateDiscount(){
        try {
            if(!NetworkUtils.isConnected(activity)){
                dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),activity.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(activity, activity.getString(R.string.txtWait), activity.getString(R.string.txt_loading));
                mForum.getApi()
                        .createDiscount(mView.tblDiscount)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TblDiscount>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @SuppressLint("LongLogTag")
                            @Override
                            public void onError(Throwable e) {
                                Log.e("loadCreateDiscount Error", e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(TblDiscount discount) {
                                if(discount.getSuccess().equalsIgnoreCase("1")){
                                    mView.tblDiscount.setDiscount_id(discount.getDiscount_id());
                                    taskController.createDiscount(mView.tblDiscount);
                                    mView.updateDiscount();
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtSuccess),discount.getMessage());
                                }else if(discount.getSuccess().equalsIgnoreCase("0")){
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),discount.getMessage());
                                }
                                dialog.dismiss();
                            }
                        });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadCreateTables(){
        try {
            if(!NetworkUtils.isConnected(activity)){
                dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),activity.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(activity, activity.getString(R.string.txtWait), activity.getString(R.string.txt_loading));
                mForum.getApi()
                        .createTables(mView.tablesList)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<TblTables>>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @SuppressLint("LongLogTag")
                            @Override
                            public void onError(Throwable e) {
                                Log.e("loadCreateDiscount Error", e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(List<TblTables> tables) {
                                if(tables.size()>0){
                                    mView.tablesList = tables;
                                    taskController.createTable(mView.tablesList);
                                    mView.updateTable();
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtSuccess),"OK");
                                }
                                dialog.dismiss();
                            }
                        });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateProductType(){
        try {
            if(!NetworkUtils.isConnected(activity)){
                dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),activity.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(activity, activity.getString(R.string.txtWait), activity.getString(R.string.txt_loading));
                mForum.getApi()
                        .updateProductType(mView.tblProductTypes)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TblProductTypes>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @SuppressLint("LongLogTag")
                            @Override
                            public void onError(Throwable e) {
                                Log.e("updateProductType Error", e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(TblProductTypes productTypes) {
                                if(productTypes.getSuccess().equalsIgnoreCase("1")){
                                    taskController.updateProductType(mView.tblProductTypes);
                                    mView.updateProductType();
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtSuccess),productTypes.getMessage());
                                }else if(productTypes.getSuccess().equalsIgnoreCase("0")){
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),productTypes.getMessage());
                                }
                                dialog.dismiss();
                            }
                        });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateProduct(){
        try {
            if(!NetworkUtils.isConnected(activity)){
                dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),activity.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(activity, activity.getString(R.string.txtWait), activity.getString(R.string.txt_loading));
                mForum.getApi()
                        .updateProduct(mView.tblProducts)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TblProducts>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @SuppressLint("LongLogTag")
                            @Override
                            public void onError(Throwable e) {
                                Log.e("updateProduct Error", e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(TblProducts products) {
                                if(products.getSuccess().equalsIgnoreCase("1")){
                                    taskController.updateProduct(mView.tblProducts);
                                    mView.updateProduct();
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtSuccess),products.getMessage());
                                }else if(products.getSuccess().equalsIgnoreCase("0")){
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),products.getMessage());
                                }
                                dialog.dismiss();
                            }
                        });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateDiscount(){
        try {
            if(!NetworkUtils.isConnected(activity)){
                dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),activity.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(activity, activity.getString(R.string.txtWait), activity.getString(R.string.txt_loading));
                mForum.getApi()
                        .updateDiscount(mView.tblDiscount)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TblDiscount>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @SuppressLint("LongLogTag")
                            @Override
                            public void onError(Throwable e) {
                                Log.e("updateDiscount Error", e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(TblDiscount discount) {
                                if(discount.getSuccess().equalsIgnoreCase("1")){
                                    taskController.updateDiscount(mView.tblDiscount);
                                    mView.updateDiscount();
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtSuccess),discount.getMessage());
                                }else if(discount.getSuccess().equalsIgnoreCase("0")){
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),discount.getMessage());
                                }
                                dialog.dismiss();
                            }
                        });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteProductType(){
        try {
            if(!NetworkUtils.isConnected(activity)){
                dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),activity.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(activity, activity.getString(R.string.txtWait), activity.getString(R.string.txt_loading));
                mForum.getApi()
                        .deleteProductType(mView.tblProductTypes)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TblProductTypes>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @SuppressLint("LongLogTag")
                            @Override
                            public void onError(Throwable e) {
                                Log.e("deleteProductType Error", e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(TblProductTypes productTypes) {
                                if(productTypes.getSuccess().equalsIgnoreCase("1")){
                                    taskController.deleteProductType(mView.tblProductTypes);
                                    mView.flagDel = false;
                                    mView.updateProductType();
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtSuccess),productTypes.getMessage());
                                }else if(productTypes.getSuccess().equalsIgnoreCase("0")){
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),productTypes.getMessage());
                                }
                                dialog.dismiss();
                            }
                        });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteProduct(){
        try {
            if(!NetworkUtils.isConnected(activity)){
                dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),activity.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(activity, activity.getString(R.string.txtWait), activity.getString(R.string.txt_loading));
                mForum.getApi()
                        .deleteProduct(mView.tblProducts)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TblProducts>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @SuppressLint("LongLogTag")
                            @Override
                            public void onError(Throwable e) {
                                Log.e("deleteProduct Error", e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(TblProducts product) {
                                if(product.getSuccess().equalsIgnoreCase("1")){
                                    taskController.deleteProduct(mView.tblProducts);
                                    mView.flagDel = false;
                                    mView.updateProduct();
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtSuccess),product.getMessage());
                                }else if(product.getSuccess().equalsIgnoreCase("0")){
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),product.getMessage());
                                }
                                dialog.dismiss();
                            }
                        });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteDiscount(){
        try {
            if(!NetworkUtils.isConnected(activity)){
                dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),activity.getString(R.string.txt_internet_is_not));
            }else {
                dialog = ProgressDialog.show(activity, activity.getString(R.string.txtWait), activity.getString(R.string.txt_loading));
                mForum.getApi()
                        .deleteDiscount(mView.tblDiscount)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TblDiscount>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @SuppressLint("LongLogTag")
                            @Override
                            public void onError(Throwable e) {
                                Log.e("deleteDiscount Error", e.getMessage());
                                dialog.dismiss();
                            }

                            @Override
                            public void onNext(TblDiscount discount) {
                                if(discount.getSuccess().equalsIgnoreCase("1")){
                                    taskController.deleteDiscount(mView.tblDiscount);
                                    mView.flagDel = false;
                                    mView.updateDiscount();
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtSuccess),discount.getMessage());
                                }else if(discount.getSuccess().equalsIgnoreCase("0")){
                                    dialogController.dialogNolmal(activity,activity.getString(R.string.txtWarning),discount.getMessage());
                                }
                                dialog.dismiss();
                            }
                        });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
