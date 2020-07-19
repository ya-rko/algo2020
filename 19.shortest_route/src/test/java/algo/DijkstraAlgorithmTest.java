package algo;


import algo.graph.DijkstraAlgorithm;
import algo.graph.WeightedEdge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class DijkstraAlgorithmTest {
    private static final WeightedEdge[] weightedEdges = {
            new WeightedEdge(0, 5, 14), // 1 - 6
            new WeightedEdge(0, 2, 9),  // 1 - 3
            new WeightedEdge(0, 1, 7),  // 1 - 2
            new WeightedEdge(1, 2, 10), // 2 - 3
            new WeightedEdge(1, 3, 15), // 2 - 4
            new WeightedEdge(2, 5, 2),  // 3 - 6
            new WeightedEdge(2, 3, 11), // 3 - 4
            new WeightedEdge(5, 4, 9),  // 6 - 5
            new WeightedEdge(4, 3, 6),  // 5 - 4
    };

    private static final int[] expectedShortestRoute = {0, 0, 0, 2, 5, 2};


    @Test
    void dijkstraAlgorithmTest() {
        int[] actualShortestRoutes = DijkstraAlgorithm.run(weightedEdges, 0);
        assertArrayEquals(expectedShortestRoute, actualShortestRoutes);
    }
}
