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
import com.example.android.bcmliteapp.ViewModels.ProcessViewModel;
import com.example.android.bcmliteapp.adapters.ResourceAdapter;
import com.example.android.bcmliteapp.clients.BCMApiClient;
import com.example.android.bcmliteapp.models.Application;
import com.example.android.bcmliteapp.models.Document;
import com.example.android.bcmliteapp.models.Equipment;
import com.example.android.bcmliteapp.models.Skill;
import com.example.android.bcmliteapp.models.ThirdParty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;


public class ResourceListFragment extends Fragment {

    //Member Variables
    //TODO: Put constants in a static utility class
    private static final String CONST_APPLICATION = "applications";
    private static final String CONST_DOCUMENT = "documents";
    private static final String CONST_EQUIPMENT = "equipment";
    private static final String CONST_SKILL = "skills";
    private static final String CONST_THIRD_PARTY = "thirdParties";
    private static final String LOG_TAG = ResourceListFragment.class.getSimpleName();
    RecyclerView mResourceList;
    private ArrayList mResourceArray;
    private String mResourceType;
    private Gson mGson;
    private RequestQueue mRequestQueue;
    private ProgressDialog mProgressDialog;

    public ResourceListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_resource_list, container, false);
        mResourceList = (RecyclerView) rootView.findViewById(R.id.lst_resources);

        //Set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mResourceList.setLayoutManager(layoutManager);

        //Create new Volley requestQueue to retrieve data
        mRequestQueue = Volley.newRequestQueue(getContext());

        //Gson object to deserialize Json response
        mGson = new GsonBuilder().create();

        //Show progress dialog
        mProgressDialog = ProgressDialog
                .show(getContext(), "Retrieving Resource List", "Please Wait",
                        true, true);
        mProgressDialog.show();

        //Retrieve data from intent
        ProcessViewModel processViewModel = (ProcessViewModel)
                getArguments().getSerializable("ProcessVM");

        //Retrieve resource list from Volley and set ResourceListAdapter
        getResource(processViewModel);

        return rootView;
    }

    //Getters and Setters
    public String getResourceType() {
        return mResourceType;
    }

    public void setResourceType(String resourceType) {
        mResourceType = resourceType;
    }

    //Item click listener
    public void onItemClick(int position) {

        //TODO: Add intent to start new activity
        Log.i(LOG_TAG, String.valueOf(position) + " Item Clicked.");
    }

    //Get resource list based on resource type
    public void getResource(ProcessViewModel processViewModel) {

        //Set resource type
        setResourceType(processViewModel.getResourceType());

        //Build Uri to access correct resource list
        Uri builtUri = Uri.parse(BCMApiClient.BASE_URL)
                .buildUpon()
                .appendPath(processViewModel.getResourceType())
                .appendQueryParameter(BCMApiClient.PARAM_PROCESS_ID,
                        Integer.toString(processViewModel.getProcessID()))
                .build();

        StringRequest request = new StringRequest(Request.Method.GET,
                builtUri.toString(), new OnVolleySuccess(processViewModel.getResourceType()),
                new OnVolleyFailure());

        mRequestQueue.add(request);
    }

    //Volley methods
    private class OnVolleySuccess implements Response.Listener<String> {
        private String mResourceType;

        OnVolleySuccess(String resourceType) {
            mResourceType = resourceType;
        }

        @Override
        public void onResponse(String response) {

            //Determine resource type
            switch (mResourceType) {
                case CONST_APPLICATION:
                    getApplications(response);
                    break;

                case CONST_DOCUMENT:
                    getDocuments(response);
                    break;

                case CONST_EQUIPMENT:
                    getEquipment(response);
                    break;

                case CONST_SKILL:
                    getSkills(response);
                    break;

                case CONST_THIRD_PARTY:
                    getThirdParties(response);
                    break;
            }


        }

        private void getSkills(String response) {

            //Deserialize Json response to Step object array
            mResourceArray = new ArrayList<Skill>();
            mResourceArray = new ArrayList<>(Arrays.asList(mGson.fromJson(response, Skill[].class)));

            //Create resource adapter
            ResourceAdapter resourceAdapter = new ResourceAdapter(mResourceArray,
                    ResourceListFragment.this);

            //Set adapter to Step RecyclerView
            mResourceList.setAdapter(resourceAdapter);

            //Close progress dialog
            mProgressDialog.dismiss();

            //TODO: REMOVE - For debugging purposes:
            Log.i(LOG_TAG, response);
        }

        private void getDocuments(String response) {

            //Deserialize Json response to Step object array
            mResourceArray = new ArrayList<Document>();
            mResourceArray = new ArrayList<>(Arrays
                    .asList(mGson.fromJson(response, Document[].class)));

            //Create step adapter
            ResourceAdapter resourceAdapter = new ResourceAdapter(mResourceArray,
                    ResourceListFragment.this);

            //Set adapter to Step RecyclerView
            mResourceList.setAdapter(resourceAdapter);

            //Close progress dialog
            mProgressDialog.dismiss();

            //TODO: REMOVE - For debugging purposes:
            Log.i(LOG_TAG, response);
        }

        private void getApplications(String response) {

            //Deserialize Json response to Step object array
            mResourceArray = new ArrayList<Application>();
            mResourceArray = new ArrayList<>(Arrays
                    .asList(mGson.fromJson(response, Application[].class)));

            //Create step adapter
            ResourceAdapter resourceAdapter = new ResourceAdapter(mResourceArray,
                    ResourceListFragment.this);

            //Set adapter to Step RecyclerView
            mResourceList.setAdapter(resourceAdapter);

            //Close progress dialog
            mProgressDialog.dismiss();

            //TODO: REMOVE - For debugging purposes:
            Log.i(LOG_TAG, response);
        }

        private void getThirdParties(String response) {

            //Deserialize Json response to Step object array
            mResourceArray = new ArrayList<ThirdParty>();
            mResourceArray = new ArrayList<>(Arrays
                    .asList(mGson.fromJson(response, ThirdParty[].class)));

            //Create step adapter
            ResourceAdapter resourceAdapter = new ResourceAdapter(mResourceArray,
                    ResourceListFragment.this);

            //Set adapter to Step RecyclerView
            mResourceList.setAdapter(resourceAdapter);

            //Close progress dialog
            mProgressDialog.dismiss();

            //TODO: REMOVE - For debugging purposes:
            Log.i(LOG_TAG, response);
        }

        private void getEquipment(String response) {

            //Deserialize Json response to Step object array
            mResourceArray = new ArrayList<Equipment>();
            mResourceArray = new ArrayList<>(Arrays
                    .asList(mGson.fromJson(response, Equipment[].class)));

            //Create step adapter
            ResourceAdapter resourceAdapter = new ResourceAdapter(mResourceArray,
                    ResourceListFragment.this);

            //Set adapter to Step RecyclerView
            mResourceList.setAdapter(resourceAdapter);

            //Close progress dialog
            mProgressDialog.dismiss();

            //TODO: REMOVE - For debugging purposes:
            Log.i(LOG_TAG, response);
        }

    }

    private class OnVolleyFailure implements Response.ErrorListener {


        @Override
        public void onErrorResponse(VolleyError error) {
            //Close progress dialog
            mProgressDialog.dismiss();

            //Print response to error log
            Log.e(LOG_TAG, error.toString());
        }
    }
}


