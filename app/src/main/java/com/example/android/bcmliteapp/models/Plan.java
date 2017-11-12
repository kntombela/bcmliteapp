package com.example.android.bcmliteapp.models;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Keketso on 07/09/2017.
 */

public class Plan implements Serializable {

    private static final String LOG_TAG = Plan.class.getSimpleName();
    //Member Variables
    @SerializedName("id")
    private int Id;
    @SerializedName("name")
    private String Name;
    @SerializedName("description")
    private String Description;
    @SerializedName("type")
    private String Type;
    @SerializedName("invoked")
    private boolean Invoked;
    @SerializedName("departmentName")
    private String DepartmentName;
    @SerializedName("departmentID")
    private int DepartmentID;

    //Constructors
    public Plan() {
    }

    public Plan(int id, String name, String description, String type, int invoked
            , String departmentName, int departmentID) {
        setId(id);
        setName(name);
        setDescription(type);
        setInvoked(invoked != 0);
        setDepartmentName(departmentName);
        setDepartmentID(departmentID);
    }

    //Methods
    public static void ActionView(int position) {

        Log.i(LOG_TAG, String.valueOf(position) + " View Button Pressed.");
    }

    public static void ActionShare(int position) {
        Log.i(LOG_TAG, String.valueOf(position) + " Share Button Pressed.");
    }

    //Getters and Setters
    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int departmentID) {
        DepartmentID = departmentID;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public boolean isInvoked() {
        return Invoked;
    }

    public void setInvoked(boolean invoked) {
        Invoked = invoked;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String department) {
        DepartmentName = department;
    }

}
