ACTIVIDAD MODULO 3

Desarrollo de una Lista de Tareas

Descripcion de la aplicación:
La aplicación al abrirse da un aviso de la tarea proxima con la fecha de finalización mas sercana,
luego muestra un panel que se divide en dos paneles, en el panel de la izquierda esta el ingreso 
de datos,boton de registro , modificación y eliminación de datos guardados. A la derecha esta la
la lista de tareas donde esta tabla con la lista de tareas, boton de actualización de datos, boton
de ordenar por fecha de finalizacion de tarea, un combo box de ordenar por prioridad, boton de 
decripcion de tarea seleccionada y el boton de eliminar todas las tareas de la tabla.

En el panel de ingreso de datos se podra agregar el nombre de la tarea (obligatorio con validecion),
la fecha de plazo de finalización de la tarea(obligatorio,con validacion y formato de la fecha), un
combo box para elegir la prioridad(ninguna,alta,media,baja), por defeto esta en baja,un text area
donde se porda agregar una descripcion o items de la tarea tambien por defeto tiene agregado ninguna.

En el panel la lista de tareas la tabla con la lista de tareas donde al seleccionar una tarea se 
podra modificar, eliminar y ver una descripción de dicha tarea en una ventana emergente.

Descripción del codigo:
En el archivo del codigo se encuentra tres Package:

Package listadetareas, donde se encutra la clase main cono la conexión a la base de datos y el inicio de la 
aplicación.

Package interfaces, donde donde esta la interfaz InterfazTarea con los metodos guardar, modificar, eliminar,
eliminarTodo y tareaProxima. Tambien esta la clase ImplementacionTarea que implenta a la InterfazTarea
y hace uso de sus metodos:
guaradar: este metodo se conecta con la base de datos y guarda los datos de la tarea en la tabla tareas
modificar: este metodo se conecta a la base de datos y guara los cambios de una tarea y guardada
eliminar: este metodo se conecta a la base de datos y elimina una tarea ya guardada
eliminarTodo: este metodo se conecta a la base de datos y elimina todos los registro de la tabla sin 
eliminar la tabla.
tareaProxima: este metodo se conecta a la base de datos y hace una consulta con la busqueda de una
tarea mas sercana al la fecha actual.

Package tareas, donde se encuentra la clase Tarea que sirve como molde para guardar y modificar las tareas.
Tambien esta la clase Disenio, donde diseña la interfaz gragica y el manejo de los eventos de los botones
y el click de una tarea en la tabla. Tambien estan los metodos:
Metodo limpiar que pone en blanco los textField, en ninguno por defecto al textArea y al combo box
Metodo mostrar, este metodo se conecta a la base de datos y hace una consulta de la metadata donde obtiene los
los campos de la tabla y sus nombres, tambien obtiene la informacion de cada tarea. Donde por medio de un for
llena los titulos de la tabla y por medio de un whiele y un for anidado llena las filas con las tareas y la 
informacion que luego las agrega al modelo de la tabla.
Metodo registrar datos, este metodo toma los datos de los textField, textArea y combo box y inicialisa un
objeto nuevaTarea de la clase Tarea y guarda esos datos. Tambien inicialisa un objeto tarea de la clase
ImplementacionTarea y lama al metodoguardar pasandole como parametro al objeto nuevaTarea para 
guardarla en la base de datos.
Metodo modificar datos, este metodo toma los datos de los textField, textArea y combo box y inicialisa un
objeto nuevaTarea de la clase Tarea y guarda esos datos. Tambien inicialisa un objeto tarea de la clase
ImplementacionTarea y lama al metodo modificar pasandole como parametro al objeto nuevaTarea para 
modificar los datos en la base de datos.
Metodo obtenerId, este metodo captura el evento click de la tabla que obtiene la fila para obtener el id de la 
tarea seleccionada en la tabla para luego ser usado en el metodo eleminar.
Metodo descripcion, este metododo captura el evento click de la tabla para obtener la fila de la tarea 
seleccionada para luego tomar todos los datos de la tarea y mostrarla en una vetana emergente.
Metodo tareaProxima, este metodo inicializa un obleto de la clase ImplentacionTarea y lusa su metodo 
tareaProxima donde muestra un aviso de la tarea proxima a terminar.
Luego siguen los eventos: 
boton Guardar
boton Modificar
boton Eliminat
jTablaMouseClicked
boton Actualzar
boton Ored por fecha finaliz
combo box prioridad
boton Descripcion
boton Eliminar

Un inconveniente que tuve me percate en ultimo momento fue que no sabia como entragar la base de datos
lo resolvi exportandola a un rachivo y agregarla junto al archivo junto al codigo fuente pero si acaso aca 
paso la estructura de la base de dato:
CREATE DATABASE  IF NOT EXISTS `listadetareas`; 

CREATE TABLE `tareas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `fecha_registro` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `prioridad` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

