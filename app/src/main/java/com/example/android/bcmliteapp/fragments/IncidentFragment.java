package com.example.android.bcmliteapp.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.bcmliteapp.R;
import com.example.android.bcmliteapp.StepDetailActivity;
import com.example.android.bcmliteapp.adapters.IncidentAdapter;
import com.example.android.bcmliteapp.adapters.StepAdapter;
import com.example.android.bcmliteapp.clients.BCMApiClient;
import com.example.android.bcmliteapp.models.Incident;
import com.example.android.bcmliteapp.models.Step;
import com.example.android.bcmliteapp.utils.DateDeserializer;
import com.example.android.bcmliteapp.utils.ProgressBarUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncidentFragment extends Fragment {

    //Member variables
    private static final String LOG_TAG = IncidentFragment.class.getSimpleName();
    private final Response.ErrorListener onFailure = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            //Close progress bar
            ProgressBarUtil.closeProgressBar();

            //Print response to error log
            Log.e("Incidents", error.toString());
        }
    };
    private RecyclerView mIncidentList;
    private ArrayList mIncidentArray;
    private Gson mGson;
    private final Response.Listener<String> onSuccess = new Response.Listener<String>() {

        @Override
        public void onResponse(String response) {

            //Deserialize Json response to Incident object array
            mIncidentArray = new ArrayList<>(Arrays.asList(mGson.fromJson(response,
                    Incident[].class)));

            //Create incident adapter
            IncidentAdapter mIncidentAdapter = new IncidentAdapter(mIncidentArray,
                    IncidentFragment.this);

            //Set adapter to Step RecyclerView
            mIncidentList.setAdapter(mIncidentAdapter);

            //Close progress bar
            ProgressBarUtil.closeProgressBar();

            //TODO: REMOVE - For debugging purposes:
            Log.i(LOG_TAG, response);

        }
    };
    private RequestQueue mRequestQueue;

    public IncidentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_incident, container, false);
        mIncidentList = (RecyclerView) rootView.findViewById(R.id.lst_incidents);

        //Set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mIncidentList.setLayoutManager(layoutManager);

        //Create new Volley requestQueue to retrieve data
        mRequestQueue = Volley.newRequestQueue(getContext());

        //Gson object to deserialize Json response
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        mGson = gsonBuilder.create();

        //Show progress bar
        ProgressBarUtil.showProgressBar(getContext());

        //Get list of incidents
        //TODO: Create shared preference for user organisation
        getIncidents(1);

        return rootView;
    }

    public void onItemClick(int position) {

/*        startActivity(new Intent(getActivity(), StepDetailActivity.class).putExtra("Step",
                mStepArray.get(position)));*/
    }

    public void getIncidents(int organisationId) {

        StringRequest request = new StringRequest(Request.Method.GET,
                BCMApiClient.getIncidentUri(organisationId), onSuccess, onFailure);

        mRequestQueue.add(request);
    }

}
