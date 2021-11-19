package sanjuan.comino.aerolinea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;

public class SecondActivity extends AppCompatActivity {
    TextView tvPrecio;
    Billete b;
    Button btAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvPrecio = findViewById(R.id.tvResumen);
        btAceptar = findViewById(R.id.btAceptar);
        btAceptar.setOnClickListener(view -> {
            Intent i = new Intent(this, ThirdActivity.class);
            i.putExtra("Billete", b);
            startActivity(i);
        });
        b = getIntent().getExtras().getParcelable("Billete");

        tvPrecio.setText("El precio final de su billete sera de " + calculaPrecioBillete(b)+"€");
    }

    //Metodo para calcular el precio de un billete
    private int calculaPrecioBillete(Billete b){
        int precio = 0;
        int precioPorExtra = 30;

        if (b.getPrecio()==0){
            precio = precio = (int) (Math.random() * 1000);
            precio += (b.getExtras() * precioPorExtra);

            b.setPrecio(precio);
        }else {

            precio = b.getPrecio();
        }

        return precio;
    }
}