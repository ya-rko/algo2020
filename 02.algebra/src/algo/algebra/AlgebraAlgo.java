package algo.algebra;

import algo.algebra.gcd.GCDBitOperation;
import algo.algebra.gcd.GCDEvklid;
import algo.algebra.gcd.GCDEvklidRecursive;
import algo.algebra.gcd.GCDEvklidRem;
import algo.algebra.gcd.GCDEvklidRemRecursive;
import algo.algebra.primenumber.PrimeNumberDivIteration;
import algo.algebra.primenumber.PrimeNumberDivIteration2;
import algo.algebra.primenumber.PrimeNumberDivIteration3;
import algo.algebra.primenumber.PrimeNumberDivIteration4;
import algo.algebra.primenumber.PrimeNumberDivIterationArray;
import algo.algebra.primenumber.PrimeNumberSieve;
import algo.algebra.primenumber.PrimeNumberSieveBitArray;
import algo.algebra.primenumber.PrimeNumberSieveLinear;
import algo.tester.ITask;
import algo.tester.Tester;

import java.util.Arrays;
import java.util.List;

public class AlgebraAlgo {
    private static final String GCD_TEST_DATA_PATH = "c:\\Otus\\homework\\src\\algo\\algebra\\TestData\\gcd";
    private static final String PRIME_TEST_DATA_PATH = "c:\\Otus\\homework\\src\\algo\\algebra\\TestData\\prime_number";

    public static void main(String[] args) {

        final List<ITask> GCDTasks = Arrays.asList(
                new GCDEvklid(),
                new GCDEvklidRecursive(),
                new GCDEvklidRem(),
                new GCDEvklidRemRecursive(),
                new GCDBitOperation()
        );

        for (ITask task : GCDTasks) {
            Tester tester = new Tester(task, GCD_TEST_DATA_PATH);
            tester.RunTest();
        }

        final List<ITask> PrimeTasks = Arrays.asList(
                new PrimeNumberDivIteration(),
                new PrimeNumberDivIteration2(),
                new PrimeNumberDivIteration3(),
                new PrimeNumberDivIteration4(),
                new PrimeNumberDivIterationArray(),
                new PrimeNumberSieve(),
                new PrimeNumberSieveBitArray(),
                new PrimeNumberSieveLinear()
        );


        for (ITask task : PrimeTasks) {
            Tester tester = new Tester(task, PRIME_TEST_DATA_PATH);
            tester.RunTest();
        }
    }
}
