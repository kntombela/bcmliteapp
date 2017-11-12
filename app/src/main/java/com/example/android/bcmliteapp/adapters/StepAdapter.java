package com.example.android.bcmliteapp.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.bcmliteapp.R;
import com.example.android.bcmliteapp.fragments.PlanProcessesFragment;
import com.example.android.bcmliteapp.fragments.PlanStepsFragment;
import com.example.android.bcmliteapp.models.Process;
import com.example.android.bcmliteapp.models.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keket on 03/10/2017.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    int i = 0;
    //Member Variables
    private PlanStepsFragment mFragment;
    private ArrayList mData;
    private Step mStep;
    private View.OnClickListener mOptionsBtnListener = new View.OnClickListener() {

        public void onClick(View v) {


        }
    };

    public StepAdapter(ArrayList d, PlanStepsFragment f) {

        mData = d;
        mFragment = f;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_step;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        StepAdapter.StepViewHolder viewHolder = new StepAdapter.StepViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
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

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTxtStepNumber;
        TextView mTxtStepTitle;
        TextView mTxtStepSummary;
        TextView mStepOptions;
        //        Button mBtnAssign;
        //        Button mBtnComment;
        //        Button mBtnstatus;

        public StepViewHolder(View v) {
            super(v);

            //Inflate view items for process row
            mTxtStepNumber = (TextView) v.findViewById(R.id.txt_step_number);
            mTxtStepTitle = (TextView) v.findViewById(R.id.txt_step_title);
            mTxtStepSummary = (TextView) v.findViewById(R.id.txt_step_summary);
            mStepOptions = (TextView) v.findViewById(R.id.txt_step_options);
//            mBtnAssign = (Button) v.findViewById(R.id.btn_step_assign);
//            mBtnComment = (Button) v.findViewById(R.id.btn_step_comment);
//            mBtnstatus = (Button) v.findViewById(R.id.btn_step_status);

            //Call setOnClickListener on the view passed into the constructor
            v.setOnClickListener(this);
        }

        void bind(int position) {
            mStep = null;
            mStep = (Step) mData.get(position);

            mTxtStepNumber.setText(new StringBuilder().append("S").append(mStep.getNumber()));
            mTxtStepTitle.setText(mStep.getTitle());
            mTxtStepSummary.setText(mStep.getSummary());
            mStepOptions.setOnClickListener(new OnOptionsClickListener(getAdapterPosition()));
            mStepOptions.setTag(getItem(position));
//            mBtnAssign.setTag(getItem(position));
//            mBtnComment.setTag(getItem(position));
//            mBtnstatus.setTag(getItem(position));

        }

        @Override
        public void onClick(View v) {
            //Set onlclick listener for view holder
            v.setOnClickListener(new StepAdapter.OnItemClickListener(getAdapterPosition()));

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
            /****Call  onItemClick Method inside PlanStepsFragment****/
            mFragment.onItemClick(mPosition);
        }
    }

    private class OnOptionsClickListener implements View.OnClickListener {
        private int mPosition;

        public OnOptionsClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View v) {

            //Inflate step options Textview
            TextView txtOptions = (TextView) v.findViewById(R.id.txt_step_options);

            //creating a popup menu
            PopupMenu popup = new PopupMenu(v.getContext(), txtOptions);

            //inflating menu from xml resource
            popup.inflate(R.menu.options_step);

            //adding click listener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_item_step_share:
                            mFragment.onDialogItemClick(mPosition);
                            break;

                        case R.id.menu_item_step_assign:
                            //handle menu2 click
                            break;
                    }
                    return false;
                }
            });

            popup.show();
        }
    }
}
