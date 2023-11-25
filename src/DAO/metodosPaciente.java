package DAO;

import Entidades.Doctor;
import Entidades.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class metodosPaciente {
    public void insertarPaciente(Paciente b) {
        List<Paciente> pacientes = leerPacientes("C:\\Users\\LOPI1\\Documents\\AAAAJAVAFINAL\\ListaPacientes.txt");
        pacientes.add(b);
        escribirListaPacientes("C:\\Users\\LOPI1\\Documents\\AAAAJAVAFINAL\\ListaPacientes.txt",pacientes);
    }

    public Paciente buscarPacientePorId(String id) {
        List<Paciente> pacientes = leerPacientes("C:\\Users\\LOPI1\\Documents\\AAAAJAVAFINAL\\ListaPacientes.txt");
        return pacientes.stream()
                .filter(paciente -> paciente.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Paciente> leerPacientes(String rutaArchivo) {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (List<Paciente>) stream.readObject();
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al leer el archivo", e);
        }
    }

    public static void escribirListaPacientes(String rutaArchivo, List<Paciente> listaPacientes) {
        try {
            List<Paciente> pacientesActuales = leerPacientes(rutaArchivo);

            pacientesActuales.addAll(listaPacientes);

            try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
                stream.writeObject(pacientesActuales);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo", e);
        }
    }
}
