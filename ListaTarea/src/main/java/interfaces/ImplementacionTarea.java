
package interfaces;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import listatarea.Main;
import tareas.Tarea;


public class ImplementacionTarea implements InterfazTarea {

    @Override
    public void guardar(Tarea tarea) {

        Main con = new Main();
        String sql = "INSERT INTO tareas (nombre,fecha_registro,fecha_fin,descripcion,prioridad) VALUES(?,?,?,?,?)";
        Connection conexion = con.establecerConeccion();
        try {

            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, tarea.getNombreTarea());
            preparedStatement.setDate(2, new Date(tarea.getFechaRegistro().getTime()));
            preparedStatement.setDate(3, new Date(tarea.getFechaFin().getTime()));
            preparedStatement.setString(4, tarea.getDescripcion());
            preparedStatement.setString(5, tarea.getPrioridad());
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Tarea guardada");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido guardar la tarea");
            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void modificar(Tarea tarea) {

        Main con = new Main();
        Connection conexion = con.establecerConeccion();
        String sql = "UPDATE tareas SET nombre=?,fecha_fin=?,descripcion=?,prioridad=? WHERE id=?;";

        try {

            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, tarea.getNombreTarea());
            preparedStatement.setDate(2, new Date(tarea.getFechaFin().getTime()));
            preparedStatement.setString(3, tarea.getDescripcion());
            preparedStatement.setString(4, tarea.getPrioridad());
            preparedStatement.setInt(5, tarea.getId());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, " se ha modificado la tarea");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido modificar la tarea");
            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {

        String sql = "DELETE FROM tareas WHERE id = " + id;
        try {
            Main con = new Main();
            Connection conexion = con.establecerConeccion();
            Statement st = conexion.createStatement();

            int filasAfectadas = st.executeUpdate(sql);

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "La tarea fue eliminada");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la tarea");
            }

            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTodo() {

        String sql = "TRUNCATE TABLE tareas;";
        try {
            Main con = new Main();
            Connection conexion = con.establecerConeccion();
            Statement st = conexion.createStatement();

            int listaTareas = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se ha eliminado todo");

            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void tareaProxima() {
        String sql ="SELECT * FROM tareas WHERE DATE(tareas.fecha_fin)> DATE(NOW()) LIMIT 1 ;";
        try {
            Main con = new Main();
            Connection conexion = con.establecerConeccion();
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                JOptionPane.showMessageDialog(null, "TAREA CON FECHA DE FINALIZACION MAS SERCANA\nTarea: " + rs.getObject(2) + "\nFecha de finalizacio: " + rs.getObject(4));
            }
            st.close();
            conexion.close();
        } catch (SQLException e) {

        }
    }

}
