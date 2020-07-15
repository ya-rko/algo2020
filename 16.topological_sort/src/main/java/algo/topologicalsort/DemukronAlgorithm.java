package algo.topologicalsort;


import algo.array.FactorArray;


public class DemukronAlgorithm {
    public static FactorArray<FactorArray<Integer>> run(int[][] adjacency) throws Exception {
        FactorArray<FactorArray<Integer>> levels = new FactorArray<>();
        FactorArray<Integer> nodes = new FactorArray<>();

        for (int i = 0; i < adjacency.length; i++) {
            nodes.add(i);
        }

        int level = 0;
        int[] semiEntries = calcSemiEntries(adjacency);

        while(nodes.size() > 0) {
            FactorArray<Integer> zeroNodes = new FactorArray<>();

            for (int i = 0; i < nodes.size(); i++) {
                int node = nodes.get(i);
                if (semiEntries[node] == 0) {
                    zeroNodes.add(node);
                }
            }

            if (zeroNodes.size() == 0) {
                throw new Exception("Ошибка в работе алгоритма");
            }

            for (int i = 0; i < zeroNodes.size(); i++) {
                int node = zeroNodes.get(i);

                if (levels.get(level) == null) {
                    levels.add(new FactorArray<>());
                }
                levels.get(level).add(node);

                nodes.remove(Integer.valueOf(node));
                recalcSemiEntries(semiEntries, node, adjacency);
            }
            level++;
        }

        return levels;
    }


    private static void recalcSemiEntries(int[] semiEntries, int node, int[][] adjacency) {
        int[] adjNode = adjacency[node];
        for (int j = 0; j < adjNode.length; j++) {
            if (semiEntries[j] != 0) {
                semiEntries[j] -= adjNode[j];
            }
        }
    }


    private static int[] calcSemiEntries(int[][] adjacency) {
        int[] semiEntries = new int[adjacency.length];
        for (int[] adjNode : adjacency) {
            for (int j = 0; j < adjNode.length; j++) {
                semiEntries[j] += adjNode[j];
            }
        }
        return semiEntries;
    }
}