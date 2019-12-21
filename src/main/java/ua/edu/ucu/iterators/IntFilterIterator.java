package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

/**
 * IntFilterIterator
 */
public class IntFilterIterator extends IntIterator {
    private IntIterator iterator;
    private IntPredicate predicate;

    public IntFilterIterator(IntIterator iterator, IntPredicate predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
        setNext();
    }

    public void setNext() {
        while (iterator.hasNext()) {
            int cur = iterator.next();
            if (predicate.test(cur)) {
                toReturn = cur;
                return;
            }
        }
    }

    @Override
    public void reset() {
        iterator.reset();
        setNext();
    }
}