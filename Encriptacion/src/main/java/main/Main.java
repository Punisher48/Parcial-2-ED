package main;

import modelo.Frase;
//import modelo.palabra1;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        Frase prueba = new Frase("HOLA MUNDO");

        LinkedList<Frase> palabras = new LinkedList<Frase>();

        palabras.add(prueba);

        System.out.println(palabras);

        prueba.Incrptador();

    }
}