package View;

import javax.swing.*;
import model.clinica;
import model.paciente;
import model.medico;
import model.consulta;
import persistencia.persistenciaArchivo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class VentanaPrincipal extends JFrame {

    private JTextArea areaTexto;

    public VentanaPrincipal() {
        setTitle("Sistema de Clínica");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnPaciente = new JButton("Registrar Paciente");
        JButton btnMedico = new JButton("Registrar Médico");
        JButton btnConsulta = new JButton("Agendar Consulta");
        JButton btnHistorial = new JButton("Ver Historial");
        JButton btnGuardar = new JButton("Guardar Datos");

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 5));
        panelBotones.add(btnPaciente);
        panelBotones.add(btnMedico);
        panelBotones.add(btnConsulta);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnGuardar);

        add(panelBotones, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnPaciente.addActionListener(this::registrarPaciente);
        btnMedico.addActionListener(this::registrarMedico);
        btnConsulta.addActionListener(this::agendarConsulta);
        btnHistorial.addActionListener(this::verHistorial);
        btnGuardar.addActionListener(e -> {
            persistenciaArchivo.guardarDatos();
            JOptionPane.showMessageDialog(this, "Datos guardados.");
        });
    }

    private void registrarPaciente(ActionEvent e) {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String cedula = JOptionPane.showInputDialog("Cédula:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        String diagnostico = JOptionPane.showInputDialog("Diagnóstico:");
        paciente p = new paciente(nombre, cedula, edad, diagnostico);
        clinica.agregarPaciente(p);
        JOptionPane.showMessageDialog(this, "Paciente registrado.");
    }

    private void registrarMedico(ActionEvent e) {
        String nombre = JOptionPane.showInputDialog("Nombre del médico:");
        String cedula = JOptionPane.showInputDialog("Cédula:");
        String especialidad = JOptionPane.showInputDialog("Especialidad:");
        medico m = new medico(nombre, cedula, especialidad);
        clinica.agregarMedico(m);
        JOptionPane.showMessageDialog(this, "Médico registrado.");
    }

    private void agendarConsulta(ActionEvent e) {
        String cedulaPaciente = JOptionPane.showInputDialog("Cédula del paciente:");
        paciente p = clinica.buscarPacientePorCedula(cedulaPaciente);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado.");
            return;
        }

        String cedulaMedico = JOptionPane.showInputDialog("Cédula del médico:");
        medico m = null;
        for (medico med : clinica.getMedicos()) {
            if (med.getCedula().equals(cedulaMedico)) {
                m = med;
                break;
            }
        }
        if (m == null) {
            JOptionPane.showMessageDialog(this, "Médico no encontrado.");
            return;
        }

        String sintomas = JOptionPane.showInputDialog("Síntomas:");
        String diagnostico = JOptionPane.showInputDialog("Diagnóstico:");
        String tratamiento = JOptionPane.showInputDialog("Tratamiento:");
        consulta c = new consulta(LocalDate.now(), m, p, sintomas, diagnostico, tratamiento);
        clinica.agregarConsulta(c);
        JOptionPane.showMessageDialog(this, "Consulta registrada.");
    }

    private void verHistorial(ActionEvent e) {
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del paciente:");
        var historial = clinica.obtenerHistorialPorCedula(cedula);
        if (historial.isEmpty()) {
            areaTexto.setText("No se encontraron consultas para esa cédula.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (consulta c : historial) {
                sb.append(c.toString()).append("\\n");
            }
            areaTexto.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        persistenciaArchivo.cargarDatos();
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
