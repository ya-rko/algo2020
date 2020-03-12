package algo.algebra.primenumber;

public class PrimeNumberSieveLinear extends PrimeNumber {

    public long count(int maxNumber) {
        long primeCount = 0;
        long[] primes = new long[maxNumber];
        long[] leastPrimeDiv = new long[maxNumber+1];

        int j = 0;
        for (int i = 2; i < maxNumber; i++) {
            if (leastPrimeDiv[i] == 0) {
                leastPrimeDiv[i] = i;
                primes[j++] = i;
                primeCount++;
            }

            int k = 0;
            while (primes[k] <= leastPrimeDiv[i]
                   && primes[k] * i <= maxNumber
                   && primes[k] != 0) {
                leastPrimeDiv[(int) (primes[k] * i)] = primes[k];
                k++;
            }
        }

        return primeCount;
    }


    @Override
    boolean isNumberPrime(long maxNumber) {
        return false;
    }


    @Override
    public String getDescription() {
        return "Prime numbers count: sieve of Eratosthenes Linear";
    }
}

