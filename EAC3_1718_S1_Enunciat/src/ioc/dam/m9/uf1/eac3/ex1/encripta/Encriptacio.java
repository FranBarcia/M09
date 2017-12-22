package ioc.dam.m9.uf1.eac3.ex1.encripta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

import javax.crypto.spec.SecretKeySpec;

public class Encriptacio {

    public PublicKey getPublica(String fitxer, String algorisme) throws Exception {
        byte[] bytesClau = Files.readAllBytes(new File(fitxer).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytesClau);
        KeyFactory kf = KeyFactory.getInstance(algorisme);
        return kf.generatePublic(spec);
    }
    
    public SecretKeySpec getClauSecreta(String fitxer, String algorisme) throws IOException {
        byte[] bytesClau = Files.readAllBytes(new File(fitxer).toPath());
        return new SecretKeySpec(bytesClau, algorisme);
    }
    
    //----------------------------------------------------------------
    public void encriptaDades(File original, File encriptat, SecretKeySpec clauSecreta, String algorismeXifrat) {
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance(algorismeXifrat);
            cipher.init(Cipher.ENCRYPT_MODE, clauSecreta);
            byte[] fitxerBytes = fitxerEnBytes(original);
            encryptedData = cipher.doFinal(fitxerBytes);
            escriuAFitxer(encriptat, encryptedData);
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
    }
    
    //--------------------------------------------------------------
    public void encriptaClau(PublicKey clau, File fitxerClauOriginal, File fitxerClauEncriptada, String algorismeXifrat) {
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance(algorismeXifrat);
            cipher.init(Cipher.ENCRYPT_MODE, clau);
            byte[] fitxerBytes = fitxerEnBytes(fitxerClauOriginal);
            encryptedData = cipher.doFinal(fitxerBytes);
            escriuAFitxer(fitxerClauEncriptada, encryptedData);
        } catch (Exception ex) {
            System.err.println("Error xifrant la clau: " + ex);
        }
    }
    
    public static void main(String[] args) throws IOException, Exception {
        Encriptacio encriptacio;
        encriptacio = new Encriptacio();
        
        String rutaClauPublica = new File("ParellClaus/publica_Joan").getAbsolutePath();
        String rutaClauSimetrica = new File("unaClau/clauSecreta").getAbsolutePath();
        String algorismeRSA = "RSA";
        String algorismeAES = "AES";
        
        File carta = new File("carta.txt");
        File cartaEncriptada = new File("FitxersEncriptats/fitxerEncriptat");
        File clauSimetrica = new File("unaClau/clauSecreta");
        File clauEncriptada = new File("FitxersEncriptats/clauSecreta");
        
        PublicKey clauPublica;
        SecretKeySpec clauSecreta;
        
        clauPublica = encriptacio.getPublica(rutaClauPublica, algorismeRSA);
        clauSecreta = encriptacio.getClauSecreta(rutaClauSimetrica, algorismeAES);
        encriptacio.encriptaClau(clauPublica, clauSimetrica, clauEncriptada, algorismeRSA);
        encriptacio.encriptaDades(carta, cartaEncriptada, clauSecreta, algorismeAES);
    }
    
// Mètodes auxiliars 
    private void escriuAFitxer(File out, byte[] aEscriure) throws  IOException {
        out.getParentFile().mkdirs();
        try (FileOutputStream fos = new FileOutputStream(out)) {
            fos.write(aEscriure);
            fos.flush();
        }
        System.out.println("El fitxer ha estat guardat a:  " + out.getPath());
    }

    private byte[] fitxerEnBytes(File f) throws IOException {
        byte[] fbytes;
        try (FileInputStream fis = new FileInputStream(f)) {
            fbytes = new byte[(int) f.length()];
            fis.read(fbytes);
        }
        return fbytes;
    }

}
