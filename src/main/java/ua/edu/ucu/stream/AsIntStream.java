package ua.edu.ucu.stream;

import java.util.ArrayList;
import java.util.List;

import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;
import ua.edu.ucu.iterators.IntArrayIterator;
import ua.edu.ucu.iterators.IntFilterIterator;
import ua.edu.ucu.iterators.IntFlatMapIterator;
import ua.edu.ucu.iterators.IntIterator;
import ua.edu.ucu.iterators.IntMapIterator;

public class AsIntStream implements IntStream {

    private IntIterator iterator;

    private AsIntStream(IntIterator iterator) {
        this.iterator = iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new IntArrayIterator(values));
    }

    @Override
    public Double average() {
        int sum = 0;
        int n = 0;
        while (iterator.hasNext()) {
            sum += iterator.next();
            n += 1;
        }
        return Double.valueOf((double) sum / n);
    }

    @Override
    public Integer max() {
        return reduce(Integer.MIN_VALUE, new IntBinaryOperator() {
            @Override
            public int apply(int left, int right) {
                if (left > right) {
                    return left;
                }
                return right;
            }
        });
    }

    @Override
    public Integer min() {
        return reduce(Integer.MAX_VALUE, new IntBinaryOperator() {
            @Override
            public int apply(int left, int right) {
                if (left < right) {
                    return left;
                }
                return right;
            }
        });
    }

    @Override
    public long count() {
        return reduce(0, (int i, int val) -> i + 1);
    }

    @Override
    public Integer sum() {
        return reduce(0, (int i, int val) -> i + val);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new IntFilterIterator(iterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new IntMapIterator(iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new IntFlatMapIterator(iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int res = identity;
        while (iterator.hasNext()) {
            res = op.apply(res, iterator.next());
        }
        return res;

    }

    @Override
    public int[] toArray() {
        List<Integer> resList = new ArrayList<Integer>();
        while (iterator.hasNext()) {
            resList.add(iterator.next());
        }
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

}
