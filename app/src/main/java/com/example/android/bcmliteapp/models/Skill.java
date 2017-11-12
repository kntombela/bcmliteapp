package com.example.android.bcmliteapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by keket on 20/09/2017.
 */

public class Skill implements Serializable {

    //Member variables
    @SerializedName("skillID")
    private int SkillID;

    @SerializedName("description")
    private String Description;

    @SerializedName("rto")
    private String RTO;

    @SerializedName("processID")
    private int ProcessID;

    public Skill() {

    }


    public int getSkillID() {
        return SkillID;
    }

    public void setSkillID(int skillID) {
        SkillID = skillID;
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
