package com.example.android.bcmliteapp.clients;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by keket on 10/09/2017.
 */

public class BCMApiClient {

//    public final static String BASE_URL = "http://192.168.43.42/api";

//    public final static String BASE_URL = "http://192.168.8.104/api";

//    public final static String BASE_URL = "http://192.168.248.2/api";

//    public final static String BASE_URL = "http://192.168.43.98/api";

//    public final static String BASE_URL = "http://192.168.8.100/api";

    //Abidjan
    public final static String BASE_URL = "http://10.192.1.107/api";


    public final static String PATH_ORGANISATIONS = "organisations";
    public final static String PATH_PLANS = "plans";
    public final static String PATH_PROCESSES = "processes";
    public final static String PATH_STEPS = "steps";
    public final static String PATH_SKILLS = "skills";
    public final static String PATH_DOCUMENTS = "documents";
    public final static String PATH_APPLICATIONS = "applications";
    public final static String PATH_THIRDPARTIES = "thirdParties";
    public final static String PATH_EQUIPMENT = "equipment";

    public final static String PARAM_USER_ID = "userID";
    public final static String PARAM_DEPARTMENT_ID = "departmentID";
    public final static String PARAM_PLAN_ID = "planID";
    public final static String PARAM_PROCESS_ID = "processID";
    public final static String PARAM_DEPARTMENT_PLAN_ID = "departmentPlanID";
    private static final Response.Listener<String> onSuccess = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("Plans", response);
        }
    };
    private static final Response.ErrorListener onFailure = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i("Plans", error.toString());
        }
    };
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, Header[] headers, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(context, /*getAbsoluteUrl(url)*/ url, headers, params, responseHandler);
    }

    public static void getPlans(RequestQueue requestQueue) {

        //Build Uri to access available plans
        Uri builtUri = Uri.parse(BCMApiClient.BASE_URL)
                .buildUpon()
                .appendPath(BCMApiClient.PATH_PLANS)
                .appendQueryParameter(BCMApiClient.PARAM_USER_ID, "45391346-cdf4-49e0-8d7d-5014381a6516")//TODO: Update
                .build();

        StringRequest request = new StringRequest(Request.Method.GET, builtUri.toString(), onSuccess, onFailure);
        requestQueue.add(request);
    }
}
