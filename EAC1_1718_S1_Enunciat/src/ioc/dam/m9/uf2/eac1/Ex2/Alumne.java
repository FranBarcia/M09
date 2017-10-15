package ioc.dam.m9.uf2.eac1.Ex2;

/**
 *
 * @author albert
 */
public class Alumne {
    int codi;
    double notaF;

    public Alumne(int codi, double notaF) {
        this.codi = codi;
        this.notaF = notaF;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public double getNotaF() {
        return notaF;
    }

    public void setNotaF(double notaF) {
        this.notaF = notaF;
    }
}
