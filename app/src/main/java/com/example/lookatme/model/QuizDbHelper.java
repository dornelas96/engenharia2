package com.example.lookatme.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lookatme.model.QuizContract.PerguntasTable;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    private  static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 1;


    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_PERGUNTAS_TABLE = "CREATE TABLE " +
                PerguntasTable.TABLE_NAME + " ( " +
                PerguntasTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PerguntasTable.COLUMN_PERGUNTA + " TEXT, " +
                PerguntasTable.COLUMN_OPCAO1 + " TEXT, " +
                PerguntasTable.COLUMN_OPCAO2 + " TEXT, " +
                PerguntasTable.COLUMN_OPCAO3 + " TEXT, " +
                PerguntasTable.COLUMN_RESPOSTA + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_PERGUNTAS_TABLE);
        fillPerguntasTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PerguntasTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillPerguntasTable(){
        Pergunta p1 = new Pergunta("Qual a figura nos olhos?","Quadrado","Triângulo","Circulo",1 );
        adicionaPergunta(p1);
        Pergunta p2 = new Pergunta("Qual a figura nos olhos?","Retângulo","Triângulo","Hexágono",2 );
        adicionaPergunta(p2);
        Pergunta p3 = new Pergunta("Qual a figura nos olhos?","Circulo","Retângulo","Triângulo",3 );
        adicionaPergunta(p3);
    }

    public void adicionaPergunta(Pergunta pergunta){
        ContentValues cv = new ContentValues();
        cv.put(PerguntasTable.COLUMN_PERGUNTA, pergunta.getTextoPergunta());
        cv.put(PerguntasTable.COLUMN_OPCAO1, pergunta.getOpcao1());
        cv.put(PerguntasTable.COLUMN_OPCAO2, pergunta.getOpcao2());
        cv.put(PerguntasTable.COLUMN_OPCAO3, pergunta.getOpcao3());
        cv.put(PerguntasTable.COLUMN_RESPOSTA, pergunta.getRespNum());

        db.insert(PerguntasTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Pergunta> listaTodasPerguntas(){
        ArrayList<Pergunta> perguntasList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + PerguntasTable.TABLE_NAME, null);

        if(c.moveToFirst()){
            do{
                Pergunta pergunta = new Pergunta();
                pergunta.setTextoPergunta(c.getString(c.getColumnIndex(PerguntasTable.COLUMN_PERGUNTA)));
                pergunta.setOpcao1(c.getString(c.getColumnIndex(PerguntasTable.COLUMN_OPCAO1)));
                pergunta.setOpcao2(c.getString(c.getColumnIndex(PerguntasTable.COLUMN_OPCAO2)));
                pergunta.setOpcao3(c.getString(c.getColumnIndex(PerguntasTable.COLUMN_OPCAO3)));
                pergunta.setRespNum(c.getInt(c.getColumnIndex(PerguntasTable.COLUMN_RESPOSTA)));
                perguntasList.add(pergunta);
            }while (c.moveToNext());
        }
        c.close();
        return perguntasList;
    }


}
