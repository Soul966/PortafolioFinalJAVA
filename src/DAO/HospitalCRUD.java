package DAO;

import Entidades.Doctor;
import Entidades.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.*;
import java.util.List;

public class HospitalCRUD {


    //metodo ingresar alumno
    public void insertarDoctor(Doctor a) {
        List<Doctor> doctores = leerDoctores("C:\\Users\\LOPI1\\Documents\\AAAAJAVAFINAL\\ListaDoctores.txt");
        doctores.add(a);
        escribirListaDoctores("C:\\Users\\LOPI1\\Documents\\AAAAJAVAFINAL\\ListaDoctores.txt",doctores);
    }


    //metodo busqueda por id
    public Doctor buscarDoctorPorId(String id) {
        List<Doctor> doctores = leerDoctores("C:\\Users\\LOPI1\\Documents\\AAAAJAVAFINAL\\ListaDoctores.txt");
        return doctores.stream()
                .filter(doctor -> doctor.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Doctor> leerDoctores(String rutaArchivo) {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (List<Doctor>) stream.readObject();
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al leer el archivo", e);
        }
    }



    public static void escribirListaDoctores(String rutaArchivo, List<Doctor> listaDoctores) {
        try {
            List<Doctor> doctoresActuales = leerDoctores(rutaArchivo);

            doctoresActuales.addAll(listaDoctores);

            try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
                stream.writeObject(doctoresActuales);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo", e);
        }
    }


}