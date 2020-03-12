package algo.algebra.primenumber;

import java.util.BitSet;

public class PrimeNumberSieveBitArray extends PrimeNumber {

    public long count(int maxNumber) {
        BitSet numbers = new BitSet(maxNumber-1);
        numbers.set(0, 1, true);

        int i = 1;
        while (Math.pow(i++, 2) < maxNumber) {
            if (numbers.get(i)) {
                continue;
            }

            for (int j = (int)Math.pow(i, 2); j < maxNumber; j += i) {
                numbers.set(j);
            }
        }

        int notPrimeNumberCount = numbers.cardinality();
        return (maxNumber - 1) - notPrimeNumberCount;
    }
    

    @Override
    boolean isNumberPrime(long maxNumber) {
        return false;
    }


    @Override
    public String getDescription() {
        return "Prime numbers count: sieve of Eratosthenes with Bit Array";
    }
}
