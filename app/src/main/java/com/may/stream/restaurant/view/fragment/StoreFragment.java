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
import com.may.stream.restaurant.adapter.StoreAdapter;
import com.may.stream.restaurant.model.TblStore;
import com.may.stream.restaurant.until.TaskController;

import java.util.List;

/**
 * Created by may on 1/5/2018.
 */

public class StoreFragment extends Fragment {
    private View rootView;
    private static RecyclerView recycler_view;
    private List<TblStore> arrayList;
    private TaskController taskController;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_store, container, false);
        taskController = new TaskController();
        init();
        getStore();
        return rootView;
    }

    private void init(){
        try {
            recycler_view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            recycler_view.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recycler_view.setLayoutManager(mLayoutManager);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getStore(){
        try {
            arrayList = taskController.getAllStore();
            setAdapter();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setAdapter() {
        try {
            StoreAdapter adapter = new StoreAdapter(getActivity(),arrayList);
            recycler_view.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.app_name));
    }
}
