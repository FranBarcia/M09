package ioc.dam.m9.uf2.eac1.Ex2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author albert
 */
public class CreaAlumnes {
    ArrayList<Alumne>alumnat = new ArrayList();
    
    ArrayList<Alumne> obtenirLlistat(){
        Random r = new Random();
        for (int i = 1; i <= 100000; i++) {
            Alumne al= new Alumne(i,r.nextDouble()*10);
            alumnat.add(al);
        }
        Collections.shuffle(alumnat);
        return alumnat;
    }
}
