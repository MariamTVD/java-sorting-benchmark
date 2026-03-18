package com.example.sortbenchmark;

/**
 * Classic bubble sort with the early-exit optimization requested.
 *
 * Important note:
 * Bubble sort on 1,000,000 elements is extremely slow (quadratic time).
 * It is included because the assignment asks for it as a baseline, but in a
 * real benchmark it may take a very long time to finish.
 */
public final class BubbleSort implements IntSorter {

    @Override
    public void sort(int[] array) {
        int n = array.length;

        // After each outer pass, the largest remaining element has "bubbled"
        // to the end, so the effective unsorted part becomes smaller.
        for (int end = n - 1; end > 0; end--) {
            boolean swapped = false;

            for (int i = 0; i < end; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }

            // Early exit: if a full pass made no swap, the array is already sorted.
            if (!swapped) {
                return;
            }
        }
    }
}
