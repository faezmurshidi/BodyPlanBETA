package com.faezmurshidi.bodyplan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class BMI extends MainActivity {

    SharedPreferences.Editor edit;
    FloatingActionButton btn;
    EditText weight,height;
    SharedPreferences preferences;
    float fheight,fweight,bmi;
    int age;
    String sex,sage;
    float need;
    String lose_gain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.bmi, null, false);
        mDrawerLayout.addView(contentView, 0);

        preferences = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        edit = preferences.edit();

        sex = preferences.getString("sex", "male");
        sage = preferences.getString("age", "20");

        age = Integer.valueOf(sage);


        weight = (EditText)findViewById(R.id.weightinput);
        height = (EditText)findViewById(R.id.heightinput);










    }

    private void getCalorie(float fheight, float fweight, String sex, int age,double bmi) {

        /*FORMULA FOR BMR:
        W = weight in kilograms (weight (lbs)/2.2) =weight in kg
        H = height in centimeters (inches x 2.54) =height in cm
                A = age in years

        Men: BMR=66.47+ (13.75 x W) + (5.0 x H) - (6.75 x A)
        Women: BMR=665.09 + (9.56 x W) + (1.84 x H) - (4.67 x A)*/

        double bmr = 0;
        double fat=0;

        //Adult Body Fat % = (1.20 x BMI) + (0.23 x Age) – (10.8 x gender) – 5.4

       // using gender male= 1, female= 0.


        switch (sex){
            case "Male":
                bmr = 66.47 + (13.75* fweight) + (5 + fheight) - (6.75 * age);
                fat=(1.20*bmi) + (0.23*age)-(10.8 *1)-5.4;
                break;
            case "Feemale":bmr=665.09 + (9.56 * fweight) + (1.84 * fheight) - (4.67 * age);
                fat=(1.20*bmi) + (0.23*age)-(10.8 *0)-5.4;
                break;
        }

        edit.putFloat("bmr", (float) bmr);
        edit.putFloat("fat", (float) fat);

    }

    private void calculateBMI(float fweight, float fheight) {

       //Formula: weight (kg) / [height (m)]2

        bmi = fweight/(fheight*fheight);

        edit.putFloat("bmi", bmi);


        if (bmi < 18.5) {
            //return "underweight";
            need = ((fheight*fheight)*19)-fweight;
            lose_gain = "To gain";
        } else if (bmi < 25) {
            lose_gain = "normal";
        }  else {
            need = fweight-((fheight*fheight)*25);
            lose_gain = "To lose";
        }

        edit.putString("lose_gain", lose_gain);
        edit.putFloat("lose_gain_value", need);






        String status = getBMIstatus(bmi);

        edit.putString("bmi_status", status.toString());
        edit.commit();

    }

    private String getBMIstatus(float bmi) {


        /*Below 18.5	Underweight
        18.5 – 24.9	Normal or Healthy Weight
        25.0 – 29.9	Overweight
        30.0 and Above	Obese*/


        if (bmi < 18.5) {
            return "underweight";
        } else if (bmi < 25) {
            return "normal";
        } else if (bmi < 30) {
            return "overweight";
        } else {
            return "obese";
        }





    }

    public void update(View view) {

        if(weight.getText().toString().equals("") && height.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(), "Please provide input", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainTab.class));

        }


        edit.putString("weight", weight.getText().toString());
        edit.putString("height", height.getText().toString());


        fweight = Float.valueOf(weight.getText().toString());
        fheight = Float.valueOf(height.getText().toString());

        calculateBMI(fweight, fheight);

        getCalorie(fheight, fweight, sex, age,bmi);

        getWater(fweight);



        edit.commit();
        startActivity(new Intent(BMI.this,MainTab.class));

    }

    private void getWater(float fweight) {


        Double pound = fweight *2.20462;


        double water = pound * 0.5;

        edit.putFloat("water", (float) water);
        edit.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fourth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



}