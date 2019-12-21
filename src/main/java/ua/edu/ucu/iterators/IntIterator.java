package ua.edu.ucu.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * IntStreamIterator
 */
public class IntIterator implements Iterator<Integer>{
    protected Integer to_return=null;

    @Override
    public boolean hasNext() {
        return to_return!=null;
    }

    @Override
    public Integer next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        Integer res = to_return;
        to_return = null;
        setNext();
        return res;
    }

    
    protected void setNext(){
    }

}