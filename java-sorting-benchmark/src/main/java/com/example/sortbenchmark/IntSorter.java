package com.example.sortbenchmark;

/**
 * Tiny interface used so all algorithms can be verified in the same way.
 */
@FunctionalInterface
public interface IntSorter {
    void sort(int[] array);
}
