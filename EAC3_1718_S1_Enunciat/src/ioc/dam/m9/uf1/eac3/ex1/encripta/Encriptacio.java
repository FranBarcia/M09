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
        
	//public byte[] encryptData(SecretKey sKey, byte[] data) {
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance(algorismeXifrat);
            cipher.init(Cipher.ENCRYPT_MODE, clauSecreta);
            encryptedData = cipher.doFinal(original);
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
    }

    //--------------------------------------------------------------
    public void encriptaClau(PublicKey clau, File fitxerClauOriginal, File fitxerClauEncriptada, String algorismeXifrat) {
        
    }

    
      
    
    public static void main(String[] args) throws IOException, Exception {
        
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
