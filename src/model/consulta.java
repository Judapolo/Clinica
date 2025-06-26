
package model;

import java.io.Serializable;
import java.time.LocalDate;

public class consulta implements Serializable {
    private final LocalDate fecha;
    private final medico medico;
    private final paciente paciente;
    private final String sintomas;
    private final String diagnostico;
    private final String tratamiento;

    public consulta(LocalDate fecha, medico medico, paciente paciente,
                    String sintomas, String diagnostico, String tratamiento) {
        this.fecha = fecha;
        this.medico = medico;
        this.paciente = paciente;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    public LocalDate getFecha() { return fecha; }
    public medico getMedico() { return medico; }
    public paciente getPaciente() { return paciente; }
    public String getSintomas() { return sintomas; }
    public String getDiagnostico() { return diagnostico; }
    public String getTratamiento() { return tratamiento; }

    @Override
    public String toString() {
        return "Consulta [" + fecha + "]\\n" +
               "Paciente: " + paciente.getNombre() + "\\n" +
               "Médico: " + medico.getNombre() + "\\n" +
               "Síntomas: " + sintomas + "\\n" +
               "Diagnóstico: " + diagnostico + "\\n" +
               "Tratamiento: " + tratamiento + "\\n";
    }
}
