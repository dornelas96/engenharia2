package com.example.appautismo;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract(){
    }

    public static class PerguntasTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_perguntas";
        public static final String COLUMN_PERGUNTA = "pergunta";
        public static final String COLUMN_IMAGEM = "imagem";//---
        public static final String COLUMN_OPCAO1 = "opcao1";
        public static final String COLUMN_OPCAO2 = "opcao2";
        public static final String COLUMN_OPCAO3 = "opcao3";
        public static final String COLUMN_RESPOSTA = "resposta_num";
    }
}
