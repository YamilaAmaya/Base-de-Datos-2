package org.example;

import org.example.DAO.ConexionBD;
import org.example.DAO.EstudianteDAO;
import org.example.Models.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EstudianteApp extends JFrame {
    private EstudianteDAO estudianteDAO;

    private JTable tablaEstudiantes;
    private JButton btnActualizar;
    private JButton btnAgregarEstudiante;
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblFechaNacimiento;
    private JLabel lblCarrera;

    public EstudianteApp(EstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;

        setTitle("Aplicación de Estudiantes");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inicializarComponentes();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void inicializarComponentes() {
        // Crear tabla para mostrar estudiantes
        tablaEstudiantes = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);

        // Botón para actualizar la lista de estudiantes
        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarTablaEstudiantes();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(EstudianteApp.this, "Error al obtener estudiantes de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botón para agregar un nuevo estudiante
        btnAgregarEstudiante = new JButton("Agregar Estudiante");
        btnAgregarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEstudiante();
            }
        });

        // Crear etiquetas para mostrar los detalles del estudiante seleccionado
        lblID = new JLabel("ID:");
        lblNombre = new JLabel("Nombre:");
        lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        lblCarrera = new JLabel("Carrera:");



        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnActualizar);
        panelBotones.add(btnAgregarEstudiante);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.NORTH);

        // Agregar panel principal al JFrame
        add(panelPrincipal);

        // Escuchar los eventos de teclado en los campos de texto para guardar los cambios cuando se presione Enter
        KeyListener guardarCambiosListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    guardarCambios();
                }
            }
        };

        lblNombre.addKeyListener(guardarCambiosListener);
        lblFechaNacimiento.addKeyListener(guardarCambiosListener);
        lblCarrera.addKeyListener(guardarCambiosListener);
        JButton btnActualizarEstudiante = new JButton("Actualizar Estudiante");
        btnActualizarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstudiante();
            }
        });
        panelBotones.add(btnActualizarEstudiante);
    }

    private void guardarCambios() {
        // Obtener los datos de la fila seleccionada en la tabla
        int filaSeleccionada = tablaEstudiantes.getSelectedRow();
        if (filaSeleccionada == -1) {
            return;
        }

        // Obtener los datos ingresados por el usuario
        int id = (int) tablaEstudiantes.getValueAt(filaSeleccionada, 0);
        String nombre = lblNombre.getText();
        Date fechaNacimiento = (Date) tablaEstudiantes.getValueAt(filaSeleccionada, 2);
        String carrera = lblCarrera.getText();

        // Crear el objeto Estudiante con los nuevos datos
        Estudiante estudianteModificado = new Estudiante(id, nombre, fechaNacimiento, carrera);

        // Actualizar el estudiante en la base de datos
        try {
            estudianteDAO.actualizarEstudiante(estudianteModificado);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar estudiante en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void actualizarEstudiante() {
        // Obtener la fila seleccionada en la tabla
        int filaSeleccionada = tablaEstudiantes.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un estudiante para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener los datos del estudiante seleccionado
        int id = (int) tablaEstudiantes.getValueAt(filaSeleccionada, 0);
        String nombre = (String) tablaEstudiantes.getValueAt(filaSeleccionada, 1);
        Date fechaNacimiento = (Date) tablaEstudiantes.getValueAt(filaSeleccionada, 2);
        String carrera = (String) tablaEstudiantes.getValueAt(filaSeleccionada, 3);

        // Mostrar un diálogo para que el usuario edite los datos del estudiante
        JTextField txtNombre = new JTextField(nombre);
        JTextField txtFechaNacimiento = new JTextField(fechaNacimiento.toString());
        JTextField txtCarrera = new JTextField(carrera);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Fecha de Nacimiento (YYYY-MM-DD):"));
        panel.add(txtFechaNacimiento);
        panel.add(new JLabel("Carrera:"));
        panel.add(txtCarrera);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Actualizar Estudiante", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            // Validar y parsear la fecha de nacimiento
            Date nuevaFechaNacimiento;
            try {
                nuevaFechaNacimiento = java.sql.Date.valueOf(txtFechaNacimiento.getText());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una fecha válida en el formato YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear un objeto Estudiante con los datos actualizados
            Estudiante estudianteActualizado = new Estudiante(id, txtNombre.getText(), nuevaFechaNacimiento, txtCarrera.getText());

            // Actualizar el estudiante en la base de datos
            try {
                estudianteDAO.actualizarEstudiante(estudianteActualizado);
                // Actualizar la tabla después de la actualización
                actualizarTablaEstudiantes();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al actualizar estudiante en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarTablaEstudiantes() throws SQLException {
        // Obtener lista de estudiantes de la base de datos
        List<Estudiante> estudiantes = estudianteDAO.obtenerTodosLosEstudiantes();

        // Convertir lista de estudiantes a un arreglo bidimensional para la tabla
        Object[][] datos = new Object[estudiantes.size()][4];
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante estudiante = estudiantes.get(i);
            datos[i][0] = estudiante.getId();
            datos[i][1] = estudiante.getNombreCompleto();
            datos[i][2] = estudiante.getFechaNacimiento();
            datos[i][3] = estudiante.getCarrera();
        }

        // Nombre de las columnas de la tabla
        String[] columnas = {"ID", "Nombre Completo", "Fecha de Nacimiento", "Carrera"};

        // Actualizar modelo de la tabla con los datos
        tablaEstudiantes.setModel(new DefaultTableModel(datos, columnas));
    }

    private void agregarEstudiante() {
        // Mostrar un diálogo para que el usuario ingrese los datos del nuevo estudiante
        JTextField txtNombre = new JTextField();
        JTextField txtFechaNacimiento = new JTextField();
        JTextField txtCarrera = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Fecha de Nacimiento (YYYY-MM-DD):"));
        panel.add(txtFechaNacimiento);
        panel.add(new JLabel("Carrera:"));
        panel.add(txtCarrera);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Agregar Estudiante", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            // Validar y parsear la fecha de nacimiento
            Date fechaNacimiento;
            try {
                fechaNacimiento = java.sql.Date.valueOf(txtFechaNacimiento.getText());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una fecha válida en el formato YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear un objeto Estudiante con los datos ingresados
            Estudiante nuevoEstudiante = new Estudiante(0, txtNombre.getText(), fechaNacimiento, txtCarrera.getText());

            // Agregar el estudiante a la base de datos
            try {
                estudianteDAO.agregarEstudiante(nuevoEstudiante);
                // Actualizar la tabla después de la inserción
                actualizarTablaEstudiantes();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al agregar estudiante a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public static void main(String[] args) throws SQLException {
        // Crear conexión a la base de datos
        Connection connection = ConexionBD.getConnection();

        // Crear instancia de EstudianteDAO
        EstudianteDAO estudianteDAO = new EstudianteDAO(connection);

        // Crear instancia de la aplicación
        SwingUtilities.invokeLater(() -> new EstudianteApp(estudianteDAO));
    }
}
