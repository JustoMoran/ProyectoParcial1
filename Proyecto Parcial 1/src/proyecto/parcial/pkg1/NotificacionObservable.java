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
public  class  NotificacionObservable  extends Notificacion{
    
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
    public void mostrarNotificacion() {
        System.out.println(etiqueta);
    }
    
}
