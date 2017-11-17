package ioc.dam.m9.uf3.eac2.ex1;

import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author acanela
 */
public class MajusculaClient {
    
    public static void main(String[] args) throws Exception {
        // Variables per la connexió
        InetAddress servidor = InetAddress.getByName("localhost");
        DatagramSocket socol = new DatagramSocket();
        byte[] dadesRebudes = new byte[1024];
        byte[] dadesEnviades;
        
        // Variable per tractar les dades a enviar
        Scanner llegirText = new Scanner(System.in);
        String missatgeEnviat;
        String missatgeRebut;
        
        System.out.println("Introdueix un text qualsevol: ");
        missatgeEnviat = llegirText.nextLine();
        
        System.out.println("Enviaré aixo: "+missatgeEnviat+"\n.........................");
        //creació del paquet per enviar la resposta
        dadesEnviades = getBytes(missatgeEnviat);
        DatagramPacket paquet = new DatagramPacket(dadesEnviades, dadesEnviades.length, servidor, 9999);
        //enviament de la resposta
        socol.send(paquet);
        
        paquet = new DatagramPacket(dadesRebudes, 1024);
        socol.receive(paquet);
        missatgeRebut = new String(paquet.getData(), 0, paquet.getLength(), "ISO-8859-15");
        System.out.println("M'han tornat això: "+missatgeRebut);
        
        socol.close();
            
        
        /*
        Aquesta classe només tindrà el mètode main que és el que heu d'implementar.
        Aquest mètode simplement:
            - demanarà un text a l'usuari, 
            - l'enviarà al servidor, 
            - mostrarà per pantalla la resposta
            - finalitzarà l'execució.
        */
    }
}
