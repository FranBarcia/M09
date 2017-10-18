package ioc.dam.m9.uf2.eac1.Ex3;


/**
 *
 * @author albert
 */
public class Caixa {

    private final int numCaixa;
    private boolean caixaOcupada = false;
    private boolean socPrimer = true;

    public Caixa(int id) {
        numCaixa = id;
    }

    public synchronized void passaACuaCaixa(int numCli) throws InterruptedException {
        
        System.out.println("Entra a la caixa"+numCaixa+" el client"+Thread.currentThread().getName());
        
        // Si la caixa està ocupada
        while (caixaOcupada) {
            // I sóc el primer
            if (socPrimer) {
                // Espero el meu torn
                wait();
            }
            // I no sóc el primer, m'espero a la cua
            else {
                wait();
            }
        }
        
        // Si la caixa està lliure
        if (!caixaOcupada)  {
            // I sóc el primer
            if (socPrimer) {
                // Ocupo la caixa
                caixaOcupada = true;
            }
            // I no sóc el primer
            else {
                // M'espero a la cua
                wait();
            }
        }
    }

    public synchronized void passaAPagar() throws InterruptedException {
        System.out.println("Entra a la caixa"+numCaixa+" a pagar el client"+Thread.currentThread().getName());
        // Deixo lliure la primera posició
        socPrimer = true;
    }

    public synchronized void surt() {
        System.out.println("Surt de la caixa"+numCaixa+" el client"+Thread.currentThread().getName());
        // Deixo lliure la caixa
        caixaOcupada = false;
        // Aviso que passi el següent
        notify();
    }
}
