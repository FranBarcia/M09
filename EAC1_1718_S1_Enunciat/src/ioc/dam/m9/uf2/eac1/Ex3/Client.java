package ioc.dam.m9.uf2.eac1.Ex3;

import java.util.Random;

/**
 *
 * @author albert
 */
public class Client extends Thread {
    int id;
      Supermercat sup;
      boolean sortir;

    public Client(int id, Supermercat sup) {
        this.id = id;
        this.sup = sup;
    }

  
   public void run() {
       Random r= new Random();
       sup.arribaClient(id);
        try {
            //Compra
            sleep(4000);
            int numCaixa=r.nextInt(sup.numCaixes);
            sup.passaACuaCaixa(id, numCaixa);
            sup.passaAPagar(numCaixa);
            //Paga
            sleep(4000);
            sup.surt(numCaixa);

            
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
       
       
   }
}
