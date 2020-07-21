package algo.searchsubstring;

public class KnuthMorrisPrattAlgorithm implements SearchSubstring {

    @Override
    public int search(String text, String pattern) {
        int[] pi = computePi(pattern);

        int q = 0;
        for (int i = 0; i < text.length(); i++) {
            while (q > 0 && text.charAt(i) != pattern.charAt(q)) {
                q = pi[q - 1];
            }
            if (text.charAt(i) == pattern.charAt(q)) {
                q++;
            }
            if (q == pattern.length()) {
                return i - pattern.length() + 1;
            }
        }
        return  -1;
    }


    private int[] computePi(String pattern) {
        int[] pi = new int[pattern.length()];
        pi[0] = 0;

        for (int i = 1; i < pattern.length(); i++) {
            int q = pi[i - 1];

            while (q > 0 && pattern.charAt(i) != pattern.charAt(q)) {
                q = pi[q - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(q)) {
                q++;
            }
            pi[i] = q;
        }
        return pi;
    }
}
