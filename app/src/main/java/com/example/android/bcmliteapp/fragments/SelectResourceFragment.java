package com.example.android.bcmliteapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.bcmliteapp.R;
import com.example.android.bcmliteapp.ViewModels.ProcessViewModel;
import com.example.android.bcmliteapp.models.Process;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectResourceFragment extends Fragment implements View.OnClickListener {

    //Member Vaariables
    private static final String LOG_TAG = SelectResourceFragment.class.getSimpleName();
    //TODO: Put constants in a static utility class
    private static final String CONST_APPLICATION = "applications";
    private static final String CONST_DOCUMENT = "documents";
    private static final String CONST_EQUIPMENT = "equipment";
    private static final String CONST_SKILL = "skills";
    private static final String CONST_THIRD_PARTY = "thirdParties";
    private ImageView mApplications;
    private ImageView mDocuments;
    private ImageView mEquipment;
    private ImageView mSkill;
    private ImageView mWorkarea;
    private ImageView mThirdParty;
    private ProcessViewModel mProcessViewModel;
    private int mProcessID;

    public SelectResourceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_select_resource,
                container, false);


        //Retrieve data from intent
        Intent intent = getActivity().getIntent();

        if (intent != null && intent.hasExtra("Process")) {

            //Retrieve ProcessViewModel object from the intent that started the activity
            Process process = (Process) intent.getSerializableExtra("Process");
            mProcessID = process.getProcessID();

            Log.i(LOG_TAG, "Intent Received, ProcessID = " + Integer.toString(mProcessID));
        }

        //Inflate views and assign onClick Listeners
        mApplications = (ImageView) rootView.findViewById(R.id.img_resource_applications);
        mApplications.setOnClickListener(this);

        mDocuments = (ImageView) rootView.findViewById(R.id.img_resource_documents);
        mDocuments.setOnClickListener(this);

        mEquipment = (ImageView) rootView.findViewById(R.id.img_resource_equipment);
        mEquipment.setOnClickListener(this);

        mSkill = (ImageView) rootView.findViewById(R.id.img_resource_skills);
        mSkill.setOnClickListener(this);

        mWorkarea = (ImageView) rootView.findViewById(R.id.img_resource_workarea);
        mWorkarea.setOnClickListener(this);

        mThirdParty = (ImageView) rootView.findViewById(R.id.img_resource_third_parties);
        mThirdParty.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            //Start ResourceListFragment based on ProcessID and ResourceType
            case R.id.img_resource_applications:
                mProcessViewModel = new ProcessViewModel(mProcessID,
                        CONST_APPLICATION);
                startResourceListFragment(mProcessViewModel);
                break;

            case R.id.img_resource_documents:
                mProcessViewModel = new ProcessViewModel(mProcessID,
                        CONST_DOCUMENT);
                startResourceListFragment(mProcessViewModel);
                break;

            case R.id.img_resource_equipment:
                mProcessViewModel = new ProcessViewModel(mProcessID,
                        CONST_EQUIPMENT);
                startResourceListFragment(mProcessViewModel);
                break;

            case R.id.img_resource_skills:
                mProcessViewModel = new ProcessViewModel(mProcessID,
                        CONST_SKILL);
                startResourceListFragment(mProcessViewModel);
                break;

            case R.id.img_resource_workarea:
                // do your code
                break;

            case R.id.img_resource_third_parties:
                mProcessViewModel = new ProcessViewModel(mProcessID,
                        CONST_THIRD_PARTY);
                startResourceListFragment(mProcessViewModel);
                break;

            default:
                break;
        }
    }

    public void startResourceListFragment(ProcessViewModel processViewModel) {
        // Create fragment and give it an argument specifying the article it should show
        ResourceListFragment resourceListFragment = new ResourceListFragment();
        Bundle args = new Bundle();
        args.putSerializable("ProcessVM", new ProcessViewModel(processViewModel.getProcessID(),
                processViewModel.getResourceType()));
        resourceListFragment.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame_resource, resourceListFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }
}
