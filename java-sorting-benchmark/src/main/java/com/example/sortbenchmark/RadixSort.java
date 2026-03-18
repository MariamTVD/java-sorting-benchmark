package com.example.sortbenchmark;

/**
 * LSD radix sort for 32-bit integers.
 *
 * Requirements satisfied:
 * - 4 passes
 * - base 256 (one byte per pass)
 * - supports negative numbers
 *
 * Key idea for negatives:
 * We flip the sign bit using XOR with Integer.MIN_VALUE.
 * This transforms signed integer order into unsigned order while preserving the
 * correct relative ordering needed for sorting.
 */
public final class RadixSort implements IntSorter {

    private static final int RADIX = 256;
    private static final int MASK = RADIX - 1;
    private static final int PASSES = 4;

    @Override
    public void sort(int[] array) {
        int[] output = new int[array.length];
        int[] input = array;
        int[] temp;

        for (int pass = 0; pass < PASSES; pass++) {
            int shift = pass * 8;
            int[] count = new int[RADIX];

            // Count byte frequency for the current pass.
            for (int value : input) {
                int normalized = value ^ Integer.MIN_VALUE;
                int digit = (normalized >>> shift) & MASK;
                count[digit]++;
            }

            // Convert counts into starting positions using prefix sums.
            int total = 0;
            for (int i = 0; i < RADIX; i++) {
                int oldCount = count[i];
                count[i] = total;
                total += oldCount;
            }

            // Stable placement into the output array.
            for (int value : input) {
                int normalized = value ^ Integer.MIN_VALUE;
                int digit = (normalized >>> shift) & MASK;
                output[count[digit]++] = value;
            }

            // Swap input/output references for the next pass.
            temp = input;
            input = output;
            output = temp;
        }

        // After 4 passes, the sorted data may be in the auxiliary array.
        // Copy back only if needed.
        if (input != array) {
            System.arraycopy(input, 0, array, 0, array.length);
        }
    }
}
