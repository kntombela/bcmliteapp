package com.example.android.bcmliteapp.models;

/**
 * Created by keket on 25/11/2017.
 */

public enum IncidentType {
    Facility("Building & Facility"),
    HealthAndSafety("Health & Safety"),
    Security("Security"),
    InformationTechnology("Information Technology"),
    Other("Other");

    private String incident;

    IncidentType(String aIncident) {
        incident = aIncident;
    }

    public static String parseIncidentType(int incidentType) {
        return IncidentType.values()[incidentType].toString();
    }

    @Override
    public String toString() {
        return incident;
    }
}
