package algo.algebra.primenumber;


public class PrimeNumberSieve extends PrimeNumber {

    public long count(int maxNumber) {
        long count = 0;
        boolean[] numbers = new boolean[maxNumber];

        int i = 1;
        while (i*i++ < maxNumber) {
            if (numbers[i]) {
                continue;
            }

            for (int j = i*i; j < numbers.length; j += i) {
                numbers[j] = true;
            }
        }

        for (int k = 2; k < maxNumber; k++) {
            if (!numbers[k]) {
                count++;
            }
        }

        return count;
    }


    @Override
    boolean isNumberPrime(long maxNumber) {
        return false;
    }


    @Override
    public String getDescription() {
        return "Prime numbers count: sieve of Eratosthenes";
    }
}
