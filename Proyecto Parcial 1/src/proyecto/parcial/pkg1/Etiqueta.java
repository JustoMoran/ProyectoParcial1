/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.parcial.pkg1;

/**
 *
 * @author Ricardo
 */
public abstract class Etiqueta {
    
    String descripcion;
    int prioridad;

    public Etiqueta(String descripcion, int prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Etiqueta{" + "descripcion=" + descripcion + ", prioridad=" + prioridad + '}';
    }
    
    
    
    
}
