package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;

public class IntFlatMapIterator extends IntIterator {
    private int[] curStream = {};
    private IntToIntStreamFunction func;
    private IntIterator iterator;
    private int index = 0;

    public IntFlatMapIterator(IntIterator iterator,
                              IntToIntStreamFunction func) {
        this.func = func;
        this.iterator = iterator;
        setNextStream();
        setNext();
    }

    protected void setNext() {
        if (index == curStream.length) {
            curStream = new int[0];
            setNextStream();
            index = 0;
        }
        if (index < curStream.length) {
            toReturn = curStream[index];
            index += 1;
        }
    }

    private void setNextStream() {
        if (iterator.hasNext()) {
            curStream = func.applyAsIntStream(iterator.next()).toArray();
        }
    }

    @Override
    public void reset() {
        iterator.reset();
        index = 0;
        setNextStream();
        setNext();
    }

}
