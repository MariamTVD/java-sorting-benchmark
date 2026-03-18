package com.example.sortbenchmark;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

/**
 * JMH state object.
 *
 * A single benchmark run chooses one distribution, generates the base array once,
 * and each benchmark method receives a fresh copy so every algorithm sorts the
 * exact same input values.
 */
@State(Scope.Thread)
public class BenchmarkState {

    @Param({"UNIFORM_RANDOM", "SORTED_ASCENDING", "REVERSE_SORTED", "NEARLY_SORTED"})
    public String distributionName;

    /**
     * You can reduce this temporarily during debugging, but the assignment asks
     * for 1,000,000 integers.
     */
    @Param({"1000000"})
    public int size;

    public int[] original;

    @Setup(Level.Trial)
    public void setUpTrial() {
        DataGenerator.Distribution distribution = DataGenerator.Distribution.valueOf(distributionName);
        original = DataGenerator.generate(distribution, size);
    }

    /**
     * Returns a brand-new copy so every benchmark method sorts identical input.
     */
    public int[] freshCopy() {
        return DataGenerator.copy(original);
    }
}
