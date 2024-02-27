package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

// Actividad para editar detalles de un elemento en la agenda
public class Editar extends AppCompatActivity {

    private EditText editNombre;
    private EditText editDescripcion;
    private EditText editCancion;
    private EditText editTelefono;
    private Agenda agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        editNombre = findViewById(R.id.nombre);
        editDescripcion = findViewById(R.id.descripcion);
        editCancion = findViewById(R.id.cancion);
        editTelefono = findViewById(R.id.telefono);

        // Verifica si hay datos extras en el intent
        if (getIntent().hasExtra("Agenda")) {
            agenda = getIntent().getParcelableExtra("Agenda");

            // Muestra los valores actuales en los campos de edición
            editNombre.setText(agenda.getTitulo());
            editDescripcion.setText(agenda.getDescripcion());
            editCancion.setText(agenda.getCancion());
            editTelefono.setText(agenda.getNumero());
        }
    }

    // Maneja el clic en el botón de guardar
    public void onGuardarClick(View view) {
        // Obtén los valores editados
        String nuevoNombre = editNombre.getText().toString();
        String nuevaDescripcion = editDescripcion.getText().toString();
        String nuevaCancion = editCancion.getText().toString();
        String nuevoTelefono = editTelefono.getText().toString();

        // Actualiza la instancia existente de Agenda con los nuevos valores
        agenda.setTitulo(nuevoNombre);
        agenda.setDescripcion(nuevaDescripcion);
        agenda.setCancion(nuevaCancion);
        agenda.setNumero(nuevoTelefono);

        // Crea una nueva instancia de Intent y devuelve la Agenda editada a la actividad Detalles
        Intent intent = new Intent();
        intent.putExtra("AgendaEditada", agenda);
        setResult(RESULT_OK, intent);

        // Cierra la actividad de edición
        finish();
    }


}
