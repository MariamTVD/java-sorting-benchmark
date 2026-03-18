# Short Analytical Report

## 1. Objective

This assignment compares the performance of multiple sorting algorithms on arrays of 1,000,000 integers using JMH.

Algorithms tested:

- Bubble Sort
- Quick Sort
- LSD Radix Sort
- Arrays.sort(int[])

Input distributions tested:

- Uniform random
- Already sorted ascending
- Reverse sorted descending
- Nearly sorted (about 1% random swaps)

## 2. Correctness verification

All custom algorithms were checked against `Arrays.sort(int[])` using:

- `Arrays.equals()`
- a sortedness check

Result:

> All algorithms produced correct sorted output.

## 3. Benchmark configuration

- Tool: JMH (Java Microbenchmark Harness)
- Warm-up iterations: 3
- Measurement iterations: 5
- Forks: 1
- Mode: Average time
- Output unit: milliseconds
- Array size: 1,000,000

## 4. Results

Fill this table with your real measured values.

| Distribution | Bubble Sort | Quick Sort | Radix Sort | Arrays.sort(int[]) |
|---|---:|---:|---:|---:|
| Uniform random | | | | |
| Sorted ascending | | | | |
| Reverse sorted | | | | |
| Nearly sorted | | | | |

## 5. Analysis

Example points you can discuss:

- Bubble Sort was by far the slowest because it has quadratic time complexity.
- Quick Sort was much faster than Bubble Sort and performed well in general.
- LSD Radix Sort was very competitive because it processes integers digit by digit in linear-style passes.
- `Arrays.sort(int[])` performed strongly because it uses a highly optimized JDK implementation.
- Already sorted and nearly sorted inputs may influence some algorithms differently.
- Reverse sorted input is often more difficult for comparison-based algorithms depending on pivot strategy.

## 6. Conclusion

This experiment shows that algorithm choice has a major impact on performance. For very large datasets, simple quadratic algorithms such as Bubble Sort are not practical, while optimized algorithms such as Quick Sort, Radix Sort, and the JDK's built-in sort are far more suitable.
