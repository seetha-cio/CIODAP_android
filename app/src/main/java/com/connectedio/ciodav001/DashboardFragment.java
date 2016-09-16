package com.connectedio.ciodav001;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class DashboardFragment extends Fragment implements View.OnClickListener {

    Button deviceScanner,newSurvey,trialSurvey,listSurvey,dashReports,dashTutorial;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dashboard,container,false);
        
        deviceScanner= (Button) view.findViewById(R.id.deviceScanner);
        newSurvey= (Button) view.findViewById(R.id.newSurvey);
        trialSurvey= (Button) view.findViewById(R.id.trialSurvey);
        listSurvey= (Button) view.findViewById(R.id.listSurvey);
        dashTutorial= (Button) view.findViewById(R.id.dashTutorial);

        deviceScanner.setOnClickListener(this);
        newSurvey.setOnClickListener(this);
        trialSurvey.setOnClickListener(this);
        listSurvey.setOnClickListener(this);
        dashTutorial.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id == R.id.newSurvey) {
            // Handle the camera action
            Intent i = new Intent(getActivity(),NewSurveyActivity.class);
            startActivity(i);
            // Toast.makeText(MainActivity.this, "Testing", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.deviceScanner){
            // Handle the camera action
            Intent i = new Intent(getActivity(),DeviceScannerActivity.class);
            startActivity(i);

        }else if(id==R.id.trialSurvey){
            // Handle the camera action
            Intent i = new Intent(getActivity(),SurveyResultActivity.class);
            startActivity(i);

        }else if(id==R.id.listSurvey){
            // Handle the camera action
            Intent i = new Intent(getActivity(),SignalStrengthActivity.class);
            startActivity(i);

        }else if(id==R.id.dashReports){
            // Handle the camera action
            Intent i = new Intent(getActivity(),DeviceScannerActivity.class);
            startActivity(i);

        }else if(id==R.id.dashTutorial){
            // Handle the camera action
            Intent i = new Intent(getActivity(),NewsActivity.class);
            startActivity(i);

        }

    }
}
