package algo.graph;


public class KruskalAlgorithm {
    public static long[] run(WeightedEdge[] edges) {
        WeightedGraph graph = new WeightedGraph(edges);
        WeightedEdge[] orderedEdges = graph.getOrderedEdges();

        long[] skeleton = new long[graph.getVertexCount() - 1];
        int skeletonVertexCount = 0;

        int[] parent = new int[graph.getVertexCount()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (WeightedEdge edge : orderedEdges) {
            int vertex1 = edge.getVertex1();
            int vertex2 = edge.getVertex2();

            int root1 = find(vertex1, parent);
            int root2 = find(vertex2, parent);

            if (root1 != root2) {
                skeleton[skeletonVertexCount++] = (long) vertex1 << 32 | (long) vertex2 & 0xFFFFFFFFL;
                union(vertex1, vertex2, parent);
            }
        }
        return skeleton;
    }


    private static int find(int vertex, int[] parent) {
        return (vertex == parent[vertex])
               ? vertex
               : (parent[vertex] = find(parent[vertex], parent));
    }


    private static void union(int vertex1, int vertex2, int[] parent) {
        int root1 = find(vertex1, parent);
        int root2 = find(vertex2, parent);
        if (root1 != root2) {
            parent[root2] = root1;
        }
    }
}
