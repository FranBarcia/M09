package ioc.dam.m9.uf2.eac1.Ex1;

import java.util.concurrent.Callable;


/**
 *
 * @author albert
 */
public class Factorial implements Callable<Double> {
    private double fact;

    public Factorial(int perCalcular){
        fact = 1;
        for (int i = 1; i <= perCalcular; i++) {
            fact *= i;
        }
    }
    
    @Override
    public Double call() throws Exception {
        return fact;
    }
}
