package sanjuan.comino.aerolinea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    Billete b;

    TextView tvResumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        tvResumen = findViewById(R.id.tvResumen);

        b = getIntent().getExtras().getParcelable("Billete");

        tvResumen.setText("Billete a nombre de "+b.getNombre()+
                " " + b.getApellidos() +
                " para el dia " + b.getFecha() +
                " con salida en " + b.getOrigen() +
                " y destino en "+b.getDestino() +
                " con " + b.getExtras() + " extras incluídos");

    }
}