
package listatarea;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import tareas.Disenio;



public class Main {
        //Atributos de conexión
    private Connection conectar = null;

    private final String usuario = "root";

    private final String contrasena = "123456789";

    private final String db = "listadetareas";

    private final String ip = "localhost";

    private final String puerto = "3306";

    private final String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + db+"?serverTimezone=UTC";

    public Connection establecerConeccion() {
        try {

            conectar = DriverManager.getConnection(cadena, usuario, contrasena);
           

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se generó la conexión" + e);
        }
        return conectar;
    }

    public static void main(String[] args) {
        
        Disenio disenio=new Disenio();
        disenio.setVisible(true);
    }
}
