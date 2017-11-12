package com.example.android.bcmliteapp.ViewModels;

import java.io.Serializable;

/**
 * Created by keket on 06/11/2017.
 */

public class ProcessViewModel implements Serializable {

    //Member Variables
    private int ProcessID;
    private String ResourceType;

    //Constructors


    public ProcessViewModel(int processID, String resourceType) {
        ProcessID = processID;
        ResourceType = resourceType;
    }

    public int getProcessID() {
        return ProcessID;
    }

    public void setProcessID(int processID) {
        ProcessID = processID;
    }

    public String getResourceType() {
        return ResourceType;
    }

    public void setResourceType(String resourceType) {
        ResourceType = resourceType;
    }
}
