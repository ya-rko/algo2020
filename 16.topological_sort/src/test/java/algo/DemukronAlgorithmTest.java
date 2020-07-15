package algo;


import algo.array.FactorArray;
import algo.topologicalsort.DemukronAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class DemukronAlgorithmTest {
    private final FactorArray<FactorArray<Integer>> expectedLevels = new FactorArray<>();
    private final int[][] adjacency = {
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
    };


    @BeforeEach
    void init() {
        FactorArray<Integer> level0 = new FactorArray<Integer>();
        level0.add(4);
        level0.add(7);
        expectedLevels.add(level0);

        FactorArray<Integer> level1 = new FactorArray<Integer>();
        level1.add(1);
        level1.add(8);
        level1.add(9);
        expectedLevels.add(level1);

        FactorArray<Integer> level2 = new FactorArray<Integer>();
        level2.add(0);
        level2.add(6);
        level2.add(13);
        expectedLevels.add(level2);

        FactorArray<Integer> level3 = new FactorArray<Integer>();
        level3.add(5);
        level3.add(12);
        expectedLevels.add(level3);

        FactorArray<Integer> level4 = new FactorArray<Integer>();
        level4.add(3);
        level4.add(10);
        level4.add(11);
        expectedLevels.add(level4);

        FactorArray<Integer> level5 = new FactorArray<Integer>();
        level5.add(2);
        expectedLevels.add(level5);
    }

    @Test
    void demukronAlgorithmTest() throws Exception {



        FactorArray<FactorArray<Integer>> actualLevels = DemukronAlgorithm.run(adjacency);
        for (int i = 0; i < actualLevels.size(); i++) {
            assertArrayEquals(expectedLevels.get(i).toArray(), actualLevels.get(i).toArray());
        }
    }

}
