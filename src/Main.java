
import Entidades.Doctor;
import DAO.HospitalCRUD;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HospitalCRUD crud = new HospitalCRUD();
        Doctor nuevoDoctor = new Doctor("001","Hugo","contreras","Masculino","pediatria");
        //crud.insertarDoctor(nuevoDoctor);
        //crud.buscarDoctorPorId("001");

    }
}