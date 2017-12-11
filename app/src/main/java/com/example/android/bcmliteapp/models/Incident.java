package com.example.android.bcmliteapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by keket on 25/11/2017.
 */

public class Incident implements Serializable {

    //Member Variables
    @SerializedName("incidentID")
    private int IncidentID;

    @SerializedName("type")
    private int Type;

    @SerializedName("date")
    private Date Date;

    @SerializedName("description")
    private String Description;

    @SerializedName("location")
    private String Location;

    @SerializedName("organisationID")
    private int OrganisationID;


    //Getters and Setters
    public int getIncidentID() {
        return this.IncidentID;
    }

    public void setIncidentID(int incidentID) {
        this.IncidentID = incidentID;
    }

    public int getType() {
        return this.Type;
    }

    public void setType(int type) {
        this.Type = type;
    }

    public Date getDate() {
        return this.Date;
    }

    public void setDate(Date date) {
        this.Date = date;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getLocation() {
        return this.Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public int getOrganisationID() {
        return this.OrganisationID;
    }

    public void setOrganisationID(int organisationID) {
        this.OrganisationID = organisationID;
    }
}
