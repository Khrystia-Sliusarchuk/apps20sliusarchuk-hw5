package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilterIterator implements Iterator<Integer> {
    private IntPredicate predicate;
    private Iterator<Integer> iterator;
    private Integer temp;


    public FilterIterator(Iterator<Integer> iterator, IntPredicate predicate) {
        this.predicate = predicate;
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        while (iterator.hasNext()) {
            temp = iterator.next();
            if (predicate.test(temp)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        return temp;
    }
}
