package algo.algebra.primenumber;

public class PrimeNumberDivIteration2 extends PrimeNumber {
    @Override
    boolean isNumberPrime(long number) {
        if (number == 2) {
            return true;
        }

        if (number % 2 == 0) {
            return false;
        }

        int divCount = 0;
        for (int i = 3; i <= number; i += 2) {
            if (number % i == 0) {
                divCount++;
            }
        }
        return divCount == 1;
    }


    @Override
    public String getDescription() {
        return "Prime numbers count: searching for divisors Optimization#1";
    }
}
