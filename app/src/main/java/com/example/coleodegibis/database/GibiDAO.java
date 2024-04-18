package com.example.coleodegibis.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coleodegibis.entities.Gibi;

import java.util.ArrayList;
import java.util.List;

public class GibiDAO {

    private SQLiteDatabase db;

    public GibiDAO() {}

    public GibiDAO(Context context){
        GibiBD gibiBD = new GibiBD(context, "gibibd", null, 1);
        db = gibiBD.getWritableDatabase();
    }

    public void createTable(SQLiteDatabase db){
        String sql = "CREATE TABLE Gibi(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "titulo TEXT, " +
                "numero TEXT, " +
                "editora TEXT) ";

        db.execSQL(sql);
    }

    public void create(Gibi gibi){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", gibi.getNome());
        contentValues.put("titulo", gibi.getTitulo());
        contentValues.put("numero", gibi.getNumero());
        contentValues.put("editora", gibi.getEditora());
        db.insert("Gibi", null, contentValues);
    }

    @SuppressLint("Range")
    public List<Gibi> read(){
        List<Gibi> gibis = new ArrayList<Gibi>();
        String[] colunas = {"_id", "nome", "titulo", "numero", "editora"};
        Cursor cursorGibis = db.query("Gibi", colunas,
                null, null, null, null, null);
        if(cursorGibis.moveToFirst()){
            do{
                Gibi gibi = new Gibi();
                gibi.set_id(cursorGibis.getLong(cursorGibis.getColumnIndex("_id")));
                gibi.setNome(cursorGibis.getString(cursorGibis.getColumnIndex("nome")));
                gibi.setTitulo(cursorGibis.getString(cursorGibis.getColumnIndex("titulo")));
                gibi.setNumero(cursorGibis.getString(cursorGibis.getColumnIndex("numero")));
                gibi.setEditora(cursorGibis.getString(cursorGibis.getColumnIndex("editora")));
                gibis.add(gibi);
            } while (cursorGibis.moveToNext());
        }
        return gibis;
    }

    @SuppressLint("Range")
    public Gibi read(long _id){
        Gibi gibi = new Gibi();
        String[] colunas = {"_id", "nome", "titulo", "numero", "editora"};
        String selection = "_id = ?";
        String[] selectionArgs = {Long.toString(_id)};

        Cursor cursorGibi = db.query("Gibi", colunas,
                selection, selectionArgs, null, null, null);

        if(cursorGibi.moveToFirst()){
            gibi.set_id(cursorGibi.getLong(cursorGibi.getColumnIndex("_id")));
            gibi.setNome(cursorGibi.getString(cursorGibi.getColumnIndex("nome")));
            gibi.setTitulo(cursorGibi.getString(cursorGibi.getColumnIndex("titulo")));
            gibi.setNumero(cursorGibi.getString(cursorGibi.getColumnIndex("numero")));
            gibi.setEditora(cursorGibi.getString(cursorGibi.getColumnIndex("editora")));
        }
        return gibi;
    }

    public Cursor getCursor() {
        String[] colunas = {"_id", "nome", "titulo", "numero", "editora"};
        return db.query("Gibi", colunas,
                null, null, null, null, null);
    }
}
