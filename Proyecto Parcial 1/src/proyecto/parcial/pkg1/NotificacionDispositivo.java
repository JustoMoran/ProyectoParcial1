/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.parcial.pkg1;

import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class NotificacionDispositivo extends Notificacion{
    
    private ArrayList<Sensor> sensores;

    public NotificacionDispositivo(ArrayList<Sensor> sensores) {
        super();
        this.sensores = sensores;
    }

    public ArrayList<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(ArrayList<Sensor> sensores) {
        this.sensores = sensores;
    }

    public boolean isEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(boolean estadoActivo) {
        this.estadoActivo = estadoActivo;
    }
    
    

    @Override
    public void mostrarNotificacion() {
        System.out.println("Los sensores existentes son: ");
        for(Sensor s: sensores){
            System.out.println(s);
        }
        
    }
    
}
