package ioc.dam.m9.uf2.eac1.Ex2;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;



/**
 *
 * @author albert
 */
public class BuscaAlumne extends RecursiveTask<Alumne> {
    private ArrayList<Alumne> alumnes;
    private int inici, fi;
    private Alumne alumne;
    
    public BuscaAlumne(ArrayList<Alumne> llistaAlumnes, int inici, int fi, Alumne alumneATrobar) {
        this.alumnes = llistaAlumnes;
        this.alumne = alumneATrobar;
        this.inici = inici;
        this.fi = fi;
    }
    
    public Alumne trobaAlumneSeq(Alumne alumneATrobar) {
        Alumne alumneTrobat = null;
        
        for (int i = inici; i < fi; i++) {
            if (alumneATrobar.getCodi() == alumnes.get(i).getCodi()) {
                alumneTrobat = alumnes.get(i);
                return alumneTrobat;
            }
        }
        return alumneTrobat;
    }
    
    public Alumne trobaAlumneReq(Alumne alumneATrobar) {
        Alumne alumneTrobat = null;
        int mig = ((inici + fi) / 2);
        BuscaAlumne b1 = new BuscaAlumne(alumnes, inici, mig, alumneATrobar);
        BuscaAlumne b2 = new BuscaAlumne(alumnes, mig + 1, fi, alumneATrobar);
        
        b1.fork();
        b2.fork();
        
        Alumne alumneTrobat2 = b2.join();
        Alumne alumneTrobat1 = b1.join();
        
        if (alumneTrobat2 != null && (alumneATrobar.codi == alumneTrobat2.codi)) {
            alumneTrobat = alumneTrobat2;
        } else if (alumneTrobat1 != null && (alumneATrobar.codi == alumneTrobat1.codi)) {
            alumneTrobat = alumneTrobat1;
        }
        
        return alumneTrobat;
    }
    
    @Override
    protected Alumne compute() {
        if (fi - inici < 10) {
            return trobaAlumneSeq(alumne);
        }
        else {
            return trobaAlumneReq(alumne);
        }
    }
}
