package com.example.fastfocus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class BreathActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 5000;//10 mins
    private TextView countdownText;
    private Button countdownButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = START_TIME_IN_MILLIS;
    private boolean timerRunning;

    private int numImage = 0;
    private ImageView imageLungs;
    private boolean breath_command = true;// true inspire
    private TextView breathCommand;

    private int breathsCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath);

        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_button);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

        imageLungs = (ImageView) findViewById(R.id.image_lungs);
        breathCommand = (TextView) findViewById(R.id.inspire_expire);

        updateTimer();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }






    public void startStop(){
        if(timerRunning){
            stopTimer();
        }else{
            startTimer();
        }
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                if(breath_command){
                    numImage++;
                }else{
                    numImage--;
                }
                changeImage(numImage);
                updateTimer();
            }

            @Override
            public void onFinish() {
                if(breathsCount<5) {
                    countdownButton.setText("START");
                    timeLeftInMilliseconds = 5000;
                    breath_command = !(breath_command);
                    breathsCount++;
                    changeText(breath_command);
                    changeImage(numImage);
                    updateTimer();
                    startTimer();
                }else{
                    exerciseFinish();
                }
            }
        }.start();

        countdownButton.setBackgroundColor(getResources().getColor(R.color.colorErable));
        countdownButton.setText("PAUSE");
        timerRunning = true;
    }

    public void exerciseFinish(){
        countDownTimer.cancel();
        countdownButton.setBackgroundColor(getResources().getColor(R.color.colorDarkGrey));
        countdownButton.setText("Exercice suivant");
        breathCommand.setText(" ");
        countdownText.setText(" ");
        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_exercise = new Intent(getApplicationContext(), RailActivity.class);
                startActivity(next_exercise);
                finish();
            }
        });
        timerRunning = false;
    }

    public void changeText(boolean text){
        if(text == true){
            breathCommand.setText(R.string.inspirer);
        }else{
            breathCommand.setText(R.string.expirer);
        }
    }

    public void changeImage(int numImage){
        switch(numImage) {
            case 1:
                imageLungs.setImageResource(R.drawable.lungs_1);
                break;
            case 2:
                imageLungs.setImageResource(R.drawable.lungs_2);
                break;
            case 3:
                imageLungs.setImageResource(R.drawable.lungs_3);
                break;
            case 4:
                imageLungs.setImageResource(R.drawable.lungs_4);
                break;
            case 5:
                imageLungs.setImageResource(R.drawable.lungs_5);
                break;
            default:
                imageLungs.setImageResource(R.drawable.lungs_0);
        }

    }

    public void stopTimer(){
        countDownTimer.cancel();
        countdownButton.setBackgroundColor(getResources().getColor(R.color.colorBrown));
        countdownButton.setText("START");
        timerRunning = false;
    }

    public void updateTimer(){
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem item_back = menu.getItem(0);
        item_back.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent home_intent = new Intent(getApplicationContext(),MainActivity.class);
        Intent exercise2_intent = new Intent(getApplicationContext(),RailActivity.class);

        switch (item.getItemId()){
            case R.id.home:
                startActivity(home_intent);
                finish();
                return true;
            case R.id.next:
                startActivity(exercise2_intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
