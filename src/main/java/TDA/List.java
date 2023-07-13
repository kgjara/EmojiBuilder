package TDA;

public interface List<E> extends Iterable<E> {

    public int size();

    public boolean isEmpty();

    public void clear();

    public boolean addFirst(E element); // inserta el elemento element al inicio

    public boolean addLast(E element); // inserta el elemento element al final

    public E removeFirst(); // remueve el elemento al inicio de la lista

    public E removeLast(); // remueve el elemento al final de la lista

    public boolean add(int index, E element); // inserta element en la posición index

   /* public E remove(int index); // remueve y retorna el elemento en la posición index*/

    public E get(int index); // retorna el elemento ubicado en la posición index

    public E set(int index, E element); // setea el element en la posición index

    @Override
    public String toString();

    public E getMiddleElement();

    public void swapElements(int index1, int index2);

    public LinkedList<E> splitIntoTwoHalves();

    public LinkedList<E> getOddElements();
}
