package com.example.agenda;

import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

// Adaptador para la RecyclerView en MainActivity
public class AdaptadorAgenda extends RecyclerView.Adapter<AdaptadorAgenda.AgendaViewHolder> {

    //declaracion de variables
    private ArrayList<Agenda> datos;
    private OnItemClickListener listener;
    private OnDeleteClickListener deleteClickListener;
    private OnEditClickListener editClickListener;

    // CONSTRUCTOR recibe la lista de datos y la asigna a la variable de la clase
    public AdaptadorAgenda(ArrayList<Agenda> datos) {
        this.datos = datos;
    }

    //Declaración de interfaces y métodos para manejar clics en los elementos de la lista
    public interface OnItemClickListener { void onItemClick(Agenda agenda);
    }

    public interface OnDeleteClickListener { void onDeleteClick(Agenda agenda);
    }

    public interface OnEditClickListener { void onEditClick(Agenda agenda);
    }

    //Configuración de listeners para clics
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {this.deleteClickListener = listener;
    }

    public void setOnEditClickListener(OnEditClickListener listener) {this.editClickListener = listener;
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //Se define una clase interna AgendaViewHolder que extiende RecyclerView.ViewHolder.
    //Se declaran las vistas (TextView, ImageView, ImageButton) que representarán los elementos de cada fila en el RecyclerView
    public class AgendaViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitulo;
        private ImageView imgImagen;
        private ImageButton eliminar;
        private ImageButton editar;

        //CONSTRUCTOR de AgendaViewHolder
        //inicializa las vistas a partir de los elementos del diseño de la fila
        public AgendaViewHolder(@NonNull View view) {
            super(view);

            txtTitulo = view.findViewById(R.id.titulo);
            imgImagen = view.findViewById(R.id.imagenes);
            eliminar = view.findViewById(R.id.eliminar);
            editar = view.findViewById(R.id.editar);

            // Se configuran OnClickListener para la fila, el botón eliminar y el botón editar
            // Configura el OnClickListener para el ViewHolder
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(datos.get(position));
                    }
                }
            });

            // Configura el OnClickListener para el botón eliminar
            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && deleteClickListener != null) {
                        deleteClickListener.onDeleteClick(datos.get(position));
                    }
                }
            });

            // Configura el OnClickListener para el botón editar
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && editClickListener != null) {
                        editClickListener.onEditClick(datos.get(position));
                    }
                }
            });
        }

        // Método para enlazar datos a la vista
        //enlazar datos de la Agenda con las vistas en un AgendaViewHolder
        public void bindTitular(Agenda t) {
            txtTitulo.setText(t.getTitulo());
            imgImagen.setImageResource(t.getImagen());
        }
    }

    // Crea nuevos ViewHolders según sea necesario/
    //Se infla el diseño de la fila y se crea un AgendaViewHolder
    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_contactos, viewGroup, false);
        AgendaViewHolder tvh = new AgendaViewHolder(itemView);
        return tvh;
    }

    // Reemplaza el contenido de un ViewHolder
    //Se llama para enlazar datos con las vistas en un AgendaViewHolder
    @Override
    public void onBindViewHolder(@NonNull AgendaViewHolder holder, int position) {
        Agenda item = datos.get(position);
        holder.bindTitular(item);
    }

    // Devuelve la cantidad de elementos de la lista
    @Override
    public int getItemCount() {
        return datos != null ? datos.size() : 0;
    }
}
