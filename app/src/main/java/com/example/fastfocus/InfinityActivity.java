package com.example.fastfocus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class InfinityActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        webView = findViewById(R.id.web_view);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String file = "file:android_asset/infinity.gif";
        webView.loadUrl(file);

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
        Intent exercise2_intent = new Intent(getApplicationContext(),RailActivity.class);
        Intent exercise4_intent = new Intent(getApplicationContext(),MentalActivity.class);

        switch (item.getItemId()){
            case R.id.back:
                startActivity(exercise2_intent);
                finish();
                return true;
            case R.id.home:
                startActivity(home_intent);
                finish();
                return true;
            case R.id.next:
                startActivity(exercise4_intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
