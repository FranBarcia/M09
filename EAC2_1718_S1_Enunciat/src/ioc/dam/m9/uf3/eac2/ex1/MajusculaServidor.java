package ioc.dam.m9.uf3.eac2.ex1;

import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author albert
 */
public class MajusculaServidor {

    public void servei() throws Exception{
        // Variables per la connexió
        DatagramSocket socol = new DatagramSocket(9999);
        byte[] dadesRebudes = new byte[1024];
        byte[] dadesEnviades;
        InetAddress clientIP;
        Integer clientPort;
        
        String missatge;

        //el servidor atén el port indefinidament
        while(true){
            //creació del paquet per rebre les dades
            DatagramPacket paquet = new DatagramPacket(dadesRebudes, 1024);
            System.out.println("Esperant dades\n.........................");
            socol.receive(paquet);
            System.out.println("Dades rebudes.");
            //processament de les dades rebudes i obtenció de la resposta
            missatge = new String(paquet.getData(), 0, paquet.getLength(), "ISO-8859-15");
            System.out.println("He rebut: "+missatge);
            missatge = missatge.toUpperCase();
            dadesEnviades = getBytes(missatge);
            System.out.println("Enviaré: "+missatge);
            /*
            private byte[] processData(byte[] data, int length) {
            //procés diferent per cada aplicació

            }
            */
            //obtenció de l'adreça i el port del client
            clientIP = paquet.getAddress();
            clientPort = paquet.getPort();
            //creació del paquet per enviar la resposta
            paquet = new DatagramPacket(dadesEnviades, dadesEnviades.length, clientIP, clientPort);
            //enviament de la resposta
            socol.send(paquet);
            System.out.println("Missatge '"+missatge+"' enviat.");
        }
    }
    
    public static void main(String[] args) throws Exception {
        MajusculaServidor majuscula = new MajusculaServidor();

        majuscula.servei();
    }
}
