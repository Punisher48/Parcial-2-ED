package modelo;

public class ListaEnlazada {
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
    }

    public void mostrar() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.print(temp.valor + " -> ");
            temp = temp.siguiente;
        }
        System.out.println("null");
    }

    // 🔹 Paso 2: Intercambio de nodos adyacentes
    public void intercambiarAdyacentes() {
        if (cabeza == null || cabeza.siguiente == null) {
            return; // no hay nada para intercambiar
        }

        Nodo prev = null;
        Nodo actual = cabeza;
        cabeza = cabeza.siguiente; // la nueva cabeza es el segundo nodo

        while (actual != null && actual.siguiente != null) {
            Nodo siguiente = actual.siguiente;
            Nodo siguientePar = siguiente.siguiente;

            // swap
            siguiente.siguiente = actual;
            actual.siguiente = siguientePar;

            if (prev != null) {
                prev.siguiente = siguiente;
            }

            // mover punteros
            prev = actual;
            actual = siguientePar;
        }
    }
}
