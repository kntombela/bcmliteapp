package com.example.android.bcmliteapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.bcmliteapp.fragments.IncidentFragment;
import com.example.android.bcmliteapp.fragments.ReportIncidentFragment;


public class IncidentActivity extends AppCompatActivity
        implements ReportIncidentFragment.OnIncidentSubmittedListener {

    //Member Variables
    private Toolbar mToolbar;
    private FloatingActionButton mFabAddIncident;
    private View.OnClickListener onFabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Replace current fragment with ReportIncidentFragment
            setReportIncidentFragment();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident);

        //Initialise activity with toolbar and incident list
        initActivity();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the toolbar menu
        getMenuInflater().inflate(R.menu.activity_incident, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onIncidentSubmitted() {
        setIncidentFragment(false);
    }

    public void initActivity() {

        //Set toolbar for activity
        setActivityToolbar();
        //Setup fab button
        setupAddIncidentFab();
        //Show list of incidents user already submitted
        setIncidentFragment(true);


    }

    private void setActivityToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.tb_incident);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupAddIncidentFab() {
        mFabAddIncident = findViewById(R.id.fab_add_incident);
        mFabAddIncident.setOnClickListener(onFabClickListener);
    }

    public void setIncidentFragment(boolean isInitialAdd) {

        //Setup fragment transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //Determine if incident fragment is already loaded, if not add fragment
        if (isInitialAdd) {
            transaction.add(R.id.content_frame_incident, new IncidentFragment()).commit();
        } else {
            transaction.replace(R.id.content_frame_incident, new IncidentFragment()).commit();
        }

        //Rename activity toolbar
        setTitle("Incidents");

        //Show fab
        showFab(true);

    }

    private void setReportIncidentFragment() {
        // Set incident fragment to fragment container in main activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_incident, new ReportIncidentFragment())
                .addToBackStack(null)
                .commit();

        //Rename activity toolbar
        setTitle("Report Incident");

        //Hide fab
        showFab(false);

    }

    private void showFab(boolean show) {

        //Determine if fab is shown for toggle between incident list and report incident
        if (!show) {
            mFabAddIncident.hide();
        } else {
            mFabAddIncident.show();
        }
    }
}
