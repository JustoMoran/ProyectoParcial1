/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.parcial.pkg1;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Collections;

/**
 *
 * @author alber
 */
public class Usuario {
    
    private String idUsuario;
    private ArrayList<Notificacion> notificaciones=new ArrayList<>();
    
    
    //Constructor de una variable
    public Usuario (String idUsuario){
        this.idUsuario=idUsuario;
    }
    
    //Método get
    public String getID(){
        return this.idUsuario;
    }
    
    public void desactivarNotificaciones(){
        for (Notificacion not: this.notificaciones){
            not.estadoActivo=false;
        }
    }
    
    public void activarNotificaciones(){
        for (Notificacion not: this.notificaciones){
            not.estadoActivo=true;
        }
    }
    
    public boolean eliminarNotificacion(Notificacion not){
        return this.notificaciones.remove(not);
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(ArrayList<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }
    
    
    
    @Override
    public boolean equals(Object o){
        if (o instanceof Usuario && o!=null){
            Usuario u= (Usuario)o;
            // Esto se llama polimorfismo
            return (u.idUsuario==this.idUsuario);
        }else{return false;}
    }
}
