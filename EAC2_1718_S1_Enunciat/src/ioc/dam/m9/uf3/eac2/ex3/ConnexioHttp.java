package ioc.dam.m9.uf3.eac2.ex3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albert
 */
public class ConnexioHttp {
    String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        ConnexioHttp http = new ConnexioHttp();

        System.out.println("Test 1 - Enviant petició GET");
        http.enviaGet();

        System.out.println("\nTest 2 - Enviant petició POST");
        http.enviaPost();

    }

    // petició GET
    private void enviaGet(){
        Integer httpCode;
        BufferedReader entrada;
        String inputLine;
        
        try {
            URL url = new URL("https://www.caixabank.cat/index_ca.html");
            HttpURLConnection conexio = (HttpURLConnection)url.openConnection();
            conexio.setRequestMethod("GET");
            conexio.setRequestProperty("User-Agent", USER_AGENT);
            
            httpCode = conexio.getResponseCode();
            System.out.println("Enviant petició 'GET' a la URL: "+url);
            System.out.println("Resposta del servidor: "+httpCode);
            
            entrada = new BufferedReader(new InputStreamReader(conexio.getInputStream()));
            
            StringBuilder resposta = new StringBuilder();

            while ((inputLine = entrada.readLine()) != null) {
                resposta.append(inputLine);
                resposta.append("\n");
            }
            entrada.close();

            // Mostrar resultat
            System.out.println(resposta.toString());
        } catch (IOException ex) {
            Logger.getLogger(ConnexioHttp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // petició POST
    private void enviaPost()  {
        Integer httpCode;
        BufferedReader entrada;
        String inputLine;
        String parametres = "kt_login_user=albert&kt_login_password=ac@nela2#17";
        
        try {
            URL url = new URL("http://www.plasticoselche.es/es/login");
            HttpURLConnection conexio = (HttpURLConnection)url.openConnection();
            conexio.setRequestMethod("POST");
            conexio.setRequestProperty("User-Agent", USER_AGENT);
            
            conexio.setDoOutput(true);
            
            System.out.println("Enviant petició 'POST' a la URL: "+url);
            System.out.println("Paràmetres POST: "+parametres);
            
            DataOutputStream wr = new DataOutputStream(conexio.getOutputStream());
            wr.writeBytes(parametres);
            wr.flush();
            wr.close();

            entrada = new BufferedReader(new InputStreamReader(conexio.getInputStream()));
            
            StringBuilder resposta = new StringBuilder();

            while ((inputLine = entrada.readLine()) != null) {
                resposta.append(inputLine);
                resposta.append("\n");
            }
            entrada.close();

            httpCode = conexio.getResponseCode();
            System.out.println("Resposta del servidor: "+httpCode);
            
            // Mostrar resultat
            System.out.println(resposta.toString());
                
        } catch (IOException ex) {
            Logger.getLogger(ConnexioHttp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
