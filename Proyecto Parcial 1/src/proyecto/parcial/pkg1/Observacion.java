/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.parcial.pkg1;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author alber y justo
 */

//Clase Observación. Asignación de del tipo que pertenece cada propiedad observable.
public class Observacion {
    public double co;
    public double humidity;
    public boolean light;
    public double lpg;
    public boolean motion;
    public double smoke;
    public Date date;
    public double temp;
    
    //Constructor con 7 parámetros
    public Observacion(double co, double humidity, boolean light,double lpg,boolean motion, double smoke,double temp, String date) throws ParseException{
        this.co=co;
        this.humidity=humidity;
        this.light=light;
        this.lpg=lpg;
        this.motion=motion;
        this.smoke=smoke;
        this.temp=temp;
        this.date=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
    }
    
    @Override
    // Método toString
    //Devuelve un con junto de String formado con la propiedad observable correspondiente  
    public String toString(){
        return "Co: "+co+"; humidity: "+humidity+"; light: "+light+"; lpg: "+lpg+"; motion: "+motion+"; smoke: "+smoke+"; fecha: "+date;
    }
    
    public boolean determinarObservacion(Etiqueta etiqueta){
        EtiquetaRango rango;
        EtiquetaBool bool;
        switch (etiqueta.getDescripcion()) {
            case "Co":
                rango = (EtiquetaRango) etiqueta;
                return rango.rangoMayor > co && co > rango.rangoMenor;
            case "Humidity":
                rango = (EtiquetaRango) etiqueta;
                return rango.rangoMayor > co && co > rango.rangoMenor;
            case "Light":
                bool = (EtiquetaBool) etiqueta;
                return booleanCast(light ,bool.valor);
            case "Lpg":
                rango = (EtiquetaRango) etiqueta;
                return rango.rangoMayor > co && co > rango.rangoMenor;
            case "Motion":
                bool = (EtiquetaBool) etiqueta;
                return booleanCast(motion ,bool.valor);
            case "Smoke":
                rango = (EtiquetaRango) etiqueta;
                return rango.rangoMayor > co && co > rango.rangoMenor;
            case "Temperature":
                rango = (EtiquetaRango) etiqueta;
                return rango.rangoMayor > co && co > rango.rangoMenor;
            default:
                break;
        }
        return false;
    }
    
    public boolean booleanCast(Boolean b1,Boolean b2){
        if((b1 == true && b2 == true) || (b1==false && b2==false)){
            return true;
        }
        else if((b1== true && b2 == false) || (b1== false && b2==true)){
            return false;
        }
        return false;
    }
}
