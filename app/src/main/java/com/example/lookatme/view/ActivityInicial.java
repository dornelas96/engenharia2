package com.example.lookatme.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lookatme.R;

public class ActivityInicial extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
    }

    public void salvarNome(View view) {

        EditText editTextNome = findViewById(R.id.nomeInput);

        String nomeUsuario = editTextNome.getText().toString();

        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nomeUsuario", nomeUsuario);
        editor.apply();

        Intent intent = new Intent(this, CategoriaActivity.class);
        startActivity(intent);
    }
}