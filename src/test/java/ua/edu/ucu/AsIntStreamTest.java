package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AsIntStreamTest {

    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = { -1, 0, 1, 2, 3 };
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testMax() {
        int expResult = 3;
        int result = intStream.max();
        assertEquals(expResult, result);
        // Test if stream reloads after terminal methods
        result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testMin() {
        int expResult = -1;
        int result = intStream.min();
        assertEquals(expResult, result);
        // Test if stream reloads after terminal methods
        result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test
    public void testSum() {
        int expResult = 5;
        int result = intStream.sum();
        assertEquals(expResult, result);
        // Test if stream reloads after terminal methods
        result = intStream.sum();
        assertEquals(expResult, result);
    }

    @Test
    public void testAverage() {
        double expResult = 1;
        double result = intStream.average();
        assertEquals(expResult, result, .0001);
        // Test if stream reloads after terminal methods
        result = intStream.average();
        assertEquals(expResult, result, .0001);
    }

    @Test
    public void testCount() {
        long expResult = 5;
        long result = intStream.count();
        assertEquals(expResult, result);
        // Test if stream reloads after terminal methods
        result = intStream.count();
        assertEquals(expResult, result);
    }

    @Test
    public void testReduce() {
        int expResult = -4;
        int result = intStream.reduce(1, (int left, int right) -> left - right);
        assertEquals(expResult, result);
        // Test if stream reloads after terminal methods
        result = intStream.reduce(1, (int left, int right) -> left - right);
        assertEquals(expResult, result);
    }

    @Test
    public void testToArray() {
        int expResult[] = { -1, 0, 1, 2, 3 };
        int result[] = intStream.toArray();
        assertArrayEquals(expResult, result);
        // Test if stream reloads after terminal methods
        result = intStream.toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testReset() {
        intStream = intStream.filter(x -> x > 0) // 1, 2, 3
                .map(x -> x * x) // 1, 4, 9
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1)); // 0, 1, 2, 3, 4, 5, 8, 9, 10
        assertEquals(9, intStream.count());
        assertEquals(9, intStream.count());
    }

}
