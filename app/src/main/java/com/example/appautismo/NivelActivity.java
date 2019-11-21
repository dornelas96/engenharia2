package com.example.appautismo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NivelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);
    }

    public void iniciaQuiz(View view) {

        Intent intent = new Intent(this, StartinScreenActivity.class);
        startActivity(intent);
    }

}
