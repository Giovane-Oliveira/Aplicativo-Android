package genteterra.com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSqlLite extends SQLiteOpenHelper {

    AdminSqlLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

// Tabela de usuarios
        db.execSQL("create table usuario(id integer primary key autoincrement , nome text, senha text)");
        db.execSQL("insert into usuario(nome, senha) values('admin','admin')");

        //tabela de dragas
        db.execSQL("create table draga(id integer primary key autoincrement , nome text)");
        db.execSQL("insert into draga(nome) values('Draga')");

        //tabela de Mineradoras
        db.execSQL("create table mineradora(id integer primary key autoincrement , nome text)");
        db.execSQL("insert into mineradora(nome) values('Mineradora')");


        //tabela de empresas
        db.execSQL("create table empresa(id integer primary key autoincrement , nome text)");
        db.execSQL("insert into empresa(nome) values('Empresa')");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //tabela usuarios
        db.execSQL("drop table if exists usuario");
        db.execSQL("create table usuario(id integer primary key autoincrement , nome text, senha text)");
        db.execSQL("insert into usuario(nome, senha) values('admin','admin')");

        //tabela de dragas
        db.execSQL("drop table if exists draga");
        db.execSQL("create table draga(id integer primary key autoincrement , nome text)");
        db.execSQL("insert into draga(nome) values('Draga')");


        //tabela mineradoras
        db.execSQL("drop table if exists mineradora");
        db.execSQL("create table mineradora(id integer primary key autoincrement , nome text)");
        db.execSQL("insert into mineradora(nome) values('Mineradora')");


        //tabela empresas
        db.execSQL("drop table if exists empresa");
        db.execSQL("create table empresa(id integer primary key autoincrement , nome text)");
        db.execSQL("insert into empresa(nome) values('Empresa')");

    }
}
