package com.example.android.bcmliteapp.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by keket on 07/09/2017.
 */

public class Step implements Serializable {


    //Member Variables
    @SerializedName("departmentPlanID")
    private int DepartmentPlanID;

    @SerializedName("number")
    private int Number;

    @SerializedName("title")
    private String Title;

    @SerializedName("summary")
    private String Summary;

    @SerializedName("detail")
    private String Detail;

    //Constructors
    public Step() {
    }


    public int getDepartmentPlanID() {
        return DepartmentPlanID;
    }

    public void setDepartmentPlanID(int departmentPlanID) {
        DepartmentPlanID = departmentPlanID;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }
}
