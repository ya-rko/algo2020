package algo.algebra.primenumber;

public class PrimeNumberDivIteration4 extends PrimeNumber {
    @Override
    boolean isNumberPrime(long number) {
        if (number == 2) {
            return true;
        }

        if (number % 2 == 0) {
            return false;
        }

        for (int i = 3; i*i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


    @Override
    public String getDescription() {
        return "Prime numbers count: searching for divisors Optimization#3";
    }
}
