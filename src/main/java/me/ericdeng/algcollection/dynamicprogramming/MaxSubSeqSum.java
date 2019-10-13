package me.ericdeng.algcollection.dynamicprogramming;

import java.util.Iterator;

public class MaxSubSeqSum {

    private static final EdgeDetector nonPositiveDetector = sum -> sum <= 0;

    private static final EdgeDetector negativeDetector = sum -> sum < 0;

    private static final Counter plusCounter = new PlusCounter();

    private static final Counter subCounter = new SubCounter();

    private Counter counter = plusCounter;

    private EdgeDetector edgeDetector = nonPositiveDetector;

    public MaxSubSeqSum maximizeLength(boolean b) {
        if (b) {
            edgeDetector = negativeDetector;
        } else {
            edgeDetector = nonPositiveDetector;
        }
        return this;
    }

    public MaxSubSeqSum leftMost(boolean b) {
        if (b) {
            counter = subCounter;
        } else {
            counter = plusCounter;
        }
        return this;
    }

    public Result naiveN3(int[] integers) {
        int max = 0;
        int begin = 0;
        int end = 0;
        for (int i = 0; i < integers.length; i++) {
            for (int j = i; j < integers.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += integers[k];
                }
                if (sum > max) {
                    max = sum;
                    begin = i;
                    end = j;
                }
            }
        }
        return new Result(begin, end, max);
    }

    public Result naiveN2(int[] integers) {
        int max = 0;
        int begin = 0;
        int end = 0;
        for (int i = 0; i < integers.length; i++) {
            int sum = 0;
            for (int j = i; j < integers.length; j++) {
                sum += integers[j];
                if (sum > max) {
                    max = sum;
                    begin = i;
                    end = j;
                }
            }
        }
        return new Result(begin, end, max);
    }

    public Result linearMethod(int[] integers) {
        int sum = 0;
        int max = 0;
        int begin = 0;
        int end = 0;
        counter.init(integers.length);
        for (var i : counter) {
            if (edgeDetector.isEdge(sum)) {
                if (integers[i] > 0) {
                    sum = integers[i];
                    if (sum > max) {
                        begin = i;
                        max = sum;
                    }
                }
            } else {
                sum += integers[i];
                if (sum > max) {
                    end = i;
                    max = sum;
                }
            }
        }
        return new Result(begin, end, max);
    }

    private interface EdgeDetector {

        boolean isEdge(int sum);

    }

    private abstract static class Counter implements Iterable<Integer> {

        int max;

        int i;

        public void init(int max) {
            this.max = max;
        }

    }

    private static class PlusCounter extends Counter {

        @Override
        public Iterator<Integer> iterator() {
            return new Iter();
        }

        private class Iter implements Iterator<Integer> {

            @Override
            public boolean hasNext() {
                return i < max;
            }

            @Override
            public Integer next() {
                return i++;
            }

        }

    }

    private static class SubCounter extends Counter {

        @Override
        public Iterator<Integer> iterator() {
            return new Iter();
        }

        private class Iter implements Iterator<Integer> {

            @Override
            public boolean hasNext() {
                return i >= 0;
            }

            @Override
            public Integer next() {
                return i--;
            }

        }

    }

    public static class Result {

        private int begin;

        private int end;

        private int max;

        Result(int begin, int end, int max) {
            this.begin = begin;
            this.end = end;
            this.max = max;
        }

        public int getBegin() {
            return begin;
        }

        public int getEnd() {
            return end;
        }

        public int getMax() {
            return max;
        }

    }

}
