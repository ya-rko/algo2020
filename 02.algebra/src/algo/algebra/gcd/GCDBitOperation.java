package algo.algebra.gcd;

public class GCDBitOperation extends GCD {
    @Override
    public long calc(long number1, long number2) {
        if (number1 == number2) {
            return number1;
        }

        if (number1 == 0) {
            return number2;
        }

        if (number2 == 0) {
            return number1;
        }

        if ((number1 & 1) == 0) {
            if ((number2 & 1) != 0) {
                return calc(number1 >> 1, number2);
            } else {
                return calc(number1 >> 1, number2 >> 1) << 1;
            }
        }

        if ((number2 & 1) == 0) {
            return calc(number1, number2 >> 1);
        }

        if (number1 > number2) {
            return calc((number1 - number2) >> 1, number2);
        }

        return calc((number2 - number1) >> 1, number1);
    }


    @Override
    public String getDescription() {
        return "Calc GCD: Bit Operation";
    }
}
