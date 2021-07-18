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
public class EtiquetaBool extends Etiqueta{
    
    boolean valor;

    public EtiquetaBool(boolean valor, String descripcion, int prioridad) {
        super(descripcion, prioridad);
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Tipo: " + this.getDescripcion()  + " con valor=" + valor + '}';
    }
    
    
    
    
}
