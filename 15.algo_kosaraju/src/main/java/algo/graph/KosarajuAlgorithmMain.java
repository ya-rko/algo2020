package algo.graph;


import java.util.Arrays;


public class KosarajuAlgorithmMain {
        public static void main(String[] args) {
            int[][] adjacency = {
                    {1},
                    {2, 4, 5},
                    {3, 6},
                    {2, 7},
                    {0, 5},
                    {6},
                    {5},
                    {3, 6}
            };

            int[] connectedComponents = KosarajuAlgorithm.run(adjacency);
            int maxIndex = Arrays.stream(connectedComponents).max().getAsInt();

            for (int i = 1; i <= maxIndex; i++) {
                System.out.println("Сonnected сomponent #" + i + ":");

                for (int c = 0; c < connectedComponents.length; c++) {
                    if (connectedComponents[c] == i) {
                        System.out.print(c + " ");
                    }
                }
                System.out.println();
            }
        }
}