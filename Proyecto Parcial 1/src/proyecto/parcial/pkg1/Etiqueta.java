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
public class Etiqueta implements Comparable<Etiqueta> {
    
    private String descripcion;
    private int prioridad;

    public Etiqueta(String descripcion, int prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Etiqueta{" + "descripcion=" + descripcion + ", prioridad=" + prioridad + '}';
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
    

    @Override
    public int compareTo(Etiqueta o) {
        if(o.getPrioridad()>prioridad){
            return -1;
        }else if(o.getPrioridad()==prioridad){
            return 0;
        }else{
            return 1;
        }
    }
   
    
    
    
}
