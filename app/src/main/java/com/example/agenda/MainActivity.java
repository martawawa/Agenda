package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

// Actividad principal que muestra la lista de elementos en la agenda
public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private Button agregar_contacto;
    private ArrayList<Agenda> datos;
    private static final int REQUEST_EDITAR_DETALLES = 1;
    private static final int REQUEST_AGREGAR_CONTACTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);
        agregar_contacto = findViewById(R.id.agregar);

        // Creamos una linea divisoria entre cada elemento del recycler
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recycler.addItemDecoration(divider);

        datos = new ArrayList<>();

        // Obtenemos los arrays creados para agregar los datos
        String[] titulosArray = getResources().getStringArray(R.array.nombres_array);
        String[] descripcionArray = getResources().getStringArray(R.array.descripciones_array);
        String[] cancionesArray = getResources().getStringArray(R.array.titulos_canciones_array);
        String[] numeroArray = getResources().getStringArray(R.array.numeros_telefono_array);
        int[] imagenesArray = {
                R.drawable.imagen1,
                R.drawable.imagen2,
                R.drawable.imagen3,
                R.drawable.imagen4,
                R.drawable.imagen5};

        //rellenamos la lista de datos
        //Se realiza un bucle para crear 30 instancias de la clase Agenda. El índice i % length asegura que los datos del array se repitan
        for (int i = 0; i < 30; i++) {
            datos.add(new Agenda(titulosArray[i % titulosArray.length], descripcionArray[i % descripcionArray.length], cancionesArray[i % cancionesArray.length], numeroArray[i % numeroArray.length], imagenesArray[i % imagenesArray.length]));
        }

        AdaptadorAgenda adapter = new AdaptadorAgenda(datos); //Se crea una instancia del adaptador pasandole la lista de datos
        recycler.setHasFixedSize(true); // Optimiza el rendimiento si el tamaño del RecyclerView no cambia.
        //: Configura el LayoutManager del RecyclerView para que sea un LinearLayoutManager vertical
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(adapter); //Asigna el adaptador al RecyclerView.
        recycler.addItemDecoration(divider); // Agrega una línea divisoria entre elementos en el RecyclerView.

        // Configura el OnClickListener para el clic en elementos
        adapter.setOnClickListener(new AdaptadorAgenda.OnItemClickListener() {
            @Override
            public void onItemClick(Agenda agenda) {
                Intent intent = new Intent(MainActivity.this, Detalles.class);
                intent.putExtra("Agenda", agenda);
                startActivity(intent);
            }
        });

        // Configura el OnClickListener para el clic en eliminar
        adapter.setOnDeleteClickListener(new AdaptadorAgenda.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Agenda agenda) {
                datos.remove(agenda);
                adapter.notifyDataSetChanged();
            }
        });

        // Configura el OnClickListener para el clic en editar
        adapter.setOnEditClickListener(new AdaptadorAgenda.OnEditClickListener() {
            @Override
            public void onEditClick(Agenda agenda) {
                Intent intent = new Intent(MainActivity.this, Editar.class);
                intent.putExtra("Agenda", agenda);
                startActivityForResult(intent, REQUEST_EDITAR_DETALLES);
            }
        });

        agregar_contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre la actividad para agregar un nuevo contacto
                Intent intent = new Intent(MainActivity.this, Agregar.class);
                startActivityForResult(intent, REQUEST_AGREGAR_CONTACTO);
            }
        });
    }

    // Maneja el resultado de la actividad de edición
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_EDITAR_DETALLES && resultCode == RESULT_OK && data != null) {
            Agenda agendaEditada = data.getParcelableExtra("AgendaEditada");
            actualizarInterfaz(agendaEditada);
            Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show();

        } else if (requestCode == REQUEST_AGREGAR_CONTACTO && resultCode == RESULT_OK && data != null) {
            Agenda nuevoContacto = data.getParcelableExtra("NuevoContacto");
            agregarNuevoContacto(nuevoContacto);
            Toast.makeText(this, "Contacto agregado", Toast.LENGTH_SHORT).show();
        }
    }

    private void agregarNuevoContacto(Agenda nuevoContacto) {
        datos.add(0, nuevoContacto); // Agrega el nuevo contacto al principio de la lista
        recycler.getAdapter().notifyItemInserted(0);
    }


    // Actualiza la interfaz con los cambios realizados
    private void actualizarInterfaz(Agenda agenda) {
        for (int i = 0; i < datos.size(); i++) {
            if (datos.get(i).getTitulo().equals(agenda.getTitulo())) {
                // Actualiza la instancia existente de Agenda con los nuevos valores
                datos.get(i).setDescripcion(agenda.getDescripcion());
                datos.get(i).setCancion(agenda.getCancion());
                datos.get(i).setNumero(agenda.getNumero());

                recycler.getAdapter().notifyItemChanged(i);
                break;
            }
        }
    }


}
