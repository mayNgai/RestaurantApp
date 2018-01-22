package com.may.stream.restaurant.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.helper.GlobalVar;
import com.may.stream.restaurant.model.TblDiscount;
import com.may.stream.restaurant.model.TblProductTypes;
import com.may.stream.restaurant.model.TblProducts;
import com.may.stream.restaurant.model.TblTables;
import com.may.stream.restaurant.view.activity.AddItemActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may on 1/10/2018.
 */

public class AddItemFragmentSecond extends Fragment implements View.OnClickListener{
    private View rootView;
    private LinearLayout linear_product,linear_product_type,linear_discount,linear_table,linear_staff;
    private EditText edt_product_name,edt_price,edt_product_type_name,edt_discount_name,edt_percent,edt_table;
    private Spinner spinner;
    private FloatingActionButton fab_save,fab,fab_del,fab_update;
    private static Toolbar toolbar;
    private AddItemActivity mView;
    private List<TblProductTypes> productTypes;
    private List<String> arrListSpinner;
    private String str_product_name,str_price,str_product_type_name,str_discount_name,str_percent,str_count_table;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    private Boolean isFabOpen = false;
    private int pos = 0 ;
    public AddItemFragmentSecond newInstance(){
        AddItemFragmentSecond fragment = new AddItemFragmentSecond();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_item_second, container, false);
        mView = new AddItemActivity();
        mView.flagDel = false;
        mView.flagFirst = false;
        initInstance();
        initListener();
        setVisibleLinear();
        setSelectSpinner();
        setHasOptionsMenu(true);
        return rootView;
    }
    private void initInstance(){
        try {
            fab_save = (FloatingActionButton) rootView.findViewById(R.id.fab_save);
            fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
            fab_del = (FloatingActionButton) rootView.findViewById(R.id.fab_del);
            fab_update = (FloatingActionButton) rootView.findViewById(R.id.fab_update);
            linear_product = (LinearLayout) rootView.findViewById(R.id.linear_product);
            linear_product_type = (LinearLayout) rootView.findViewById(R.id.linear_product_type);
            linear_discount = (LinearLayout) rootView.findViewById(R.id.linear_discount);
            linear_table = (LinearLayout) rootView.findViewById(R.id.linear_table);
            linear_staff = (LinearLayout) rootView.findViewById(R.id.linear_staff);
            spinner = (Spinner) rootView.findViewById(R.id.spinner);
            edt_product_name = (EditText)rootView.findViewById(R.id.edt_product_name);
            edt_price = (EditText)rootView.findViewById(R.id.edt_price);
            edt_product_type_name = (EditText)rootView.findViewById(R.id.edt_product_type_name);
            edt_discount_name = (EditText)rootView.findViewById(R.id.edt_discount_name);
            edt_percent = (EditText)rootView.findViewById(R.id.edt_percent);
            edt_table = (EditText)rootView.findViewById(R.id.edt_table);
            fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
            fab_close = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fab_close);
            rotate_forward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_forward);
            rotate_backward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_backward);
            toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            if(mView.flagEdit)
                toolbar.setTitle("แก้ไข" + mView.tblStore.getStore_name());
            else
                toolbar.setTitle("เพิ่ม" + mView.tblStore.getStore_name());
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mView.flagFirst = true;
                    AddItemFragmentFirst.spinner.setEnabled(true);
                    getActivity().onBackPressed();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initListener(){
        try {
            fab_save.setOnClickListener(this);
            fab.setOnClickListener(this);
            fab_del.setOnClickListener(this);
            fab_update.setOnClickListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressLint("ResourceAsColor")
    private void setVisibleLinear(){
        try {
            linear_product.setVisibility(View.GONE);
            linear_product_type.setVisibility(View.GONE);
            linear_discount.setVisibility(View.GONE);
            linear_table.setVisibility(View.GONE);
            linear_staff.setVisibility(View.GONE);
            if(mView.tblStore.getStore_id().equalsIgnoreCase("1")) {
                linear_product.setVisibility(View.VISIBLE);
                productTypes = GlobalVar.getProductTypes();
                addDataSpinner();
                if(mView.flagEdit){
                    edt_product_name.setText(mView.tblProducts.getProduct_name());
                    edt_price.setText(mView.tblProducts.getPrice());
                }
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("2")) {
                linear_product_type.setVisibility(View.VISIBLE);
                if(mView.flagEdit){
                    edt_product_type_name.setText(mView.tblProductTypes.getProduct_type_name());
                }
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("3")){
                linear_discount.setVisibility(View.VISIBLE);
                if(mView.flagEdit){
                    edt_discount_name.setText(mView.tblDiscount.getDiscount_name());
                    edt_percent.setText(mView.tblDiscount.getPercent());
                }
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("4")){
                linear_table.setVisibility(View.VISIBLE);
                edt_table.setText(String.valueOf(mView.tablesList.size()));

            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("5")){
                linear_staff.setVisibility(View.VISIBLE);
            }

            if(mView.flagEdit){
                fab.setVisibility(View.VISIBLE);
                fab_save.setVisibility(View.GONE);
            }else {
                fab.setVisibility(View.GONE);
                fab_save.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addDataSpinner(){
        try {
            arrListSpinner = new ArrayList<String>();
            if(mView.flagEdit){
                if(productTypes.size()>0){
                    List<String> list = new ArrayList<String>();
                    for(TblProductTypes t : productTypes){
                        if(mView.tblProducts.getProduct_type_id().equalsIgnoreCase(t.getProduct_type_id())){
                            arrListSpinner.add(t.getProduct_type_name());
                        }else {
                            list.add(t.getProduct_type_name());
                        }
                    }
                    arrListSpinner.addAll(list);
                }
            }else {
                if(productTypes.size()>0){
                    for(TblProductTypes t : productTypes){
                        arrListSpinner.add(t.getProduct_type_name());
                    }
                }
            }
            setSpinner();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setSelectSpinner(){
        try {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String str_name = String.valueOf(spinner.getSelectedItem());
                    for(TblProductTypes t : productTypes){
                        if(str_name.equalsIgnoreCase(t.getProduct_type_name())){
                            pos = Integer.parseInt(t.getProduct_type_id());
                            break;
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setSpinner(){
        try {
            ArrayAdapter<String> arrAd = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_dropdown_item, arrListSpinner);
            spinner.setAdapter(arrAd);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setValidate() {
        try {
            boolean cancel = false;
            View focusView = null;
            if(mView.tblStore.getStore_id().equalsIgnoreCase("1")) {
                str_product_name = edt_product_name.getText().toString();
                str_price = edt_price.getText().toString();
                if(!GlobalVar.isTextValid(str_product_name)){
                    edt_product_name.setError(getString(R.string.error_invalid_product_name));
                    focusView = edt_product_name;
                    cancel = true;
                }else if(!GlobalVar.isTextValid(str_price)){
                    edt_price.setError(getString(R.string.error_invalid_price));
                    focusView = edt_price;
                    cancel = true;
                }
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("2")){
                str_product_type_name = edt_product_type_name.getText().toString();
                if(!GlobalVar.isTextValid(str_product_type_name)){
                    edt_product_type_name.setError(getString(R.string.error_invalid_product_type_name));
                    focusView = edt_product_type_name;
                    cancel = true;
                }
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("3")){
                str_discount_name = edt_discount_name.getText().toString();
                str_percent = edt_percent.getText().toString();
                if(!GlobalVar.isTextValid(str_discount_name)){
                    edt_discount_name.setError(getString(R.string.error_invalid_discount_name));
                    focusView = edt_discount_name;
                    cancel = true;
                }else if(!GlobalVar.isTextValid(str_percent)){
                    edt_percent.setError(getString(R.string.error_invalid_percent));
                    focusView = edt_percent;
                    cancel = true;

                }
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("4")){
                str_count_table = edt_table.getText().toString();
                if(!GlobalVar.isTextValid(str_count_table)&&Integer.parseInt(str_count_table)>0){
                    edt_table.setError(getString(R.string.error_invalid_count_table));
                    focusView = edt_table;
                    cancel = true;
                }
            }

            if(cancel) {
                focusView.requestFocus();
            }else {
                setSendData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void setSendData(){
        try {
            if(mView.tblStore.getStore_id().equalsIgnoreCase("1")){
                if(!mView.flagEdit)
                    mView.tblProducts = new TblProducts();
                mView.tblProducts.setCompany_id(Integer.parseInt(mView.companyList.get(0).getCompany_id()));
                mView.tblProducts.setProduct_name(str_product_name);
                mView.tblProducts.setProduct_type_id(String.valueOf(pos));
                mView.tblProducts.setPrice(str_price);
                mView.callService(1);
                edt_product_name.setText("");
                edt_price.setText("");
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("2")){
                if(!mView.flagEdit)
                    mView.tblProductTypes = new TblProductTypes();
                mView.tblProductTypes.setCompany_id(Integer.parseInt(mView.companyList.get(0).getCompany_id()));
                mView.tblProductTypes.setProduct_type_name(str_product_type_name);
                mView.callService(2);
                edt_product_type_name.setText("");
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("3")){
                if(!mView.flagEdit)
                    mView.tblDiscount = new TblDiscount();
                mView.tblDiscount.setCompany_id(Integer.parseInt(mView.companyList.get(0).getCompany_id()));
                mView.tblDiscount.setDiscount_name(str_discount_name);
                mView.tblDiscount.setPercent(str_percent);
                mView.callService(3);
                edt_discount_name.setText("");
                edt_percent.setText("");
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("4")){
                if(!mView.flagEdit)
                    mView.tablesList = new ArrayList<TblTables>();
                for(int i = 0 ; i< Integer.parseInt(str_count_table); i++){
                    TblTables t = new TblTables();
                    t.setCompany_id(Integer.parseInt(mView.companyList.get(0).getCompany_id()));
                    t.setTable_name(String.valueOf(i+1));
                    mView.tablesList.add(t);
                }
                mView.callService(4);
                edt_table.setText("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void animateFAB(){
        if(isFabOpen){
            fab.startAnimation(rotate_backward);
            fab_del.startAnimation(fab_close);
            fab_update.startAnimation(fab_close);
            fab_del.setClickable(false);
            fab_update.setClickable(false);
            isFabOpen = false;
        } else {
            fab.startAnimation(rotate_forward);
            fab_del.startAnimation(fab_open);
            fab_update.startAnimation(fab_open);
            fab_del.setClickable(true);
            fab_update.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_save:
                setValidate();
                break;
            case R.id.fab:
                animateFAB();
                break;
            case R.id.fab_del:
                mView.flagDel = true;
                setSendData();
                break;
            case R.id.fab_update:
                setValidate();
                break;
        }
    }



}
