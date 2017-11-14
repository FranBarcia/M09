package ioc.dam.m9.uf3.eac2.ex1;

/**
 *
 * @author albert
 */
public class MajusculaServidor {

    public void servei() {
        /*
        Aquesta classe estarà sempre “escoltant”. 
        Quan rebi un text d'un client:
            - el convertirà a majúscules
            - li retornarà. 
        Tot ho farà a través del mètode servei()
        */
    }

    public static void main(String[] args) {
        MajusculaServidor majuscula = new MajusculaServidor();

        majuscula.servei();

    }
}
