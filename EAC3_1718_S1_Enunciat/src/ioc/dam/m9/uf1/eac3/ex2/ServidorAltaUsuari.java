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
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 *
 * @author albert
 */
public class ServidorAltaUsuari {

    private static int port = 7000;
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        KeyManagerFactory factoriaKeystore = KeyManagerFactory.getInstance("SunX509");
        KeyStore keystore = KeyStore.getInstance("JKS");
        
        FileInputStream fileIn = new FileInputStream("eac4_1718s1.jks");
        
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        trustManagerFactory.init(keystore);
        TrustManager[] tm = trustManagerFactory.getTrustManagers();
        
        keystore.load(fileIn, "iocioc".toCharArray()); 
        factoriaKeystore.init(keystore, "iocioc".toCharArray());
        
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(factoriaKeystore.getKeyManagers(), tm, null);
        
        SSLServerSocketFactory factoriaSSL = sslContext.getServerSocketFactory();
        SSLServerSocket serverSSL = (SSLServerSocket) factoriaSSL.createServerSocket(port);
        
        String[] supported = serverSSL.getSupportedCipherSuites();
        serverSSL.setEnabledCipherSuites(supported);
        
        System.out.println("Donat d'alta l'usuari:");
        String usuari;
        String contrasenya;
        
        while (true) {
            SSLSocket sslSocket = (SSLSocket) serverSSL.accept();
            
            PrintStream sortida = new PrintStream(sslSocket.getOutputStream(), true, "UTF-8");
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            
            sortida.println("Benvingut al registre\n\nIntrodueix el nom d'usuari: ");
            sortida.flush();
            
            usuari = entrada.readLine();
            System.out.println("Usuari: "+usuari);
            
            sortida.println("Introdueix la contrasenya: ");
            sortida.flush();
            
            contrasenya = entrada.readLine();
            System.out.println("Contrasenya: "+contrasenya);
        }
    }
}