# Java Sorting Benchmark Project

This project is a complete starter solution for the assignment **Sorting 1,000,000 Integers in Java using JMH**. It implements:

- Bubble Sort with early exit
- Quick Sort (in-place, median-of-three pivot)
- LSD Radix Sort (4 passes, base-256, supports negative numbers)
- `Arrays.sort(int[])` as the reference sorter
- JMH benchmarks
- Correctness verification against `Arrays.sort`

This project was built from the assignment PDF. fileciteturn0file0

## Project structure

```text
java-sorting-benchmark/
├── pom.xml
├── README.md
├── report-template.md
└── src/
    └── main/
        └── java/
            └── com/example/sortbenchmark/
                ├── BenchmarkState.java
                ├── BubbleSort.java
                ├── CorrectnessCheck.java
                ├── DataGenerator.java
                ├── IntSorter.java
                ├── QuickSort.java
                ├── RadixSort.java
                ├── SortingBenchmark.java
                └── SortVerifier.java
```

## What to install first in VS Code

1. Install **Java JDK 17**.
2. Install **Apache Maven**.
3. Install **Visual Studio Code** extensions:
   - Extension Pack for Java
   - Maven for Java
4. Open this project folder in VS Code.

## Step-by-step instructions

### Step 1: Open the folder

Open VS Code, then choose:

- **File > Open Folder**
- Select the `java-sorting-benchmark` folder

### Step 2: Check Java and Maven

Open the VS Code terminal and run:

```bash
java -version
mvn -version
```

You should see Java 17 and Maven installed.

### Step 3: Compile the project

In the terminal, run:

```bash
mvn clean compile
```

If this works, your project setup is correct.

### Step 4: Run the correctness checker

Before benchmarking, verify that all algorithms sort correctly:

```bash
mvn exec:java -Dexec.mainClass="com.example.sortbenchmark.CorrectnessCheck"
```

If your Maven installation does not run that command because the exec plugin is missing, use this instead:

```bash
mvn -q clean compile
java -cp target/classes com.example.sortbenchmark.CorrectnessCheck
```

### Step 5: Package the JMH benchmark jar

Run:

```bash
mvn clean package
```

This creates:

```text
target/benchmarks.jar
```

### Step 6: Run all benchmarks

Run:

```bash
java -jar target/benchmarks.jar
```

### Step 7: Run only one benchmark if needed

Examples:

```bash
java -jar target/benchmarks.jar quickSort
java -jar target/benchmarks.jar radixSort
java -jar target/benchmarks.jar arraysSort
```

### Step 8: Save benchmark results to a file

```bash
java -jar target/benchmarks.jar > benchmark-results.txt
```

### Step 9: Write your report

Use the included `report-template.md` file. Copy the measured results into the table and write your observations.

---

## Important note about Bubble Sort

Bubble sort on **1,000,000** integers can be extremely slow. That is expected because bubble sort has **O(n²)** time complexity.

If your instructor allows it, you may:

- keep bubble sort in the code for completeness
- mention in the report that it is impractical on very large input sizes
- optionally run it on a smaller size for demonstration only

If your instructor strictly requires 1,000,000 for every algorithm, leave the benchmark as-is and be prepared for a long runtime.

## How the benchmark is designed

- JMH warmup: 3 iterations
- JMH measurement: 5 iterations
- One benchmark method per algorithm
- One base array generated per trial
- Each algorithm sorts a fresh copy of the same input

This is important because comparing algorithms is only fair when they all receive the same data.

## Suggested workflow for your submission

1. Run `CorrectnessCheck`
2. Run JMH benchmarks
3. Copy results into `report-template.md`
4. Export your report to PDF
5. Submit:
   - Java source code
   - JMH benchmark classes
   - short analytical report PDF

## If you want to adjust settings

You can change these in `SortingBenchmark.java`:

- `@Warmup(iterations = 3, time = 1)`
- `@Measurement(iterations = 5, time = 1)`
- `@Fork(1)`

You can change benchmark size in `BenchmarkState.java`:

```java
@Param({"1000000"})
public int size;
```

During debugging, you can temporarily change it to a smaller value such as `10000`, then change it back before final submission.
