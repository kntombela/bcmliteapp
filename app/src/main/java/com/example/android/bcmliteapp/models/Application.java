package com.example.android.bcmliteapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by keket on 20/09/2017.
 */

public class Application implements Serializable {

    //Member Variables
    @SerializedName("applicationID")
    private int ApplicationID;

    @SerializedName("name")
    private String Name;

    @SerializedName("description")
    private String Description;

    @SerializedName("rto")
    private String RTO;

    @SerializedName("rpo")
    private String RPO;

    @SerializedName("processID")
    private int ProcessID;


    //Constructors
    public Application() {

    }

    public int getApplicationID() {
        return ApplicationID;
    }

    public void setApplicationID(int applicationID) {
        ApplicationID = applicationID;
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

    public String getRPO() {
        return RPO;
    }

    public void setRPO(String RPO) {
        this.RPO = RPO;
    }

    public int getProcessID() {
        return ProcessID;
    }

    public void setProcessID(int processID) {
        ProcessID = processID;
    }
}
