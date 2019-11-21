package com.example.lookatme.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lookatme.R;

public class CategoriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
    }

    public void selecionaCategoria(View view) {

        Intent intent = new Intent(this, NivelActivity.class);
        startActivity(intent);
    }
}
