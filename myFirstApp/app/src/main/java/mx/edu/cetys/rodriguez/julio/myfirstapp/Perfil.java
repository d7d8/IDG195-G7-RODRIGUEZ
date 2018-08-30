package mx.edu.cetys.rodriguez.julio.myfirstapp;

import com.google.gson.annotations.SerializedName;


public class Perfil {

    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("Nombre")
    private String nombre;
    @SerializedName("Apellido")
    private String apellido;
    @SerializedName("Carrera")
    private String carrera;
    @SerializedName("Semestre")
    private int semestre;
    @SerializedName("materiasaprobadas")
    private int materiasaprobadas;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Perfil{");
        sb.append("id='").append(id).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", carrera='").append(carrera).append('\'');
        sb.append(", semestre=").append(semestre);
        sb.append(", materiasaprobadas=").append(materiasaprobadas);
        sb.append('}');
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getMateriasaprobadas() {
        return materiasaprobadas;
    }

    public void setMateriasaprobadas(int materiasaprobadas) {
        this.materiasaprobadas = materiasaprobadas;
    }
}
