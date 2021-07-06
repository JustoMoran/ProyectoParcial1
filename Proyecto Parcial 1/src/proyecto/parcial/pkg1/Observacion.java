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
public class Observacion {
    public double co;
    public double humidity;
    public boolean light;
    public double lpg;
    public boolean motion;
    public double smoke;
    public Date date;
    
    public Observacion(double co, double humidity, boolean light,double lpg,boolean motion, double smoke,String date) throws ParseException{
        this.co=co;
        this.humidity=humidity;
        this.light=light;
        this.lpg=lpg;
        this.motion=motion;
        this.smoke=smoke;
        this.date=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
    }
    
    @Override
    // Método toString
    //devuelve un con junto de String formado con la propiedad observable correspondiente  
    public String toString(){
        return "Co: "+co+"; humidity: "+humidity+"; light: "+light+"; lpg: "+lpg+"; motion: "+motion+"; smoke: "+smoke+"; fecha: "+date;
    }
}
