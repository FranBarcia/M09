package ioc.dam.m9.uf2.eac1.Ex2;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author albert
 */
public class GestioNotes {

    public static void main(String[] args) {
        Alumne a;
        CreaAlumnes l = new CreaAlumnes();
        ArrayList<Alumne> llista = l.obtenirLlistat();
        
        ForkJoinPool pool = new ForkJoinPool();
        
        //Aquí cal codificar
        Alumne aux = new Alumne(3037, 0);
        BuscaAlumne b = new BuscaAlumne(llista, 0, llista.size()-1, aux);
        
        a = pool.invoke(b);
        
        if (a == null) {
            System.out.println("L'alumne no existeix");

        } else {
            System.out.println(a.codi + " " + a.notaF);
        }
    }
}
