/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDA;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 *
 * @author Kenny
 */
public class CircularDoublyLL<E> implements List<E> {

    private CDLNode<E> Last;

    public CircularDoublyLL() {
        this.Last = null;
    }

    @Override
    public int size() {
        int cont = 0;
        if (!isEmpty()) {
            CDLNode<E> n = Last.getNext();
            do {
                cont++;
                n = n.getNext();
            } while (n != Last.getNext());
        }
        return cont;
    }

    @Override
    public boolean isEmpty() {
        return this.Last == null;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addFirst(E element) {
        CDLNode<E> nuevo = new CDLNode<>(element);

        if (element == null) {
            return false;
        } else {
            this.Last.getNext().setPrevious(nuevo);
            nuevo.setNext(this.Last.getNext());
            this.Last.setNext(nuevo);
        }
        this.Last = nuevo;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        CDLNode<E> nuevo = new CDLNode<>(element);

        if (element == null) {
            return false;
        } else if (!isEmpty()) {
            nuevo.setNext(Last.getNext());
            nuevo.setPrevious(Last);
            Last.setNext(nuevo);
            nuevo.getNext().setPrevious(nuevo);
        }

        this.Last = nuevo;
        return true;
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E removeLast() {
        E removido = null;
        if (!isEmpty()) {
            CDLNode nuevoLast = Last.getPrevious();
            removido = Last.getContent();
            Last.getPrevious().setNext(Last.getNext());
            Last.getNext().setPrevious(Last.getPrevious());
            Last.setNext(null);
            Last.setPrevious(null);
            Last = nuevoLast;
        }
        return removido;
    }

    @Override
    public boolean add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
/*
    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    @Override
    public E get(int index) {
        int cont = 0;
        if (!isEmpty()) {
            CDLNode<E> n = Last.getNext();
            do {
                if (cont == index) {
                    return n.getContent();
                }
                n = n.getNext();
                cont++;
            } while (n != Last.getNext());
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E getMiddleElement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void swapElements(int index1, int index2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LinkedList<E> splitIntoTwoHalves() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LinkedList<E> getOddElements() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String toString() {
        String result = "{";
        CDLNode<E> n;
        if (!isEmpty()) {
            for (n = Last.getNext(); n != Last; n = n.getNext()) {

                result += n.getContent() + ", ";
            }
            result = result.substring(0, result.length() - 2);

        }
        return result + "}";
    }

    public E getAnterior(E e) {
        CDLNode<E> n = Last.getNext();
        E anterior = null;
        do {
            if (e == n.getContent()) {
                anterior = n.getPrevious().getContent();
            }
            n = n.getNext();
        } while (n != Last.getNext());
        return anterior;
    }

    public E getSiguiente(E e) {
        CDLNode<E> n = Last.getNext();
        E siguiente = null;
        do {
            if (e == n.getContent()) {
                siguiente = n.getNext().getContent();
            }
            n = n.getNext();
        } while (n != Last.getNext());
        return siguiente;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ListIterator<E> listIterator() {
        ListIterator<E> it = new ListIterator<>() {
            CDLNode<E> cursor = Last.getNext();

            @Override
            public void forEachRemaining(Consumer<? super E> action) {
                ListIterator.super.forEachRemaining(action); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public boolean hasNext() {
                return cursor != Last;
            }

            @Override
            public E next() {
                E contenido = cursor.getContent();
                cursor = cursor.getNext();
                return contenido;
            }

            @Override
            public boolean hasPrevious() {
                return cursor != null;// En caso de haber solo uno, podria el anterior ser null
            }

            @Override
            public E previous() {
                E contenido = cursor.getContent();
                cursor = cursor.getPrevious();
                return contenido;
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        return it;
    }

    public void remove(E e) {
        if (!isEmpty()) {
            CDLNode<E> n = Last.getNext();
            if (Last.getContent() == e) {
                removeLast();
            } else {
                do {
                    if (n.getContent() == e) {
                        n.getPrevious().setNext(n.getNext());
                        n.getNext().setPrevious(n.getPrevious());
                        n.setNext(null);
                        n.setPrevious(null);

                    }
                    n = n.getNext();
                } while (n != null);
            }
        }
    }

}
