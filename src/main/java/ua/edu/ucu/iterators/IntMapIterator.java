package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

public class IntMapIterator extends IntIterator {
    private IntIterator iterator;
    private IntUnaryOperator mapper;

    public IntMapIterator(IntIterator iterator, IntUnaryOperator mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
        setNext();
    }

    protected void setNext() {
        if (iterator.hasNext()) {
            toReturn = mapper.apply(iterator.next());
        }
    }

}
