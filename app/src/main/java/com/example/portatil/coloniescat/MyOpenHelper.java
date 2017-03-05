package com.example.portatil.coloniescat;

/**
 * Created by Portatil on 03/03/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyOpenHelper extends SQLiteOpenHelper {
    //camps de la taula y les columnes
    public static final String TABLE_PRODUCTES = "colonies";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PREGUNTA = "pregunta";
    public static final String COLUMN_RESPOSTA = "resposta";
    public static final String COLUMN_RESPOSTAUSUARI = "respostausuari";
    public static final String COLUMN_ENCERTAT = "encertat";
    //nom de la bs i la seva versio
    private static final String DATABASE_NAME = "coloniescat.db";
    private static final int DATABASE_VERSION = 1;

    //Creacio de la base de dades
    private static final String DATABASE_CREATE = "create table "
            + TABLE_PRODUCTES + "( " +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_PREGUNTA + " text not null, " +
            COLUMN_RESPOSTA + " text not null, " +
            COLUMN_RESPOSTAUSUARI + " text not null, " +
            COLUMN_ENCERTAT + " integer not null);";

    //Para poder hacer inserts a la base de datos desdde el propio openhelper
    private MyOpenHelper bdAjudant;

    //metode  constructor del openhelper per quan necesitem acedir a la BD
    public MyOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //en el on create genera la nova base de dades si es el primer cop, sino tindriem que mirar version actuals
    //y fer un upgrade de la base de dades
    //a mes inserts de base per tal de tenir informacio en la propia base de dades
    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_CREATE);
        insertsBaseDades(database,"Busca la teva propia imatge al CCCB","Hi ha un mirall al sostre del vestibul","no reposta",0);
        insertsBaseDades(database,"Quina funcio te actualment el convent dels Angels?","Museu i arxiu","no resposta",0);
        insertsBaseDades(database,"Quina estil arquitectonic te l'esglesia?","Modernista","no resposta",0);
        insertsBaseDades(database,"Escriu les lletres que trobem sota del escut","HVGVET","no resposta",0);
        insertsBaseDades(database,"Quina obra es dur a terme acualment?","Magia junior","no resposta",0);
        insertsBaseDades(database,"A mes del Raval quins mes barris componen la Barceloneta?","Gotic,Barceloneta,St Pere,Sta Caterina i la Ribera","no resposta",0);
        insertsBaseDades(database,"Quines caracteristiques te un edifici modernista?","Innovacio","no resposta",0);


    }
    //de moment no serveix per res ja que la versio sempre es la 1
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* aqui ira codigo sobre reinciar la tabla*/

    }

    //insert a la base de dades quan es crea
    public void insertsBaseDades(SQLiteDatabase database,String descripcio, String resposta, String respostaUsuari , int encertat){

        ContentValues valors =new ContentValues();

        valors.put(COLUMN_PREGUNTA, descripcio);
        valors.put(COLUMN_RESPOSTA, resposta);
        valors.put(COLUMN_RESPOSTAUSUARI, respostaUsuari);
        valors.put(COLUMN_ENCERTAT, encertat);
        database.insert(TABLE_PRODUCTES,null,valors);

    }



}
