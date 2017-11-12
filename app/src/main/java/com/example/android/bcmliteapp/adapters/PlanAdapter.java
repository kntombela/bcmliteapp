package com.example.android.bcmliteapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bcmliteapp.fragments.PlanFragment;
import com.example.android.bcmliteapp.models.Plan;
import com.example.android.bcmliteapp.R;

import java.util.ArrayList;

/**
 * Created by keket on 11/09/2017.
 */

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {

    //Member Variables
    private Activity mActivity;
    private PlanFragment mFragment;
    private ArrayList mData;
    private Plan mPlan = null;

    public PlanAdapter(Activity a, ArrayList d, PlanFragment f) {

        mActivity = a;
        mData = d;
        mFragment = f;
    }

    @Override
    public PlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_plan;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        PlanViewHolder viewHolder = new PlanViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlanViewHolder holder, int position) {

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


    class PlanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //        public TextView mTxtPlanType;
        public TextView mTxtPlanInvoked;
        private TextView mTxtPlanName;
        private TextView mTxtPlanDescription;
        private TextView mTxtPlanDepartment;
        private Button mBtnPlanShare;
        private Button mBtnPlanView;
        private ImageView mImgPlanType;
        private int mImageID;

        //Onclick Listeners For Step Actions
        private View.OnClickListener mBtnPlanShareListener = new View.OnClickListener() {
            public void onClick(View v) {
                Plan.ActionShare(getAdapterPosition());
            }
        };

        private View.OnClickListener mBtnPlanViewListener = new View.OnClickListener() {
            public void onClick(View v) {
                mFragment.onItemClick(getAdapterPosition());
            }
        };

        public PlanViewHolder(View itemView) {
            super(itemView);

            //Inflate view items for step row
            mTxtPlanName = (TextView) itemView.findViewById(R.id.txt_plan_name);
            mTxtPlanDescription = (TextView) itemView.findViewById(R.id.txt_plan_description);
//            mTxtPlanType = (TextView) itemView.findViewById(R.id.txt_plan_type);
            mTxtPlanDepartment = (TextView) itemView.findViewById(R.id.txt_plan_department);
            mImgPlanType = (ImageView) itemView.findViewById(R.id.img_plan_type);

            //TODO: Update once views have been updated
            /*TextView mTxtPlanInvoked = (TextView) itemView.findViewById(R.id.txt_plan_invoked);
            TextView mTxtPlanDepartment = (TextView) itemView.findViewById(R.id.txt_plan_department);*/

            mBtnPlanShare = (Button) itemView.findViewById(R.id.btn_plan_share);
            mBtnPlanView = (Button) itemView.findViewById(R.id.btn_plan_view);

            //Call setOnClickListener on the view passed into the constructor
            itemView.setOnClickListener(this);
        }

        void bind(int position) {

            mPlan = null;
            mPlan = (Plan) mData.get(position);

            mTxtPlanName.setText(mPlan.getName());
//            mTxtPlanType.setText(mPlan.getType());
            mTxtPlanDescription.setText(mPlan.getDescription());
            mTxtPlanDepartment.setText(mPlan.getDepartmentName());

            //Set plan type image based on plan type
            if (mPlan.getDepartmentName().equals("All")) {
                mImageID = R.drawable.img_imt;
            } else {
                mImageID = R.drawable.img_bcp;
            }
            mImgPlanType.setImageResource(mImageID);

            //TODO: Update once views have been updated
            /*mTxtPlanInvoked.setText(mPlan.isInvoked());
            */

            mBtnPlanShare.setOnClickListener(mBtnPlanShareListener);
            mBtnPlanView.setOnClickListener(mBtnPlanViewListener);
            mBtnPlanShare.setTag(getItem(position));
            mBtnPlanView.setTag(getItem(position));
        }

        @Override
        public void onClick(View v) {

            //Set onlclick listener for view holder
            v.setOnClickListener(new OnItemClickListener(getAdapterPosition()));
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
