package com.example.cuc.apartamentos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by CUC on 19/05/2017.
 */

public class Datos {

    public static ArrayList<Apartamento> traerApartamentos(Context contexto){
        ArrayList<Apartamento> apartamentos = new ArrayList<>();

        SQLiteDatabase db;
        String sql, nomenclatura, piso, numero, comodidad, metros;
        int precio;
        Apartamento a;

        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto, "DBApartamentos", null, 1);
        db = aux.getReadableDatabase();
        sql = "select * from Apartamentos";
        Cursor c = db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                nomenclatura = c.getString(0);
                piso = c.getString(1);
                numero = c.getString(2);
                comodidad = c.getString(3);
                metros = c.getString(4);
                precio = c.getInt(5);
                a = new Apartamento(nomenclatura,piso,numero,comodidad,metros,precio);
                apartamentos.add(a);
            }while (c.moveToNext());
        }

        db.close();
        return apartamentos;
    }

    public static Apartamento buscarApartamento(Context contexto, String nom){
        SQLiteDatabase db;
        String sql, nomenclatura, piso, numero, comodidad, metros;
        int precio;
        Apartamento a=null;

        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db=aux.getReadableDatabase();

        sql = "select * from Apartamentos where nomenclatura='"+nom+"'";
        Cursor c=db.rawQuery(sql,null);

        if(c.moveToFirst()){

            nomenclatura=c.getString(0);
            piso=c.getString(1);
            numero=c.getString(2);
            comodidad=c.getString(3);
            metros=c.getString(4);
            precio=c.getInt(5);
            a = new Apartamento(nomenclatura,piso,numero,comodidad,metros,precio);
        }

        db.close();

        return a;
    }
}
