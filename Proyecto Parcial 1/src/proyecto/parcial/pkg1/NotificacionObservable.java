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
public  class  NotificacionObservable  extends Notificacion implements Comparable<NotificacionObservable>{
    
    Etiqueta etiqueta;
    
    
    public NotificacionObservable(Etiqueta etiqueta){
        this.etiqueta =  etiqueta;

    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return "NotificacionObservable: " + etiqueta +" con prioridad: " + obtenerPrioridad(this.etiqueta.getPrioridad()) ;
    }
    
    private String obtenerPrioridad(int p){
        if(p == 1){
            return "Alta";
        }
        else if(p == 2){
            return "Media";
        }
        else if(p == 3){
            return "Baja";
        }
        return "";
        
    }

    
    

    @Override
    public void mostrarNotificacion() {
        System.out.println(etiqueta);
    }

    @Override
    public int compareTo(NotificacionObservable o) {
        if(o.getEtiqueta().getPrioridad()>this.etiqueta.getPrioridad()){
            return -1;
        }
        else if(o.getEtiqueta().getPrioridad()==this.etiqueta.getPrioridad()){
            return 0;
        }
        else {
            return 1;
        }
        
    }
    
    
}