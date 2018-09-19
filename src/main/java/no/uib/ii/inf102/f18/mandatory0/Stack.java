package no.uib.ii.inf102.f18.mandatory0;

import java.util.Iterator;

public class Stack<E> implements Iterable<E> {
    private Node top;
    private int stackCount = 0;

    private class Node {
        E element;
        Node next;
    }

    public boolean isEmpty() {
        if(top.equals(null)) {
            return true;
        }
        return false;
    }

    public void push(E element) {
        Node oldTop = top;
        top = new Node();
        top.element = element;
        top.next = oldTop;
        stackCount++;
    }

    public E pop() {
        E element = top.element;
        Node newTop = top.next;
        remove(top);
        top = newTop;
        stackCount--;

        return element;
    }

    public void remove(Node popped) {
        popped.element = null;
        popped.next = null;
        popped = null;
    }

    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {
        private Node current = top;

        public boolean hasNext() {
            if(current.equals(null)) {
                return false;
            }
            return true;
        }

        public E next() {
            E element = current.element;
            current = current.next;
            return element;
        }
    }
}
