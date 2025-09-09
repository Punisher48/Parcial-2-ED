package modelo;
import java.util.LinkedList;

public class Frase {
    public String frase;

    public Frase(String frase) {

        this.frase = frase;

    }
    public void Incrptador() {

        String[] palabras = frase.split(" ");

        LinkedList<Integer> valoresIncriptados = new LinkedList<>();

        int contador = 1;

        for  (int i = 0; i < palabras.length; i++) {
            System.out.println(palabras[i]);

            //System.out.printf(String.valueOf(palabras[i].charAt(0)));

            for(int j = 0; j < palabras[i].length(); j++) {

                char miCaracter = palabras[i].charAt(j);

                int valorAscii = (int) miCaracter;

                valorAscii += contador;

                // aqui lo metemos

                valoresIncriptados.add(valorAscii);

                System.out.println( miCaracter + ": " + valorAscii );

                contador += 2;

            }

            valoresIncriptados.add(0);

            System.out.println(valoresIncriptados);

        }

    }
}

