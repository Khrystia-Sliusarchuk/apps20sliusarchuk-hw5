package ua.edu.ucu.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BaseIterator implements Iterator<Integer> {
    private int[] values;
    private int counter = 0;

    public BaseIterator(int[] values) {
        this.values = values.clone();
    }

    @Override
    public boolean hasNext() {
        if (counter < values.length) {
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return values[counter++];
        } else {
            throw new NoSuchElementException();
        }
    }
}
