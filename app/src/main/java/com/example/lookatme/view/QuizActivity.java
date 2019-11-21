package com.example.lookatme.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lookatme.R;
import com.example.lookatme.controller.QuizController;
import com.example.lookatme.model.Pergunta;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_PONTUACAO = "extraPontuacao";

    public static final String KEY_PONTUACAO = "keyPontuacao";
    public static final String KEY_RESPONDIDO = "keyRespondido";
    public static final String KEY_PERGUNTAS_COUNT = "keyPerguntasCount";
    public static final String KEY_PERGUNTAS_LISTA = "keyPerguntasLista";

    private TextView textViewPergunta;
    private TextView textViewPontuacao;
    private TextView textViewPerguntaCont;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmarProx;

    private ColorStateList textColorDefaultRb;

    private ArrayList<Pergunta> perguntaList;
    private int perguntasContador;
    private int perguntasTotal;
    private Pergunta atualPergunta;

    private int pontuacao;
    private boolean respondida;

    private long backPressedTime;

    private QuizController quizController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewPergunta = findViewById(R.id.text_view_pergunta);
        textViewPontuacao = findViewById(R.id.text_view_pontuacao);
        textViewPerguntaCont = findViewById(R.id.text_view_pergunta_count);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        buttonConfirmarProx = findViewById(R.id.button_confirmar_prox);

        textColorDefaultRb = rb1.getTextColors();

        quizController = new QuizController(this);

        if(savedInstanceState == null) {

            perguntaList = quizController.listaTodasPerguntasEmbaralhadas();
            perguntasTotal = perguntaList.size();

            mostraProximaPergunta();
        } else{
            perguntaList = savedInstanceState.getParcelableArrayList(KEY_PERGUNTAS_LISTA);
            perguntasTotal = perguntaList.size();
            perguntasContador = savedInstanceState.getInt(KEY_PERGUNTAS_COUNT);
            atualPergunta = perguntaList.get(perguntasContador - 1);
            pontuacao = savedInstanceState.getInt(KEY_PONTUACAO);
            respondida = savedInstanceState.getBoolean(KEY_RESPONDIDO);

        }

        buttonConfirmarProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!respondida){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        verificaResposta();
                    }else{
                        Toast.makeText(QuizActivity.this, "Selecione uma resposta!", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    mostraProximaPergunta();
                }
            }
        });


    }

    private void mostraProximaPergunta(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if(perguntasContador < perguntasTotal){
            atualPergunta = perguntaList.get(perguntasContador);

            textViewPergunta.setText(atualPergunta.getTextoPergunta());
            rb1.setText(atualPergunta.getOpcao1());
            rb2.setText(atualPergunta.getOpcao2());
            rb3.setText(atualPergunta.getOpcao3());

            perguntasContador++;
            textViewPerguntaCont.setText("Pergunta: "+perguntasContador + "/"+ perguntasTotal);
            respondida = false;
            buttonConfirmarProx.setText("Confirmar");
        }else{
            terminaQuiz();
        }
    }

    private void verificaResposta(){
        respondida = true;
        RadioButton rbSelecionado = findViewById(rbGroup.getCheckedRadioButtonId());
        int respNum = rbGroup.indexOfChild(rbSelecionado) + 1;

        if(respNum == atualPergunta.getRespNum()){
            pontuacao++;
            textViewPontuacao.setText("Pontuação: "+pontuacao);
        }

        mostraSolucao();
    }

    private void mostraSolucao(){

        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (atualPergunta.getRespNum()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewPergunta.setText("Resposta A é a correta!");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewPergunta.setText("Resposta B é a correta!");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewPergunta.setText("Resposta C é a correta!");
                break;
        }

        if(perguntasContador < perguntasTotal){
            buttonConfirmarProx.setText("Proxima");
        }else{
            buttonConfirmarProx.setText("FIM");
        }
    }

    private void terminaQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_PONTUACAO,pontuacao);
        setResult(RESULT_OK,resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            terminaQuiz();
        }else{
            Toast.makeText(this, "Pressione mais uma vez para sair!", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PONTUACAO, pontuacao);
        outState.putBoolean(KEY_RESPONDIDO, respondida);
        outState.putInt(KEY_PERGUNTAS_COUNT, perguntasContador);
        outState.putParcelableArrayList(KEY_PERGUNTAS_LISTA,perguntaList);
    }
}
