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
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
/**
 *
 * @author alber
 */
public class ProyectoParcial1 {
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    public static Usuario usuarioActual;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        List<String> lineas = LeerFichero.leer("C:/Users/alber/Downloads/iot_telemetry_data_new.csv");
        System.out.println(lineas.get(0));
        String linea1 =lineas.get(0).replace('"','\0');
        String[] lineaCortada =linea1.split(",");
        String idSensor=lineaCortada[1];
        System.out.println(idSensor);
        var co1 = Double.parseDouble(lineaCortada[2]);
        System.out.println(co1);
    }
    
    public static boolean iniciarSesion(){
        System.out.println("Ingrese su ID de usuario: ");
        String id= sc.nextLine();
        if(validarIDUsuario(id)){
            usuarioActual =obtenerUsuario(id);
            System.out.println("Bienvenido "+id);
            return true;
            }else{System.out.println("Usuario invalido");}
        return false;
    } 
    
    public static boolean registrarUsuario (){
        System.out.println("Ingrese una nueva ID: ");
        String idUsuario = sc.nextLine();     
        if(!validarIDUsuario(idUsuario)){
            Usuario u = new Usuario(idUsuario);
            usuarios.add(u);
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
}
