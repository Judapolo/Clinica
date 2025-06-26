package model;

import java.io.Serializable;

public class paciente implements Serializable {
    private String nombre;
    private String cedula;
    private int edad;
    private String diagnostico;

    public paciente(String nombre, String cedula, int edad, String diagnostico) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.diagnostico = diagnostico;
    }

    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public int getEdad() { return edad; }
    public String getDiagnostico() { return diagnostico; }
}
