package com.example.af_listadecontatos_2021.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_CONTATOS";
    public static String TABELA_CONTATOS = "CONTATOS";


    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE IF NOT EXISTS " + TABELA_CONTATOS + " (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nome TEXT NOT NULL, " +
                "Apelido TEXT, " +
                "Email TEXT NOT NULL, " +
                "Telefone TEXT NOT NULL, " +
                "Grupo TEXT NOT NULL " + ")";
        try{
            db.execSQL(sql);
            Log.i("INFO DB", "Sucesso Ao Criar a Tabela");
        }catch (Exception e){
            Log.i("INFO DB", "Erro Ao Criar a Tabela : " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+ TABELA_CONTATOS + ";";
        try{
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB", "Sucesso Ao Atualizar a Tabela");
        }catch (Exception e){
            Log.i("INFO DB", "Erro Ao Atualizar a Tabela : " + e.getMessage());
        }
    }
}
