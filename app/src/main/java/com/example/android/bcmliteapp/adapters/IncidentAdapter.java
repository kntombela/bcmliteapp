package com.example.android.bcmliteapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bcmliteapp.R;
import com.example.android.bcmliteapp.fragments.IncidentFragment;
import com.example.android.bcmliteapp.models.Incident;
import com.example.android.bcmliteapp.models.IncidentType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by keket on 25/11/2017.
 */

public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder> {

    //Member Variables
    private static final String LOG_TAG = IncidentAdapter.class.getSimpleName();
    private IncidentFragment mFragment;
    private ArrayList mData;
    private Incident mIncident;

    public IncidentAdapter(ArrayList d, IncidentFragment f) {
        mData = d;
        mFragment = f;
    }

    @Override
    public IncidentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_incident;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        IncidentAdapter.IncidentViewHolder viewHolder = new IncidentAdapter.IncidentViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IncidentViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        //Return size of array
        if (mData.size() <= 0)
            return 1;
        else
            return mData.size();
    }

    public class IncidentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTxtIncidentType;
        TextView mTxtIncidentDescription;
        TextView mTxtIncidenDate;
        TextView mTxtIncidentNumber;

        public IncidentViewHolder(View v) {
            super(v);

            //Inflate view items for incident row
            mTxtIncidentType = (TextView) v.findViewById(R.id.txt_incident_type);
            mTxtIncidentDescription = (TextView) v.findViewById(R.id.txt_incident_description);
            mTxtIncidenDate = (TextView) v.findViewById(R.id.txt_incident_time);
            mTxtIncidentNumber = (TextView) v.findViewById(R.id.txt_incident_number);
        }

        public void bind(int position) {
            //Initialise incident object
            mIncident = null;
            mIncident = (Incident) mData.get(position);

            mTxtIncidentType.setText(IncidentType.parseIncidentType(mIncident.getType()));
            mTxtIncidentDescription.setText(mIncident.getDescription());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            mTxtIncidenDate.setText(format.format(mIncident.getDate()));
            mTxtIncidentNumber.setText(Integer.toString(position + 1));

        }

        @Override
        public void onClick(View v) {
            //Set onlclick listener for view holder
            v.setOnClickListener(new IncidentAdapter.OnItemClickListener(getAdapterPosition()));
        }

    }

    /********* Called when Item click in RecyclerView ************/
    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            /****Call  onItemClick Method inside IncidentFragment****/
            mFragment.onItemClick(mPosition);
        }
    }
}
