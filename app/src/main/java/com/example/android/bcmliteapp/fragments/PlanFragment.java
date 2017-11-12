package com.example.android.bcmliteapp.fragments;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.android.bcmliteapp.PlanDetailActivity;
import com.example.android.bcmliteapp.adapters.PlanAdapter;
import com.example.android.bcmliteapp.clients.BCMApiClient;
import com.example.android.bcmliteapp.models.Plan;
import com.example.android.bcmliteapp.R;
import com.example.android.bcmliteapp.utils.SQLiteHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Keketso on 11/09/2017.
 */

public class PlanFragment extends Fragment {

    //Member Variables
    private static final String LOG_TAG = PlanFragment.class.getSimpleName();
    private RecyclerView mPlanList;
    private ArrayList<Plan> mPlanListArray;
    private Gson mGson;
    private RequestQueue mRequestQueue;
    private ProgressDialog mProgressDialog;
    private final Response.Listener<String> onSuccess = new Response.Listener<String>() {

        @Override
        public void onResponse(String response) {

            //Deserialize Json response to Plan object array and set list adapter
            mPlanListArray = new ArrayList<>(Arrays.asList(mGson.fromJson(response, Plan[].class)));

/*            // Put plans into database
            mSQLiteHelper = new SQLiteHelper(getContext());
            SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
            SQLiteContract.SQLitePlan.addPlans(getContext(), mPlanListArray, db);

            //Get plans from data base
            db = mSQLiteHelper.getReadableDatabase();
            ArrayList<Plan> plans = SQLiteContract.SQLitePlan.getPlans(db);*/

            Log.i(LOG_TAG, response);

            //Create plan adapter with plans from database
            PlanAdapter mPlanAdapter = new PlanAdapter(getActivity(),
                    mPlanListArray, PlanFragment.this);
            mPlanList.setAdapter(mPlanAdapter);

            //Close progress dialog
            mProgressDialog.dismiss();

        }
    };
    private final Response.ErrorListener onFailure = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            //Close progress dialog
            mProgressDialog.dismiss();

            //Print response to error log
            Log.e("Plans", error.toString());
        }
    };
    private SQLiteHelper mSQLiteHelper;

    //Constructor
    public PlanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_plans, container, false);
        mPlanList = (RecyclerView) rootView.findViewById(R.id.lst_plans);

        //Set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mPlanList.setLayoutManager(layoutManager);

        //Create new Volley requestQueue to retrieve data
        mRequestQueue = Volley.newRequestQueue(getContext());

        //Gson object to deserialize Json response
        mGson = new GsonBuilder().create();

        //Show progress dialog
        mProgressDialog = ProgressDialog
                .show(getContext(), "Please Wait", "Retrieving Plans", true, true);
        mProgressDialog.show();

        //Plug data from volley request to RecyclerView
//        BCMApiClient.getPlans(mRequestQueue);
        getPlans();

        return rootView;
    }

    public void onItemClick(int position) {

        startActivity(new Intent(getActivity(), PlanDetailActivity.class).putExtra("Plan",
                mPlanListArray.get(position)));

        Log.i(LOG_TAG, String.valueOf(position) + " Item Clicked.");
    }

    public void getPlans() {

        //Build Uri to access available plans
        Uri builtUri = Uri.parse(BCMApiClient.BASE_URL)
                .buildUpon()
                .appendPath(BCMApiClient.PATH_PLANS)
                .appendQueryParameter(BCMApiClient.PARAM_USER_ID,
                        "45391346-cdf4-49e0-8d7d-5014381a6516")//TODO: Update
                .build();

        StringRequest request = new StringRequest(Request.Method.GET,
                builtUri.toString(), onSuccess, onFailure);

        mRequestQueue.add(request);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
/*        // Close database connection
        mSQLiteHelper.close();*/

    }
}
