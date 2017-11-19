package ioc.dam.m9.uf3.eac2.ex3;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albert
 */
public class ConnexioHttp {

    public static void main(String[] args) throws Exception {

        ConnexioHttp http = new ConnexioHttp();

        System.out.println("Test 1 - Enviant petició GET");
        http.enviaGet();

        System.out.println("\nTest 2 - Enviant petició POST");
        http.enviaPost();

    }

    // petició GET
    private void enviaGet(){
        try {
            URL url = new URL("https://www.caixabank.cat/index_ca.html");
            HttpURLConnection conexio = (HttpURLConnection)url.openConnection();
            conexio.setRequestMethod("GET");
        } catch (IOException ex) {
            Logger.getLogger(ConnexioHttp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // petició POST
    private void enviaPost()  {
        try {
            URL url = new URL("http://www.plasticoselche.es/es/login");
            HttpURLConnection conexio = (HttpURLConnection)url.openConnection();
            conexio.setRequestMethod("POST");
        } catch (IOException ex) {
            Logger.getLogger(ConnexioHttp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
