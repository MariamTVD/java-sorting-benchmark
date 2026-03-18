package com.example.sortbenchmark;

/**
 * In-place quick sort for int[].
 *
 * Pivot strategy used here: median-of-three
 * (first element, middle element, last element).
 *
 * Why this strategy?
 * It is simple, avoids some bad cases compared with always using the first
 * element, and is easy to explain in a report.
 */
public final class QuickSort implements IntSorter {

    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivotIndex = partition(array, low, high);

        quickSort(array, low, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, high);
    }

    /**
     * Rearranges the array so values <= pivot go left and values > pivot go right.
     * Returns the pivot's final location.
     */
    private int partition(int[] array, int low, int high) {
        int pivotIndex = medianOfThree(array, low, high);
        int pivotValue = array[pivotIndex];

        // Move pivot to the end so partitioning logic is simpler.
        swap(array, pivotIndex, high);

        int storeIndex = low;
        for (int i = low; i < high; i++) {
            if (array[i] <= pivotValue) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }

        // Put pivot into its final sorted position.
        swap(array, storeIndex, high);
        return storeIndex;
    }

    /**
     * Chooses the median value among first/middle/last and returns its index.
     */
    private int medianOfThree(int[] array, int low, int high) {
        int mid = low + (high - low) / 2;

        int a = array[low];
        int b = array[mid];
        int c = array[high];

        if ((a <= b && b <= c) || (c <= b && b <= a)) {
            return mid;
        }
        if ((b <= a && a <= c) || (c <= a && a <= b)) {
            return low;
        }
        return high;
    }

    private void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
