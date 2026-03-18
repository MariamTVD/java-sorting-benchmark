package com.example.sortbenchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Separate benchmark method per algorithm, as required.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
public class SortingBenchmark {

    private static final BubbleSort BUBBLE_SORT = new BubbleSort();
    private static final QuickSort QUICK_SORT = new QuickSort();
    private static final RadixSort RADIX_SORT = new RadixSort();

    @Benchmark
    public int[] bubbleSort(BenchmarkState state) {
        int[] data = state.freshCopy();
        BUBBLE_SORT.sort(data);
        return data;
    }

    @Benchmark
    public int[] quickSort(BenchmarkState state) {
        int[] data = state.freshCopy();
        QUICK_SORT.sort(data);
        return data;
    }

    @Benchmark
    public int[] radixSort(BenchmarkState state) {
        int[] data = state.freshCopy();
        RADIX_SORT.sort(data);
        return data;
    }

    @Benchmark
    public int[] arraysSort(BenchmarkState state) {
        int[] data = state.freshCopy();
        Arrays.sort(data);
        return data;
    }
}
