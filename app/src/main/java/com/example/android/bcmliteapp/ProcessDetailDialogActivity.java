package com.example.android.bcmliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.bcmliteapp.models.Process;
import com.example.android.bcmliteapp.models.Step;

public class ProcessDetailDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_process_detail);

        //Retrieve data from intent
        Intent intent = getIntent();

        // The detail Activity called via intent.  Inspect the intent for step details
        if (intent != null && intent.hasExtra("Process")) {

            Process process = (Process) intent.getSerializableExtra("Process");

            //Set Name
            ((TextView) findViewById(R.id.txt_dialog_process_name))
                    .setText(process.getName());

            //Set Description
            ((TextView) findViewById(R.id.txt_dialog_process_description))
                    .setText(process.getDescription());

            //Set CriticalTime
            ((TextView) findViewById(R.id.txt_dialog_process_critical_time))
                    .setText(process.getCriticalTime());

            //Set SOP TODO: Update process model to return yes or no string
            ((TextView) findViewById(R.id.txt_dialog_process_sop))
                    .setText(Boolean.toString(process.isSop()));

            //Set SLA TODO: Update process model to return yes or no string
            ((TextView) findViewById(R.id.txt_dialog_process_sla))
                    .setText(Boolean.toString(process.isSla()));

            //Set RTO
            ((TextView) findViewById(R.id.txt_dialog_process_rto))
                    .setText(process.getRto());

            //Set MBCO TODO: Update model to return percentage formatted string
            ((TextView) findViewById(R.id.txt_dialog_process_mbco))
                    .setText(Double.toString(process.getMbco()));

            //Set Operational Impact
            ((TextView) findViewById(R.id.txt_dialog_process_ops_impact))
                    .setText(process.getOperationalImpact());

            //Set Financial Impact
            ((TextView) findViewById(R.id.txt_dialog_process_fin_impact))
                    .setText(process.getFinancialImpact());

            //Set Staff Compliment TODO: Change property type to integer
            ((TextView) findViewById(R.id.txt_dialog_process_staff_comp))
                    .setText(Double.toString(process.getStaffCompliment()));

            //Set Staff Compliment Description
            ((TextView) findViewById(R.id.txt_dialog_process_staff_comp_desc))
                    .setText(process.getStaffCompDesc());

            //Set Revised Ops Level TODO: Update model to return percentage formatted string
            ((TextView) findViewById(R.id.txt_dialog_process_revised_ops_level))
                    .setText(Double.toString(process.getRevisedOpsLevel()));

            //Set Remote Working TODO: Update model to return percentage formatted string
            ((TextView) findViewById(R.id.txt_dialog_process_remote_working))
                    .setText(Boolean.toString(process.isRemoteWorking()));

            //Set Site Dependent TODO: Update model to return percentage formatted string
            ((TextView) findViewById(R.id.txt_dialog_process_remote_working))
                    .setText(Boolean.toString(process.isRemoteWorking()));
        }

    }
}
