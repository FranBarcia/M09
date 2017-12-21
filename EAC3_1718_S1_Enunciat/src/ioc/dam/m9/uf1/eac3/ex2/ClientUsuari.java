package ioc.dam.m9.uf1.eac3.ex2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 *
 * @author albert
 */
public class ClientUsuari {

    private static int port = 7000;
    
    public static void main(String[] argv) throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        //System.setProperty("javax.net.debug", "SSL,handshake");
        
        KeyManagerFactory factoriaKeystore = KeyManagerFactory.getInstance("SunX509");
        KeyStore keystore = KeyStore.getInstance("JKS");
        
        /*  Tinc dos keystores, el eac4_1718s1.jks, que em funciona en un ordinador
            i el keystore eac4.jks que em funciona en un altre ordinador
            Deixo els dos al projecte per si algún donés error
        */
        //FileInputStream fileIn = new FileInputStream("eac4_1718s1.jks");
        FileInputStream fileIn = new FileInputStream("eac4.jks");
        
        keystore.load(fileIn, "iocioc".toCharArray()); 
        factoriaKeystore.init(keystore, "iocioc".toCharArray());
        
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        trustManagerFactory.init(keystore);
        TrustManager[] tm = trustManagerFactory.getTrustManagers();
        
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(factoriaKeystore.getKeyManagers(), tm, null);
        
        SSLSocketFactory factoriaSSL = sslContext.getSocketFactory();
        SSLSocket clientSSL = (SSLSocket) factoriaSSL.createSocket("localhost", port);
        
        PrintStream sortida = new PrintStream(clientSSL.getOutputStream(), true, "UTF-8");
        BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSSL.getInputStream()));
        Scanner llegirText = new Scanner(System.in);
        
        System.out.println(entrada.readLine());
        System.out.println(entrada.readLine());
        System.out.println(entrada.readLine());
        sortida.println(llegirText.nextLine());
        
        System.out.println(entrada.readLine());
        sortida.println(llegirText.nextLine());
        
	clientSSL.close();
    }
}
