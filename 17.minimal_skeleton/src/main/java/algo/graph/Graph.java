package algo.graph;


import algo.array.FactorArray;

import java.util.stream.IntStream;


public class Graph {
    private FactorArray<Integer>[] adjacency;


    public Graph(FactorArray<Integer>[] adjacency) {
        this.adjacency = adjacency;
    }


    public Graph(int[][] inputAdjacency) {
        adjacency = (FactorArray<Integer>[]) new FactorArray[inputAdjacency.length];

        for (int i = 0; i < inputAdjacency.length; i++) {
            FactorArray<Integer> nodes = new FactorArray<>();
            for (int inputNode : inputAdjacency[i]) {
                nodes.add(inputNode);
            }
            adjacency[i] = nodes;
        }
    }


    public int size() {
        return adjacency.length;
    }


    public int[] getNodes() {
        return IntStream.range(0, adjacency.length).toArray();
    }


    public FactorArray<Integer>[] invert() {
        @SuppressWarnings("unchecked")
        FactorArray<Integer>[] invertedAdjacency = (FactorArray<Integer>[]) new FactorArray[adjacency.length];

        for (int i = 0; i < adjacency.length; i++) {
            FactorArray<Integer> nodes = adjacency[i];
            if (nodes == null) {
                continue;
            }

            for (int j = 0; j < nodes.size(); j++) {
                Integer node = nodes.get(j);
                FactorArray<Integer> invertedNodes;
                if ((invertedNodes = invertedAdjacency[node]) == null) {
                    invertedNodes = new FactorArray<>();
                    invertedAdjacency[node] = invertedNodes;
                }
                invertedNodes.add(i);
            }
        }

        return invertedAdjacency;
    }


    public FactorArray<Integer> depthFirstSearch(int node) {
        return depthFirstSearch(node, null);
    }


    public FactorArray<Integer> depthFirstSearch(int node, boolean[] inputIsVisitedNodes) {
        final FactorArray<Integer> path = new FactorArray<>();
        final boolean[] isVisitedNodes;

        if (inputIsVisitedNodes == null) {
            isVisitedNodes = new boolean[adjacency.length];
        } else {
            isVisitedNodes = inputIsVisitedNodes;
        }

        class DepthFirstSearch {
            public void runDepthFirstSearch(int node) {
                isVisitedNodes[node] = true;

                FactorArray<Integer> adjacentNodes = adjacency[node];
                if (adjacentNodes == null) {
                    return;
                }

                for (int i = 0; i < adjacentNodes.size(); i++) {
                    int adjacentNode = adjacentNodes.get(i);
                    if (!isVisitedNodes[adjacentNode]) {
                        runDepthFirstSearch(adjacentNode);
                    }
                }
                path.add(node);
            }
        }

        new DepthFirstSearch().runDepthFirstSearch(node);
        return path;
    }
}