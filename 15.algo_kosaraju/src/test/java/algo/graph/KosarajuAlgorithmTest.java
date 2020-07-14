package algo.graph;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class KosarajuAlgorithmTest {

    @Test
    void kosarajuAlgorithmTest() {
        int[][] adjacency = {
                {1},
                {2, 4, 5},
                {3, 6},
                {2, 7},
                {0, 5},
                {6},
                {5},
                {3, 6}
        };

        int[] expectedConnectedComponents = {3, 3, 2, 2, 3, 1, 1, 2};
        int[] connectedComponents = KosarajuAlgorithm.run(adjacency);

        Object[] expected = { expectedConnectedComponents };
        Object[] actual = { connectedComponents };

        assertArrayEquals(expected, actual);
    }
}
