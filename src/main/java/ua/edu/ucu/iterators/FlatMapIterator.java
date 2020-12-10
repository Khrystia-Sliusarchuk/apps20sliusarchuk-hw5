package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Iterator<Integer> tempIterator;
    private IntToIntStreamFunction function;

    public FlatMapIterator(Iterator<Integer> iterator, IntToIntStreamFunction function) {
        this.iterator = iterator;
        this.function = function;
    }

    @Override
    public boolean hasNext() {
        if (tempIterator != null && tempIterator.hasNext()) {
            return true;
        }

        while (iterator.hasNext()) {
            int current = iterator.next();
            AsIntStream tempStream = (AsIntStream) function.applyAsIntStream(current);
            int[] lst = tempStream.toArray();
            tempIterator = new BaseIterator(lst);
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        return tempIterator.next();
    }
}
