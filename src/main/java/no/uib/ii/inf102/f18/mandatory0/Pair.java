package no.uib.ii.inf102.f18.mandatory0;

public class Pair<E extends Comparable<E>> implements Comparable<Pair<E>> {
    E number;
    E word;

    public Pair(E pageNr, E content) {
        number = pageNr;
        word = content;
    }

    public E getNumber() {
        return number;
    }

    public E getWord() {
        return word;
    }

    public int compareTo(Pair<E> o) {
        return this.number.compareTo(o.number);
    }

    @Override
    public String toString() {
        return "" + word;
    }
}
