package ioc.dam.m9.uf1.eac3.ex1.unaClau;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class GeneraClauSimetrica {

    private SecretKeySpec clauSecreta;

    public SecretKeySpec getClauSecreta() {
        return clauSecreta;
    }

    public GeneraClauSimetrica(int longitud, String algorisme)  {
        int longInBits = longitud * 8;
        SecretKey secret = null;
        
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(algorisme);
            kgen.init(longInBits, new SecureRandom());
            secret = kgen.generateKey();
            this.clauSecreta = (SecretKeySpec) secret;
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Generador no disponible.");
        }
    }
    
    public static void main(String[] args) throws IOException   {
        GeneraClauSimetrica clauSimetrica = new GeneraClauSimetrica(16, "AES");
        clauSimetrica.escriuAFitxer("UnaClau/clauSecreta", clauSimetrica.getClauSecreta().getEncoded());
    }
    
    private void escriuAFitxer(String ruta, byte[] clau) throws IOException {
        File f = new File(ruta);
        f.getParentFile().mkdirs();

        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(clau);
            fos.flush();
        }
    }
}
