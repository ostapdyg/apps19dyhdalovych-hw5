package ua.edu.ucu;

import ua.edu.ucu.iterators.IntArrayIterator;
import ua.edu.ucu.iterators.IntIterator;
import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;

public class IntIteratorTest {

    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmpty() {
        IntIterator iter = new IntArrayIterator();
        iter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextOnLast() {
        IntIterator iter = new IntArrayIterator(1, 2);
        iter.next();
        iter.next();
        iter.next();

    }
}