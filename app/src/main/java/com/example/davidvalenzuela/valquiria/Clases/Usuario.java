package com.example.davidvalenzuela.valquiria.Clases;

public class Usuario {
    int telefono;
    String nombre;
    String apellido;



    @Override
    public String toString() {
        return "Usuario{" +
                "telefono=" + telefono +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


}
