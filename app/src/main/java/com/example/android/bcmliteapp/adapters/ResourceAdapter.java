package com.example.android.bcmliteapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bcmliteapp.R;
import com.example.android.bcmliteapp.fragments.ResourceListFragment;
import com.example.android.bcmliteapp.models.Application;
import com.example.android.bcmliteapp.models.Document;
import com.example.android.bcmliteapp.models.Equipment;
import com.example.android.bcmliteapp.models.Skill;
import com.example.android.bcmliteapp.models.ThirdParty;

import java.util.ArrayList;

/**
 * Created by keket on 05/11/2017.
 */

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder> {


    //Member Variables
    //TODO: Put constants in a static utility class
    private static final String CONST_APPLICATION = "applications";
    private static final String CONST_DOCUMENT = "documents";
    private static final String CONST_EQUIPMENT = "equipment";
    private static final String CONST_SKILL = "skills";
    private static final String CONST_THIRD_PARTY = "thirdParties";

    private ResourceListFragment mFragment;
    private ArrayList mData;
    private Object mResource;
    private String mResourceType;

    //Class Constructors


    public ResourceAdapter(ArrayList d, ResourceListFragment f) {
        mFragment = f;
        mData = d;
        mResourceType = f.getResourceType();
    }

    @Override
    public ResourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_resource;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ResourceAdapter.ResourceViewHolder viewHolder =
                new ResourceAdapter.ResourceViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResourceViewHolder holder, int position) {

        //Bind view using bind method from ResourceViewHolder class
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

    //Retrieve a row item from the Recycle view
    public Object getItem(int position) {
        return position;
    }

    public class ResourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Member variables
        private TextView mTxtResourceName;
        private TextView mTxtResourceDescription;
        private TextView mTxtResourceRTO;


        public ResourceViewHolder(View itemView) {
            super(itemView);

            //Inflate view items for resource row
            mTxtResourceName = (TextView) itemView.findViewById(R.id.txt_resource_name);
            mTxtResourceDescription = (TextView) itemView.findViewById(R.id.txt_resource_description);
            mTxtResourceRTO = (TextView) itemView.findViewById(R.id.txt_resource_rto);

            //Call setOnClickListener on the view passed into the constructor
            itemView.setOnClickListener(this);
        }

        //TODO: Make method throw Index out of bounds exception
        void bind(int position) {

            //TODO: Simplify
            //Determine type of resource
            switch (mResourceType) {
                case CONST_SKILL:
                    try {
                        Skill skill = (Skill) mData.get(position);
                        mTxtResourceName.setText(skill.getDescription());
                        mTxtResourceDescription.setText("N/A");
                        mTxtResourceRTO.setText(skill.getRTO());
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    break;

                case CONST_THIRD_PARTY:
                    try {
                        ThirdParty thirdParty = (ThirdParty) mData.get(position);
                        mTxtResourceName.setText(thirdParty.getName());
                        mTxtResourceDescription.setText(thirdParty.getDescription());
                        mTxtResourceRTO.setText(thirdParty.getRTO());
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    break;

                case CONST_DOCUMENT:
                    try {
                        Document document = (Document) mData.get(position);
                        mTxtResourceName.setText(document.getDescription());
                        mTxtResourceDescription.setText("N/A");
                        mTxtResourceRTO.setText(document.getRTO());
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    break;

                case CONST_APPLICATION:
                    try {
                        Application application = (Application) mData.get(position);
                        mTxtResourceName.setText(application.getName());
                        mTxtResourceDescription.setText(application.getDescription());
                        mTxtResourceRTO.setText(application.getRTO());
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    break;

                case CONST_EQUIPMENT:
                    try {
                        Equipment equipment = (Equipment) mData.get(position);
                        ;
                        mTxtResourceName.setText(equipment.getDescription());
                        mTxtResourceDescription.setText("N/A");
                        mTxtResourceRTO.setText(equipment.getRTO());
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    break;
            }


        }

        @Override
        public void onClick(View v) {
            //Set onlclick listener for view holder
            v.setOnClickListener(new ResourceAdapter.OnItemClickListener(getAdapterPosition()));
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
            /****Call  onItemClick Method inside PlanFragment****/
            mFragment.onItemClick(mPosition);
        }
    }
}
