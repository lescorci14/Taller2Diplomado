package com.example.cuc.apartamentos;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Reportes extends AppCompatActivity {
    private ListView op;
    private Resources res;
    private String[] opc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        op=(ListView)findViewById(R.id.lstReportes);

        res=this.getResources();

        opc=res.getStringArray(R.array.reportes);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,opc);

        op.setAdapter(adapter);

        op.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        balsom(getApplicationContext(),res.getString(R.string.ambas));
                        break;
                    case 1:
                        caroPiso(getApplicationContext());
                        break;
                    case 2:
                        grandeNom(getApplicationContext());
                        break;
                    case 3:
                        promTam(getApplicationContext());
                        break;
                }
            }
        });
    }

    public void balsom(Context contexto, String comodidad){
        SQLiteDatabase db;
        String sql, result;
        int cont=0;

        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto,"DBApartamentos",null,3);
        db=aux.getReadableDatabase();

        sql = "select * from Apartamentos where comodidad='"+comodidad+"'";
        Cursor c =db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                cont=cont+1;
            }while (c.moveToNext());
        }

        db.close();
        result = res.getString(R.string.reporte1)+" "+cont;

        new AlertDialog.Builder(this).setMessage(result).setCancelable(true).show();

    }

    public void caroPiso(Context contexto){
        SQLiteDatabase db;
        String sql, piso="",result;
        int precio=0;

        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto,"DBApartamentos",null,3);
        db=aux.getReadableDatabase();

        sql = "select * from Apartamentos";
        Cursor c=db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                if(precio<c.getInt(5)){
                    precio = c.getInt(5);
                    piso = c.getString(1);
                }
            }while (c.moveToNext());
        }

        result = res.getString(R.string.reporte2)+" "+piso;

        new AlertDialog.Builder(this).setMessage(result).setCancelable(true).show();
    }

    public void grandeNom(Context contexto){
        SQLiteDatabase db;
        String sql, tam, nom="",result;
        int comparar=0,grande=0;

        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto,"DBApartamentos",null,3);
        db = aux.getReadableDatabase();

        sql = "select * from Apartamentos";
        Cursor c=db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                tam = c.getString(4);
                comparar = Integer.parseInt(tam);
                if(grande<comparar){
                    grande = comparar;
                    nom = c.getString(0);
                }
            }while (c.moveToNext());
        }

        result = res.getString(R.string.reporte3)+" "+nom+"\n"
                +res.getString(R.string.reporte3b)+" "+grande+" "+" "+res.getString(R.string.metros);

        new AlertDialog.Builder(this).setMessage(result).setCancelable(true).show();
    }

    public void promTam(Context contexto){
        SQLiteDatabase db;
        String sql, tam, piso,result;
        int tama=0,suma=0, suma2=0,suma3=0,suma4=0,suma5=0,
                prom=0,prom2=0, prom3=0, prom4=0,prom5=0, cont=0, cont2=0,cont3=0,cont4=0,cont5=0;

        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto,"DBApartamentos",null,3);
        db = aux.getReadableDatabase();

        sql = "select * from Apartamentos";
        Cursor c=db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                piso = c.getString(1);
                if(piso.equalsIgnoreCase(res.getString(R.string.piso1))){
                    cont = cont+1;
                    tam = c.getString(4);
                    tama = Integer.parseInt(tam);
                    suma = suma+tama;
                }
                if(piso.equalsIgnoreCase(res.getString(R.string.piso2))){
                    cont2 = cont2+1;
                    tam = c.getString(4);
                    tama = Integer.parseInt(tam);
                    suma2 = suma2+tama;
                }
                if(piso.equalsIgnoreCase(res.getString(R.string.piso3))){
                    cont3 = cont3+1;
                    tam = c.getString(4);
                    tama = Integer.parseInt(tam);
                    suma3 = suma3+tama;
                }
                if(piso.equalsIgnoreCase(res.getString(R.string.piso4))){
                    cont4 = cont4+1;
                    tam = c.getString(4);
                    tama = Integer.parseInt(tam);
                    suma4 = suma4+tama;
                }
                if(piso.equalsIgnoreCase(res.getString(R.string.piso5))){
                    cont5 = cont5+1;
                    tam = c.getString(4);
                    tama = Integer.parseInt(tam);
                    suma5 = suma5+tama;
                }
            }while (c.moveToNext());
        }

        if(cont!=0){
            prom = suma/cont;
        }
        if(cont2!=0){
            prom2 = suma2/cont2;
        }
        if(cont3!=0){
            prom3 = suma3/cont3;
        }
        if(cont4!=0){
            prom4 = suma4/cont4;
        }
        if(cont5!=0){
            prom5 = suma5/cont5;
        }

        result = res.getString(R.string.piso)+res.getString(R.string.piso1)+" "+prom+" "+res.getString(R.string.metros)+"\n"+
                res.getString(R.string.piso)+res.getString(R.string.piso2)+" "+prom2+" "+res.getString(R.string.metros)+"\n"+
                res.getString(R.string.piso)+res.getString(R.string.piso2)+" "+prom3+" "+res.getString(R.string.metros)+"\n"+
                res.getString(R.string.piso)+res.getString(R.string.piso2)+" "+prom4+" "+res.getString(R.string.metros)+"\n"+
                res.getString(R.string.piso)+res.getString(R.string.piso2)+" "+prom5+" "+res.getString(R.string.metros);

        new AlertDialog.Builder(this).setMessage(result).setCancelable(true).show();
    }
}
