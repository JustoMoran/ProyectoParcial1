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

/**
 *
 * @author alber
 */
public class Usuario {
    
    private String idUsuario;
    public ArrayList<Notificacion> notificaciones=new ArrayList<>();
    
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
    
    @Override
    public boolean equals(Object o){
        if (o instanceof Usuario && o!=null){
            Usuario u= (Usuario)o;
            // Esto se llama polimorfismo
            return (u.idUsuario==this.idUsuario);
        }else{return false;}
    }
}
