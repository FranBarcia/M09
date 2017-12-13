package ioc.dam.m9.uf1.eac3.ex1.parell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GeneraClaus {


    private PrivateKey privada;
    private PublicKey publica;

    public GeneraClaus(int longitud) {
    }

    public PrivateKey getPrivada() {
        return privada;
    }

    public PublicKey getPublica() {
        return publica;
    }

    private void escriuAFitxer(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(key);
            fos.flush();
        }
    }

    public static void main(String[] args) throws IOException {
        GeneraClaus gc_Maria;
        GeneraClaus gc_Joan;
        gc_Maria = new GeneraClaus(1024);
        gc_Maria.escriuAFitxer("ParellClaus/publica_Maria", gc_Maria.getPublica().getEncoded());
        gc_Maria.escriuAFitxer("ParellClaus/privada_Maria", gc_Maria.getPrivada().getEncoded());
        gc_Joan = new GeneraClaus(1024);
        gc_Joan.escriuAFitxer("ParellClaus/publica_Joan", gc_Joan.getPublica().getEncoded());
        gc_Joan.escriuAFitxer("ParellClaus/privada_Joan", gc_Joan.getPrivada().getEncoded());
    }
}
