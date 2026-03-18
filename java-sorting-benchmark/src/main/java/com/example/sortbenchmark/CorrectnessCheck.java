package com.example.sortbenchmark;

/**
 * Small runner you can execute before benchmarking.
 *
 * This verifies all custom algorithms on all required data distributions.
 * Running this first is a good habit because benchmarks are meaningless if the
 * algorithms are not correct.
 */
public final class CorrectnessCheck {

    private CorrectnessCheck() {
    }

    public static void main(String[] args) {
        int size = 20_000;

        for (DataGenerator.Distribution distribution : DataGenerator.Distribution.values()) {
            int[] original = DataGenerator.generate(distribution, size);
            System.out.println("Checking distribution: " + distribution);

            SortVerifier.verifySorter("BubbleSort", new BubbleSort(), original);
            SortVerifier.verifySorter("QuickSort", new QuickSort(), original);
            SortVerifier.verifySorter("RadixSort", new RadixSort(), original);

            System.out.println();
        }

        System.out.println("All correctness checks passed.");
    }
}
