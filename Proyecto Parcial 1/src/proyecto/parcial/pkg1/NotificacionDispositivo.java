/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.parcial.pkg1;

import java.util.ArrayList;

/**
 *
 * @author homar
 */
public class NotificacionDispositivo extends Notificacion{
    
    public ArrayList<Sensor> sensores;

    public NotificacionDispositivo(ArrayList<Sensor> sensores) {
        this.sensores = sensores;
    }
    
    

    @Override
    public void mostrarNotificacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
