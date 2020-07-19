package algo;

import algo.graph.WeightedEdge;
import algo.graph.DijkstraAlgorithm;


public class DijkstraAlgorithmMain {
    public static void main(String[] args) {
        runAlgorithmGraph1();
        runAlgorithmGraph2();
    }


    private static void runAlgorithmGraph1() {
        WeightedEdge[] weightedEdges = {
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

        int[] shortestRoutes = DijkstraAlgorithm.run(weightedEdges, 0);
        DijkstraAlgorithm.printShortestRoute(shortestRoutes, 4, false);
    }


    private static void runAlgorithmGraph2() {
        WeightedEdge[] weightedEdges = {
                new WeightedEdge(0, 1, 2), // AB
                new WeightedEdge(0, 2, 3), // AC
                new WeightedEdge(0, 3, 6), // AD
                new WeightedEdge(1, 2, 4), // BC
                new WeightedEdge(1, 4, 9), // BE
                new WeightedEdge(2, 4, 7), // CE
                new WeightedEdge(2, 3, 1), // CD
                new WeightedEdge(2, 5, 6), // CF
                new WeightedEdge(3, 5, 4), // DF
                new WeightedEdge(4, 5, 1), // EF
                new WeightedEdge(4, 6, 5), // EG
                new WeightedEdge(5, 6, 8), // FG
        };

        int[] shortestRoutes = DijkstraAlgorithm.run(weightedEdges, 0);
        DijkstraAlgorithm.printShortestRoute(shortestRoutes, 6, true);
    }
}
