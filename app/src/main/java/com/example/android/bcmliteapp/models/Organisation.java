package com.example.android.bcmliteapp.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by keket on 07/09/2017.
 */

public class Organisation {

    private String Name;
    private String Type;
    private String Industry;
    private int NumberOfPlans;

    public Organisation(JSONObject object) {
        try {
            this.setName(object.getString("name"));
            this.setIndustry(object.getString("industry"));
            this.setType(object.getString("type"));
            this.setNumberOfPlans(object.getInt("numberOfPlans"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String industry) {
        Industry = industry;
    }

    public int getNumberOfPlans() {
        return NumberOfPlans;
    }

    public void setNumberOfPlans(int numberOfPlans) {
        NumberOfPlans = numberOfPlans;
    }
}
