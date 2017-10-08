
package ioc.dam.m9.uf2.eac1.Ex3;

/**
 *
 * @author albert
 */
public class GestioSuper {
    public static void main(String[] args) {
        Supermercat s= new Supermercat(3);
        Client c[]= new Client [10];
        for (int i = 0; i < 10; i++) {
           c[i] = new Client(i+1,s);
            c[i].start();
        }
        
        
    }


}
