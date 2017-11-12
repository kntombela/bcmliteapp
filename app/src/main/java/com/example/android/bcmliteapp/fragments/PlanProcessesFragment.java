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
import com.example.android.bcmliteapp.ProcessDetailDialogActivity;
import com.example.android.bcmliteapp.R;
import com.example.android.bcmliteapp.ResourceActivity;
import com.example.android.bcmliteapp.adapters.PlanAdapter;
import com.example.android.bcmliteapp.adapters.ProcessAdapter;
import com.example.android.bcmliteapp.clients.BCMApiClient;
import com.example.android.bcmliteapp.models.Plan;
import com.example.android.bcmliteapp.models.Process;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class PlanProcessesFragment extends Fragment {

    private static final String LOG_TAG = PlanProcessesFragment.class.getSimpleName();
    //Member Variables
    RecyclerView mProcessList;
    ArrayList<Process> mProcessArray;
    private Gson mGson;
    private RequestQueue mRequestQueue;
    private ProgressDialog mProgressDialog;
    private final Response.Listener<String> onSuccess = new Response.Listener<String>() {

        @Override
        public void onResponse(String response) {

            //Deserialize Json response to Process object array
            mProcessArray = new ArrayList<>(Arrays.asList(mGson.fromJson(response, Process[].class)));

            //Create process adapter
            ProcessAdapter mProcessAdapter = new ProcessAdapter(mProcessArray,
                    PlanProcessesFragment.this);

            //Set adapter to Process RecyclerView
            mProcessList.setAdapter(mProcessAdapter);

            //Close progress dialog
            mProgressDialog.dismiss();

            //TODO: REMOVE - For debugging purposes:
            Log.i(LOG_TAG, response);

        }
    };
    private final Response.ErrorListener onFailure = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            //Close progress dialog
            mProgressDialog.dismiss();

            //Print response to error log
            Log.e("Processes", error.toString());
        }
    };

    public PlanProcessesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_plan_processes, container,
                false);
        mProcessList = (RecyclerView) rootView.findViewById(R.id.lst_process);

        //Set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mProcessList.setLayoutManager(layoutManager);

        //Create new Volley requestQueue to retrieve data
        mRequestQueue = Volley.newRequestQueue(getContext());

        //Gson object to deserialize Json response
        mGson = new GsonBuilder().create();

        //Show progress dialog
        mProgressDialog = ProgressDialog
                .show(getContext(), "Retrieving Processes", "Please Wait",
                        true, true);
        mProgressDialog.show();

        //Retrieve data from intent
        Intent intent = getActivity().getIntent();

        if (intent != null && intent.hasExtra("Plan")) {

            //Retrieve plan object from the intent that started the activity
            Plan plan = (Plan) intent.getSerializableExtra("Plan");

            //Get processes based on department ID
            getPlanProcesses(Integer.toString(plan.getDepartmentID()));

        }

        return rootView;
    }

    public void onItemClick(int position) {
        //TODO: Add intent to start new activity

    }

    //Retrieve a row item mProcessArray
    public Object getItem(int position) {
        return position;
    }

    //TODO: Create methods to handle popup menu item clicks
    public void onDialogResourceItemClick(int position) {

        startActivity(new Intent(getActivity(), ResourceActivity.class)
                .putExtra("Process", mProcessArray.get(position)));

    }

    public void getPlanProcesses(String planID) {

        //Build Uri to access correct process
        Uri builtUri = Uri.parse(BCMApiClient.BASE_URL)
                .buildUpon()
                .appendPath(BCMApiClient.PATH_PROCESSES)
                .appendQueryParameter(BCMApiClient.PARAM_DEPARTMENT_ID, planID)
                .build();

        StringRequest request = new StringRequest(Request.Method.GET,
                builtUri.toString(), onSuccess, onFailure);

        mRequestQueue.add(request);

    }

    public static class OnDialogItemClick {

        public static void itemShare(int position) {
        }

        public static void itemResources(int position) {
        }

        public static void itemDetails(int position) {
        }

    }


}
