package modelo;


public class MylinkedList {

Nodo head;

public MylinkedList(){
    this.head=null;
}

public void add(int letra){

    Nodo newNodo = new Nodo(letra);
    if(this.head==null){

        this.head=newNodo;
        return;
    }
}

}
