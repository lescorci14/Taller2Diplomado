package com.example.cuc.apartamentos;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {
    private TableLayout tabla;
    private ArrayList<Apartamento> apartamentos;
    private Resources res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        tabla = (TableLayout)findViewById(R.id.tblApartamentos);
        res = this.getResources();

        apartamentos = Datos.traerApartamentos(getApplicationContext());

        for (int i = 0; i < apartamentos.size(); i++) {
            TableRow fila = new TableRow(this);
            TextView c1 = new TextView(this);
            TextView c2 = new TextView(this);
            TextView c3 = new TextView(this);
            TextView c4 = new TextView(this);
            TextView c5 = new TextView(this);

            c1.setText(""+(i+1));
            c2.setText(""+apartamentos.get(i).getNomenclatura());
            c3.setText(""+apartamentos.get(i).getComodidad());
            c4.setText(""+apartamentos.get(i).getMetros());
            c5.setText(""+apartamentos.get(i).getPrecio());

            fila.addView(c1);
            fila.addView(c2);
            fila.addView(c3);
            fila.addView(c4);
            fila.addView(c5);

            tabla.addView(fila);

        }
    }
}
