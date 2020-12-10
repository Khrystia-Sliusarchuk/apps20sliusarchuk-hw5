package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private IntUnaryOperator operator;

    public MapIterator(Iterator<Integer> iterator, IntUnaryOperator operator) {
        this.iterator = iterator;
        this.operator = operator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        int temp = iterator.next();
        return operator.apply(temp);
    }
}
