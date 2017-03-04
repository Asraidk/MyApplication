package com.example.portatil.coloniescat;

/**
 * Created by Portatil on 04/03/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TalkerOH {

    // Database fields
    private SQLiteDatabase baseDadesLlegir,baseDadesEscriure;
    private MyOpenHelper bdAjudant;
    private Cursor cursor;

    public TalkerOH(Context context) {
        //per poder comunucarnos amb la base de dades
        bdAjudant = new MyOpenHelper(context);
        //creme dos database per lleguir i modifica la bsasede dades
        baseDadesLlegir= bdAjudant.getReadableDatabase();
        baseDadesEscriure=bdAjudant.getWritableDatabase();
    }
    //per tancar la comunicacio de la base de dades
    @Override
    protected void finalize(){
        baseDadesLlegir.close();
        baseDadesEscriure.close();
    }

    public Cursor carregaTotaLaTaula() {
        // Retorem totes les tasques
        SQLiteDatabase baseDadesEscriure=bdAjudant.getWritableDatabase();

        return baseDadesEscriure.query(bdAjudant.TABLE_PRODUCTES, new String[]{bdAjudant.COLUMN_ID,bdAjudant.COLUMN_PREGUNTA,bdAjudant.COLUMN_RESPOSTA,bdAjudant.COLUMN_RESPOSTAUSUARI,bdAjudant.COLUMN_ENCERTAT},
                null, null,
                null, null, bdAjudant.COLUMN_ID);
    }

    //metode per fer updates dels camps que deixem que es puguin cambiar mitjançant la PK de la row
    public void modificaResposta(long id, String resposta) {
        String respostaCorrecte;
        respostaCorrecte=carregarCodi(id);
        // Modifiquem els valors de las tasca amb clau primària "id"
        ContentValues valors = new ContentValues();
        valors.put(bdAjudant.COLUMN_RESPOSTAUSUARI, resposta);

        if(respostaCorrecte.equals(resposta)){
            valors.put(bdAjudant.COLUMN_ENCERTAT, 1);
        }else{
            valors.put(bdAjudant.COLUMN_ENCERTAT, 0);
        }

        baseDadesEscriure.update(bdAjudant.TABLE_PRODUCTES,valors, bdAjudant.COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }


    public String carregarCodi(long codi) {

        String respostaCorrecte="";
        // Retorna un cursor només amb el id indicat
        cursor= baseDadesLlegir.query(bdAjudant.TABLE_PRODUCTES, new String[]{bdAjudant.COLUMN_RESPOSTA},
                bdAjudant.COLUMN_ID + "=?", new String[]{String.valueOf(codi)},
                null, null, null);
        if(cursor.moveToFirst()){
            respostaCorrecte= cursor.getString(0);
        }else{
            respostaCorrecte="Problemes en la base de dades";
        }

        return respostaCorrecte;
        //
    }

}
