package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;

public class IntFlatMapIterator extends IntIterator {
    private int[] cur_stream = {};
    private IntToIntStreamFunction func;
    private IntIterator iterator;
    private int index=0;

    public IntFlatMapIterator(IntIterator iterator, IntToIntStreamFunction func) {
        this.func = func;
        this.iterator = iterator;
        setNextStream();
        setNext();
    }
    
    protected void setNext(){
        if(index==cur_stream.length){
            cur_stream = new int[0];
            setNextStream();
            index = 0;
        }
        if(index<cur_stream.length){
            to_return = cur_stream[index];
            index += 1;
        }
    }

    private void setNextStream(){
        if(iterator.hasNext()){
            cur_stream = func.applyAsIntStream(iterator.next()).toArray();
        }
    }


}
