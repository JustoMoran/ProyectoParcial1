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
import java.util.logging.Level;
import java.util.logging.Logger;
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
            System.out.println("1.-Programar notificaciones\n2.-Generar notificaciones\n3.-Desactivar notificaciones\n4.-Salir\nIngresar opcion: ");
            submenu  = entrada.nextLine();
            switch (submenu) {
                case "1":
                    programarNotificacion();
                    break;
                case "2":
                    break;
                case "3":
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
            String linea1 =linea.replace('"','\0');
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
                
            }
            else if(notif.equals("2")){
                generarNotDispositivo();
            }
        }
    }
    public static void generarNotificacion(){
        
    }
    
    public static void desactivarNotificacion(){
        
    }
    
    public static void generarNotDispositivo(){
        if(existeObservable()){
            String disp = "";
            ArrayList<Sensor> sensors = new ArrayList<>();
            while (!disp.equals("")){
                System.out.print("Ingrese dispositivo, vacio si quiere dejar de ingresar: ");
                disp = entrada.nextLine();
                for(Sensor s: sensores){
                    if(s.getId().equals(disp)){
                        if(!sensors.contains(s)){
                            sensors.add(s);
                        }
                        else{
                            System.out.println("Sensor Ingresado previamente");
                        }
                    }
                    else{
                        System.out.println("Id ingresado no existe");
                    }
                }
            }
            NotificacionDispositivo n = new NotificacionDispositivo(sensors);
            usuarioActual.notificaciones.add(n);
        }
    }
    
    private static boolean existeObservable(){
        for(Notificacion n: usuarioActual.notificaciones){
            if(n instanceof NotificacionObservable){
                return true;
            }
        }
        return false;
    }
        
}
