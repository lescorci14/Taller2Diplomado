package com.example.cuc.apartamentos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by CUC on 19/05/2017.
 */

public class Apartamento {
    private String nomenclatura, piso, numero, comodidad, metros;
    private int precio;

    public Apartamento(String nomenclatura, String piso, String numero, String comodidad, String metros, int precio) {
        this.nomenclatura = nomenclatura;
        this.piso = piso;
        this.numero = numero;
        this.comodidad = comodidad;
        this.metros = metros;
        this.precio = precio;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComodidad() {
        return comodidad;
    }

    public void setComodidad(String comodidad) {
        this.comodidad = comodidad;
    }

    public String getMetros() {
        return metros;
    }

    public void setMetros(String metros) {
        this.metros = metros;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void guardar(Context contexto){
        SQLiteDatabase db;
        String sql;

        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto, "DBApartamentos", null, 1);
        db = aux.getWritableDatabase();

        sql = "INSERT INTO Apartamentos values('"+this.getNomenclatura()+"','"
                +this.getPiso()+"','"
                +this.getNumero()+"','"
                +this.getComodidad()+"','"
                +this.getMetros()+"','"
                +this.getPrecio()+"')";

        db.execSQL(sql);

        db.close();
    }
}
