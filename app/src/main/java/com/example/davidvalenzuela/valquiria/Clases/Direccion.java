package com.example.davidvalenzuela.valquiria.Clases;

public class Direccion {
    long id;
    Double longitud;
    Double latitud;
    String direccion;
    String estado;

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", longitud=" + longitud +
                ", latitud=" + latitud +
                ", direccion='" + direccion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
