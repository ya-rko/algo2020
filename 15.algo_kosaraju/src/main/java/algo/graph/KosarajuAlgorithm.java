package algo.graph;

import algo.array.FactorArray;

public class KosarajuAlgorithm {
    public static int[] run(int[][] adjacency) {
        Graph graph = new Graph(adjacency);
        Graph invertedGraph = new Graph(graph.invert());

        boolean[] isVisitedNodes = new boolean[invertedGraph.size()];
        FactorArray<Integer> visitedNodes = new FactorArray<>();

        int[] nodes = invertedGraph.getNodes();
        for (int i = 0; i < nodes.length; i++) {
            if (isVisitedNodes[i]) {
                continue;
            }

            FactorArray<Integer> path = invertedGraph.depthFirstSearch(nodes[i], isVisitedNodes);
            for (int p = 0; p < path.size(); p++) {
                int node = path.get(p);
                isVisitedNodes[node] = true;
                visitedNodes.add(node);
            }
        }

        isVisitedNodes = new boolean[graph.size()];
        int[] connectedComponents = new int[graph.size()];
        int connectedComponentIndex = 1;

        for (int i = visitedNodes.size() - 1; i >= 0; i--) {
            if (isVisitedNodes[i]) {
                continue;
            }

            int node = visitedNodes.get(i);
            FactorArray<Integer> path = graph.depthFirstSearch(node, isVisitedNodes);
            for (int p = 0; p < path.size(); p++) {
                connectedComponents[path.get(p)] = connectedComponentIndex;
            }
            connectedComponentIndex++;
        }

        return connectedComponents;
    }
}