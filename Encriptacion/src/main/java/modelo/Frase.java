package modelo;

import java.util.ArrayDeque;

public class Frase {

    private String frase;
    private ArrayDeque<ListaEnlazada> dequeSoporte = new ArrayDeque<>();

    public Frase(String frase) {
        this.frase = frase;
    }

    public void procesarFrases() {
        System.out.println("\nFrase completa: " + frase);

        String[] palabras = frase.split(" ");

        for (String palabra : palabras) {
            System.out.println("\nPalabra: " + palabra);
            ListaEnlazada lista = new ListaEnlazada();

            int contador = 1;

            for (int j = 0; j < palabra.length(); j++) {
                char c = palabra.charAt(j);
                int ascii = (int) c;
                int encriptado = ascii + contador;

                lista.insertar(encriptado);
                System.out.println(" " + c + " (" + ascii + ") +" + contador + " -> " + encriptado);
                contador += 2;
            }

            System.out.print("Encriptada como: ");
            lista.mostrar();

            // Intercambiar nodos adyacentes
            lista.intercambiarAdyacentes();
            System.out.print("Despues de intercambiar: ");
            lista.mostrar();

            // 🔹 Paso 3: Almacenar en deque
            dequeSoporte.add(lista);
        }

        // Mostrar confirmación del contenido en deque
        System.out.println("\nContenido del ArrayDeque:");
        int i = 1;
        for (ListaEnlazada l : dequeSoporte) {
            System.out.print(" Palabra #" + i + ": ");
            l.mostrar();
            i++;
        }
    }

    public ArrayDeque<ListaEnlazada> getDequeSoporte() {
        return dequeSoporte;
    }
}
