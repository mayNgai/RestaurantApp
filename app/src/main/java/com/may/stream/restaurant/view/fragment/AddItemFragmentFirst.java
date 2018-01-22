package com.may.stream.restaurant.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.adapter.AddItemAdapter;
import com.may.stream.restaurant.helper.GlobalVar;
import com.may.stream.restaurant.model.TblAddItems;
import com.may.stream.restaurant.model.TblDiscount;
import com.may.stream.restaurant.model.TblProductTypes;
import com.may.stream.restaurant.model.TblProducts;
import com.may.stream.restaurant.model.TblTables;
import com.may.stream.restaurant.until.RecyclerItemClickListener;
import com.may.stream.restaurant.until.TaskController;
import com.may.stream.restaurant.view.activity.AddItemActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may on 1/10/2018.
 */

public class AddItemFragmentFirst extends Fragment {
    private View rootView;
    private static LinearLayout linear_add,linear_spinner;
    public static Spinner spinner;
    private static RecyclerView recycler_view;
    private static Toolbar toolbar;
    private static AddItemActivity mView;
    private TaskController taskController;
    private List<TblAddItems> arrayList;
    private List<TblProductTypes> productTypes;
    private List<TblProducts> products;
    private List<TblDiscount> discounts;
    private List<TblTables> tables;
    private List<String> arrListSpinner;

