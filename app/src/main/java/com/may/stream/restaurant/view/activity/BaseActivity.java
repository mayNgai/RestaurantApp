package com.may.stream.restaurant.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.view.fragment.DashboardFragment;
import com.may.stream.restaurant.view.fragment.HelpFragment;
import com.may.stream.restaurant.view.fragment.ItemsFragment;
import com.may.stream.restaurant.view.fragment.SettingFragment;
import com.may.stream.restaurant.view.fragment.StoreFragment;
import com.may.stream.restaurant.helper.GlobalVar;
import com.may.stream.restaurant.model.TblMember;
import com.may.stream.restaurant.view.fragment.TableFragment;

import java.util.List;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static List<TblMember> memberList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        TextView txt_authen = (TextView)header.findViewById(R.id.txt_authen);
        TextView txt_name = (TextView)header.findViewById(R.id.txt_name);
        memberList = GlobalVar.getMember();
        if(memberList != null&&memberList.size()>0){
            txt_authen.setText(memberList.get(0).getAuthen());
            txt_name.setText(memberList.get(0).getFirst_name() + " " + memberList.get(0).getLast_name());
        }else {
            txt_authen.setText("");
            txt_name.setText("");
        }
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.nav_dashboard);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }else if(id == R.id.action_logout){
//            Intent intent = new Intent(this,MenuActivity.class);
//            startActivity(intent);
//            finish();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_dashboard:
                fragment = new DashboardFragment();
                break;
            case R.id.nav_table:
                fragment = new TableFragment();
                break;
            case R.id.nav_items:
                fragment = new ItemsFragment();
                break;
            case R.id.nav_store:
                fragment = new StoreFragment();
                break;
            case R.id.nav_manage:
                fragment = new SettingFragment();
                break;
            case R.id.nav_help:
                fragment = new HelpFragment();
                break;
            case R.id.nav_logout:
                if(GlobalVar.logOut()){
                    Intent intent = new Intent(this,MenuActivity.class);
                    startActivity(intent);
                    finish();
                }

                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displaySelectedScreen(item.getItemId());
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // your code
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
