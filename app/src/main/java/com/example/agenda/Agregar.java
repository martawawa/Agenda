// Agregar.java
package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Agregar extends AppCompatActivity {

    private EditText editNombre;
    private EditText editDescripcion;
    private EditText editCancion;
    private EditText editTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        editNombre = findViewById(R.id.nombre);
        editDescripcion = findViewById(R.id.descripcion);
        editCancion = findViewById(R.id.cancion);
        editTelefono = findViewById(R.id.telefono);


        Button btnAgregar = findViewById(R.id.agregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarContacto();
            }
        });
    }

    private void agregarContacto() {
        // Obtén los valores ingresados por el usuario
        String nuevoNombre = editNombre.getText().toString();
        String nuevaDescripcion = editDescripcion.getText().toString();
        String nuevaCancion = editCancion.getText().toString();
        String nuevoTelefono = editTelefono.getText().toString();


        // Crea un nuevo objeto Agenda con los valores ingresados
        Agenda nuevoContacto = new Agenda(nuevoNombre, nuevaDescripcion, nuevaCancion, nuevoTelefono, R.drawable.imagen1);

        // Devuelve el nuevo contacto a la actividad MainActivity
        Intent intent = new Intent();
        intent.putExtra("NuevoContacto", nuevoContacto);
        setResult(RESULT_OK, intent);

        // Cierra la actividad de agregar
        finish();
    }
}
