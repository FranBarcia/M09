package ioc.dam.m9.uf3.eac2.ex2;

/**
 *
 * @author albert
 */
public class ServeiMatematicImpl implements ServeiMatematic{

    @Override
    public double suma(double valor1, double valor2) {
       return valor1+valor2;
    }

    @Override
    public double resta(double valor1, double valor2) {
        return valor1-valor2;
    }

    @Override
    public double div(double valor1, double valor2) {
        if(valor2!=0) return valor1/valor2;
        return Double.MAX_VALUE;
    }

    @Override
    public double mult(double valor1, double valor2) {
        return valor1*valor2;
    }
    
}
