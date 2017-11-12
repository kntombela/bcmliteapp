package com.example.android.bcmliteapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by keket on 20/09/2017.
 */

public class Equipment implements Serializable {

    //Member Variables
    @SerializedName("equipmentID")
    private int EquipmentID;

    @SerializedName("description")
    private String Description;

    @SerializedName("rto")
    private String RTO;

    @SerializedName("processID")
    private int ProcessID;

    //Constructors
    public Equipment() {

    }

    public int getEquipmentID() {
        return EquipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        EquipmentID = equipmentID;
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
