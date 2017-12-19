package ioc.dam.m9.uf1.eac3.ex2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Scanner;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author albert
 */
public class ClientUsuari {

    private static int port = 7000;
    
    public static void main(String[] argv) throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        System.setProperty("javax.net.debug", "SSL,handshake");
        
        KeyManagerFactory factoriaKeystore = KeyManagerFactory.getInstance("SunX509");
        KeyStore keystore = KeyStore.getInstance("JKS");
        
        FileInputStream fileIn = new FileInputStream("eac4_1718s1.jks");
        
        keystore.load(fileIn, "ioc_1718s1".toCharArray()); 
        factoriaKeystore.init(keystore, "ioc_1718s1".toCharArray());
        
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(factoriaKeystore.getKeyManagers(), null, null);
        
        SSLSocketFactory factoriaSSL = sslContext.getSocketFactory();
        SSLSocket socketSSL = (SSLSocket) factoriaSSL.createSocket("localhost", port);
        
        String[] supported = socketSSL.getSupportedCipherSuites();
        socketSSL.setEnabledCipherSuites(supported);
        
        // Variable per tractar les dades a enviar
        Scanner llegirText = new Scanner(System.in);
        
        PrintStream enviador = new PrintStream(socketSSL.getOutputStream());
        
        System.out.println("Benvingut al registre\n\nIntrodueix el nom d'usuari: ");
        
        enviador.println(llegirText.nextLine());
        enviador.flush();
        
        System.out.println("Introdueix la contrasenya: ");
        
        enviador.println(llegirText.nextLine());
        enviador.flush();
            
	socketSSL.close();
    }
}
