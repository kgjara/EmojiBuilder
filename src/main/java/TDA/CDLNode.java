/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDA;

/**
 *
 * @author Kenny
 */
public class CDLNode<E> {

    private E content;
    private CDLNode<E> next;
    private CDLNode<E> previous;

    public CDLNode(E content) {
        this.content = content;
        this.next = this;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public CDLNode<E> getNext() {
        return next;
    }

    public void setNext(CDLNode<E> next) {
        this.next = next;
    }

    public CDLNode<E> getPrevious() {
        return previous;
    }

    public void setPrevious(CDLNode<E> previous) {
        this.previous = previous;
    }

}
