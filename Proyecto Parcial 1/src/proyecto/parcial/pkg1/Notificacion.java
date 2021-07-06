/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.parcial.pkg1;

/**
 *
 * @author alber
 */

public abstract class Notificacion {
    public boolean estadoActivo=true;
    
    //Constructor por defecto
    public Notificacion(){}
    
    public abstract void mostrarNotificacion();
    
    public void cambiarEstado(){
        if (this.estadoActivo==true){
            this.estadoActivo=false;
        }else{this.estadoActivo=true;}
    }
}
