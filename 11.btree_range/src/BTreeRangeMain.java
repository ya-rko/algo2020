import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;


public class BTreeRangeMain {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("sum.in"));
        if (input.size() == 0) {
            return;
        }

        FileWriter writer = new FileWriter("sum.out");

        String[] parameters = input.get(0).split(" ");
        int elementCount = Integer.parseInt(parameters[0]);
        int queryCount = Integer.parseInt(parameters[1]);

        BTreeRange range = new BTreeRange(elementCount);
        for (int i = 1; i <= queryCount; i++) {
            String[] queryParam = input.get(i).split(" ");
            String queryType = queryParam[0];
            int operand1 = Integer.parseInt(queryParam[1]);
            int operand2 = Integer.parseInt(queryParam[2]);

            switch (queryType) {
                case "A":
                    range.assign(operand1, operand2);
                    break;
                case "Q":
                    writer.write(range.query(operand1, operand2) + "\n");
                    break;
            }
        }
        writer.close();
    }


    private static final class BTreeRange {
        private static final BiFunction<Long, Long, Long> FUNC = Math::addExact;
        private static final int DEFAULT_VALUE = 0;
        private long[] elements;


        public BTreeRange(int elementCount) {
            int size = 2 * (1 << (int)(Math.log(elementCount - 1) / Math.log(2) + 1));
            elements = new long[size];

            Arrays.fill(elements, DEFAULT_VALUE);
        }


        public void assign(int index, int value) {
            if (index < 0 || index > elements.length - 1) {
                return;
            }
            update(index, value, FUNC);
        }


        public long query(int left, int right) {
            return calcRangeFunc(left, right, FUNC);
        }


        private long calcRangeFunc(int left, int rigth, BiFunction<Long, Long, Long> func) {
            long funcResult = DEFAULT_VALUE;
            int startElement = elements.length / 2;
            left += startElement - 1;
            rigth += startElement - 1;
            while (left <= rigth) {
                if (left % 2 != 0) {
                    funcResult = func.apply(funcResult, elements[left]);
                }
                if (rigth % 2 == 0) {
                    funcResult = func.apply(funcResult, elements[rigth]);
                }

                left = (left + 1) / 2;
                rigth = (rigth - 1) / 2;
            }

            return funcResult;
        }


        private void update(int index, int value, BiFunction<Long, Long, Long> func) {
            int startElement = elements.length / 2;
            index += startElement - 1;
            elements[index] = value;
            while((index /= 2) > 0) {
                elements[index] = func.apply(elements[2 * index], elements[(2 * index) + 1]);
            }
        }
    }
}