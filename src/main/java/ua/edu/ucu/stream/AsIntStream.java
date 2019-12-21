package ua.edu.ucu.stream;

import java.util.ArrayList;
import java.util.List;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.*;

public class AsIntStream implements IntStream {

    private IntIterator iterator;

    private AsIntStream(IntIterator iterator) {
        this.iterator = iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(
            new IntArrayIterator(values)
        );
    }

    @Override
    public Double average() {
        int sum = 0;
        int n = 0;
        while(iterator.hasNext()){
            sum+=iterator.next();
            n+=1;
        }
        return Double.valueOf((double) sum/n);
    }

    @Override
    public Integer max() {
        return reduce(Integer.MIN_VALUE, (int i, int val)->(val>i)?val:i);

    }

    @Override
    public Integer min() {
        return reduce(Integer.MAX_VALUE, (int i, int val)->(val<i)?val:i);
    }

    @Override
    public long count() {
        return reduce(0, (int i, int val)->i+1);
    }

    @Override
    public Integer sum() {
        return reduce(0, (int i, int val)->i+val);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new IntFilterIterator(iterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while(iterator.hasNext()){
            action.accept(iterator.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new IntMapIterator(iterator, mapper));    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new IntFlatMapIterator(iterator, func));    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        while(iterator.hasNext()){
            identity = op.apply(identity, iterator.next());
        }
        return identity;

    }

    @Override
    public int[] toArray() {
        List<Integer> res_list = new ArrayList<Integer>();
        while(iterator.hasNext()){
            res_list.add(iterator.next());
        }
        int[] res = new int[res_list.size()];
        for(int i=0; i<res_list.size(); i++){
            res[i] = res_list.get(i);
        }
        return res;
    }


}
