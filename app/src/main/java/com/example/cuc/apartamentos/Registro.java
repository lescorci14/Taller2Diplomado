package com.example.cuc.apartamentos;

import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class Registro extends AppCompatActivity {
    private Spinner comboPiso, comboHabitacion;
    private RadioButton rBalcon, rSombra, rAmbas;
    private EditText cajaMetros, cajaPrecio;
    private ArrayAdapter<String> adapter1, adapter2;
    private String[] opc1, opc2;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        comboPiso = (Spinner)findViewById(R.id.cmbPiso);
        comboHabitacion = (Spinner)findViewById(R.id.cmbNumero);

        rBalcon = (RadioButton)findViewById(R.id.r1);
        rSombra = (RadioButton)findViewById(R.id.r2);
        rAmbas = (RadioButton)findViewById(R.id.r3);

        cajaMetros = (EditText)findViewById(R.id.txtMetros);
        cajaPrecio = (EditText)findViewById(R.id.txtPrecio);

        res = this.getResources();

        opc1 = res.getStringArray(R.array.pisos);
        opc2 = res.getStringArray(R.array.habitaciones);
        adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opc1);
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opc2);

        comboPiso.setAdapter(adapter1);
        comboHabitacion.setAdapter(adapter2);
    }

    public boolean validarCampos(){
        if(cajaMetros.getText().toString().isEmpty()){
            cajaMetros.setError(res.getString(R.string.error1));
            cajaMetros.requestFocus();
            return false;
        }
        if(cajaPrecio.getText().toString().isEmpty()){
            cajaPrecio.setError(res.getString(R.string.error2));
            cajaPrecio.requestFocus();
            return false;
        }

        return true;
    }

    public void guardar(View v){
        String nomenclatura, piso, numero, comodidad = "", metros, pre,aux ="";
        int precio=0;
        Apartamento a, b;

        if(validarCampos()){
            piso= comboPiso.getSelectedItem().toString();
            numero = comboHabitacion.getSelectedItem().toString();
            nomenclatura = piso+numero;

            b=Datos.buscarApartamento(getApplicationContext(),nomenclatura);
            if(b==null){
                if(rBalcon.isChecked()){
                    comodidad = res.getString(R.string.balcon);
                }else if(rSombra.isChecked()){
                    comodidad = res.getString(R.string.sombra);
                }else if(rAmbas.isChecked()){
                    comodidad = res.getString(R.string.ambas);
                }

                metros = cajaMetros.getText().toString();
                pre = cajaPrecio.getText().toString();
                precio = Integer.parseInt(pre);

                a = new Apartamento(nomenclatura,piso,numero,comodidad,metros,precio);
                a.guardar(getApplicationContext());

                new AlertDialog.Builder(this).setMessage(res.getString(R.string.guardar)).setCancelable(true).show();
                limpiar();
            }else{
                new AlertDialog.Builder(this).setMessage(res.getString(R.string.error3)).setCancelable(true).show();
            }
        }
    }

    public void limpiar(){
        cajaMetros.setText("");
        cajaPrecio.setText("");
    }
}
