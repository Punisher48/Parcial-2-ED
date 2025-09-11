package main;

import modelo.Frase;
import modelo.ListaEnlazada;
import org.apache.log4j.Logger;
import service.Api;

import java.util.ArrayDeque;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            long inicioPrograma = System.nanoTime(); //para performance

            logger.info("Iniciando programa...");

            ArrayDeque<ArrayDeque<ListaEnlazada>> dequeGlobal = new ArrayDeque<>();
            String[] frases = Api.obtenerFrases();

            int numFrase = 1;
            for (String f : frases) {
                long inicioFrase = System.currentTimeMillis(); //tiempo por frase

                logger.info("Procesando frase #" + numFrase);
                Frase frase = new Frase(f);
                frase.procesarFrases();

                dequeGlobal.add(frase.getDequeSoporte());

                long finFrase = System.currentTimeMillis();
                logger.info("Tiempo de procesamiento frase #" + numFrase + ": " + (finFrase - inicioFrase) + " ms\n");

                numFrase++;
            }

            logger.info("=== FASE #2: DESENCRIPTACION ===");

            int fraseNum = 1;
            for (ArrayDeque<ListaEnlazada> dequeFrase : dequeGlobal) {
                logger.info("Desencriptando frase #" + fraseNum);
                Frase fraseTemp = new Frase("");
                fraseTemp.getDequeSoporte().addAll(dequeFrase);
                fraseTemp.desencriptarFrase();
                fraseNum++;
            }

            long finPrograma = System.nanoTime();
            long duracion = (finPrograma - inicioPrograma) / 1_000_000; // en milisegundos
            logger.info("Duracion total del programa: " + duracion + " ms");

            //Logs de performance (memoria)
            Runtime runtime = Runtime.getRuntime();
            long memoriaUsada = (runtime.totalMemory() - runtime.freeMemory()) / 1024;
            logger.info("Memoria usada: " + memoriaUsada + " KB");

            logger.info("Programa finalizado correctamente.");

        } catch (Exception e) {
            logger.error("Error durante la ejecucion", e);
        }
    }
}
