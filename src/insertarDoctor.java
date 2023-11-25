import javax.print.Doc;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Entidades.Doctor;
import DAO.HospitalCRUD;

public class insertarDoctor extends JFrame {
    private JPanel miPanel;
    private JLabel lbl_ID;
    private JTextField txt_ID;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblEspecialidad;
    private JTextField txtEspecialidad;
    private JLabel lblGenero;
    private JLabel lblApellido;
    private JTextField txtApellido;
    private JComboBox cmbGenero;
    private JButton btIngresar;
    private JButton btnLimpiar;
    private JButton btnBuscar;
    private JButton btneliminar;
    private JButton btnactualizar;

//action listener boton ingresar
    public insertarDoctor() {
        btIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //verificamos que los textfields esten llenados
                if (!verificarContenido()) {
                    // Si los campos no están llenados, salimos del método
                    return;
                }
                //empezamos con el proceso de llenado.
                try{
                    HospitalCRUD crud = new HospitalCRUD();
                    Doctor nuevoDoctor = new Doctor(txt_ID.getText(),txtNombre.getText(),txtApellido.getText(),cmbGenero.getSelectedItem().toString(),txtEspecialidad.getText());
                    crud.insertarDoctor(nuevoDoctor);
                    JOptionPane.showMessageDialog(miPanel,"Doctor agregado con exito","Doctores",JOptionPane.INFORMATION_MESSAGE);

                } catch (RuntimeException runtimeException) {
                    runtimeException.printStackTrace();
                    JOptionPane.showMessageDialog(miPanel, "Error inesperado", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

//action listener boton limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();

            }
        });
        //action listener buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HospitalCRUD crud = new HospitalCRUD();
                String ID = txt_ID.getText();
                if(ID.isEmpty()){
                    JOptionPane.showMessageDialog(miPanel, "No hay ningun ID", "Error", JOptionPane.ERROR_MESSAGE);
                    txt_ID.requestFocusInWindow();
                    return;
                }

                Doctor DoctorEncontrado = crud.buscarDoctorPorId(ID);

                if(DoctorEncontrado == null){
                    JOptionPane.showMessageDialog(miPanel, "No hay ningun Doctor con ese ID", "Error", JOptionPane.ERROR_MESSAGE);

                }else{
                    txt_ID.setText(DoctorEncontrado.getId());
                    txtNombre.setText(DoctorEncontrado.getNombre());
                    txtApellido.setText(DoctorEncontrado.getApellido());
                    txtEspecialidad.setText(DoctorEncontrado.getEspecialidad());
                    cmbGenero.setSelectedItem(DoctorEncontrado.getGenero());
                }

            };
        });
    }

    public static void main(String[] args) {
        insertarDoctor p = new insertarDoctor();
        p.setContentPane(p.miPanel);
        p.setSize(500,250);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setVisible(true);
        p.setTitle("Doctores");
        p.setLocationRelativeTo(null);
    }

    //metodo para validar los campos con texto
    public boolean verificarContenido(){
        String ID = txt_ID.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String especialidad = txtEspecialidad.getText();
        String genero = cmbGenero.getSelectedItem().toString();

        if(ID.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || genero.isEmpty() || especialidad.isEmpty()){
        JOptionPane.showMessageDialog(miPanel,"Verifique que todos los campos esten llenados","Doctores",JOptionPane.ERROR_MESSAGE);
            if (ID.isEmpty()) {
                txt_ID.requestFocusInWindow();
            } else if (nombre.isEmpty()) {
                txtNombre.requestFocusInWindow();
            } else if (apellido.isEmpty()) {
                txtApellido.requestFocusInWindow();
            }else if (especialidad.isEmpty()){
                txtEspecialidad.requestFocusInWindow();
            }
            return false;
        }else {
            return true;
        }

    }

    //metodo para limpiar los textfields
    public void limpiar(){
        txt_ID.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEspecialidad.setText("");

    }
}
