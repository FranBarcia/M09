package ioc.dam.m9.uf3.eac2.ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albert
 */
public class ClientMatematic {

    public static void main(String[] args) {
        String adresa = "localhost";
        Integer port = 9999;
        String operacio = "*:214.25:8"; // Resultat: 1714
        String resposta;
        PrintStream sortida;
        BufferedReader entrada;
        
        try {
            // conectar con server localhost:9999
            Socket socolClient = new Socket(InetAddress.getByName(adresa), port);
            
            // enviar operacion op:v1:v2, enviarem 214.25 * 8
            sortida = new PrintStream(socolClient.getOutputStream());
            System.out.println("Envio al server la opreració: "+operacio);
            sortida.println(operacio);
            sortida.flush();
            
            // recibir respuesta
            entrada = new BufferedReader(new InputStreamReader(socolClient.getInputStream()));
            resposta = entrada.readLine();
            
            System.out.println("He rebut del servidor això: "+resposta);
            
        } catch (IOException ex) {
            Logger.getLogger(ClientMatematic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
