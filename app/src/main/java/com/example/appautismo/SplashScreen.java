package com.example.appautismo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
                Intent intent;

                if (preferences.contains("nomeUsuario")){
                    intent = new Intent(SplashScreen.this, MenuActivity.class);
                } else {
                    intent = new Intent(SplashScreen.this, ActivityInicial.class);
                }
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
