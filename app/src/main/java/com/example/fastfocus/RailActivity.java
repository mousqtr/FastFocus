package com.example.fastfocus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class RailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent home_intent = new Intent(getApplicationContext(),MainActivity.class);
        Intent exercise1_intent = new Intent(getApplicationContext(),BreathActivity.class);
        Intent exercise3_intent = new Intent(getApplicationContext(),InfinityActivity.class);

        switch (item.getItemId()){
            case R.id.back:
                startActivity(exercise1_intent);
                finish();
                return true;
            case R.id.home:
                startActivity(home_intent);
                finish();
                return true;
            case R.id.next:
                startActivity(exercise3_intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
