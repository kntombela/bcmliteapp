package com.example.android.bcmliteapp.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.android.bcmliteapp.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Set date picked from dialog to date textbox
        setTime(hourOfDay, minute);
    }

    private void setTime(int hour, int minute) {

        String format = "";

        if (hour == 0) {
            hour += 12;
            format = "am";
        } else if (hour == 12) {
            format = "pm";
        } else if (hour > 12) {
            hour -= 12;
            format = "pm";
        } else {
            format = "am";
        }

        ((EditText) getActivity().findViewById(R.id.txt_incident_time))
                .setText(new StringBuilder().append(hour).append(" : ").append(minute)
                        .append(" ").append(format));
    }
}
