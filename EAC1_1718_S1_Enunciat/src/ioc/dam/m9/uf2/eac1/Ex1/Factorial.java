package ioc.dam.m9.uf2.eac1.Ex1;


/**
 *
 * @author albert
 */
public class Factorial  {
    private int numFact;

    public Factorial(int numCalcular) {
        numFact = 1;
        for (int i = 1; i <= numCalcular; i++) {
            numFact *= i;
            System.out.println("numFact: "+numFact);
        }
    }
}
