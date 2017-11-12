package com.example.android.bcmliteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.bcmliteapp.models.Step;

public class StepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        //Retrieve data from intent
        Intent intent = getIntent();

        // The detail Activity called via intent.  Inspect the intent for step details
        if (intent != null && intent.hasExtra("Step")) {

            Step step = (Step) intent.getSerializableExtra("Step");

            //Set Activity Title
            setTitle(step.getTitle());

            //Set Step Summary
            ((TextView) findViewById(R.id.txt_step_detail_summary)).setText(step.getSummary());

            //Set Step Detail
            ((TextView) findViewById(R.id.txt_step_detail_detail)).setText(step.getDetail());
        }

    }
}
