package com.example.android.bcmliteapp.utils;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by keket on 26/11/2017.
 */

public class DateDeserializer implements JsonDeserializer<Date> {

    private static final String LOG_TAG = DateDeserializer.class.getSimpleName();

    @Override
    public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2)
            throws JsonParseException {
        String date = element.getAsString();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            return format.parse(date);
        } catch (ParseException exp) {
            Log.e(LOG_TAG, "Failed to parse Date: " + exp);
            return null;
        }
    }
}
