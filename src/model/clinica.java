
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class clinica implements Serializable {

    private static List<paciente> pacientes = new ArrayList<>();
    private static List<medico> medicos = new ArrayList<>();
    private static List<consulta> consultas = new ArrayList<>();

    public static void agregarPaciente(paciente p) {
        pacientes.add(p);
    }

    public static void agregarMedico(medico m) {
        medicos.add(m);
    }

    public static void agregarConsulta(consulta c) {
        consultas.add(c);
    }

    public static paciente buscarPacientePorCedula(String cedula) {
        for (paciente p : pacientes) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }

    public static List<consulta> obtenerHistorialPorCedula(String cedula) {
        return consultas.stream()
                .filter(c -> c.getPaciente().getCedula().equals(cedula))
                .collect(Collectors.toList());
    }

    public static List<paciente> getPacientes() { return pacientes; }
    public static List<medico> getMedicos() { return medicos; }
    public static List<consulta> getConsultas() { return consultas; }

    public static void setPacientes(List<paciente> lista) { pacientes = lista; }
    public static void setMedicos(List<medico> lista) { medicos = lista; }
    public static void setConsultas(List<consulta> lista) { consultas = lista; }
}