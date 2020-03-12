package algo.algebra.gcd;

public class GCDEvklidRem extends GCD {
    @Override
    public long calc(long number1, long number2) {
        while (number1 != 0 && number2 != 0) {
            if (number1 > number2) {
                number1 %= number2;
            } else {
                number2 %= number1;
            }
        }

        return number1 == 0
                ? number2
                : number1;
    }


    @Override
    public String getDescription() {
        return "Calc GCD: Alorithm Evklida (Remainder of division)";
    }
}
