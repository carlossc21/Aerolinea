package sanjuan.comino.aerolinea;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    Spinner origen, destino;
    Button comprar;
    EditText etNombre, etApellidos, etDireccion, etTelefono, etEmail, etDate;
    ImageButton accesoPreferente;
    CheckBox cbTerminos, cbSeguro;
    RadioButton rb1, rb2, rb3, rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        onCreateOptionsMenu(toolbar.getMenu());
        initialice();


    }



    private void initialice() {
        origen = findViewById(R.id.spOrigen);
        destino = findViewById(R.id.spDestino);
        comprar = findViewById(R.id.btComprar);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etDireccion = findViewById(R.id.etDireccion);
        etTelefono = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etDate = findViewById(R.id.etDate);
        accesoPreferente = findViewById(R.id.btAccesoPreferente);
        cbTerminos = findViewById(R.id.cbTerminos);
        cbSeguro = findViewById(R.id.cbSeguro);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        //Accion del Botón de ACCESO PREFERENTE---------------------------------------------------------------------------------------------------------------
        accesoPreferente.setOnClickListener(view -> {
            //control para comprobar que se han seleccionado lugares de salida y destino diferentes
            if (origen.getSelectedItem().equals(destino.getSelectedItem())){
                Snackbar.make(view, "Selecciona un Origen y un Destino Diferentes", Snackbar.LENGTH_SHORT).show();


            }else {
                //comprueba que se han llenado todos los campos
                if (etNombre.getText().toString().trim().equals(null) || etApellidos.getText().toString().trim().equals("") || etTelefono.getText().toString().trim().equals("") || etEmail.getText().toString().trim().equals("") || etDireccion.getText().toString().trim().equals("") || etDate.getText().toString().trim().equals("")) {
                    Toast toast = Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT);

                    toast.show();

                } else {

                    //comprueba si se han aceptado los terminos
                    if (!cbTerminos.isChecked()){
                        Toast.makeText(this, "Debes aceptar los terminos", Toast.LENGTH_SHORT).show();

                    }else {

                        //confirmacion de compra del billete

                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                        alertBuilder.setTitle("Comprar");
                        alertBuilder.setMessage("¿Quieres comprar un billete con salida en " + origen.getSelectedItem().toString() + " y destino " + destino.getSelectedItem().toString() + "?");
                        alertBuilder.setPositiveButton("Aceptar", (dialogInterface, i) -> {

                            Billete b = creaBillete();

                            Intent intent = new Intent(this, SecondActivity.class);
                            intent.putExtra("Billete", b);
                            startActivity(intent);

                        });
                        alertBuilder.setNegativeButton("Cancelar", null);
                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.show();
                    }
                }
            }
        });



        //Se muestra un Date Picker cuando se hace click sobre el editText Fecha ---------------------------------------------------------------------------------------------------------------
        etDate.setOnClickListener(view -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog datePicker = new DatePickerDialog(this);
                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String[] meses = {"Enero", "Febrero","Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septempre", "Octubre", "Noviembre", "Diciembre"} ;
                        etDate.setText(datePicker.getDayOfMonth()+"/"+meses[datePicker.getMonth()]+"/"+datePicker.getYear());

                    }
                });

                datePicker.show();

            }
        });

        //Accion del botón de compra normal---------------------------------------------------------------------------------------------------------------
        comprar.setOnClickListener(view -> {
            //control para comprobar que se han seleccionado lugares de salida y destino diferentes
            if (origen.getSelectedItem().equals(destino.getSelectedItem())){
                Snackbar.make(view, "Selecciona un Origen y un Destino Diferentes", Snackbar.LENGTH_SHORT).show();
            }else {
                //comprueba que se han llenado todos los campos
                if (etNombre.getText().toString().trim().equals(null) || etApellidos.getText().toString().trim().equals("") || etTelefono.getText().toString().trim().equals("") || etEmail.getText().toString().trim().equals("") || etDireccion.getText().toString().trim().equals("") || etDate.getText().toString().trim().equals("")) {
                    Toast toast = Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT);

                    toast.show();

                } else {
                    //comprueba si se han aceptado los terminos
                    if (!cbTerminos.isChecked()){
                        Toast.makeText(this, "Debes aceptar los terminos", Toast.LENGTH_SHORT).show();

                    }else {

                        //confirmacion de compra del billete

                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                        alertBuilder.setTitle("Comprar");
                        alertBuilder.setMessage("¿Quieres comprar un billete con salida en " + origen.getSelectedItem().toString() + " y destino " + destino.getSelectedItem().toString() + "?");
                        alertBuilder.setPositiveButton("Aceptar", (dialogInterface, i) -> {

                            Billete b = creaBillete();

                            Intent intent = new Intent(this, SecondActivity.class);
                            intent.putExtra("Billete", b);
                            startActivity(intent);

                        });
                        alertBuilder.setNegativeButton("Cancelar", null);
                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.show();
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutopbar, menu);
        return true;
    }
//Accion de las opciones del menú
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.misViajes){
            Toast.makeText(this, "Mis Viajes", Toast.LENGTH_SHORT).show();
        }




        return super.onOptionsItemSelected(item);
    }

    //Metodo para crear un billete cogiendo los datos de los inputs del layout
    private Billete creaBillete(){
        String nombre = etNombre.getText().toString();
        String apellidos = etApellidos.getText().toString();
        String email = etEmail.getText().toString();
        String direccion = etDireccion.getText().toString();
        String fecha = etDate.getText().toString();
        String origen = this.origen.getSelectedItem().toString();
        String destino = this.destino.getSelectedItem().toString();
        int extras = 0;
        long telefono = Long.parseLong(etTelefono.getText().toString());
        boolean seguro = cbSeguro.isChecked();
        System.out.println(origen + destino);
        if (rb1.isChecked()){
            extras++;
        }
        if (rb2.isChecked()){
            extras++;
        }
        if (rb3.isChecked()){
            extras++;
        }
        if (rb4.isChecked()){
            extras++;
        }

        Billete b = new Billete(nombre, apellidos, email, direccion, fecha, telefono, extras, seguro, origen, destino);

        return  b;
    }
}