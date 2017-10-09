package ioc.dam.m9.uf2.eac1.Ex1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albert
 */
public class CaculMultiplicacioFactorial {

    public static void main(String[] args) {

        double sum = 0;
        try {
            int count = Runtime.getRuntime().availableProcessors();
            ScheduledExecutorService servei = Executors.newScheduledThreadPool(count);
            servei.awaitTermination(60, TimeUnit.SECONDS);
            servei.shutdownNow();
        } catch (InterruptedException ex) {
        }
       

        System.out.println(sum);

    }
}
