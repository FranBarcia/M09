package ioc.dam.m9.uf3.eac2.ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albert
 */
public class ServidorMatematic {
    private static ServerSocket socolServidor;
    private static Socket socolClient;
    private String operacioRebuda;
    private double resultat;
    private PrintStream sortida;
    private BufferedReader entrada;

    public void executa() {
        try {
            // Rebre operacio i analitzar-la
            entrada = new BufferedReader(new InputStreamReader(socolClient.getInputStream()));
            operacioRebuda = entrada.readLine();
            System.out.println("He rebut del client això: "+operacioRebuda);
            resultat = analitza(operacioRebuda);
            
            // Enviar resposta
            sortida = new PrintStream(socolClient.getOutputStream());
            System.out.println("Envio al client la resposta a la operació: "+resultat);
            sortida.println(resultat);
            sortida.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorMatematic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //El protocol per a la operació és operador:valor1:valor2
    private double analitza(String operacio){
        ServeiMatematicImpl calculadora = new ServeiMatematicImpl();
        String[] rebut;
        double resultat;
        
        System.out.println("Analitzem si es válid");
        
        rebut = operacio.split(":");
        
        switch (rebut[0]) {
            case "+":
                resultat = calculadora.suma(Double.parseDouble(rebut[1]), Double.parseDouble(rebut[2]));
                break;
            case "-":
                resultat = calculadora.resta(Double.parseDouble(rebut[1]), Double.parseDouble(rebut[2]));
                break;
            case "*":
                resultat = calculadora.mult(Double.parseDouble(rebut[1]), Double.parseDouble(rebut[2]));
                break;
            case "/":
                resultat = calculadora.div(Double.parseDouble(rebut[1]), Double.parseDouble(rebut[2]));
                break;
            default:
                resultat = 0;
                break;
        }
        System.out.println("El resultat de la operació és: "+resultat);
        return resultat;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("El servidor matemàtic està executant...");
        
        ServidorMatematic mats = new ServidorMatematic();
        
        socolServidor = new ServerSocket(9999);
        socolClient = socolServidor.accept();
        mats.executa();
        
        System.out.println("El servidor matemàtic s'ha tancat...");
    }
}
