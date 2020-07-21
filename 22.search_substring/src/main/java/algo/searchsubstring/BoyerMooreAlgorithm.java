package algo.searchsubstring;


import java.util.Arrays;


public class BoyerMooreAlgorithm implements SearchSubstring {

    @Override
    public int search(String text, String pattern) {
        int[] prefixTable = createPrefixTable(pattern);
        int[] suffixTable = createSuffixTable(pattern);
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

            textIndex += Math.max(patternIndex - prefixTable[text.charAt(textIndex + patternIndex)],
                                  suffixTable[patternIndex +  1]);
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


    private int[] createSuffixTable(String pattern) {
        int[] suffix = new int[pattern.length() + 1];
        Arrays.fill(suffix, pattern.length());
        suffix[pattern.length()] = 1;

        for (int j = pattern.length() - 1; j >= 0 ; j--) {
            for (int at = j; at < pattern.length(); at++) {
                String a = pattern.substring(at);
                for (int i = at - 1; i >= 0; i--) {
                    String b = pattern.substring(i, i + a.length());

                    if (a.equals(b)) {
                        suffix[j] = at - 1;
                        at = pattern.length();
                        break;
                    }
                }
            }    
        }
        return suffix;
    }
}
