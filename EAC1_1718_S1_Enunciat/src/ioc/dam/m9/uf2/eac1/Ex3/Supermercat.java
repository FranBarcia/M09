
package ioc.dam.m9.uf2.eac1.Ex3;

/**
 *
 * @author albert
 */
public class Supermercat {

    Caixa[] caixes;
    int numCaixes;
  

    public Supermercat(int numCaixes) {
        this.numCaixes = numCaixes;
        caixes = new Caixa[numCaixes];
        for (int i = 0; i < numCaixes; i++) {
            Caixa caixa = new Caixa(i + 1);
            caixes[i] = caixa;

        }
    }

    public void arribaClient(int numCli) {
        System.out.println("Entra al super el client " + numCli);
    }

    public void passaACuaCaixa(int numCli, int numCaixa) {
        caixes[numCaixa].passaACuaCaixa(numCli);

    }

    public void passaAPagar(int numCaixa) {
        caixes[numCaixa].passaAPagar();

    }

    public void surt(int numCaixa) {
        caixes[numCaixa].surt();
     
    }
}
