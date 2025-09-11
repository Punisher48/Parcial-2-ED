package modelo;

import org.apache.log4j.Logger;

public class ListaEnlazada {
    private static final Logger logger = Logger.getLogger(ListaEnlazada.class);
    Nodo cabeza;

    public void insertar(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
        logger.debug("Nodo insertado: " + valor);
    }

    public void mostrar() {
        Nodo temp = cabeza;
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            sb.append(temp.valor).append(" -> ");
            temp = temp.siguiente;
        }
        sb.append("null");
        logger.debug(sb.toString());
    }

    public void intercambiarAdyacentes() {
        if (cabeza == null || cabeza.siguiente == null) return;

        Nodo prev = null;
        Nodo actual = cabeza;
        cabeza = cabeza.siguiente;

        while (actual != null && actual.siguiente != null) {
            Nodo siguiente = actual.siguiente;
            Nodo siguientePar = siguiente.siguiente;

            siguiente.siguiente = actual;
            actual.siguiente = siguientePar;

            if (prev != null) {
                prev.siguiente = siguiente;
            }

            prev = actual;
            actual = siguientePar;
        }

        logger.debug("Intercambio de adyacentes realizado");
    }

    public String obtenerPalabraComoTexto() {
        StringBuilder palabra = new StringBuilder();
        Nodo temp = cabeza;
        while (temp != null) {
            palabra.append((char) temp.valor);
            temp = temp.siguiente;
        }
        return palabra.toString();
    }
}
