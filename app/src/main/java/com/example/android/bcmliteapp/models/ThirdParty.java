package com.example.android.bcmliteapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by keket on 20/09/2017.
 */

public class ThirdParty implements Serializable {

    //Member Variables
    @SerializedName("thirdPartyID")
    private int ThirdPartyID;

    @SerializedName("name")
    private String Name;

    @SerializedName("description")
    private String Description;

    @SerializedName("rto")
    private String RTO;

    @SerializedName("processID")
    private int ProcessID;

    //Constructors
    public ThirdParty() {

    }

    public int getThirdPartyID() {
        return ThirdPartyID;
    }

    public void setThirdPartyID(int thirdPartyID) {
        ThirdPartyID = thirdPartyID;
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

    public String getRTO() {
        return RTO;
    }

    public void setRTO(String RTO) {
        this.RTO = RTO;
    }

    public int getProcessID() {
        return ProcessID;
    }

    public void setProcessID(int processID) {
        ProcessID = processID;
    }
}
