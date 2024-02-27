package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.os.Parcelable;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Detalles extends AppCompatActivity {

    private TextView mensaje;
    private static final int REQUEST_EDITAR_DETALLES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        mensaje = findViewById(R.id.mensaje);

        if (getIntent().hasExtra("Agenda")) {
            Agenda agenda = getIntent().getParcelableExtra("Agenda");
            mostrarDetalles(agenda);
        }
    }

    private void mostrarDetalles(Agenda agenda) {
        mensaje.setText(Html.fromHtml("<b> <big><big>" + agenda.getTitulo() + "</big></big></b>" +
                "<br><br><b>Descripción: </b>" + agenda.getDescripcion() +
                "<br><br><b>Teléfono: </b>" + agenda.getNumero() +
                "<br><br><b>Canción: </b>" + agenda.getCancion()));
    }


    public void onEditarClick(View view) {
        Intent intent = new Intent(Detalles.this, Editar.class);
        intent.putExtra("Agenda", (Bundle) getIntent().getParcelableExtra("Agenda"));
        startActivityForResult(intent, REQUEST_EDITAR_DETALLES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_EDITAR_DETALLES && resultCode == RESULT_OK && data != null) {
            Agenda agendaEditada = data.getParcelableExtra("AgendaEditada");
            mostrarDetalles(agendaEditada);
            Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show();
        }
    }

}
