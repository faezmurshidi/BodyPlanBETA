package com.faezmurshidi.bodyplan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by faez on 12/14/2015.
 */
public class Workout extends MainActivity {

    Button btnStart, btnStop, btn;
    TextView textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.workout_start, null, false);
        mDrawerLayout.addView(contentView, 0);



        textViewTime = (TextView) findViewById(R.id.textView4);
        textViewTime.setText(R.string.workoutOne);



        Button start = (Button) findViewById(R.id.startbutton);
        start.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Workout.this, startWorkout.class);

                startActivity(in);

            }









        });
    }
}
