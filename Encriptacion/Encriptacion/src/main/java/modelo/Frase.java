package modelo;

import org.apache.log4j.Logger;
import java.util.ArrayDeque;

public class Frase {
    private static final Logger logger = Logger.getLogger(Frase.class);
    private String frase;
    private ArrayDeque<ListaEnlazada> dequeSoporte = new ArrayDeque<>();

    public Frase(String frase) {
        this.frase = frase;
    }

    // ============================================
    // FASE 1: ENCRIPTACIÓN DE LA FRASE
    // ============================================
    public void procesarFrases() {
        logger.info("Frase completa: " + frase + "\n");

        String[] palabras = frase.split(" ");
        for (String palabra : palabras) {
            logger.info("Palabra: " + palabra);
            ListaEnlazada lista = new ListaEnlazada();
            int contador = 1;

            for (int j = 0; j < palabra.length(); j++) {
                char c = palabra.charAt(j);
                int ascii = (int) c;
                int encriptado = ascii + contador;

                lista.insertar(encriptado);
                logger.debug("  " + c + " (" + ascii + ") + " + contador + " = " + encriptado);
                contador += 2;
            }

            logger.debug("Encriptada como:");
            lista.mostrar();

            lista.intercambiarAdyacentes();
            logger.debug("Despues de intercambiar:");
            lista.mostrar();

            logger.info("--------------------------------------------------\n");

            dequeSoporte.add(lista);
        }
    }

    // ============================================
    // FASE 2: DESENCRIPTACIÓN DE LA FRASE
    // ============================================
    public void desencriptarFrase() {
        logger.info("=== PROCESO DE DESENCRIPTACION ===\n");

        StringBuilder fraseEncTexto = new StringBuilder();
        for (ListaEnlazada lista : dequeSoporte) {
            fraseEncTexto.append(lista.obtenerPalabraComoTexto()).append(" ");
        }
        logger.info("Frase encriptada (texto): " + fraseEncTexto.toString().trim() + "\n");

        StringBuilder fraseOriginal = new StringBuilder();
        int palabraNum = 1;

        for (ListaEnlazada lista : dequeSoporte) {
            logger.info("Palabra #" + palabraNum);

            logger.debug("Encriptada como:");
            lista.mostrar();

            lista.intercambiarAdyacentes();
            logger.debug("Despues de revertir intercambio:");
            lista.mostrar();

            Nodo temp = lista.cabeza;
            int contador = 1;
            StringBuilder palabraDes = new StringBuilder();

            while (temp != null) {
                int valor = temp.valor;
                int ascii = valor - contador;
                char c = (char) ascii;
                logger.debug("  " + valor + " - " + contador + " = " + ascii + " (" + c + ")");
                palabraDes.append(c);
                contador += 2;
                temp = temp.siguiente;
            }

            logger.info("Palabra desencriptada: " + palabraDes + "\n");
            logger.info("--------------------------------------------------\n");

            fraseOriginal.append(palabraDes).append(" ");
            palabraNum++;
        }

        logger.info("Frase desencriptada completa: " + fraseOriginal.toString().trim() + "\n");
    }

    public ArrayDeque<ListaEnlazada> getDequeSoporte() {
        return dequeSoporte;
    }
}
