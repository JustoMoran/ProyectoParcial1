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
public class EtiquetaRango extends Etiqueta {

    
   double rangoMayor;
   double rangoMenor;

    public EtiquetaRango(double rangoMayor, double rangoMenor, String descripcion, int prioridad) {
        super(descripcion, prioridad);
        this.rangoMayor = rangoMayor;
        this.rangoMenor = rangoMenor;
    }

    @Override
    public String toString() {
        return "EtiquetaRango{" + "rangoMayor=" + rangoMayor + ", rangoMenor=" + rangoMenor + '}';
    }
   
   
    
    
    
}
