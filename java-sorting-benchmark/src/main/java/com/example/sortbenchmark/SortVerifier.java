package com.example.sortbenchmark;

import java.util.Arrays;

/**
 * Helper methods requested by the assignment.
 *
 * We use this class to confirm that every custom algorithm produces the same
 * output as Arrays.sort(int[]).
 */
public final class SortVerifier {

    private SortVerifier() {
        // Utility class: prevent accidental construction.
    }

    /**
     * Returns true only if every item is <= the next item.
     */
    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares a sorted result against Java's trusted reference implementation.
     */
    public static void assertCorrect(int[] original, int[] actual) {
        int[] expected = Arrays.copyOf(original, original.length);
        Arrays.sort(expected);

        if (!isSorted(actual)) {
            throw new AssertionError("Array is not sorted");
        }

        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError("Sorted result differs from reference output");
        }
    }

    /**
     * Convenience method for quickly verifying any algorithm object.
     */
    public static void verifySorter(String name, IntSorter sorter, int[] original) {
        int[] workingCopy = Arrays.copyOf(original, original.length);
        sorter.sort(workingCopy);
        assertCorrect(original, workingCopy);
        System.out.println(name + " passed correctness verification.");
    }
}