    public AddItemFragmentFirst newInstance(){
        AddItemFragmentFirst fragment = new AddItemFragmentFirst();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_item_first, container, false);
        taskController = new TaskController();
        mView = new AddItemActivity();
        mView.flagFirst = true;
        initInstance();
        getData();
        return rootView;
    }

    private void initInstance(){
        try {
            linear_add = (LinearLayout) rootView.findViewById(R.id.linear_add);
            linear_spinner = (LinearLayout) rootView.findViewById(R.id.linear_spinner);
            spinner = (Spinner) rootView.findViewById(R.id.spinner);
            toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            toolbar.setTitle(mView.tblStore.getStore_name());
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });

            recycler_view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            recycler_view.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recycler_view.setLayoutManager(mLayoutManager);
            FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mView.flagEdit = false;
                    mView.flagFirst = false;
                    setSpinnerEnable(false);
                    addFragment();
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                }
            });

            recycler_view.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if(mView.flagFirst && !mView.tblStore.getStore_id().equalsIgnoreCase("4")){

                        if(mView.tblStore.getStore_id().equalsIgnoreCase("1")) {
                            mView.tblProducts = new TblProducts();
                            mView.tblProducts = products.get(position);
                        }else if(mView.tblStore.getStore_id().equalsIgnoreCase("2")){
                            mView.tblProductTypes = new TblProductTypes();
                            mView.tblProductTypes = productTypes.get(position);
                        }else if(mView.tblStore.getStore_id().equalsIgnoreCase("3")){
                            mView.tblDiscount = new TblDiscount();
                            mView.tblDiscount = discounts.get(position);
                        }
                        mView.flagEdit = true;
                        mView.flagFirst = false;
                        setSpinnerEnable(false);
                        addFragment();
                    }

                }
            }));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getData(){
        try {
            productTypes = GlobalVar.getProductTypes();
            products = GlobalVar.getProducts();
            discounts = GlobalVar.getDiscounts();
            tables = GlobalVar.getTables();
            if(mView.tblStore.getStore_id().equalsIgnoreCase("1")){
                arrayList = new ArrayList<TblAddItems>();
                for(TblProducts t : products){
                    TblAddItems items = new TblAddItems();
                    items.setName(t.getProduct_name());
                    items.setValue(t.getPrice() + " " + "บาท");
                    arrayList.add(items);
                }
                addDataSpinner();
                setSelectSpinner();
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("2")){
                arrayList = new ArrayList<TblAddItems>();
                for(TblProductTypes t : productTypes){
                    TblAddItems items = new TblAddItems();
                    items.setName(t.getProduct_type_name());
                    items.setValue("");
                    arrayList.add(items);
                }
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("3")){
                arrayList = new ArrayList<TblAddItems>();
                for(TblDiscount d : discounts){
                    TblAddItems items = new TblAddItems();
                    items.setName(d.getDiscount_name());
                    items.setValue(d.getPercent()+ " " + "%");
                    arrayList.add(items);
                }
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("4")){
                mView.tablesList = tables;
                arrayList = new ArrayList<TblAddItems>();
                for(TblTables t : tables){
                    TblAddItems items = new TblAddItems();
                    items.setName(t.getTable_name());
                    items.setValue("");
                    arrayList.add(items);
                }
            }else if(mView.tblStore.getStore_id().equalsIgnoreCase("5")){
                arrayList = new ArrayList<TblAddItems>();
            }
            setLinearAdd();
            showSpinner();
            setAdapter();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setSpinnerEnable(boolean flag){
        spinner.setEnabled(flag);
    }

    private void setLinearAdd(){
        try {
            if(arrayList.size()>0)
                linear_add.setVisibility(View.GONE);
            else
                linear_add.setVisibility(View.VISIBLE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showSpinner(){
        try {
            if(mView.tblStore.getStore_id().equalsIgnoreCase("1")) {
                if(arrListSpinner.size()>0){
                    linear_spinner.setVisibility(View.VISIBLE);
                    setSpinner();
                }else
                    linear_spinner.setVisibility(View.GONE);
            }else
                linear_spinner.setVisibility(View.GONE);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addDataSpinner(){
        try {
            arrListSpinner = new ArrayList<String>();
            arrListSpinner.add("หมวดหมู่ทั้งหมด");
            if(productTypes.size()>0){
                for(TblProductTypes t : productTypes){
                    arrListSpinner.add(t.getProduct_type_name());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setSelectSpinner(){
        try {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    productTypes = GlobalVar.getProductTypes();
                    products = GlobalVar.getProducts();
                    if(i!=0){
                        int pos = 0;
                        String str_name = String.valueOf(spinner.getSelectedItem());
                        for(TblProductTypes t : productTypes){
                            if(str_name.equalsIgnoreCase(t.getProduct_type_name())){
                                pos = Integer.parseInt(t.getProduct_type_id());
                                break;
                            }
                        }
                        if(pos != 0){
                            arrayList = new ArrayList<TblAddItems>();
                            for(TblProducts p : products){
                                if(pos == Integer.parseInt(p.getProduct_type_id())){
                                    TblAddItems items = new TblAddItems();
                                    items.setName(p.getProduct_name());
                                    arrayList.add(items);
                                }
                            }
                        }else {
                            arrayList = new ArrayList<TblAddItems>();
                            for(TblProducts t : products){
                                TblAddItems items = new TblAddItems();
                                items.setName(t.getProduct_name());
                                arrayList.add(items);
                            }
                        }
                        setAdapter();
                    }else {
                        arrayList = new ArrayList<TblAddItems>();
                        for(TblProducts t : products){
                            TblAddItems items = new TblAddItems();
                            items.setName(t.getProduct_name());
                            arrayList.add(items);
                        }
                        setAdapter();
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

    private void setAdapter() {
        try {
            AddItemAdapter adapter = new AddItemAdapter(getActivity(),arrayList);
            recycler_view.setAdapter(adapter);
            setLinearAdd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProductType(){
        try {
            productTypes = GlobalVar.getProductTypes();
            arrayList = new ArrayList<TblAddItems>();
            for(TblProductTypes t : productTypes){
                TblAddItems items = new TblAddItems();
                items.setName(t.getProduct_type_name());
                arrayList.add(items);
            }
            setAdapter();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateProduct(){
        try {
            products = GlobalVar.getProducts();
            arrayList = new ArrayList<TblAddItems>();
            for(TblProducts p : products){
                TblAddItems items = new TblAddItems();
                items.setName(p.getProduct_name());
                arrayList.add(items);
            }
            setAdapter();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateDiscount(){
        try {
            discounts = GlobalVar.getDiscounts();
            arrayList = new ArrayList<TblAddItems>();
            for(TblDiscount d : discounts){
                TblAddItems items = new TblAddItems();
                items.setName(d.getDiscount_name());
                arrayList.add(items);
            }
            setAdapter();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateTable(){
        try {
            tables = GlobalVar.getTables();
            arrayList = new ArrayList<TblAddItems>();
            for(TblTables t : tables){
                TblAddItems items = new TblAddItems();
                items.setName(t.getTable_name());
                arrayList.add(items);
            }
            setAdapter();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addFragment(){
        try {
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new AddItemFragmentSecond()).addToBackStack("tag" ).commit();
            }else {
                mView.getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new AddItemFragmentSecond()).addToBackStack("tag" ).commit();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
