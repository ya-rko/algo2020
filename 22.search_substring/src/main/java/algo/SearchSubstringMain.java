package algo;


import algo.searchsubstring.BoyerMooreAlgorithm;
import algo.searchsubstring.BoyerMooreHorspoolAlgorithm;
import algo.searchsubstring.KnuthMorrisPrattAlgorithm;
import algo.searchsubstring.SearchSubstring;


public class SearchSubstringMain {
    public static void main(String[] args) {
        String[][] testText = {
                {"somestring", "string"},
                {"abeccaabadbabbad", "abbad"},
                {"aaaaaaaaaaaaaaabbbbbbbbbbbbbbbccccccccccccccc", "aaaaabbbbccccc"}
        };

        for (String[] testSet : testText) {
            String text = testSet[0];
            String pattern = testSet[1];

            runSearchTest(text, pattern, new BoyerMooreHorspoolAlgorithm());
            runSearchTest(text, pattern, new BoyerMooreAlgorithm());
            runSearchTest(text, pattern, new KnuthMorrisPrattAlgorithm());
        }
    }


    private static void runSearchTest(String text, String pattern, SearchSubstring searchAlgorithm) {
        long startTime = System.nanoTime();
        int pos = searchAlgorithm.search(text, pattern);
        long endTime = System.nanoTime();

        System.out.println(searchAlgorithm.getClass().getSimpleName() + ":");
        System.out.println("Pattern:'" + pattern + "' found in text '" + text + "' at position " + pos);
        System.out.println("Duration: " + (endTime - startTime) + " ns");
        System.out.println();
    }
}