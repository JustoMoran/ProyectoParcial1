/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.parcial.pkg1;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alber
 */
public class Sensor {
    private String id;
    private ArrayList<Observacion> observaciones;
    
    public Sensor(String id) {
        this.id=id;
    }
    
    public void obtenerObservaciones() throws IOException, ParseException{
        List<String> lineas = LeerFichero.leer("src/iot_telemetry_data_new.csv");
        for (String linea:lineas){
            linea.replace('"', '\0');
            String[] lineaC=linea.split(",");
            if(lineaC[1].equals(this.id)){
                double co=Double.parseDouble(lineaC[2]);
                double humidity=Double.parseDouble(lineaC[3]);
                boolean light=Boolean.parseBoolean(lineaC[4]);
                double lpg=Double.parseDouble(lineaC[5]);
                boolean motion=Boolean.parseBoolean(lineaC[6]);
                double smoke=Double.parseDouble(lineaC[7]);
                String date=lineaC[8];
                Observacion o = new Observacion(co,humidity,light,lpg,motion,smoke,date);
                observaciones.add(o);
            }   
        } 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(ArrayList<Observacion> observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
}
