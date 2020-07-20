package algo;


import algo.graph.KruskalAlgorithm;
import algo.graph.WeightedEdge;


public class KruskalAlgorithmMain {
    private static final char[] VERTEX_LETTER = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};


    public static void main(String[] args) {
        WeightedEdge[] weightedEdges = {
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

        long[] edges = KruskalAlgorithm.run(weightedEdges);
        printMinimalSkeleton(edges);
    }


    private static void printMinimalSkeleton(long[] edges) {
        System.out.println("Minimal skeleton:");
        for (long v : edges) {
            int vertex1 = (int)(v >> 32);
            int vertex2 = (int)(v & 0xFFFFFFFFL);
            System.out.print(VERTEX_LETTER[vertex1] + "-" + VERTEX_LETTER[vertex2]);
            System.out.println();
        }
    }
}
