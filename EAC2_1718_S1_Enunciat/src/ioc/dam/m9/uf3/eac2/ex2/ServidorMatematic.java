package ioc.dam.m9.uf3.eac2.ex2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albert
 */
public class ServidorMatematic {

    public void executa() {
        ServerSocket socolServidor;
        try {
            socolServidor = new ServerSocket(9999);
            Socket socolClient = socolServidor.accept();
        } catch (IOException ex) {
            Logger.getLogger(ServidorMatematic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //El protocol per a la operació és operador:valor1:valor2
    private double analitza(String operacio){
        
        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("El servidor matemàtic està executant...");
        
        
        
        System.out.println("El servidor matemàtic s'ha tancat...");
    }
}
