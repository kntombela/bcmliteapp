package com.example.android.bcmliteapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.bcmliteapp.R;
import com.example.android.bcmliteapp.ReportIncidentActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportIncidentFragment extends Fragment {

    //Member Variables
    private Toolbar mToolbar;
    private AutoCompleteTextView mTxtType;
    private EditText mTxtDate;
    private EditText mTxtTime;
    private EditText mTxtDescription;
    private EditText mTxtLocation;
    private ImageView mBtnType;
    private ImageView mBtnDate;
    private ImageView mBtnTime;
    private TextInputLayout mTxtLayoutType;
    private TextInputLayout mTxtLayoutDate;
    private TextInputLayout mTxtLayoutTime;
    private TextInputLayout mTxtLayoutDescription;
    private TextInputLayout mTxtLayoutLocation;
    private View.OnClickListener mIncidentTypeSpnListener = new View.OnClickListener() {
        public void onClick(View v) {
            //Show drop down on click
            mTxtType.showDropDown();
        }
    };
    private View.OnClickListener mTimeBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            //Show time picker dialog
            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
        }
    };
    private View.OnClickListener mDateBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            //Show date picker dialog
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
        }
    };

    public ReportIncidentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_report_incident,
                container, false);

        //Inflate views
        mTxtType = (AutoCompleteTextView) rootView.findViewById(R.id.txt_incident_type);
        mTxtDate = (EditText) rootView.findViewById(R.id.txt_incident_date);
        mTxtTime = (EditText) rootView.findViewById(R.id.txt_incident_time);
        mTxtDescription = (EditText) rootView.findViewById(R.id.txt_incident_description);
        mTxtLocation = (EditText) rootView.findViewById(R.id.txt_incident_location);
        mBtnType = (ImageView) rootView.findViewById(R.id.spn_incident_type);
        mBtnDate = (ImageView) rootView.findViewById(R.id.spn_incident_date);
        mBtnTime = (ImageView) rootView.findViewById(R.id.spn_incident_time);
        mTxtLayoutType = (TextInputLayout) rootView.findViewById(R.id.txt_layout_incident_type);
        mTxtLayoutDate = (TextInputLayout) rootView.findViewById(R.id.txt_layout_incident_date);
        mTxtLayoutTime = (TextInputLayout) rootView.findViewById(R.id.txt_layout_incident_time);
        mTxtLayoutDescription = (TextInputLayout) rootView
                .findViewById(R.id.txt_layout_incident_description);
        mTxtLayoutLocation = (TextInputLayout) rootView
                .findViewById(R.id.txt_layout_incident_location);

        //Set threshold for when autocomplete provides suggestion to user
        mTxtType.setThreshold(2);

        //Set click listeners for all buttons
        mBtnType.setOnClickListener(mIncidentTypeSpnListener);
        mBtnDate.setOnClickListener(mDateBtnListener);
        mBtnTime.setOnClickListener(mTimeBtnListener);

        //Set change listeners for all textfields
        mTxtType.addTextChangedListener(new MyTextWatcher(mTxtType));
        mTxtDate.addTextChangedListener(new MyTextWatcher(mTxtDate));
        mTxtTime.addTextChangedListener(new MyTextWatcher(mTxtTime));
        mTxtDescription.addTextChangedListener(new MyTextWatcher(mTxtDescription));

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_incident_type, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mTxtType.setAdapter(adapter);

        return rootView;
    }

    private boolean validateField(View view, View viewLayout) {
        EditText editText = (EditText) view;
        TextInputLayout textInputLayout = (TextInputLayout) viewLayout;

        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError(getString(R.string.err_required_fields));
            requestFocus(view);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private void submitIncident() {
        if (!validateForm()) {
            return;
        }

        //TODO: Add JSON Post Method
        Toast.makeText(getContext(), "Incident Submitted!", Toast.LENGTH_SHORT).show();
        //TODO: Reload Incident List Fragment
        //NavUtils.navigateUpFromSameTask(this);
    }

    private boolean validateForm() {

        if (!validateField(mTxtType, mTxtLayoutType)) {
            return false;
        }

        if (!validateField(mTxtDate, mTxtLayoutDate)) {
            return false;
        }

        if (!validateField(mTxtTime, mTxtLayoutTime)) {
            return false;
        }

        if (!validateField(mTxtDescription, mTxtLayoutDescription)) {
            return false;
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.txt_incident_type:
                    validateField(view, mTxtLayoutType);
                    break;
                case R.id.txt_incident_date:
                    validateField(view, mTxtLayoutDate);
                    break;
                case R.id.txt_incident_time:
                    validateField(view, mTxtLayoutTime);
                    break;
                case R.id.txt_incident_description:
                    validateField(view, mTxtLayoutDescription);
                    break;
            }
        }
    }
}
