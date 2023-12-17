
package interfaces;

import tareas.Tarea;


public interface InterfazTarea {
    public void guardar(Tarea tarea);
    public void modificar(Tarea tarea);
    public void eliminar(int id);
    public void eliminarTodo();
    public void tareaProxima();
}
