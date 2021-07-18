/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.parcial.pkg1;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
/**
 *
 * @author alber
 */
public class ProyectoParcial1 {
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    public static Usuario usuarioActual;
    public static Scanner entrada = new Scanner(System.in);
    public static ArrayList<Sensor> sensores = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        generarSensores();
        cargarObservaciones();
        System.out.println("BIENVENIDO AL SISTEMA DE NOTIFACION DE SENSORES");
        String menu = "";
        while (!menu.equals("3")){
            System.out.print("1.-Registrar Usuario\n2.-Iniciar Sesion\n3.-Salir\nIngresar opcion: ");
            menu = entrada.nextLine();
            if(menu.equals("1")){
                registrarUsuario();
            }
            else if(menu.equals("2")){
                iniciarSesion();
            }
        }
        
    }
    
    public static boolean iniciarSesion(){
        System.out.print("Ingrese su ID de usuario: ");
        String id= sc.nextLine();
        if(validarIDUsuario(id)){
            usuarioActual =obtenerUsuario(id);
            System.out.println("Bienvenido "+id);
            subMenu();
            return true;
            }else{System.out.println("Usuario invalido");}
        return false;
    } 
    
    public static boolean registrarUsuario (){
        System.out.print("Ingrese una nueva ID: ");
        String idUsuario = sc.nextLine();     
        if(!validarIDUsuario(idUsuario)){
            Usuario u = new Usuario(idUsuario);
            usuarios.add(u);
            System.out.println("Usuario generado con exito");
            return true;
        }System.out.println("Usuario ya existente, intente con otro ID");
        return false;
    }
    //valida que el usuario cuya id recibe se encuenre en la lista de usuarios registrados, retorna true si es asi.
    public static boolean validarIDUsuario(String id){
        for(Usuario u: usuarios){
            if(u.getID().equals(id)){
                return true;
            }
        }return false;
    }
    
    public static Usuario obtenerUsuario(String id){        
            for (Usuario u:usuarios){
                if (u.getID().equals(id)){
                    return u;                
                }               
            }
        return null;       
    }
    
    public static void subMenu(){
        String submenu = "";
        while (!submenu.equals("4")){
            System.out.print("1.-Programar notificaciones\n2.-Generar notificaciones\n3.-Desactivar notificaciones\n4.-Salir\nIngresar opcion: ");
            submenu  = entrada.nextLine();
            switch (submenu) {
                case "1":
                    programarNotificacion();
                    break;
                case "2":
                    generarNotificacion();
                    break;
                case "3":
                    desactivarNotificacion();
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * Funcion que genera sensores
     * @throws IOException 
     */
    public static void generarSensores() throws IOException{
        List<String> lineas = LeerFichero.leer("src/iot_telemetry_data_new.csv");
        for(String linea:lineas){
            String linea1 = linea.replace('"','\0');
            String[] lineaCortada =linea1.split(",");
            String idSensor=lineaCortada[1];
            if(existeSensor(idSensor)==false){
                sensores.add(new Sensor(idSensor));
            }
        }
    }
    
    /**
     * funcion que determina la existencia de un sensor
     * @param id
     * @return boolean
     */
    public static boolean existeSensor(String id){
        for(Sensor s: sensores){
            if(s.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * funcion que carga las observaciones en los sensores
     */
    public static void cargarObservaciones(){
        for(Sensor s: sensores){
            System.out.println(s.getId());
            try {
                s.obtenerObservaciones();
            } catch (IOException ex) {
                Logger.getLogger(ProyectoParcial1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ProyectoParcial1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void programarNotificacion(){
        String notif = "";
        while (!notif.equals("3")){
            System.out.print("1.-Notificación por propiedad observable\n2.-Notificación por dispositivo\n3.-Salir\nIngresar opcion: ");
            notif = entrada.nextLine();
            if (notif.equals("1")){
                crearNotObservable();
            }
            else if(notif.equals("2")){
                crearNotDispositivo();
            }
        }
    }
    public static void generarNotificacion(){
        Date menor = pedirFechaMenor();
        Date mayor = pedirFechaMayor(menor);
        generarReporte(menor,mayor);
        
        
        
    }
    
    public static void desactivarNotificacion(){
        usuarioActual.desactivarNotificaciones();
        
    }
    
    public static void crearNotObservable(){
        String obs = "";
        System.out.print("1.-Co\n2.-Humidity\n3.-Light\n4.-Lpg\n5.-Motion\n6.-Smoke\n7.-Temperature\nIngrese opcion: ");
        obs = entrada.nextLine(); 
        NotificacionObservable not;
        double mayor;
        double menor;
        int priority;
        switch (obs) {
            case "1":
                System.out.println("La variable Co usa atributos de verdad, por lo que se pide ingresar un rango numerico decimal");
                menor = rangoMenor();
                mayor = rangoMayor(menor);
                priority = pedirPrioridad();
                Etiqueta co =  new EtiquetaRango(mayor,menor,"Co",priority);
                not = new NotificacionObservable(co);
                usuarioActual.getNotificaciones().add(not);               
                break;
            case "2":
                System.out.println("La variable Humidity usa atributos de verdad, por lo que se pide ingresar un rango numerico decimal");
                menor = rangoMenor();
                mayor = rangoMayor(menor);
                priority = pedirPrioridad();
                Etiqueta humidity =  new EtiquetaRango(mayor,menor,"Humidity",priority);
                not = new NotificacionObservable(humidity);
                usuarioActual.getNotificaciones().add(not);

                break;
            case "3":
                System.out.println("La variable Light usa atributos de verdad, por lo que se pide ingresar datos de verdad");
                Boolean bo = observableBoolean();
                priority = pedirPrioridad();
                Etiqueta luz =  new EtiquetaBool(bo,"Light",priority);
                not = new NotificacionObservable(luz);
                usuarioActual.getNotificaciones().add(not);
                break;
            case "4":
                System.out.println("La variable Lpg usa atributos de verdad, por lo que se pide ingresar un rango numerico decimal");
                menor = rangoMenor();
                mayor = rangoMayor(menor);
                priority = pedirPrioridad();
                Etiqueta lpg =  new EtiquetaRango(mayor,menor,"Lpg",priority);
                not = new NotificacionObservable(lpg);
                usuarioActual.getNotificaciones().add(not);
                break;
            case "5":
                System.out.println("La variable Motion usa atributos de verdad, por lo que se pide ingresar datos de verdad");
                Boolean b = observableBoolean();
                priority = pedirPrioridad();
                Etiqueta motion =  new EtiquetaBool(b,"Motion",priority);
                not = new NotificacionObservable(motion);
                usuarioActual.getNotificaciones().add(not);
                break;
            case "6":
                System.out.println("La variable Smoke usa atributos de verdad, por lo que se pide ingresar un rango numerico decimal");
                menor = rangoMenor();
                mayor = rangoMayor(menor);
                priority = pedirPrioridad();
                Etiqueta smoke =  new EtiquetaRango(mayor,menor,"Smoke",priority);
                not = new NotificacionObservable(smoke);
                usuarioActual.getNotificaciones().add(not);
                break;
            case "7":
                System.out.println("La variable Temperature usa atributos de verdad, por lo que se pide ingresar un rango numerico decimal");
                menor = rangoMenor();
                mayor = rangoMayor(menor);
                priority = pedirPrioridad();
                Etiqueta temperature =  new EtiquetaRango(mayor,menor,"Temperature",priority);
                not = new NotificacionObservable(temperature);
                usuarioActual.getNotificaciones().add(not);
                break;
            default:
                System.out.println("Opcion ingresada no existente");
                break;
        }   
    }
    
    public static double rangoMenor(){
        String rmenor = "a";
        double d = 0;
        while (!rmenor.equals("")){
            System.out.print("Ingrese rango menor : ");
            rmenor = entrada.nextLine();
            if(isDouble(rmenor)){
                d = Double.parseDouble(rmenor);
                rmenor="";
            }
        }
        return d;
    }
    
    public static double rangoMayor(double menor){
        String rmayor = "a";
        double d = 0;
        while (!rmayor.equals("")){
            System.out.print("Ingrese rango mayor : ");
            rmayor = entrada.nextLine();
            if(isDouble(rmayor)){
                d = Double.parseDouble(rmayor);
                if(d>menor){
                    rmayor="";
                }else{
                    System.out.println("El numero perteneciente a rango mayor no cumple con ser mayor al rango menor");
                }
            }
        }
        return d;
    }
    
    public static boolean observableBoolean(){
        Boolean b =true;
        String bool = "a";
        while (!bool.equals("")){
            System.out.print("1.-True\n2.-False\nIngrese opcion: ");
            bool = entrada.nextLine();
            switch (bool) {
                case "1":
                    bool = "";
                    break;
                case "2":
                    b = false;
                    bool = "";
                    break;
                default:
                    System.out.println("Opcion no valida ingrese nuevamente");
                    break;
            }
        }
        return b;
    }
    
    public static boolean crearNotDispositivo(){
        if(existeObservable()){
            String disp = "a";
            ArrayList<Sensor> sensors = new ArrayList<>();
            while (!disp.equals("")){
                System.out.print("Ingrese dispositivo, vacio si quiere dejar de ingresar: ");
                disp = entrada.nextLine();
                for(Sensor s: sensores){
                    if(s.getId().equals(disp)){
                        if(!sensors.contains(s)){
                            sensors.add(s);
                            System.out.println("Se ha añadido correctamente a la notificación");
                            disp= "";
                        }
                        else{
                            System.out.println("Sensor Ingresado previamente");
                        }
                    }
                }
            }
            NotificacionDispositivo n = new NotificacionDispositivo(sensors);
            usuarioActual.getNotificaciones().add(n);
            
        }
        else{
            System.out.println("No existe notificación observable para crear notificación por dispositivo");
        }return false;
    }
    
    private static boolean existeObservable(){
        for(Notificacion n: usuarioActual.getNotificaciones()){
            if(n instanceof NotificacionObservable){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static Date pedirFechaMenor(){
        Date testDate = null;
        while(true){
            System.out.print("Introduzca la fecha menor con formato dd/mm/yyyy: ");
            String fecha = entrada.nextLine();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = fecha;
            try{
                testDate = df.parse(date);
                System.out.println("Ahora hemos creado un objeto date con la fecha indicada, "+testDate);
            } catch (Exception e){ System.out.println("invalid format");}

            if (!df.format(testDate).equals(date)){
                System.out.println("invalid date!!");
            } else {
                System.out.println("valid date");
                return testDate;
            }
        }
    }
    
    public static Date pedirFechaMayor(Date menor){
        Date testDate = null;
        while(true){
            System.out.print("Introduzca la fecha mayor con formato dd/mm/yyyy: ");
            String fecha = entrada.nextLine();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = fecha;
            try{
                testDate = df.parse(date);
                System.out.print("Ahora hemos creado un objeto date con la fecha indicada, "+testDate);
            } catch (Exception e){ System.out.println("invalid format");}

            if (!df.format(testDate).equals(date)){
                System.out.println("invalid date!!");
            } else {
                if(testDate.after(menor)){
                    System.out.println("valid date");
                    return testDate;
                }
                else{
                    System.out.println("fecha no es mayor que la especificada como menor");
                    
                }
                
            }
        }
    }
    
    public static int pedirPrioridad(){
        String prioridad = "a";
        while (!prioridad.equals("")){
            System.out.print("1.-Prioridad Alta\n2.-Prioridad media\n3.-Prioridad Baja\nIngrese opcion de prioridad: ");
            prioridad= entrada.nextLine();
            if(prioridad.equals("1")){
                return 1;
            }
            else if(prioridad.equals("2")){
                return 2;
            }
            else if(prioridad.equals("3")){
                return 3;
            }
        }
        return 0;
    }
    
    public static void generarReporte(Date menor, Date mayor){
        ArrayList<String> ids = new ArrayList<>();
        for(Sensor s: sensores){
            ids.add(s.getId());
        }
        for(Notificacion n: usuarioActual.getNotificaciones()){
            if(n instanceof NotificacionDispositivo){
                ids.clear();
            }
        }
        for(Notificacion n: usuarioActual.getNotificaciones()){
            if(n instanceof NotificacionDispositivo){
               for(Sensor s: ((NotificacionDispositivo) n).getSensores() ){
                   if(!ids.contains(s.getId())){
                       ids.add(s.getId());
                   }
               }
            }
        }
        System.out.println("El tam es " +ids.size());
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter("src/data.txt");
            pw = new PrintWriter(fichero);
            for(Notificacion n: usuarioActual.getNotificaciones()){
                if(n instanceof NotificacionObservable && n.estadoActivo){
                    NotificacionObservable  nobs= (NotificacionObservable) n;
                    pw.println(nobs);
                    for(String id: ids){
                        for(Sensor s: sensores){
                            if(s.getId().equals(id)){
                                for(Observacion obs: s.getObservaciones()){
                                    if(menor.before(obs.date) && mayor.after(obs.date)){
                                        if(obs.determinarObservacion(nobs.getEtiqueta())){
                                            System.out.println(obs);
                                            pw.println(obs);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
         catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }

        

    }
    
        
}
