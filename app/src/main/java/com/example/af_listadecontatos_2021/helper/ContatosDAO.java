package com.example.af_listadecontatos_2021.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.af_listadecontatos_2021.model.Contatos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ContatosDAO implements IContatoDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase ler;

    public ContatosDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public boolean Salvar(Contatos contatos) {

        ContentValues cv = new ContentValues();
        cv.put("Nome",contatos.getNomeContato());
        cv.put("Apelido",contatos.getApelidoContato());
        cv.put("Email",contatos.getEmailContato());
        cv.put("Telefone",contatos.getNumeroContato());
        cv.put("Grupo",contatos.getGrupoContato());

        try{
            escreve.insert(DbHelper.TABELA_CONTATOS,null,cv);
        }catch (Exception e){
            Log.e("INFO","Error : " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean Atualizar(Contatos contatos) {

        ContentValues cv = new ContentValues();
        cv.put("Nome",contatos.getNomeContato());
        cv.put("Apelido",contatos.getApelidoContato());
        cv.put("Email",contatos.getEmailContato());
        cv.put("Telefone",contatos.getNumeroContato());
        cv.put("Grupo",contatos.getGrupoContato());

        try{
            String[] args = {contatos.getId().toString()};
            escreve.update(DbHelper.TABELA_CONTATOS,cv,"Id=?",args);
        }catch (Exception e){
            Log.e("INFO","Error : " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean Deletar(Contatos contatos) {

        try{
            String[] args = {contatos.getId().toString()};
            escreve.delete(DbHelper.TABELA_CONTATOS,"Id=?", args);
        }catch (Exception e){
            Log.e("INFO","Error : " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Contatos> Listar() {
        List<Contatos> contatos = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_CONTATOS + ";";
        Cursor c = ler.rawQuery(sql, null);

        while(c.moveToNext()){
            Contatos contato = new Contatos();

            Long id = c.getLong(c.getColumnIndex("Id"));
            String nomeContato = c.getString(c.getColumnIndex("Nome"));
            String apelidoContato = c.getString(c.getColumnIndex("Apelido"));
            String emailContato = c.getString(c.getColumnIndex("Email"));
            String telefoneContato = c.getString(c.getColumnIndex("Telefone"));
            String GrupoContato = c.getString(c.getColumnIndex("Grupo"));

            contato.setId(id);
            contato.setNomeContato(nomeContato);
            contato.setApelidoContato(apelidoContato);
            contato.setEmailContato(emailContato);
            contato.setNumeroContato(telefoneContato);
            contato.setGrupoContato(GrupoContato);

            contatos.add(contato);
        }

        return contatos;
    }

}
