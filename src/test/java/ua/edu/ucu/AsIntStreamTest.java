package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AsIntStreamTest {
    private IntStream intStream;
    private int[] tempArr;

    @Before
    public void setUp() throws Exception {
        tempArr = new int[]{10, 3, 5, 0, -1, 4, -7, 3};
    }

    @Test
    public void testOf() {
        intStream = AsIntStream.of(tempArr);
        int[] expResult = {10, 3, 5, 0, -1, 4, -7, 3};
        int[] result = intStream.toArray();
        assertArrayEquals(expResult, result);
        IntStream intStream2 = AsIntStream.of(10, 3, 5, 0, -1, 4, -7, 3);
        int[] result2 = intStream2.toArray();
        assertArrayEquals(expResult, result2);
        IntStream intStream3 = AsIntStream.of(10);
        int[] expResult3 = {10};
        int[] result3 = intStream3.toArray();
        assertArrayEquals(expResult3, result3);
        IntStream intStream4 = AsIntStream.of();
        int[] expResult4 = {};
        int[] result4 = intStream3.toArray();
        assertArrayEquals(expResult4, result4);
    }

    @Test
    public void testAverage() {
        intStream = AsIntStream.of(tempArr);
        double expResult = 2.125;
        double result = intStream.average();
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testMin() {
        intStream = AsIntStream.of(tempArr);
        int expResult = -7;
        int result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test
    public void testMax() {
        intStream = AsIntStream.of(tempArr);
        int expResult = 10;
        int result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testCount() {
        intStream = AsIntStream.of(tempArr);
        long expResult = 8;
        long result = intStream.count();
        assertEquals(expResult, result);
    }

    @Test
    public void testSum() {
        intStream = AsIntStream.of(tempArr);
        int expResult = 17;
        int result = intStream.sum();
        assertEquals(expResult, result);
    }

    @Test
    public void testFilter(){
        intStream = AsIntStream.of(tempArr);
        intStream = intStream.filter(x -> x > 0);
        int expResult = intStream.min();
        int result = 3;
        assertEquals(expResult, result);
    }

    @Test
    public void forEach() {
        intStream = AsIntStream.of(tempArr);
        StringBuilder str = new StringBuilder();
        intStream.forEach(x -> str.append(x));
        String expResult = "10350-14-73";
        assertEquals(str.toString(), expResult);
    }

    @Test
    public void map() {
        intStream = AsIntStream.of(tempArr);
        intStream = intStream.map(x -> x + 3);
        int expResult = intStream.min();
        int real = -4;
        assertEquals(expResult, real);
    }

    @Test
    public void flatMap() {
        intStream = AsIntStream.of(tempArr);
        intStream = intStream.flatMap(x -> AsIntStream.of(x - 1, x, x + 1));
        int expResult = intStream.min();
        int real = -8;
        assertEquals(expResult, real);
    }

    @Test
    public void reduce() {
        intStream = AsIntStream.of(tempArr);
        int res = intStream.reduce(0, (sum, x) -> sum += x);
        int real = 17;
        assertEquals(res, real);

    }

    @Test
    public void toArray() {
        intStream = AsIntStream.of(tempArr);
        int[] res = intStream.filter(x -> x > 0).toArray();
        int[] exp = {10, 3, 5, 4, 3};
        assertArrayEquals(exp, res);
    }

}
