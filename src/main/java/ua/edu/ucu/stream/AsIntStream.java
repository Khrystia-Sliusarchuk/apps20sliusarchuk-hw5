package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;
import ua.edu.ucu.iterators.BaseIterator;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class AsIntStream implements IntStream {
    private Iterator<Integer> iterator;

    private AsIntStream(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new BaseIterator(values));
    }

    public void isEmpty() {
        if (!this.iterator.hasNext()) {
            throw new IllegalArgumentException("Stream is empty!");
        }
    }

    @Override
    public Double average() {
        isEmpty();
        Iterable<Integer> iterable = () -> this.iterator;
        int sum = 0;
        int counter = 0;
        for (int elem : iterable) {
            sum = sum + elem;
            counter = counter + 1;
        }
        return (double) sum / counter;
    }

    @Override
    public Integer max() {
        int maxValue = Integer.MIN_VALUE;
        return minOrMax(maxValue, true);
    }

    @Override
    public Integer min() {
        int minValue = Integer.MAX_VALUE;
        return minOrMax(minValue, false);
    }

    private Integer minOrMax(int value, boolean max) {
        isEmpty();
        Iterable<Integer> iterable = () -> this.iterator;
        for (int elem : iterable) {
            if (elem > value && max || elem < value && !max) {
                value = elem;
            }
        }
        return value;
    }

    @Override
    public long count() {
        return reduce(0, (sum, val) -> sum += 1);
    }

    @Override
    public Integer sum() {
        isEmpty();
        return reduce(0, (sum, val) -> sum += val);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(this.iterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        Iterable<Integer> iterable = () -> this.iterator;
        for (int elem : iterable) {
            action.accept(elem);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(this.iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(this.iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        Iterable<Integer> iterable = () -> this.iterator;
        int temp = identity;
        for (int elem : iterable) {
            temp = op.apply(temp, elem);
        }
        return temp;
    }

    @Override
    public int[] toArray() {
        Iterable<Integer> iterable = () -> this.iterator;
        ArrayList<Integer> tempArr = new ArrayList<>();
        for (Integer elem : iterable) {
            tempArr.add(elem);
        }
        int[] resultArr = new int[tempArr.size()];
        for (int i = 0; i < resultArr.length; i++) {
            resultArr[i] = tempArr.get(i);
        }
        return resultArr;
    }
}
