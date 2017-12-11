package ioc.dam.m9.uf1.eac3.ex1.unaClau;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.crypto.spec.SecretKeySpec;

public class GeneraClauSimetrica {

    private SecretKeySpec clauSecreta;

    public SecretKeySpec getClauSecreta() {
        return clauSecreta;
    }

    public GeneraClauSimetrica(int longitud, String algorisme)  {
       
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
