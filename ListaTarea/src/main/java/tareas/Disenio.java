
package tareas;

import interfaces.ImplementacionTarea;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import listatarea.Main;


public class Disenio extends javax.swing.JFrame {


    public Disenio() {
        initComponents();
                setLocationRelativeTo(null);
        mostrar("SELECT * FROM tareas");
        tareaProxima();
    }

        //____________________Limpiar______________________________
    
    public void limpiar() {
        jTarea.setText("");
        jFechaFin.setText("");
        jPrioridad.setSelectedItem("ninguna");
        jDescripcion.setText("ninguna");

    }

    //______________________Mostrar_Los_Datos__________________________
    
    public void mostrar(String sql) {
        if(sql.length()<22){
        jPrioridadBusqueda.setSelectedItem("ninguna");
    }
                
        Main con = new Main();
        Connection conexion = con.establecerConeccion();

        DefaultTableModel model = new DefaultTableModel();

        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();

            int numColumnas = metaData.getColumnCount();

            for (int column = 1; column <= numColumnas; column++) {
                model.addColumn(metaData.getColumnName(column));
            }
            
            while (rs.next()) {
                Object[] rowData = new Object[numColumnas];
                for (int i = 0; i < numColumnas; i++) {
                    rowData[i] = rs.getObject(i + 1);
                }
                model.addRow(rowData);

            }

            jTabla.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
                limpiar();
    }

//______________________________Registrar_Los_Datos______________________________________
    public void guardar() {

        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        ImplementacionTarea tarea = new ImplementacionTarea();

        String nombre = jTarea.getText();
        Date fechReg = new Date();
        Date fechaFin = null;
        String prioridad = (String) jPrioridad.getSelectedItem();
        String descripcion = jDescripcion.getText();
        try {
            fechaFin = fecha.parse(jFechaFin.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto");
            return;
        }

        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setNombreTarea(nombre);
        nuevaTarea.setDescripcion(descripcion);
        nuevaTarea.setFechaRegistro(fechReg);
        nuevaTarea.setFechaFin(fechaFin);
        nuevaTarea.setPrioridad(prioridad);
        if ("".equals(nombre)) {
            JOptionPane.showMessageDialog(null, "Debe agregar una tarea");
        } else {
            tarea.guardar(nuevaTarea);
                        limpiar();
        }

    }

    //____________________________Modificar_Los_Datos_____________________________
    public void modificar() {

        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        ImplementacionTarea tarea = new ImplementacionTarea();
        int id = obtenerId();
        String nombre = jTarea.getText();
        Date fechaFin = null;
        String prioridad = (String) jPrioridad.getSelectedItem();
        String descripcion = jDescripcion.getText();
        try {
            fechaFin = fecha.parse(jFechaFin.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto");
            return;
        }

        Date fechaActual = new Date();
        if (!fechaFin.after(fechaActual)) {
            JOptionPane.showMessageDialog(null, "Tiene que ingresar una fechas posterior a la actual");

        } else {
            Tarea nuevaTarea = new Tarea();
            nuevaTarea.setId(id);
            nuevaTarea.setNombreTarea(nombre);
            nuevaTarea.setDescripcion(descripcion);
            nuevaTarea.setFechaFin(fechaFin);
            nuevaTarea.setPrioridad(prioridad);
            if ("".equals(nombre)) {
                JOptionPane.showMessageDialog(null, "Debe agregar una tarea");
            } else {
                tarea.modificar(nuevaTarea);
                limpiar();
            }
        }
    }

    //________________Obtener_id_De_La_Fila_Seleccionada____________________________________
    public int obtenerId() {

        int filaSeleccionada = jTabla.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla");
            return -1;
        }
        int id = (int) jTabla.getValueAt(filaSeleccionada, 0);

        return id;
    }

    //_______________________Descripcion_De_La_Fila_Seleccionada_________________
    private void descripcion() {

        int fila = jTabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "No selecciono ninguna tarea");
        } else {

            String tarea = (String) jTabla.getValueAt(fila, 1);
            String fechaRegistro = (String) jTabla.getValueAt(fila, 2).toString();
            String fechaFin = (String) jTabla.getValueAt(fila, 3).toString();
            String descripcion = (String) jTabla.getValueAt(fila, 4);
            String prioridad = (String) jTabla.getValueAt(fila, 5);

            JOptionPane.showMessageDialog(null, "Tarea: " + tarea + "\n Fecha de registri: " + fechaRegistro + "\n Fecha de finalización: " + fechaFin + "\n Prioridad: " + prioridad + "\n Descripcion: " + descripcion);

        }

    }
    
    //______________________Mostrar_la_tare_proxima_a_vencer___________________
    
    private void tareaProxima(){
        ImplementacionTarea tarea = new ImplementacionTarea(); 
        tarea.tareaProxima();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTarea = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jFechaFin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPrioridad = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDescripcion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jActualizarLista = new javax.swing.JButton();
        jOrdenarPorFechaFinaliz = new javax.swing.JButton();
        jPrioridadBusqueda = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabla = new javax.swing.JTable();
        btnDescripcion = new javax.swing.JButton();
        btnEliminarTodo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ingreso de datos");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tarea:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha de finaliz.(yyyy-MM-dd):");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Prioridad(opciona):");

        jPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ninguna", "alta", "media", "baja" }));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Descripción(opcional):");

        jDescripcion.setColumns(20);
        jDescripcion.setRows(5);
        jDescripcion.setText(" ninguna");
        jScrollPane1.setViewportView(jDescripcion);

        btnGuardar.setText("Agregar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPrioridad, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jFechaFin, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTarea, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("LISTA DE TAREAS");

        jActualizarLista.setText("Actualizar Lista");
        jActualizarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jActualizarListaActionPerformed(evt);
            }
        });

        jOrdenarPorFechaFinaliz.setText("Ord. por fecha de finaliz");
        jOrdenarPorFechaFinaliz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOrdenarPorFechaFinalizActionPerformed(evt);
            }
        });

        jPrioridadBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ninguna", "alta", "media", "baja" }));
        jPrioridadBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPrioridadBusquedaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel7.setText("Ordenar por prioridad");

        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTabla);

        btnDescripcion.setText("Descripción");
        btnDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescripcionActionPerformed(evt);
            }
        });

        btnEliminarTodo.setText("Eliminar todo");
        btnEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(btnDescripcion)
                                .addGap(109, 109, 109)
                                .addComponent(btnEliminarTodo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jActualizarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jOrdenarPorFechaFinaliz)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jPrioridadBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jActualizarLista)
                    .addComponent(jOrdenarPorFechaFinaliz)
                    .addComponent(jPrioridadBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDescripcion)
                    .addComponent(btnEliminarTodo))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //Boton que guarda los datos
        guardar();
        mostrar("SELECT * FROM tareas");        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        //Boton que modifica los datos de la fila seleccionada
        modificar();
        mostrar("SELECT * FROM tareas ");
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //Boton que elimina la tarea de la fila seleccionada
        int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea elimitar la tarea", "Confirmar Eliminar Todo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (JOptionPane.OK_OPTION == confirmado) {
            ImplementacionTarea tarea = new ImplementacionTarea();
            int id = obtenerId();
            tarea.eliminar(id);
            mostrar("SELECT * FROM tareas");
            limpiar();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaMouseClicked
    //Carga los datos a los txtFile de la fila seleccionanda
        int fila = jTabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "No selecciono ninguna tarea");
        } else {

            String tarea = (String) jTabla.getValueAt(fila, 1);
            String fecha = (String) jTabla.getValueAt(fila, 3).toString();
            String descripcion = (String) jTabla.getValueAt(fila, 4);
            String prioridad = (String) jTabla.getValueAt(fila, 5);

            jTarea.setText(tarea);
            jFechaFin.setText(fecha);
            jDescripcion.setText(descripcion);
            jPrioridad.setSelectedItem(prioridad);
        }
    }//GEN-LAST:event_jTablaMouseClicked

    private void jActualizarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jActualizarListaActionPerformed
        //Boton que Atualiza las tareas
        mostrar("SELECT * FROM tareas ");
        limpiar();
        tareaProxima();
    }//GEN-LAST:event_jActualizarListaActionPerformed

    private void jOrdenarPorFechaFinalizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOrdenarPorFechaFinalizActionPerformed
       //Boton que Muestra las fechas en orden ascendente
        mostrar("SELECT * FROM  tareas ORDER BY fecha_fin ASC;");
        limpiar();
        tareaProxima();
    }//GEN-LAST:event_jOrdenarPorFechaFinalizActionPerformed

    private void jPrioridadBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPrioridadBusquedaActionPerformed
        //Boton que Separa las tareas por prioridad
        String prioridad = "SELECT * FROM tareas WHERE prioridad='" + (String) jPrioridadBusqueda.getSelectedItem() + "';";
        mostrar(prioridad);

        limpiar();
    }//GEN-LAST:event_jPrioridadBusquedaActionPerformed

    private void btnDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescripcionActionPerformed
       //Boton que Muestra ventana emergente con la descripcion de la tarea seleccionada
        descripcion();
    }//GEN-LAST:event_btnDescripcionActionPerformed

    private void btnEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTodoActionPerformed
        //Boton que elimina todos los datos de la tabla
        ImplementacionTarea tarea = new ImplementacionTarea();
        int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea elimitar todo?", "Confirmar Eliminar Todo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (JOptionPane.OK_OPTION == confirmado) {
            tarea.eliminarTodo();
            mostrar("SELECT * FROM tareas ");
            limpiar();
        }
    }//GEN-LAST:event_btnEliminarTodoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Disenio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Disenio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Disenio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Disenio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Disenio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescripcion;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarTodo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton jActualizarLista;
    private javax.swing.JTextArea jDescripcion;
    private javax.swing.JTextField jFechaFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton jOrdenarPorFechaFinaliz;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> jPrioridad;
    private javax.swing.JComboBox<String> jPrioridadBusqueda;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabla;
    private javax.swing.JTextField jTarea;
    // End of variables declaration//GEN-END:variables
}
