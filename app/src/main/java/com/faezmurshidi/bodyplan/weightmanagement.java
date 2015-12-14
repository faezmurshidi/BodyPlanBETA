package com.faezmurshidi.bodyplan;

/**
 * Created by faez on 11/08/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class weightmanagement extends Fragment {

    FloatingActionButton btn;
    View myView;
    SharedPreferences sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.tab_weightmanagement, container, false);

        loadPrefs();
        
        btn = (FloatingActionButton)myView.findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BMI.class));
            }
        });

        return myView;
    }

    private void loadPrefs() {

        sp = getActivity().getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);

        String weight = sp.getString("weight", "90");
        String height = sp.getString("height", "170");

       Float wat = sp.getFloat("water", 100);

        String status = sp.getString("bmi_status", "170");

        Float calorie = sp.getFloat("bmr", 5000);

        String lose_gain = sp.getString("lose_gain", "to");
        Float lose_gain_value = sp.getFloat("lose_gain_value", 20);


        TextView ed = (TextView)myView.findViewById(R.id.weight);
        TextView ed2 = (TextView)myView.findViewById(R.id.height);
        TextView textBMI = (TextView)myView.findViewById(R.id.bmi);
        TextView textCal = (TextView)myView.findViewById(R.id.calorie);
        TextView water = (TextView)myView.findViewById(R.id.water);

        TextView lolgainval = (TextView)myView.findViewById(R.id.lose_gain_value);
        TextView lolgain = (TextView)myView.findViewById(R.id.lose_gain);


        water.setText(String.format("%.2f", wat));


        ed.setText(weight);
        ed2.setText(height);

        textBMI.setText(status);
        textCal.setText(String.format("%.2f", calorie));



        lolgainval.setText(String.format("%.2f", lose_gain_value));
        lolgain.setText(lose_gain);



    }
}
