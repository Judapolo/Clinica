package model;

import java.io.Serializable;

public class medico implements Serializable {
    private String nombre;
    private String cedula;
    private String especialidad;

    public medico(String nombre, String cedula, String especialidad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = especialidad;
    }

    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getEspecialidad() { return especialidad; }
}
