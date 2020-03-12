package algo.algebra.gcd;

import algo.tester.ITask;
import java.util.List;

public abstract class GCD implements ITask {
    abstract public long calc(long number1, long number2);
    abstract public String getDescription();

    @Override
    public String run(List<String> data) {
        long number1 = Long.parseLong(data.get(0));
        long number2 = Long.parseLong(data.get(1));

        System.out.println(getDescription() + " for numbers: " + number1 + " " + number2);

        long startTime = System.nanoTime();
        long gcd = calc(number1, number2);
        long endTime = System.nanoTime();

        System.out.println("calc duration: " + (endTime - startTime) + " ns");

        return String.valueOf(gcd);
    }
}
