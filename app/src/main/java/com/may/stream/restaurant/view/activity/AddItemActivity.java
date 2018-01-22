package com.may.stream.restaurant.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.model.TblDiscount;
import com.may.stream.restaurant.model.TblTables;
import com.may.stream.restaurant.view.fragment.AddItemFragmentFirst;
import com.may.stream.restaurant.helper.GlobalVar;
import com.may.stream.restaurant.model.TblCompany;
import com.may.stream.restaurant.model.TblProductTypes;
import com.may.stream.restaurant.model.TblProducts;
import com.may.stream.restaurant.model.TblStore;
import com.may.stream.restaurant.presenters.AddItemPresenter;
import com.may.stream.restaurant.service.ApiService;
import com.may.stream.restaurant.until.ApplicationController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may on 1/10/2018.
 */

public class AddItemActivity extends FragmentActivity {
    private static AddItemFragmentFirst fragmentFirst;
    private Intent extra;
    public static TblStore tblStore;
    private static ApiService mApiService;
    private static AddItemPresenter mAddItemPresenter;
    public static List<TblCompany> companyList;
    public static TblProductTypes tblProductTypes;
    public static TblProducts tblProducts;
    public static TblDiscount tblDiscount;
    public static List<TblTables> tablesList;
    public static boolean flagEdit = false;
    public static boolean flagDel = false;
    public static boolean flagFirst = true;
    public static Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        getIntentExtra();
        activity = AddItemActivity.this;
        ApplicationController.setAppActivity(activity);
        fragmentFirst = new AddItemFragmentFirst();
        companyList = new ArrayList<TblCompany>();
        tblProductTypes = new TblProductTypes();
        tblProducts = new TblProducts();
        tblDiscount = new TblDiscount();
        tablesList = new ArrayList<TblTables>();
        companyList = GlobalVar.getCompany();
        setFragmentFirst();
    }

    private void getIntentExtra() {
        try {
            extra = getIntent();
            if (extra != null) {
                if(extra.getSerializableExtra("list")!=null)
                    tblStore = (TblStore) extra.getSerializableExtra("list");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void callService(int pos){
        try {
            mApiService = new ApiService();
            mAddItemPresenter = new AddItemPresenter(this,mApiService);
            if(pos == 1){
                if(flagEdit){
                    if(flagDel)
                        mAddItemPresenter.deleteProduct();
                    else
                        mAddItemPresenter.updateProduct();
                }else {
                    mAddItemPresenter.loadCreateProduct();
                }
            }else if(pos == 2){
                if(flagEdit){
                    if(flagDel)
                        mAddItemPresenter.deleteProductType();
                    else
                        mAddItemPresenter.updateProductType();
                }else
                    mAddItemPresenter.loadCreateProductType();

            }else if(pos == 3){
                if(flagEdit){
                    if(flagDel)
                        mAddItemPresenter.deleteDiscount();
                    else
                        mAddItemPresenter.updateDiscount();
                }else
                    mAddItemPresenter.loadCreateDiscount();
            }else if(pos == 4){
                mAddItemPresenter.loadCreateTables();
            }else if(pos == 5){
                if(flagEdit){

                }else {

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateProductType(){
        try {
            fragmentFirst.updateProductType();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateProduct(){
        try {
            fragmentFirst.updateProduct();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateDiscount(){
        try {
            fragmentFirst.updateDiscount();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateTable(){
        try {
            fragmentFirst.updateTable();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setFragmentFirst(){
        try {
            getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new AddItemFragmentFirst()).commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
