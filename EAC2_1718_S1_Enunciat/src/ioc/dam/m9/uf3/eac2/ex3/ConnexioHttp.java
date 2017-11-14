package ioc.dam.m9.uf3.eac2.ex3;

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

    }

    // petició POST
    private void enviaPost()  {

    }
}
