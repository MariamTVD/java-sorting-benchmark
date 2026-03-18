package com.example.sortbenchmark;

import java.util.Arrays;
import java.util.Random;

/**
 * Builds the four input distributions required by the assignment.
 */
public final class DataGenerator {

    /**
     * Assignment size requirement.
     */
    public static final int DEFAULT_SIZE = 1_000_000;

    /**
     * Fixed seed makes experiments repeatable.
     *
     * Each time you run the benchmark, the generated data will be the same,
     * which helps comparison between algorithms.
     */
    private static final long SEED = 123456789L;

    private DataGenerator() {
    }

    public enum Distribution {
        UNIFORM_RANDOM,
        SORTED_ASCENDING,
        REVERSE_SORTED,
        NEARLY_SORTED
    }

    /**
     * Main factory method used by benchmark state.
     */
    public static int[] generate(Distribution distribution, int size) {
        return switch (distribution) {
            case UNIFORM_RANDOM -> uniformRandom(size);
            case SORTED_ASCENDING -> sortedAscending(size);
            case REVERSE_SORTED -> reverseSorted(size);
            case NEARLY_SORTED -> nearlySorted(size);
        };
    }

    /**
     * Uniform random values across the full int range, including negatives.
     */
    public static int[] uniformRandom(int size) {
        Random random = new Random(SEED);
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt();
        }
        return data;
    }

    /**
     * Values 0, 1, 2, ..., size-1.
     */
    public static int[] sortedAscending(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
        return data;
    }

    /**
     * Values size, size-1, ..., 1.
     */
    public static int[] reverseSorted(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = size - i;
        }
        return data;
    }

    /**
     * Start from sorted data, then perform about 1% random swaps.
     */
    public static int[] nearlySorted(int size) {
        int[] data = sortedAscending(size);
        Random random = new Random(SEED);

        int swaps = Math.max(1, size / 100);
        for (int i = 0; i < swaps; i++) {
            int first = random.nextInt(size);
            int second = random.nextInt(size);
            swap(data, first, second);
        }
        return data;
    }

    /**
     * Defensive copy helper so benchmark code stays clean and readable.
     */
    public static int[] copy(int[] source) {
        return Arrays.copyOf(source, source.length);
    }

    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
