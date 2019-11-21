package com.example.lookatme.controller;

import android.content.Context;

import com.example.lookatme.model.QuizDbHelper;
import com.example.lookatme.model.Pergunta;

import java.util.ArrayList;
import java.util.Collections;

public class QuizController {

    private QuizDbHelper quizDbHelper;

    public QuizController(Context context) {
        quizDbHelper = new QuizDbHelper(context);
    }

    public ArrayList<Pergunta> listaTodasPerguntasEmbaralhadas() {
        ArrayList<Pergunta> listaPergunta = quizDbHelper.listaTodasPerguntas();

        Collections.shuffle(listaPergunta);

        return listaPergunta;
    }

    public void adicionaPergunta(final Pergunta pergunta) {

        quizDbHelper.adicionaPergunta(pergunta);
    }
}
