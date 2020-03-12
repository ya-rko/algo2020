package algo.algebra.gcd;

public class GCDEvklid extends GCD {
    @Override
    public long calc(long number1, long number2) {
        while (number1 != number2) {
            if (number1 > number2) {
                number1 -= number2;
            } else {
                number2 -= number1;
            }
        }

        return number1;
    }


    @Override
    public String getDescription() {
        return "Calc GCD: Alorithm Evklida";
    }
}
