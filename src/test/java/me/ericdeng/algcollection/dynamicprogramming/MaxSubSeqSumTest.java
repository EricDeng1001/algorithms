package me.ericdeng.algcollection.dynamicprogramming;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

class MaxSubSeqSumTest {
    private static int[] L1_0 = new int[1_0];
    private static int[] L1_00 = new int[1_00];
    private static int[] L1_000 = new int[1_000];
    private static int[] L1_00_00 = new int[1_00_00];
    private static int[] L1_00_00_00 = new int[1_00_00_00];
    private static int[] L1_000_000_000 = new int[1_000_000_000];

    @Test
    void N3PerformanceTestL1_0() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.naiveN3(L1_0);
    }

    @Test
    void N3PerformanceTestL1_00() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.naiveN3(L1_00);
    }

    @Test
    void N3PerformanceTestL1_00_0() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.naiveN3(L1_000);
    }

    @Test
    void N3PerformanceTestL1_00_00() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.naiveN3(L1_00_00);
    }

    @Test
    void N2PerformanceTestL1_0() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.naiveN2(L1_0);
    }

    @Test
    void N2PerformanceTestL1_00() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.naiveN2(L1_00);
    }

    @Test
    void N2PerformanceTestL1_00_0() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.naiveN2(L1_000);
    }


    @Test
    void N2PerformanceTestL1_00_00() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.naiveN2(L1_00_00);
    }

    @Test
    void N2PerformanceTestL1_00_00_00() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.naiveN2(L1_00_00_00);
    }

    @Test
    void LinearPerformanceTestL1_0() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.linearMethod(L1_0);
    }

    @Test
    void LinearPerformanceTestL1_00() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.linearMethod(L1_00);
    }

    @Test
    void LinearPerformanceTestL1_00_0() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.linearMethod(L1_000);
    }

    @Test
    void LinearPerformanceTestL1_00_00() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.linearMethod(L1_00_00);
    }

    @Test
    void LinearPerformanceTestL1_00_00_00() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.linearMethod(L1_00_00_00);
    }

    @Test
    void LinearPerformanceTestL1_000_000_000() {
        MaxSubSeqSum maxSubSeqSum = new MaxSubSeqSum();
        maxSubSeqSum.linearMethod(L1_000_000_000);
    }
}