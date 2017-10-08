package ioc.dam.m9.uf2.eac1.Ex2;

import java.util.ArrayList;

/**
 *
 * @author albert
 */
public class GestioNotes {

    public static void main(String[] args) {
        Alumne a = null;
        CreaAlumnes l = new CreaAlumnes();
        ArrayList<Alumne> llista = l.obtenirLlistat();

        //Aquí cal codificar
        if (a == null) {
            System.out.println("L'alumne no existeix");

        } else {
            System.out.println(a.codi + " " + a.notaF);

        }
    }

}
