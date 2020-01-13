package edu.ib.lab9zpo;

import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds=0;
    private boolean running;

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    private void runTimer() {
        final TextView timeView=(TextView)findViewById(R.id.timerTV);
        final TextView hValueTV=(TextView)findViewById(R.id.hValueTV);
        final TextView vValueTV=(TextView)findViewById(R.id.vValueTV);


        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs=seconds%60;
                String time =String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                double vValue=seconds*Math.sqrt(2*9.81*Double.valueOf(hValueTV.getText().toString())*(780-1.2)/1.2)*0.8*Math.PI*0.2*0.2/4.;
                vValueTV.setText(String.valueOf(vValue));
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    public void startOnClick(View view){
        running=true;
    }
    public void stopOnClick(View view){

        running=false;
    }
    public void resetOnClick(View view){
        final TextView vValueTV=(TextView)findViewById(R.id.vValueTV);
        vValueTV.setText("0");
        running=false;
        seconds=0;
    }
}