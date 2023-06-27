package TDA;

import java.util.Iterator;

public class LinkedList<E> implements List<E> {

    private Node<E> first;
    private Node<E> last;

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    @Override
    public int size() {
        int cont = 0;
        Node<E> viajero;
        for (viajero = first; viajero != null; viajero = viajero.getNext()) {
            cont++;
        }
        return cont;
    }

    @Override
    public String toString() {
        String result = "{";
        Node<E> p;
        if (!isEmpty()) {
            for (p = first; p != null; p = p.getNext()) {

                result += p.getContent() + ", ";
            }
            result = result.substring(0, result.length() - 2);

        }
        return result + "}";
    }

    @Override
    public boolean isEmpty() {
        return this.first == null && this.last == null;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        }
        Node<E> nuevo = new Node<>(element);
        nuevo.setNext(this.first);
        if (this.isEmpty()) {
            this.last = nuevo;
        }
        this.first = nuevo;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        }
        Node<E> nuevo = new Node<>(element);
        if (this.isEmpty()) {
            this.first = nuevo;
        } else {
            this.last.setNext(nuevo);
        }
        this.last = nuevo;
        return true;
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public E remove(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
/*
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    @Override
    public E get(int index) {
        Node<E> n;
        int cont = 0;
        E elemento = null;
        for (n = first; n != null; n = n.getNext()) {
            if (cont == index) {
                elemento = n.getContent();
            }
            cont++;
        }
        return elemento;
    }

    @Override
    public E set(int index, E element) {
        Node<E> n;
        int cont = 0;
        E nuevoContenido = null;
        for (n = first; n != null; n = n.getNext()) {
            if (cont == index) {
                n.setContent(element);
                nuevoContenido = n.getContent();
            }
            cont++;
        }
        return nuevoContenido;
    }

    // Implemente aquí los métodos del taller
    // No olvide incluir sus prototipos en la interface List
    @Override
    public void swapElements(int index1, int index2) {
        if (!isEmpty() & index1 <= size() - 1 & index2 <= size() - 1) {
            E recordar = get(index1);
            set(index1, get(index2));
            set(index2, recordar);
        } else {
            System.out.println("Lista vacia o el indice esta fuera del rango de la lista");
        }
    }

    @Override
    public LinkedList<E> splitIntoTwoHalves() {
        Node<E> n;
        int cont = 0;
        LinkedList<E> nuevaList = new LinkedList<>();
        for (n = first; n != null; n = n.getNext()) {
            if (cont >= size() / 2) {
                nuevaList.addLast(n.getContent());
            }
            cont++;
        }
        cont=0;
        for (n = first; n != null; n = n.getNext()) {
            if (cont == (size() / 2) - 1) {
                n.setNext(null);
                this.last = n;
            }
            cont++;
        }
        return nuevaList;
    }

    @Override
    public LinkedList<E> getOddElements() {
        LinkedList<E> nuevaLista = new LinkedList<>();
        for (int i = 0; i <= size() - 1; i++) {
            if (i % 2 == 0) {
                nuevaLista.addLast(get(i));
            }
        }
        return nuevaLista;
    }

    @Override
    public E getMiddleElement() {
        Node<E> n;
        E elemento = null;
        for (n = first; n != null; n = n.getNext()) {
            elemento = get(size() / 2);
        }
        return elemento;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<>() {
            Node<E> cursor=first;
            @Override
            public boolean hasNext() {
                return cursor!=null;
            }

            @Override
            public E next() {
                E e = cursor.getContent();
                cursor = cursor.getNext().getNext();
                return e;
                
            }
        };
        return it;    }

}
