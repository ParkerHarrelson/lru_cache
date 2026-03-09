package org.parkerharrelson.linkedlist;

import org.parkerharrelson.exception.NodeDoesNotExistException;

public class LinkedList<E, T> {

    private Node<E, T> head, tail;
    private int size;

    public LinkedList () {
        size = 0;
    }

    /*
        Insertion functionality
     */
    public void insertHead(Node<E, T> node) {
        if (node == null) {
            return;
        }

        node.setNext(head);
        node.setPrev(null);

        if (head != null) {
            head.setPrev(node);
        } else {
            tail = node;
        }

        head = node;
        size++;
    }

    public void insertTail(Node<E, T> node) {
        if (node == null) {
            return;
        }

        node.setPrev(tail);
        node.setNext(null);

        if (tail != null) {
            tail.setNext(node);
        } else {
            head = node;
        }

        tail = node;
        size++;
    }

    public void insertAfter(Node<E, T> prev, Node<E, T> node) {
        if (prev == null) {
            throw new NodeDoesNotExistException("Node does not exist.");
        }

        if (prev == tail) {
            insertTail(node);
            return;
        }

        node.setNext(prev.getNext());
        node.setPrev(prev);
        prev.getNext().setPrev(node);
        prev.setNext(node);

        size++;
    }

    public void insertBefore(Node<E, T> next, Node<E, T> node) {
        if (next == null) {
            throw new NodeDoesNotExistException("Node does not exist.");
        }

        if (next == head) {
            insertHead(node);
            return;
        }

        node.setPrev(next.getPrev());
        node.setNext(next);
        next.getPrev().setNext(node);
        next.setPrev(node);

        size++;
    }

    /*
        Retrieval functionality
    */
    public Node<E, T> peekFirst() {
        return head;
    }

    public Node<E, T> peekLast() {
        return tail;
    }

    /*
        Removal functionality
     */
    public void removeFirst() {
        if (head == null) {
            return;
        }

        if (head.getNext() == null) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
        size --;
    }

    public void removeLast() {
        if (tail == null) {
            return;
        }

        if (tail.getPrev() == null) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        size--;
    }

    public void removeNode(Node<E, T> node) {
        if (node == null) {
            return;
        }

        if (node == head) {
            removeFirst();
            return;
        }

        if (node == tail) {
            removeLast();
            return;
        }

        Node<E, T> prev = node.getPrev();
        Node<E, T> next = node.getNext();

        if (prev == null || next == null) {
            return;
        }

        prev.setNext(next);
        next.setPrev(prev);

        node.setPrev(null);
        node.setNext(null);

        size--;
    }

    public Node<E, T> peekLastAndRemove() {
        if (tail == null) {
            return null;
        }

        Node<E, T> removed = tail;
        removeLast();
        removed.setPrev(null);
        removed.setNext(null);
        return removed;
    }

    /*
        States
     */

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return head == null && tail == null && size == 0;
    }

    public void clear() {
        Node<E, T> cur = head;

        while (cur != null) {
            Node<E, T> next = cur.getNext();
            cur.setPrev(null);
            cur.setNext(null);
            cur = next;
        }

        head = null;
        tail = null;
        size = 0;
    }
}
