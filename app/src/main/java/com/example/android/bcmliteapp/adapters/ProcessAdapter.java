package com.example.android.bcmliteapp.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bcmliteapp.fragments.PlanProcessesFragment;
import com.example.android.bcmliteapp.models.Process;

import com.example.android.bcmliteapp.R;

import java.util.ArrayList;

/**
 * Created by keket on 21/09/2017.
 */

public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ProcessViewHolder> {

    //Member Variables
    private static final String LOG_TAG = ProcessAdapter.class.getSimpleName();
    private PlanProcessesFragment mFragment;
    private ArrayList mData;
    private Process mProcess;

    public ProcessAdapter(ArrayList d, PlanProcessesFragment f) {

        mData = d;
        mFragment = f;
    }

    @Override
    public ProcessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_process;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ProcessAdapter.ProcessViewHolder viewHolder = new ProcessAdapter.ProcessViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProcessViewHolder holder, int position) {
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


    public class ProcessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTxtProcessName;
        TextView mTxtProcessDescription;
        TextView mTxtProcessRTO;
        TextView mTxtProcessOptions;
        TextView mTxtProcessNumber;

        public ProcessViewHolder(View v) {
            super(v);

            //Inflate view items for process row
            mTxtProcessName = (TextView) v.findViewById(R.id.txt_process_name);
            mTxtProcessDescription = (TextView) v.findViewById(R.id.txt_process_description);
            mTxtProcessRTO = (TextView) v.findViewById(R.id.txt_process_rto);
            mTxtProcessOptions = (TextView) v.findViewById(R.id.txt_process_options);
            mTxtProcessNumber = (TextView) v.findViewById(R.id.txt_process_number);

        }

        void bind(int position) {
            mProcess = null;
            mProcess = (Process) mData.get(position);

            mTxtProcessNumber.setText(new StringBuilder().append("P").append(position + 1));
            mTxtProcessName.setText(mProcess.getName());
            mTxtProcessDescription.setText(mProcess.getDescription());
            mTxtProcessRTO.setText(mProcess.getRto());
            mTxtProcessOptions.setOnClickListener(new OnOptionsClickListener(getAdapterPosition()));
            mTxtProcessOptions.setTag(getItem(position));

        }

        @Override
        public void onClick(View v) {

            //Set onlclick listener for view holder
            v.setOnClickListener(new ProcessAdapter.OnItemClickListener(getAdapterPosition()));
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
            /****Call  onItemClick Method inside ProcessFragment****/
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
            TextView txtOptions = (TextView) v.findViewById(R.id.txt_process_options);

            //creating a popup menu
            PopupMenu popup = new PopupMenu(v.getContext(), txtOptions);

            //inflating menu from xml resource
            popup.inflate(R.menu.options_process);

            //Handle process option dialog click events
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_item_process_details:
                            PlanProcessesFragment.OnDialogItemClick.itemDetails(mPosition);
                            break;

                        case R.id.menu_item_process_resources:
                            Log.i(LOG_TAG, Integer.toString(mPosition));
                            mFragment.onDialogResourceItemClick(mPosition);

                            break;

                        case R.id.menu_item_process_share:
                            PlanProcessesFragment.OnDialogItemClick.itemShare(mPosition);
                            break;
                    }
                    return false;
                }
            });

            popup.show();
        }
    }
}

