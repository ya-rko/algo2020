package algo;

import algo.graph.KruskalAlgorithm;
import algo.graph.WeightedEdge;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class KruskalAlgorithmTest {
    private static final WeightedEdge[] weightedEdges = {
            new WeightedEdge(0, 1, 7),  // AB
            new WeightedEdge(1, 2, 8),  // BC
            new WeightedEdge(0, 3, 5),  // AD
            new WeightedEdge(3, 1, 9),  // DB
            new WeightedEdge(1, 4, 7),  // BE
            new WeightedEdge(2, 4, 5),  // CE
            new WeightedEdge(3, 4, 15), // DE
            new WeightedEdge(3, 5, 6),  // DF
            new WeightedEdge(5, 4, 8),  // FE
            new WeightedEdge(5, 6, 11), // FG
            new WeightedEdge(4, 6, 9)   // EG
    };

    private static final long[] expectedSkeleton = {
            3L,
            8589934596L,
            12884901893L,
            1L,
            4294967300L,
            17179869190L
    };

    @Test
    void kruskalAlgorithmTest() {
        long[] actualSkeleton = KruskalAlgorithm.run(weightedEdges);
        assertArrayEquals(expectedSkeleton, actualSkeleton);
    }
}
