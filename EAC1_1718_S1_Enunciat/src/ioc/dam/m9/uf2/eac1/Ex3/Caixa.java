package ioc.dam.m9.uf2.eac1.Ex3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albert
 */
public class Caixa {

    private final int numCaixa;
    private boolean caixaOcupada = false;

    public Caixa(int id) {
        numCaixa = id;
    }

    public synchronized void passaACuaCaixa(int numCli) {
        System.out.println("Entra a la caixa "+numCaixa+" el client "+Thread.currentThread().getName());
        
        if (caixaOcupada) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
    }

    public synchronized void passaAPagar() {
        try {
            caixaOcupada = true;
            Thread.sleep(1000);
            System.out.println("Entra a la caixa "+numCaixa+" a pagar el client "+Thread.currentThread().getName());
        } catch (InterruptedException ex) {
        }
    }

    public synchronized void surt() {
        System.out.println("Surt de la caixa "+numCaixa+" el client "+Thread.currentThread().getName());
        caixaOcupada = false;
        notify();
    }
}
