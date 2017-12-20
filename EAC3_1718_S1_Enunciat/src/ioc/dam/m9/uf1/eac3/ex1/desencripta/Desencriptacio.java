package ioc.dam.m9.uf1.eac3.ex1.desencripta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Desencriptacio {

    public PrivateKey getPrivada(String fitxer, String algorisme) throws Exception {
        byte[] bytesClau = Files.readAllBytes(new File(fitxer).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytesClau);
        KeyFactory kf = KeyFactory.getInstance(algorisme);
        return kf.generatePrivate(spec);
    }

    public SecretKeySpec getClauSecreta(String fitxer, String algorisme) throws IOException {
        byte[] bytesClau = Files.readAllBytes(new File(fitxer).toPath());
        return new SecretKeySpec(bytesClau, algorisme);
    }

    //-----------------------------------------------------------------
    public void desencriptaClau(PrivateKey clauPrivada, File clauEncriptadaRebuda, File fitxerClauDesencriptat, String algorisme) {
        
    }

    //----------------------------------------------------------------------------
    public void desencriptaDades(File fitxerEncriptatRebut, File fitxerDesencriptat, SecretKeySpec clauSecreta, String algorisme) {
        
    }

    public static void main(String[] args) throws IOException, Exception {
        
    }

   
    
// Mètodes auxiliars 
    private void escriuAFitxer(File out, byte[] aEscriure) throws IOException {
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
