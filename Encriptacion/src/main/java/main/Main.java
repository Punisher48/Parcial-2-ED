package main;

import modelo.*;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {
        try {
            // 🔹 ArrayDeque global que contendrá TODAS las frases (cada frase es otro deque con sus palabras)
            ArrayDeque<ArrayDeque<ListaEnlazada>> dequeGlobal = new ArrayDeque<>();

            String[] frases = Api.obtenerFrases();

            int numFrase = 1;
            for (String f : frases) {
                System.out.println("\n=== Nueva frase ===");
                FraseApi fraseApi = new FraseApi(f);
                fraseApi.procesarFrases();

                // 🔹 Guardar el deque de esta frase dentro del deque global
                dequeGlobal.add(fraseApi.getDequeSoporte());

                System.out.println("----------------------------");
                numFrase++;
            }

            // 🔹 Al final, imprimir el ArrayDeque GLOBAL separado por frases
            System.out.println("\n>>> ArrayDeque GLOBAL (todas las frases encriptadas):");
            int fraseNum = 1;
            for (ArrayDeque<ListaEnlazada> dequeFrase : dequeGlobal) {
                System.out.println("\nFrase #" + fraseNum + ":");
                int palabraNum = 1;
                for (ListaEnlazada lista : dequeFrase) {
                    System.out.print("  Palabra #" + palabraNum + ": ");
                    lista.mostrar();
                    palabraNum++;
                }
                fraseNum++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
