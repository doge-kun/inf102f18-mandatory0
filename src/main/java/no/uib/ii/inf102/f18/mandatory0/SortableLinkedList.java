package no.uib.ii.inf102.f18.mandatory0;

import java.util.Iterator;

public class SortableLinkedList<E extends Comparable<E>> implements ISortableList<E> {
    private static int counter;
    private Node head = null;
    private Node tail = null;

    private class Node {
        Node next;
        E data;

        public Node(E dataElement) {
            next = null;
            data = dataElement;
        }

        public E getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node nextNode) {
            next = nextNode;
        }
    }

    public void add(E element) {
        if(head == null) {
            head = new Node(element);
            tail = head;
        } else {
            Node newTail = new Node(element);
            tail.next = newTail;
            tail = newTail;
        }
    }

    public void add(int index, E element) {
        Node temp = new Node(element);
        Node current = head;
        if(current != null) {
            for(int i=0; i<index && current.getNext() != null; i++) {
                current = current.getNext();
            }
        }
        temp.setNext(current.getNext());
        current.setNext(temp);
        counter++;
    }

    public void clear() {
        head = null;
        tail = null;
        counter = 0;
    }

    public E get(int index) {
        if(index < 0) {
            return null;
        }

        Node current = null;

        if(head != null) {
            current = head.getNext();

            for(int i=0; i<index; i++) {
                if(current.getNext() == null) {
                    return null;
                }
                current = current.getNext();
            }
            return current.getData();
        }
        return current.getData();
    }

    public E remove(int index) {
        if(index < 1 || index > size()) {
            return null;
        }

        Node current = head;
        if(head != null) {
            for(int i=0; i<index; i++) {
                if(current.getNext() == null) {
                    return null;
                }
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
            return current.getData();
        }
        return null;
    }

    public boolean isEmpty() {
        return (head == null && tail == null);
    }

    public int size() {
        return counter;
    }

    public void sort() {
        mergeSort(head);
    }

    private Node mergeSort(Node a) {
        if(a == null || a.next == null) {
            return a;
        }

        Node middle = getMiddle(a);
        Node nextToMiddle = middle.next;

        middle.next = null;

        Node left = mergeSort(a);
        Node right = mergeSort(nextToMiddle);

        return sortedMerge(left, right);
    }

    private Node getMiddle(Node a) {
        if(a != null) {
            Node slowPointer = a, fastPointer = a.next;

            while(fastPointer != null && fastPointer.next != null) {
                fastPointer = fastPointer.next.next;
                slowPointer = slowPointer.next;
            }
            return slowPointer;
        }
        return null;
    }

    private Node sortedMerge(Node a, Node b) {
        Node result;

        if(a == null) {
            return b;
        }
        if(b == null) {
            return a;
        }

        if((a.getData().compareTo(b.getData()) >= 0)) {
            result = b;
            result.next = sortedMerge(a, b.next);
        } else {
            result = a;
            result.next = sortedMerge(a.next, b);
        }
        return result;
    }

    private void printLinkedList(Node start) {
        Node copy = start;
        while(copy != null) {
            System.out.print(copy.data + " ");
            copy = copy.next;
        }
    }

    public E[] toArray(E[] a) {
        for(int i=0; i<a.length; i++) {
            a[i] = get(i);
        }
        return a;
    }

    public Iterator iterator() {
        return new Iterator() {
            Node previous = null;
            Node current = head;

            public boolean hasNext() {
                return current.getNext() != null;
            }

            public void remove() {
                previous.setNext(current.getNext());
            }

            public E next() {
                if(!hasNext()) return null;
                E data = current.getData();
                previous = current;
                current = current.getNext();
                return data;
            }
        };
    }

    public static void main(String[] args) {
        SortableLinkedList<String> list = new SortableLinkedList<String>();

        list.add("i"); list.add("m"); list.add("x"); list.add("k"); list.add("v"); list.add("o"); list.add("c");
        list.add("n"); list.add("u"); list.add("t"); list.add("r"); list.add("s"); list.add("b"); list.add("e");
        list.add("a"); list.add("q"); list.add("j"); list.add("d"); list.add("f"); list.add("g"); list.add("z");
        list.add("l"); list.add("h"); list.add("y"); list.add("p"); list.add("w");

        //list.add('!'); list.add('´'); list.add(':'); list.add('#'); list.add('/'); list.add('*'); list.add('+');
        //list.add('"'); list.add('%'); list.add('.'); list.add('&'); list.add(')'); list.add('('); list.add('-');

        //list.add("7"); list.add("1"); //list.add("6"); list.add("2"); list.add("8"); list.add("4"); list.add("9");
        //list.add("3"); list.add("5");

        list.printLinkedList(list.head);
        System.out.println();
        list.head = list.mergeSort(list.head);
        list.printLinkedList(list.head);
    }
}
