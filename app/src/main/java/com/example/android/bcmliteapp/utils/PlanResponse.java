package com.example.android.bcmliteapp.utils;

import com.example.android.bcmliteapp.models.Plan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created by keket on 18/10/2017.
 */

public class PlanResponse {

    public ArrayList<Plan> plans;

    //Default contructor: Create new blank list of plans of object intantiate
    public PlanResponse() {
        plans = new ArrayList<Plan>();
    }

    public static PlanResponse parseJson(String response) {

        //Map array response to plan PlanResponse array
        Gson gson = new GsonBuilder().create();
        PlanResponse planResponse = gson.fromJson(response, PlanResponse.class);

        return planResponse;
    }
}
