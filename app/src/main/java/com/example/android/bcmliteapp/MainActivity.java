package com.example.android.bcmliteapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.bcmliteapp.fragments.FeedbackFragment;
import com.example.android.bcmliteapp.fragments.GlossaryFragment;
import com.example.android.bcmliteapp.fragments.GovernanceFragment;
import com.example.android.bcmliteapp.fragments.HomeFragment;
import com.example.android.bcmliteapp.fragments.PlanFragment;
import com.example.android.bcmliteapp.fragments.TeamFragment;
import com.example.android.bcmliteapp.utils.SharedActivityHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Member Variables
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    DrawerLayout mDrawer;
    NavigationView mNavigationView;
    private Toolbar mToolbar;
    private CharSequence mTitle;
    private BottomNavigationViewEx mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }

        /*Initialise activity with toolbar and navigation drawer*/
        initActivity();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the toolbar menu
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_main_search:
                //TODO: Action for main search menu item
                return true;

            case R.id.menu_item_main_report:
                startIncidentActivity();
                return true;

            case R.id.menu_item_main_refresh:
                setPlansFragment();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        //Check drawer state and close if opened
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_home:
                setHomeFragment(false);
                break;

            case R.id.nav_plans:
                setPlansFragment();
                break;

            case R.id.nav_governance:
                setGovernanceFragment();
                break;

            case R.id.nav_glossary:
                setGlossaryFragment();
                break;

            case R.id.nav_team:
                setTeamFragment();
                break;

            case R.id.nav_resources:
                setResourceFragment();
                break;

            case R.id.nav_feedback:
                setFeedbackFragment();
                break;
            default:
                setHomeFragment(false);

        }

        //Close drawer when item selected
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        mToolbar.setTitle(mTitle);
    }

    public void initActivity() {
        //Set toolbar for activity
        mToolbar = findViewById(R.id.tb_main);
        SharedActivityHelper.setActivityToolBar(this, mToolbar);

        //Set app navigation drawer
        setupNavigationDrawer();

        //Set bottom navigation
        setupBottomNavigationView();

        //Set home fragment as default loaded fragment
        setHomeFragment(true);
    }

    public void setupNavigationDrawer() {

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    public void setupBottomNavigationView() {
        //Inflate view and remove default side animation
        mBottomNavigationView = findViewById(R.id.navigation);

        //Remove default BottomNavigationView behavior
        mBottomNavigationView.enableAnimation(false);
        mBottomNavigationView.enableShiftingMode(false);
        mBottomNavigationView.enableItemShiftingMode(false);
        mBottomNavigationView.setTextVisibility(false);

    }

    public void setHomeFragment(boolean isInitialAdd) {

        //Setup fragment transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //Determine if home fragment is already loaded, if not add home fragment
        if (isInitialAdd) {
            transaction.add(R.id.content_frame_main, new HomeFragment()).commit();
            setDrawerItemChecked();
            setTitle(R.string.app_name);
        } else {
            transaction.replace(R.id.content_frame_main, new HomeFragment()).commit();
            setTitle(R.string.app_name);
        }

        // Set home fragment to fragment container in main activity
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.content_frame_main, new HomeFragment())
//                .commit();
    }

    public void setPlansFragment() {
        // Set plans fragment to fragment container in main activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_main, new PlanFragment())
                .commit();

        //Set new activity title
        setTitle(R.string.nav_item_plans);
    }

    public void setGlossaryFragment() {
        // Set glossary fragment to fragment container in main activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_main, new GlossaryFragment())
                .addToBackStack(null)
                .commit();

        //Set new activity title
        setTitle(R.string.nav_item_glossary);
    }

    public void setTeamFragment() {
        // Set team fragment to fragment container in main activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_main, new TeamFragment())
                .addToBackStack(null)
                .commit();

        //Set new activity title
        setTitle(R.string.nav_item_team);
    }

    public void setGovernanceFragment() {
        // Set governance fragment to fragment container in main activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_main, new GovernanceFragment())
                .addToBackStack(null)
                .commit();

        //Set new activity title
        setTitle(R.string.nav_item_governance);
    }

    public void setFeedbackFragment() {
        // Set feedback fragment to fragment container in main activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_main, new FeedbackFragment())
                .addToBackStack(null)
                .commit();

        //Set new activity title
        setTitle(R.string.nav_item_feedback);
    }

    public void setResourceFragment() {

        // Set resource fragment to fragment container in main activity
        //Set new activity title
        setTitle(R.string.nav_item_resources);
    }

    public void startIncidentActivity() {
        //Start report incident activity
        startActivity(new Intent(this, ReportIncidentActivity.class));
    }

    public void setDrawerItemChecked() {
        mNavigationView.setCheckedItem(R.id.nav_home);
    }

}
