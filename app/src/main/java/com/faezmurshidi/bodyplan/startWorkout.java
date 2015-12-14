package com.faezmurshidi.bodyplan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by faez on 12/14/2015.
 */
public class startWorkout extends MainActivity {


    TextView textViewTime,worktittle,noWork,workdesc;
    ImageView workoutpic;
    int flags=0;
    int rest=0;
    final CounterClass timer = new CounterClass(120000, 1000);
    //rest based on 30sec
    final CounterClass restinterval = new CounterClass(60000,1000);
    int points = 0;
    String[] werk,werklist;
    int images_id ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.workoutsession, null, false);
        mDrawerLayout.addView(contentView, 0);

        textViewTime = (TextView) findViewById(R.id.countdowntimer);
        worktittle = (TextView) findViewById(R.id.workouttittle);
        noWork = (TextView)findViewById(R.id.workoutNo);
        workdesc = (TextView)findViewById(R.id.workoutdescription);
        workoutpic =(ImageView)findViewById(R.id.workoutpic);
        werk = getResources().getStringArray(R.array.workoutdesc);
        werklist = getResources().getStringArray(R.array.workoutlist);
        workdesc.setMovementMethod(new ScrollingMovementMethod());






        //initial
        workdesc.setText("When you can barely squeeze a workout into your day, taking time to focus on flexibility may feel like, well, a stretch. But stretching is an important part of fitness: It can improve your range of motion, increase circulation, and calm your mind—which may help fend off injuries and illness, as well as bring on a better night’s sleep. Do the complete series once daily. Deepen each stretch with every exhalation, and stop if you feel any strain or pain.");
        worktittle.setText("Stretching");
        noWork.setText("Get Ready");
        workoutpic.setImageResource(R.drawable.workout_stretch);



        //workout based on 2min
        //textViewTime.setText("02:00");
        restinterval.start();





    }

    CountDownTimer newtimer = new CountDownTimer(30000, 1000) {




        @Override
        public void onTick(long millisUntilFinished) {

            long scds =0;
            scds=(millisUntilFinished/1000);
            textViewTime.setText(""+scds);


            worktittle.setText("Rest Interval");
            workoutpic.setImageResource(R.mipmap.ic_launcher);
            noWork.setText("Rest: " + flags + "/6");
            workdesc.setText("Take a deep breath.");




        }

        @Override
        public void onFinish() {
            textViewTime.setText("Completed");
            noWork.setText("Workout: " + flags + "/7");

            if (flags==7){

                worktittle.setText("Workout Done");
                workoutpic.setImageResource(R.mipmap.ic_launcher);
                noWork.setText("Rest: " + flags + "/7");
                workdesc.setText("Congratulations! You finish your workout");
                startActivity(new Intent(getApplicationContext(),Workout.class));
            }else {
                workdesc.setText(werk[flags]);
                worktittle.setText(werklist[flags]);
                images_id = getResources().getIdentifier("drawable/"+"workout_"+flags, null, getPackageName());
                workoutpic.setImageResource(images_id);



                restinterval.start();
            }

        }
    };






    public class CounterClass extends CountDownTimer{




        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture,countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished){
            long millis = millisUntilFinished;
            String hms=String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            System.out.println(hms);
            textViewTime.setText(hms);

        }

        @Override
        public void onFinish() {

            textViewTime.setText("Completed");
            try {
                wait(1500);
                int workk = flags+1;

                noWork.setText("Workout: " + workk + "/6");

                newtimer.start();


                flags++;
                textViewTime.setText("Completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }










        }


    }



}
