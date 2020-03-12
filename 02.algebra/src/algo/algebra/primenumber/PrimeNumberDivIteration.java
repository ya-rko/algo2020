package algo.algebra.primenumber;

public class PrimeNumberDivIteration extends PrimeNumber {
    @Override
    boolean isNumberPrime(long number) {
        int divCount = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                divCount++;
            }
        }
        return divCount == 2;
    }


    @Override
    public String getDescription() {
        return "Prime numbers count: searching for divisors";
    }
}
