package ua.edu.ucu.iterators;

/**
 * IntArrayIterator
 */
public class IntArrayIterator extends IntIterator {

    private int[] values;
    private int index;

    public IntArrayIterator(int... values) {
        this.values = values;
        this.index = 0;
        setNext();
    }

    protected void setNext() {
        if (index < values.length) {
            to_return = (Integer) values[index];
            index++;
        }
    }

}