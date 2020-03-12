package algo.algebra.primenumber;

public class PrimeNumberDivIterationArray extends PrimeNumber {
    @Override
    boolean isNumberPrime(long number) {
        if (number == 2) {
            return true;
        }

        if (number % 2 == 0) {
            return false;
        }

        for (int i = 1; Math.pow(getPrimes().get(i-1), 2) <= number; i++) {
            if (number % getPrimes().get(i-1) == 0) {
                return false;
            }
        }
        return true;
    }


    @Override
    public String getDescription() {
        return "Prime numbers count: searching for divisors with Array";
    }
}
