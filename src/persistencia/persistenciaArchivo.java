package persistencia;

import java.io.*;
import model.clinica;
import model.paciente;
import model.medico;
import model.consulta;
import java.util.List;

public class persistenciaArchivo {

    private static final String ARCHIVO = "datosClinica.dat";

    public static void guardarDatos() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(clinica.getPacientes());
            out.writeObject(clinica.getMedicos());
            out.writeObject(clinica.getConsultas());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void cargarDatos() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            List<paciente> pacientes = (List<paciente>) in.readObject();
            List<medico> medicos = (List<medico>) in.readObject();
            List<consulta> consultas = (List<consulta>) in.readObject();

            clinica.setPacientes(pacientes);
            clinica.setMedicos(medicos);
            clinica.setConsultas(consultas);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontraron datos guardados. Se empezará desde cero.");
        }
    }
}
