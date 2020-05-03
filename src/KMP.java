import java.util.Random;
import java.util.Timer;

public class KMP {
    private final int R;       // the radix
    private int[][] dfa;       // the KMP automoton

    private char[] pattern;    // either the character array for the pattern
    private String pat;        // or the pattern string

    private int NUM_COMPARISONS;

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public KMP(String pat) {
        this.R = 256;
        this.pat = pat;

        // build DFA from pattern
        int m = pat.length();
        dfa = new int[R][m];
        dfa[pat.charAt(0)][0] = 1;
        NUM_COMPARISONS++;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases.
            dfa[pat.charAt(j)][j] = j+1;   // Set match case.
            x = dfa[pat.charAt(j)][x];     // Update restart state.
            NUM_COMPARISONS+=2;
        }
    }

    /**
     * Preprocesses the pattern string.
     *
     * @param pattern the pattern string
     * @param R the alphabet size
     */
    public KMP(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // build DFA from pattern
        int m = pattern.length;
        dfa = new int[R][m];
        dfa[pattern[0]][0] = 1;
        NUM_COMPARISONS++;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases.
            dfa[pattern[j]][j] = j+1;      // Set match case.
            x = dfa[pattern[j]][x];        // Update restart state.
            NUM_COMPARISONS+=2;
        }
    }

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param  txt the text string
     * @return the index of the first occurrence of the pattern string
     *         in the text string; N if no such match
     */
    public int search(String txt) {

        // simulate operation of DFA on text
        int m = pat.length();
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            NUM_COMPARISONS++;      //worst-case: n
            j = dfa[txt.charAt(i)][j];
        }
        if (j == m) return i - m;    // found
        return n;                    // not found
    }

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param  text the text string
     * @return the index of the first occurrence of the pattern string
     *         in the text string; N if no such match
     */
    public int search(char[] text) {

        // simulate operation of DFA on text
        int m = pattern.length;
        int n = text.length;
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            NUM_COMPARISONS++; //Figure out which thing to count with this variable (do we count .charAt()? what about a[i]?)
            j = dfa[text[i]][j]; //ISSUE: this is too compact code to show how a smaller alphabet is worse.
        }
        if (j == m) return i - m;    // found
        return n;                    // not found
    }


    /**
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

//        String pat = args[0];
//        String txt = args[1];
    	System.out.println("Enter the pattern: ");

        //String pat = StdIn.readString();
        System.out.println("Enter the text: ");
        //String txt = StdIn.readString();
        String pat = "dsgwadsgz";
        String txt = "adsgwadsxdsgwadsgz";

        char[] pattern = pat.toCharArray();
        char[] text    = txt.toCharArray();

        KMP kmp1 = new KMP(pat);
        int offset1 = kmp1.search(txt);
        KMP kmp2 = new KMP(pattern, 256);
        int offset2 = kmp2.search(text);

        // print results
        System.out.println("text:    " + txt);

        System.out.println("pattern: ");
        for (int i = 0; i < offset1; i++)
            System.out.print(" ");
        System.out.println(pat);

        System.out.println("pattern: ");
        for (int i = 0; i < offset2; i++)
            System.out.print(" ");
        System.out.println(pat);
    }

    /**
     * Gets average number of comparisons KMP uses for a given text_length (n) and alphabet
     * @param text_length the length of text to be searched through
     * @param alphabet the alphabet to be used for constructing the random text and pattern.
     * @return average number of comparisons of KMP
     */
    public static double particular_test(int text_length, int pattern_length, String alphabet) {
        double sum = 0;
        int trial_kount = 1000;
        long time_sum = 0;
        for(int i=0; i<trial_kount; i++) {
            //int random = (int)Math.ceil((Math.random()*text_length)+0.01); //we can change this to a nonrandom, or a range, or something else
            String pattern = Randomizer.numRandomizer(pattern_length, alphabet);
            String text = Randomizer.numRandomizer(text_length, alphabet);
            long startTime = System.nanoTime();
            KMP kmp = new KMP(pattern);
            kmp.search(text);
            long endTime = System.nanoTime();
            time_sum += (endTime-startTime);
            sum+= kmp.NUM_COMPARISONS;
        }
        long avg_time = time_sum/trial_kount;
        double average = sum/trial_kount;
        return average;
    }
}
