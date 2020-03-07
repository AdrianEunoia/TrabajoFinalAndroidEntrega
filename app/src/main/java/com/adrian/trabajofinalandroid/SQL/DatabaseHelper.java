package com.adrian.trabajofinalandroid.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "segunda.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table users(nombreuser text primary key,passworduser text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    // Metodo para insertar usuarios
    public boolean insertarUsuarios(String nombreUsuario, String passwordUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombreuser",nombreUsuario);
        contentValues.put("passworduser",passwordUsuario);
        long insert = db.insert("users", null, contentValues);
        // Checkear si se insertar bool.
        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    // Comprobar si hay usuarios ya registrados con ese nombre
    public boolean comprobarUsuario (String nombreUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select nombreuser from users where nombreuser =?", new String[] {nombreUsuario});
        if (cursor.getCount()>0){
            return false;
        }else{
            return true;
        }
    }

    // Metodo para validar logeo
    public boolean comprobarLogin (String nombreUsuario, String passwordUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where nombreuser =? and passworduser =?", new String[] {nombreUsuario, passwordUsuario});
        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

}
