package algo.algebra.gcd;

public class GCDEvklidRemRecursive extends GCD {
    @Override
    public long calc(long number1, long number2) {
        if (number2 == 0) {
            return number1;
        }

        return calc(number2, number1 % number2);
    }


    @Override
    public String getDescription() {
        return "Calc GCD (recursive): Alorithm Evklida (Remainder of division)";
    }
}
