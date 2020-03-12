package algo.algebra.gcd;

public class GCDEvklidRecursive extends GCD {
    @Override
    public long calc(long number1, long number2) {
        if (number1 == number2) {
            return number1;
        }

        if (number1 > number2) {
            number1 -= number2;
        } else {
            number2 -= number1;
        }

        return calc(number1, number2);
    }


    @Override
    public String getDescription() {
        return "Calc GCD (recursive): Alorithm Evklida";
    }
}
