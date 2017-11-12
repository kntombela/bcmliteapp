package com.example.android.bcmliteapp.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by keket on 20/09/2017.
 */

public class Process implements Serializable {

    private static final String LOG_TAG = Process.class.getName();
    //Member Variables
    @SerializedName("processID")
    private int ProcessID;
    @SerializedName("name")
    private String Name;
    @SerializedName("description")
    private String Description;
    @SerializedName("criticalTime")
    private String CriticalTime;
    @SerializedName("sop")
    private boolean Sop;
    @SerializedName("sla")
    private boolean Sla;
    @SerializedName("departmentID")
    private int DepartmentID;
    @SerializedName("rto")
    private String Rto;
    @SerializedName("mbco")
    private double Mbco;
    @SerializedName("operationalImpact")
    private String OperationalImpact;
    @SerializedName("financialImpact")
    private String FinancialImpact;
    @SerializedName("staffCompliment")
    private double StaffCompliment;
    @SerializedName("staffCompDesc")
    private String StaffCompDesc;
    @SerializedName("revisedOpsLevel")
    private double RevisedOpsLevel;
    @SerializedName("revisedOpsLevelDesc")
    private String RevisedOpsLevelDesc;
    @SerializedName("remoteWorking")
    private boolean RemoteWorking;
    @SerializedName("siteDependent")
    private boolean SiteDependent;

    //Constructors
    public Process() {
    }

    public int getProcessID() {
        return ProcessID;
    }

    public void setProcessID(int processID) {
        ProcessID = processID;
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

    public String getCriticalTime() {
        return CriticalTime;
    }

    public void setCriticalTime(String criticalTime) {
        CriticalTime = criticalTime;
    }

    public boolean isSop() {
        return Sop;
    }

    public void setSop(boolean SOP) {
        this.Sop = SOP;
    }

    public boolean isSla() {
        return Sla;
    }

    public void setSla(boolean SLA) {
        this.Sla = SLA;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int departmentID) {
        DepartmentID = departmentID;
    }

    public String getRto() {
        return Rto;
    }

    public void setRto(String RTO) {
        this.Rto = RTO;
    }

    public double getMbco() {
        return Mbco;
    }

    public void setMbco(double MBCO) {
        this.Mbco = MBCO;
    }

    public String getOperationalImpact() {
        return OperationalImpact;
    }

    public void setOperationalImpact(String operationalImpact) {
        OperationalImpact = operationalImpact;
    }

    public String getFinancialImpact() {
        return FinancialImpact;
    }

    public void setFinancialImpact(String financialImpact) {
        FinancialImpact = financialImpact;
    }

    public double getStaffCompliment() {
        return StaffCompliment;
    }

    public void setStaffCompliment(double staffCompliment) {
        StaffCompliment = staffCompliment;
    }

    public String getStaffCompDesc() {
        return StaffCompDesc;
    }

    public void setStaffCompDesc(String staffCompDesc) {
        StaffCompDesc = staffCompDesc;
    }

    public double getRevisedOpsLevel() {
        return RevisedOpsLevel;
    }

    public void setRevisedOpsLevel(double revisedOpsLevel) {
        RevisedOpsLevel = revisedOpsLevel;
    }

    public String getRevisedOpsLevelDesc() {
        return RevisedOpsLevelDesc;
    }

    public void setRevisedOpsLevelDesc(String revisedOpsLevelDesc) {
        RevisedOpsLevelDesc = revisedOpsLevelDesc;
    }

    public boolean isRemoteWorking() {
        return RemoteWorking;
    }

    public void setRemoteWorking(boolean remoteWorking) {
        RemoteWorking = remoteWorking;
    }

    public boolean isSiteDependent() {
        return SiteDependent;
    }

    public void setSiteDependent(boolean siteDependent) {
        SiteDependent = siteDependent;
    }

}
