package com.may.stream.restaurant.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.adapter.ProductAdapter;
import com.may.stream.restaurant.helper.GlobalVar;
import com.may.stream.restaurant.model.TblProducts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may on 1/19/2018.
 */

public class TabFragment extends Fragment{
    private View rootView;
    private static RecyclerView recycler_view;
    private static List<TblProducts> productsList;
    private static List<TblProducts> arrayList;
    private static ProductAdapter adapter;
    private DashboardFragment mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        mView = new DashboardFragment();
        initInstance();
        setData();
        return rootView;

    }

    private void initInstance(){
        try {
            recycler_view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            recycler_view.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recycler_view.setLayoutManager(mLayoutManager);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setData(){
        try {
            arrayList = new ArrayList<TblProducts>();
            productsList = GlobalVar.getProducts();
            for(TblProducts p : productsList){
                if(p.getProduct_type_id().equalsIgnoreCase(mView.strProductTypeID)){
                    arrayList.add(p);
                }
            }
            setAdapter();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setAdapter(){
        try {
            adapter = new ProductAdapter(getActivity(),arrayList);
            recycler_view.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
