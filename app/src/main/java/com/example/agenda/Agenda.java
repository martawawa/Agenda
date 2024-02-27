package com.example.agenda;

import android.os.Parcel;
import android.os.Parcelable;

public class Agenda implements Parcelable {

    private String nombre;
    private String descripcion;
    private String cancion;
    private String numero;
    private int imagen;

    // Constructor
    public Agenda(String titulo, String descripcion, String cancion, String numero, int imagen) {
        this.nombre = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.numero = numero;
        this.cancion = cancion;
    }

    // Métodos getter y setter para acceder a las propiedades
    public String getTitulo() {
        return nombre;
    }

    public void setTitulo(String titulo) {
        this.nombre = titulo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    // Métodos necesarios para implementar Parcelable
    protected Agenda(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
        cancion = in.readString();
        numero = in.readString();
        imagen = in.readInt();
    }

    public static final Creator<Agenda> CREATOR = new Creator<Agenda>() {
        @Override
        public Agenda createFromParcel(Parcel in) {
            return new Agenda(in);
        }

        @Override
        public Agenda[] newArray(int size) {
            return new Agenda[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(cancion);
        dest.writeString(numero);
        dest.writeInt(imagen);
    }
}
