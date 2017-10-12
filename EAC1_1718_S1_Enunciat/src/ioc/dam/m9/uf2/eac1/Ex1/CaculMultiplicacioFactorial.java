package ioc.dam.m9.uf2.eac1.Ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.logging.*;

/**
 *
 * @author albert
 */
public class CaculMultiplicacioFactorial {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Factorial> llistaTasques= new ArrayList<>();
        Scanner lector = new Scanner(System.in);
        int numACalcular = 0;
        Double sum = 0.0;
        
        System.out.print("Introdueix el número del que vols calcular el factorial: ");
        numACalcular = lector.nextInt();
        
        for (int i = 0; i < 1000; i++) {
            Factorial calcula = new Factorial(numACalcular);
            llistaTasques.add(calcula);
        }
        List <Future<Double>> llistaResultats;
        llistaResultats = executor.invokeAll(llistaTasques);
        
        executor.shutdown();
        for (int i = 0; i < llistaResultats.size(); i++) {
            sum += llistaResultats.get(i).get();
        }
        System.out.println("El resultat final és "+sum);
    }
}
