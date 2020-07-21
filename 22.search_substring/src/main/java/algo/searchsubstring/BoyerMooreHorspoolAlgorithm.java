package algo.searchsubstring;


import java.util.Arrays;


public class BoyerMooreHorspoolAlgorithm implements SearchSubstring {

    @Override
    public int search(String text, String pattern) {
        int[] prefixTable = createPrefixTable(pattern);
        int last = pattern.length() - 1;
        int textIndex = 0;

        while (textIndex < text.length() - last) {
            int patternIndex = last;

            while (patternIndex >= 0
                   && text.charAt(textIndex + patternIndex) == pattern.charAt(patternIndex)) {
                patternIndex--;
            }
            if (patternIndex == -1) {
                return textIndex;
            }

            textIndex += last - prefixTable[text.charAt(textIndex + last)];
        }

        return -1;
    }


    private int[] createPrefixTable(String pattern) {
        int[] prefix = new int[128];
        Arrays.fill(prefix, -1);
        for (int i = 0; i < pattern.length() - 1; i++) {
            prefix[pattern.charAt(i)] = i;
        }
        return prefix;
    }
}
