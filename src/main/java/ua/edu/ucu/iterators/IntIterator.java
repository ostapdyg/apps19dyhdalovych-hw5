package ua.edu.ucu.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * IntStreamIterator
 */
public class IntIterator implements Iterator<Integer> {
    protected Integer toReturn = null;

    @Override
    public boolean hasNext() {
        return toReturn != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer res = toReturn;
        toReturn = null;
        setNext();
        return res;
    }

    protected void setNext() {
    }

}