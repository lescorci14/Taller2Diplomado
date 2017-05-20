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
                        balsom(getApplicationContext());
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
    }

    public void balsom(Context contexto){
        SQLiteDatabase db;
        String sql, result;
        int cont=0;

        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db=aux.getReadableDatabase();

        sql = "select * from Apartamentos where comodidad='"+res.getString(R.string.ambas);
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
}
