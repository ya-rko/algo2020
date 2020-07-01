package algo.tester;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Tester {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private final ITask task;
    private final String path;

    public Tester(ITask task, String path) {
        this.task = task;
        this.path = path;
    }

    public void RunTest() {
        int i = 0;

        while (true) {
            String inFile = path + "\\test." + i + ".in";
            String outFile = path + "\\test." + i + ".out";

            if (!Files.exists(Paths.get(inFile)) || !Files.exists(Paths.get(outFile))) {
                break;
            }

            System.out.println("Test #" + i + ": BEGIN");
            boolean result = RunTest(inFile, outFile);
            System.out.println(result
                               ? ANSI_GREEN + "TEST RESULT: " + result + ANSI_RESET
                               : ANSI_RED + "TEST RESULT: " + result + ANSI_RESET);
            System.out.println("Test #" + i + ": END\n");
            i++;
        }
    }

    private boolean RunTest(String inFile, String outFile) {
        try {
            List<String> input = Files.readAllLines(Paths.get(inFile));
            List<String> expect = Files.readAllLines(Paths.get(outFile));
            List<String> actual = task.run(input);
            System.out.println("actual result: " + actual + " (expected result: " + expect + ") ");
            return expect.equals(actual);
        } catch (Throwable e) {
            System.out.println("Exception: " + e);
            return false;
        }
    }
}
