package com.example.android.bcmliteapp;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.bcmliteapp.fragments.DatePickerFragment;
import com.example.android.bcmliteapp.fragments.HomeFragment;
import com.example.android.bcmliteapp.fragments.IncidentFragment;
import com.example.android.bcmliteapp.fragments.ReportIncidentFragment;
import com.example.android.bcmliteapp.fragments.TimePickerFragment;


public class ReportIncidentActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_report_incident);

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

        switch (item.getItemId()) {
            case R.id.menu_item_incident_save:
                //Submit Incident
                //submitIncident();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initActivity() {

        //Set toolbar for activity
        setActivityToolbar();
        //Show list of incidents user already submitted
        setIncidentFragment(true);
        //Setup fab button
        setupAddIncidentFab();

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

    private void setIncidentFragment(boolean isInitialAdd) {

        //Setup fragment transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //Determine if incident fragment is already loaded, if not add fragment
        if (isInitialAdd) {
            transaction.add(R.id.content_frame_incident, new IncidentFragment()).commit();
        } else {
            transaction.replace(R.id.content_frame_incident, new IncidentFragment()).commit();
        }

    }

    private void setReportIncidentFragment() {
        // Set incident fragment to fragment container in main activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_incident, new ReportIncidentFragment())
                .addToBackStack(null)
                .commit();
    }

}
