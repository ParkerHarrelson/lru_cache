package org.parkerharrelson.linkedlist;

public class Node<E, T> {

    private Node<E, T> next, prev;
    private T data;
    private E key;

    public Node(E key, T data) {
        this.key = key;
        this.data = data;
    }

    public Node<E, T> getNext() {
        return this.next;
    }

    public Node<E, T> getPrev() {
        return this.prev;
    }

    public void setNext(Node<E, T> node) {
        this.next = node;
    }

    public void setPrev(Node<E, T> node) {
        this.prev = node;
    }

    public E getKey() {
        return this.key;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
