package com.example.android.bcmliteapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by keket on 20/09/2017.
 */

public class Document implements Serializable {

    //Member Variables
    @SerializedName("documentID")
    private int DocumentID;

    @SerializedName("description")
    private String Description;

    @SerializedName("rto")
    private String RTO;

    @SerializedName("processID")
    private int ProcessID;

    //Constructors
    public Document() {

    }

    public int getDocumentID() {
        return DocumentID;
    }

    public void setDocumentID(int documentID) {
        DocumentID = documentID;
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
