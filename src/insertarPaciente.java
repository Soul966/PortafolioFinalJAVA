import DAO.HospitalCRUD;
import DAO.metodosPaciente;
import Entidades.Doctor;
import Entidades.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class insertarPaciente extends JFrame {

    //componentes del jpanel
    private JButton btnInsertar;
    private JButton btnLimpiar;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JPanel panelPacientes;
    private JTextField txtId;
    private JLabel lbl_ID;
    private JTextField txtNombre;
    private JLabel lblNombre;
    private JTextField txtApellido;
    private JLabel lblApellido;
    private JTextField txtFecha;
    private JLabel lblFecha;
    private JLabel lblGenero;
    private JTextField txtAlergias;
    private JLabel lblAlergias;
    private JComboBox cmbGenero;


    //action listener del boton limpiar para probar el metodo
    public insertarPaciente() {
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //llamamos al metodo limpiar
                limpiar();
            }
        });


        //actionlistener del boton insertar para provar los metodos
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!verificarContenido()) {
                    return;
                }

                //empezamos con el proceso de llenado.
                try{
                     metodosPaciente crud = new metodosPaciente();
                    Paciente nuevoPaciente = new Paciente(txtId.getText(),txtNombre.getText(),txtApellido.getText(),txtFecha.getText(),cmbGenero.getSelectedItem().toString(),txtAlergias.getText());
                    crud.insertarPaciente(nuevoPaciente);
                    JOptionPane.showMessageDialog(panelPacientes,"Paciente agregado con exito","paciente",JOptionPane.INFORMATION_MESSAGE);

                } catch (RuntimeException runtimeException) {
                    runtimeException.printStackTrace();
                    JOptionPane.showMessageDialog(panelPacientes, "Error inesperado", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodosPaciente crud = new metodosPaciente();
                String ID = txtId.getText();
                if(ID.isEmpty()){
                    JOptionPane.showMessageDialog(panelPacientes, "No hay ningun ID", "Error", JOptionPane.ERROR_MESSAGE);
                    txtId.requestFocusInWindow();
                    return;
                }

                Paciente PacienteEncontrado = crud.buscarPacientePorId(ID);

                if(PacienteEncontrado == null){
                    JOptionPane.showMessageDialog(panelPacientes, "No hay ningun Paciente con ese ID", "Error", JOptionPane.ERROR_MESSAGE);

                }else{
                    txtId.setText(PacienteEncontrado.getId());
                    txtNombre.setText(PacienteEncontrado.getNombre());
                    txtApellido.setText(PacienteEncontrado.getApellido());
                    txtFecha.setText(PacienteEncontrado.getFechaNacimiento());
                    cmbGenero.setSelectedItem(PacienteEncontrado.getGenero());
                    txtAlergias.setText(PacienteEncontrado.getAlergias());
                }
            }
        });
    }

    // main para ejecutar la ventana
    public static void main(String[] args) {
        insertarPaciente p = new insertarPaciente();
        p.setContentPane(p.panelPacientes);
        p.setSize(500, 250);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setVisible(true);
        p.setTitle("Pacientes");
        p.setLocationRelativeTo(null);

    }

    //metodo para verificar el contenido de los texfields
    public boolean verificarContenido() {
        String ID = txtId.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String alergias = txtAlergias.getText();
        String fechaNacimiento = txtFecha.getText();


        if (ID.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || alergias.isEmpty() || fechaNacimiento.isEmpty()) {
            JOptionPane.showMessageDialog(panelPacientes, "Verifique que todos los campos esten llenados", "Pacientes", JOptionPane.ERROR_MESSAGE);
            if (ID.isEmpty()) {
                txtId.requestFocusInWindow();
            } else if (nombre.isEmpty()) {
                txtNombre.requestFocusInWindow();
            } else if (apellido.isEmpty()) {
                txtApellido.requestFocusInWindow();
            } else if (fechaNacimiento.isEmpty()) {
                txtFecha.requestFocusInWindow();
            } else if (alergias.isEmpty()) {
                txtAlergias.requestFocusInWindow();
            }
            return false;

        } else {
            return true;
        }

    }


    //metodo para limpiar los texfields de pacientes
    public void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtFecha.setText("");
        txtAlergias.setText("");

    }
}
