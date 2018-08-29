package mx.edu.cetys.rodriguez.julio.myfirstapp;

import com.google.gson.annotations.SerializedName;


public class Empresa {
    @SerializedName("id")
    private String id;
    @SerializedName("Nombre")
    private String nombre;
    @SerializedName("Telefono")
    private String telefono;
    @SerializedName("Direccion")
    private String direccion;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Empresa{");
        sb.append("id='").append(id).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", telefono='").append(telefono).append('\'');
        sb.append(", direccion='").append(direccion).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
