package algo.algebra.primenumber;

import algo.tester.ITask;

import java.util.ArrayList;
import java.util.List;

public abstract class PrimeNumber implements ITask {
    private final List<Long> primes = new ArrayList<>();

    abstract boolean isNumberPrime(long maxNumber);
    abstract public String getDescription();


    public long count(int maxNumber) {
        long count = 0;
        for (long i = 2; i <= maxNumber; i++) {
            if (isNumberPrime(i)) {
                count++;
                primes.add(i);
            }
        }
        return count;
    }


    public List<Long> getPrimes() {
        return primes;
    }


    @Override
    public String run(List<String> data) {
        int maxNumber = Integer.parseInt(data.get(0));

        System.out.println(getDescription() + " up to Max number: " + maxNumber);

        long startTime = System.currentTimeMillis();
        long primeCount = count(maxNumber);
        long endTime = System.currentTimeMillis();

        System.out.println("calc duration: " + (endTime - startTime) + " ms");

        return String.valueOf(primeCount);
    }
}
