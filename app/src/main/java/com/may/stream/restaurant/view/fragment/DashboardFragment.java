package com.may.stream.restaurant.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.adapter.TabAdapter;
import com.may.stream.restaurant.helper.GlobalVar;
import com.may.stream.restaurant.model.TblProductTypes;

import java.util.List;

/**
 * Created by may on 1/4/2018.
 */

public class DashboardFragment extends Fragment {
    private View rootView;
    private TextView textCartItemCount;
    private List<TblProductTypes> productTypes;
    public static String strProductTypeID;
    private TabFragment tabFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        tabFragment = new TabFragment();
        setHasOptionsMenu(true);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.app_name));
        initInstance();
    }

    private void setupViewPager(ViewPager viewPager) {
        productTypes = GlobalVar.getProductTypes();
        if(productTypes.size()>0){
            strProductTypeID = productTypes.get(0).getProduct_type_id();
            TabAdapter adapter = new TabAdapter(getChildFragmentManager());
            for(TblProductTypes t : productTypes){
                adapter.addFragment(new TabFragment(), t.getProduct_type_name());
            }
            viewPager.setAdapter(adapter);
        }
    }

    private void setProductTypeID(String name){
        try {
            for(TblProductTypes p :productTypes){
                if(p.getProduct_type_name().equalsIgnoreCase(name)){
                    strProductTypeID = p.getProduct_type_id();
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initInstance(){
        try {

            ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            // Set Tabs inside Toolbar
            TabLayout tabs = (TabLayout) rootView.findViewById(R.id.result_tabs);
            tabs.setupWithViewPager(viewPager);
            tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    setProductTypeID(String.valueOf(tab.getText()));
                    tabFragment.setData();
                    //Toast.makeText(getActivity(),tab.getText(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart: {
                // Do something
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
