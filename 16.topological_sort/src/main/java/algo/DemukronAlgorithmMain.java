package algo;


import algo.array.FactorArray;
import algo.topologicalsort.DemukronAlgorithm;


public class DemukronAlgorithmMain {
    public static void main(String[] args) throws Exception {
        int[][] adjacency = {
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        FactorArray<FactorArray<Integer>> levels = DemukronAlgorithm.run(adjacency);
        printLevelNodes(levels);
    }


    private static void printLevelNodes(FactorArray<FactorArray<Integer>> levels) {
        for (int i = 0; i < levels.size(); i++) {
            System.out.print("Level #" + i + ": ");
            FactorArray<Integer> levelNodes = levels.get(i);

            for (int j = 0; j < levelNodes.size(); j++) {
                System.out.print((levelNodes.get(j) + 1) + " ");
            }
            System.out.println();
        }
    }
}