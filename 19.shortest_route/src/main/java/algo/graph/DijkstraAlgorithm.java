package algo.graph;


import algo.array.FactorArray;

import java.util.Arrays;


public class DijkstraAlgorithm {
    private static final double VISITED = -1;

    public static int[] run(WeightedEdge[] edges, int startVertex) {
        WeightedGraph graph = new WeightedGraph(edges);
        if (graph.getVertexCount() == 0) {
            return new int[0];
        }

        int[] shortestRoute = new int[graph.getVertexCount()];
        double[] routeWeight = new double[graph.getVertexCount()];
        Arrays.fill(routeWeight, Double.MAX_VALUE);
        int routeWeightSize = routeWeight.length;

        routeWeight[startVertex] = 0;

        while (routeWeightSize-- > 0) {
            int vertexWithMinRoute = getVertexWithMinRoute(routeWeight);
            FactorArray<WeightedEdge> vertexEdges = graph.getVertexEdges(vertexWithMinRoute);

            for (int i = 0; i < vertexEdges.size(); i++) {
                WeightedEdge edge = vertexEdges.get(i);
                int secondVertex = edge.getVertex1() == vertexWithMinRoute ? edge.getVertex2() : edge.getVertex1();

                if (routeWeight[secondVertex] != VISITED) {
                    if (routeWeight[vertexWithMinRoute] + edge.getWeight() < routeWeight[secondVertex]) {
                        routeWeight[secondVertex] = routeWeight[vertexWithMinRoute] + edge.getWeight();
                        shortestRoute[secondVertex] = vertexWithMinRoute;
                    }
                }
            }
            routeWeight[vertexWithMinRoute] = VISITED;
        }

        return shortestRoute;
    }


    public static void printShortestRoute(int[] shortestRoute, int endVertex, boolean isVertexChar) {
        final char[] VERTEX_LETTER = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        class TravelShortestRoute {
            public int moveBackAndPrint(int[] shortestRoute, int vertex) {
                if (vertex == shortestRoute[vertex]) {
                    return vertex;
                }
                int parent = moveBackAndPrint(shortestRoute, shortestRoute[vertex]);
                System.out.print((isVertexChar ? VERTEX_LETTER[parent] : String.valueOf(parent)) + " - ");
                return vertex;
            }
        }

        System.out.println("Shortest route to '" + (isVertexChar ? VERTEX_LETTER[endVertex] : String.valueOf(endVertex)) + "':");
        new TravelShortestRoute().moveBackAndPrint(shortestRoute, endVertex);
        System.out.print((isVertexChar ? VERTEX_LETTER[endVertex] : String.valueOf(endVertex)) + "\n");
    }


    private static int getVertexWithMinRoute(double[] routeWeight) {
        int vertex = -1;
        double minWeight = Double.MAX_VALUE;

        for (int i = 0; i < routeWeight.length; i++) {
            if (routeWeight[i] != VISITED && routeWeight[i] < minWeight) {
                vertex = i;
                minWeight = routeWeight[i];
            }
        }

        return vertex;
    }
}