package algo.graph;


import algo.array.FactorArray;

import java.util.Arrays;
import java.util.Comparator;


public class WeightedGraph {
    private Graph graph;
    private WeightedEdge[] edges;

    public WeightedGraph(WeightedEdge[] edges) {
        this.edges = edges;
        graph = new Graph(edges2adjacency(edges));
    }


    public int getVertexCount() {
        return graph.size();
    }


    public WeightedEdge[] getAllEdges() {
        return edges;
    }


    public FactorArray<WeightedEdge> getVertexEdges(int vertex) {
        FactorArray<WeightedEdge> vertexEdges = new FactorArray<>();
        for (WeightedEdge edge : edges) {
            if (edge.getVertex1() == vertex || edge.getVertex2() == vertex) {
                vertexEdges.add(edge);
            }
        }
        return vertexEdges;
    }


    public WeightedEdge[] getOrderedEdges() {
        if (edges == null) {
            return null;
        }
        WeightedEdge[] orderedEdges = Arrays.copyOf(edges, edges.length);
        Arrays.sort(orderedEdges, Comparator.comparingDouble(WeightedEdge::getWeight));
        return orderedEdges;
    }


    public FactorArray<Integer> depthFirstSearch(int node) {
        return graph.depthFirstSearch(node);
    }


    private FactorArray<Integer>[] edges2adjacency(WeightedEdge[] edges) {
        WeightedEdge[] orderedEdges = Arrays.copyOf(edges, edges.length);
        Arrays.sort(orderedEdges, Comparator.comparingInt(WeightedEdge::getVertex1));
        int maxVertex1 = orderedEdges[orderedEdges.length - 1].getVertex1();

        Arrays.sort(orderedEdges, Comparator.comparingInt(WeightedEdge::getVertex2));
        int maxVertex2 = orderedEdges[orderedEdges.length - 1].getVertex2();

        @SuppressWarnings("unchecked")
        FactorArray<Integer>[] adjacency = (FactorArray<Integer>[]) new FactorArray[Math.max(maxVertex1, maxVertex2) + 1];

        for (WeightedEdge edge : edges) {
            int vertex1 = edge.getVertex1();
            int vertex2 = edge.getVertex2();

            if (adjacency[vertex1] == null) {
                adjacency[vertex1] = new FactorArray<>();
            }
            adjacency[vertex1].add(vertex2);

            if (adjacency[vertex2] == null) {
                adjacency[vertex2] = new FactorArray<>();
            }
            adjacency[vertex2].add(vertex1);
        }

        return adjacency;
    }
}
