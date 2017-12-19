package ioc.dam.m9.uf1.eac3.ex2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Scanner;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;


/**
 *
 * @author albert
 */
public class ServidorAltaUsuari {
    
    private static int port = 7000;
    
    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException, KeyManagementException, CertificateException, UnrecoverableKeyException, KeyStoreException {
        KeyManagerFactory factoriaKeystore = KeyManagerFactory.getInstance("SunX509");
        KeyStore keystore = KeyStore.getInstance("JKS");
        
        FileInputStream fileIn = new FileInputStream("eac4_1718s1.jks");
        
        keystore.load(fileIn, "ioc_1718s1".toCharArray()); 
        factoriaKeystore.init(keystore, "ioc_1718s1".toCharArray());
        
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(factoriaKeystore.getKeyManagers(), null, null);
        
        SSLServerSocketFactory factoriaSSL = sslContext.getServerSocketFactory();
        SSLServerSocket serverSSL = (SSLServerSocket) factoriaSSL.createServerSocket(port);
        
        String[] supported = serverSSL.getSupportedCipherSuites();
        serverSSL.setEnabledCipherSuites(supported);
        
        System.out.println("Donat d'alta l'usuari:");
        String usuari;
        String contrasenya;
        
        while (true) {
            SSLSocket sslSocket = (SSLSocket) serverSSL.accept();
            Scanner lector = new Scanner(sslSocket.getInputStream());
            
            usuari = lector.nextLine();
            System.out.println("Usuari: "+usuari);
            contrasenya = lector.nextLine();
            System.out.println("Contrasenya: "+contrasenya);
        }
    }
}