public class Main {
    public static void main(String[] args) {
        test_suites();
    }


    private static void test_suites() {
        n_test_suite();
        m_test_suite();
        alphabet_test_suite();
    }

    //Below are the three primary test suites. Included with them are comments discussing what we can do with the data
    //IMPORTANT TERMINOLOGY: N = text size. M = pattern size.

    /**
     * Hypothesis: different n sizes will cause variation in time and # of comparisons when holding M and alphabet constant
     * Additional questions to ask based on data collected from this test suite:
     *      For both search algorithms, what happens when we have a larger N? smaller N?
     *      For both search algorithms, what is the relationship as N increases while holding M and alphabet constant?
     *          i.e., does it change on a O(N) scale? O(logN)?
     *      Is one particular search algorithm more sensitive than the other one to text size? Which one do you want to use with a larger/smaller N?
     *          Furthermore, does one search algorithm work better until N reaches a large enough size?
     *      When answering these questions, don't look at exact values. Look at changes between values, and difference between
     *      numbers based on the algorithm (as number variations will occur due to different Ms and Alphabets)
     * Testing:
     *      We run tests with several large N values (to reduce noise) AND small N values (to answer one of the above questions)
     *      We also try three different M values and alphabets (for 9 total combinations), to make sure that the data is credible
     *          for the large N tests, the M value is large to reduce noise; for the small N tests, M value is also small
     */
    private static void n_test_suite() {
        System.out.println("BEGINNING N TEST SUITE");
        System.out.println("------------------------------------------");
        final int trial_count = 100;
        final int[] large_m_values = {(int)Math.pow(2,6), (int)Math.pow(2,10), (int)Math.pow(2,13)};
        final String[] alphabets = {"abcdefghijklmnopqrstuvwxyz", "01", "actg"};
        System.out.println("Comparison testing for 9 (M,Alphabet) pairs for varying LARGE N's");
        System.out.println("------------------------------------------");
        for(int m: large_m_values) {
            for(String a: alphabets) {
                comparisons_vary_n(m,a,trial_count, true);
            }
        }
        final int[] small_m_values = {2, (int)Math.pow(2,3), (int)Math.pow(2,4)};
        System.out.println("Comparison testing for 9 (M,Alphabet) pairs for varying SMALL N's");
        System.out.println("------------------------------------------");
        for(int m: small_m_values) {
            for(String a: alphabets) {
                comparisons_vary_n(m,a,trial_count, false);
            }
        }
        System.out.println("Time testing for 9 (M,Alphabet) pairs for varying LARGE N's");
        System.out.println("------------------------------------------");
        for(int m: large_m_values) {
            for(String a: alphabets) {
                time_vary_n(m,a,trial_count, true);
            }
        }
        System.out.println("Time testing for 9 (M,Alphabet) pairs for varying SMALL N's");
        System.out.println("------------------------------------------");
        for(int m: small_m_values) {
            for(String a: alphabets) {
                time_vary_n(m,a,trial_count, false);
            }
        }
    }

    /**
     * Hypothesis: different m sizes will cause variation in time and # of comparisons when holding N and alphabet constant
     * Additional questions to ask based on data collected from this test suite:
     *      For both search algorithms, what happens when we have a larger M? smaller M?
     *      For both search algorithms, what is the relationship as M increases while holding N and alphabet constant?
     *          i.e., does it change on a O(N) scale? O(logN)?
     *      Is one particular search algorithm more sensitive than the other one to pattern size? Which one do you want to use with a larger/smaller N?
     *          Furthermore, does one search algorithm work better until M reaches a large enough size?
     *      When answering these questions, don't look at exact values. Look at changes between values, and difference between
     *      numbers based on the algorithm (as number variations will occur due to different N and Alphabets)
     * Testing:
     *      We run tests with several large M values (to reduce noise) AND small M values (to answer one of the above questions)
     *      We also try three different N values and alphabets (for 9 total combinations), to make sure that the data is credible
     *          for the large M tests, the N value is large to reduce noise; for the small M tests, N value is also small
     */
    private static void m_test_suite() {
        System.out.println("BEGINNING M TEST SUITE");
        System.out.println("------------------------------------------");
        final int trial_count = 100;
        final int[] large_n_values = {(int)Math.pow(2,14), (int)Math.pow(2,18), (int)Math.pow(2,22)};
        final String[] alphabets = {"abcdefghijklmnopqrstuvwxyz", "01", "actg"};
        System.out.println("Comparison testing for 9 (N,Alphabet) pairs for varying LARGE M's");
        System.out.println("------------------------------------------");
        for(int n: large_n_values) {
            for(String a: alphabets) {
                comparisons_vary_m(n,a,trial_count, true);
            }
        }
        final int[] small_n_values = {(int)Math.pow(2,6), (int)Math.pow(2,9), (int)Math.pow(2,12)};
        System.out.println("Comparison testing for 9 (N,Alphabet) pairs for varying SMALL M's");
        System.out.println("------------------------------------------");
        for(int n: small_n_values) {
            for(String a: alphabets) {
                comparisons_vary_m(n,a,trial_count, false);
            }
        }
        System.out.println("Time testing for 9 (N,Alphabet) pairs for varying LARGE M's");
        System.out.println("------------------------------------------");
        for(int n: large_n_values) {
            for(String a: alphabets) {
                time_vary_m(n,a,trial_count, true);
            }
        }
        System.out.println("Time testing for 9 (N,Alphabet) pairs for varying SMALL M's");
        System.out.println("------------------------------------------");
        for(int n: small_n_values) {
            for(String a: alphabets) {
                time_vary_m(n,a,trial_count, false);
            }
        }
    }

    /**
     * Hypothesis: different alphabet sizes will cause variations in time and # of comparisons when holding N and M constant
     * Additional questions to ask based on data collected from this test suite:
     *      For both search algorithms, what happens when we have a larger alphabet? smaller alphabet?
     *      For both search algorithms, what is the relationship as alphabet size increases while holding M and N constant?
     *          i.e., does it change on a O(N) scale? O(logN)?
     *      Is one particular search algorithm more sensitive than the other one to alphabet? Which one do we want to use in what case?
     *      When answering these questions, don't look at exact values. Look at changes between values, and difference between
     *      numbers based on the algorithm (as number variations will occur due to different N and Ms)
     * Testing:
     *      We run tests with several alphabets of varying sizes (the alphabets will be in the code)
     *      We also try three different N and M values (for 9 total combinations), to make sure that the data is credible
     *          we use large values for N and M to reduce noise
     */
    private static void alphabet_test_suite() {
        System.out.println("BEGINNING ALPHABET TEST SUITE");
        System.out.println("------------------------------------------");
        final int trial_count = 100;
        final int[] n_values = {(int)Math.pow(2,14), (int)Math.pow(2,18), (int)Math.pow(2,22)};
        final int[] m_values = {(int)Math.pow(2,6), (int)Math.pow(2,10), (int)Math.pow(2,13)};
        System.out.println("Comparison testing for 9 (N,M) pairs for varying alphabets");
        System.out.println("------------------------------------------");
        for(int n:n_values) {
            for(int m:m_values) {
                comparisons_vary_alphabet(n,m,trial_count);
            }
        }
        System.out.println("Time testing for 9 (N,M) pairs for varying alphabets");
        System.out.println("------------------------------------------");
        for(int n:n_values) {
            for(int m:m_values) {
                time_vary_alphabet(n,m,trial_count);
            }
        }
    }
    /*
        What we want to test (aka values that we can vary):
            M (pattern size)
            N (text size)
            Alphabet
        **only vary one at a time
*/

    /**
     * test suite for variable m (pattern length)
     * @param n N value (text size)
     * @param alphabet the alphabet used for text and pattern
     * @param trials the number of trials to be run
     */
    private static void comparisons_vary_m(int n, String alphabet, int trials, boolean isLarge) {
        final int[] m_values = new int[(isLarge ? 8 : 6)];
        for(int i=(isLarge ? 7 : 1); i<=((isLarge ? 14 : 6)); i++) m_values[i-(isLarge ? 7 : 1)] = (int)Math.pow(2,i);
        System.out.println("CONSTANTS: N = " + n + ", alphabet = " + alphabet + "");
        System.out.println("M\tKMP\tNaive");
        final String[] random_texts = initializeRandomStrings(n, trials, alphabet);
        for(int i=0;i<m_values.length&&m_values[i]<n;i++) {
            final String[] random_patterns = initializeRandomStrings(m_values[i], trials, alphabet);
            System.out.println(m_values[i] + "\t" + kmp_tests(random_patterns, random_texts) + "\t" + naive_tests(random_patterns, random_texts));
        }
        System.out.println("------------------------------------------");
    }

    /**
     * test suite for variable n (text length)
     * @param m M value (pattern size)
     * @param alphabet the alphabet used for text and pattern
     * @param trials the number of trials to be run
     */
    private static void comparisons_vary_n(int m, String alphabet, int trials, boolean isLarge) {
        final int[] n_values = new int[(isLarge ? 9 : 7)];
        for(int i=isLarge ? 14 : 5; i<=(isLarge ? 22 : 11); i++) n_values[i-(isLarge ? 14 : 5)] = (int)Math.pow(2,i);
        System.out.println("CONSTANTS: M = " + m + ", alphabet = " + alphabet + "");
        System.out.println("N\tKMP\tNaive");
        final String[] random_patterns = initializeRandomStrings(m, trials, alphabet);
        for(int i=0;i<n_values.length&&n_values[i]>m;i++) {
            final String[] random_texts = initializeRandomStrings(n_values[i], trials, alphabet);
            System.out.println(n_values[i] + "\t" + kmp_tests(random_patterns, random_texts) + "\t" + naive_tests(random_patterns, random_texts));
        }
        System.out.println("------------------------------------------");
    }

    /**
     * test suite for variable alphabet
     * @param n N value (text size)
     * @param m M value (pattern size)
     * @param trials the number of trials to be run
     */
    private static void comparisons_vary_alphabet(int n, int m, int trials) {
        final String[] alphabets = {"abcdefghijklmnopqrstuvwxyz", "01", "actg", "0123456789", "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()`~,.<>/?;:[{]}-_=+|"};
        System.out.println("CONSTANTS: N = " + n + ", M = " + m + "");
        System.out.println("A size\tKMP\tNaive");
        for(String a:alphabets) {
            final String[] random_patterns = initializeRandomStrings(m, trials, a);
            final String[] random_texts = initializeRandomStrings(n, trials, a);
            System.out.println(a.length() + "\t" + kmp_tests(random_patterns, random_texts) + "\t" + naive_tests(random_patterns, random_texts));
        }
        System.out.println("------------------------------------------");
    }

    /**
     * test suite for variable m (pattern length)
     * @param n N value (text size)
     * @param alphabet the alphabet used for text and pattern
     * @param trials the number of trials to be run
     */
    private static void time_vary_m(int n, String alphabet, int trials, boolean isLarge) {
        final int[] m_values = new int[(isLarge ? 8 : 6)];
        for(int i=(isLarge ? 7 : 1); i<=((isLarge ? 14 : 6)); i++) m_values[i-(isLarge ? 7 : 1)] = (int)Math.pow(2,i);
        System.out.println("CONSTANTS: N = " + n + ", alphabet = " + alphabet + "");
        System.out.println("M\tKMP\tNaive");
        final String[] random_texts = initializeRandomStrings(n, trials, alphabet);
        for(int i=0;i<m_values.length&&m_values[i]<n;i++) {
           final String[] random_patterns = initializeRandomStrings(m_values[i], trials, alphabet);
            System.out.println(m_values[i] + "\t" + kmp_test_time(random_patterns, random_texts) + "\t" + naive_test_time(random_patterns, random_texts));
        }
        System.out.println("------------------------------------------");
    }

    /**
     * test suite for variable n (text length)
     * @param m M value (pattern size)
     * @param alphabet the alphabet used for text and pattern
     * @param trials the number of trials to be run
     */
    private static void time_vary_n(int m, String alphabet, int trials, boolean isLarge) {
        final int[] n_values = new int[(isLarge ? 9 : 7)];
        for(int i=isLarge ? 14 : 5; i<=(isLarge ? 22 : 11); i++) n_values[i-(isLarge ? 14 : 5)] = (int)Math.pow(2,i);
        System.out.println("CONSTANTS: M = " + m + ", alphabet = " + alphabet + "");
        System.out.println("N\tKMP\tNaive");
        final String[] random_patterns = initializeRandomStrings(m, trials, alphabet);
        for(int i=0;i<n_values.length&&n_values[i]>m;i++) {
            final String[] random_texts = initializeRandomStrings(n_values[i], trials, alphabet);
            System.out.println(n_values[i] + "\t" + kmp_test_time(random_patterns, random_texts) + "\t" + naive_test_time(random_patterns, random_texts));
        }
        System.out.println("------------------------------------------");
    }

    /**
     * test suite for variable alphabet
     * @param n N value (text size)
     * @param m M value (pattern size)
     * @param trials the number of trials to be run
     */
    private static void time_vary_alphabet(int n, int m, int trials) {
        final String[] alphabets = {"abcdefghijklmnopqrstuvwxyz", "01", "actg", "0123456789", "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()`~,.<>/?;:[{]}-_=+|"};
        System.out.println("CONSTANTS: N = " + n + ", M = " + m + "");
        System.out.println("A Size\tKMP\tNaive");
        for(String a:alphabets) {
            final String[] random_patterns = initializeRandomStrings(m, trials, a);
            final String[] random_texts = initializeRandomStrings(n, trials, a);
            System.out.println(a.length() + "\t" + kmp_test_time(random_patterns, random_texts) + "\t" + naive_test_time(random_patterns, random_texts));
        }
        System.out.println("------------------------------------------");
    }

    //the below 1 functions are necessary so that I can run the exact same test strings for both searches to
    //make our data as accurate as possible.
    /**
     * creates a list of random strings of size n, with the list being of length size.
     * @param n size of strings
     * @param size size of list
     * @param alphabet the alphabet to use for the random strings
     * @return the list
     */
    private static String[] initializeRandomStrings(int n, int size, String alphabet) {
        String[] strs = new String[size];
        for(int i=0; i<size; i++) {
            strs[i] = Randomizer.numRandomizer(n, alphabet);
        }
        return strs;
    }


    /**
     * Gets average number of comparisons KMP uses for a given text_length (n) and alphabet
     * @param patterns  list of randomly generated patterns
     * @param texts list of randomly generated texts
     * @return average number of comparisons of KMP
     */
    private static double kmp_tests(String[] patterns, String[] texts) {
        double sum = 0;
        final int trial_kount = patterns.length;
        for(int i=0; i<trial_kount; i++) {
            String pattern = patterns[i];
            String text = texts[i];
            final KMP kmp = new KMP(pattern);
            kmp.search(text);
            sum+= kmp.NUM_COMPARISONS;
        }
        return sum/trial_kount;
    }

    /**
     * Gets average number of comparisons Naive implementation uses for a given text_length (n) and alphabet
     * @param patterns  list of randomly generated patterns
     * @param texts list of randomly generated texts
     * @return average number of comparisons of Naive implementation
     */
    private static double naive_tests(String[] patterns, String[] texts) {
        double sum = 0;
        for(int i=0; i<patterns.length; i++) {
            NaiveSearch.search(texts[i], patterns[i]);
            sum+=NaiveSearch.numComp;
        }
        return sum/patterns.length;
    }

    /**
     * testing time, for kmp search algorithm
     * @param patterns list of random patterns
     * @param texts list of random texts
     * @return average running time of a trial, in milliseconds
     */
    private static double kmp_test_time(String[] patterns, String[] texts) {
        double sum = 0;
        for(int i=0; i<patterns.length; i++) {
            String pattern = patterns[i], text = texts[i];
            final long startTime = System.nanoTime();
            final KMP kmp = new KMP(pattern);
            kmp.search(text);
            final long endTime = System.nanoTime();
            sum += (endTime-startTime);
        }
        final int avg = (int)(sum/patterns.length);
        final double avg_temp = avg/1000000.0;
        return avg_temp;
    }

    /**
     * testing time, for naive search algorithm
     * @param patterns list of random patterns
     * @param texts list of random texts
     * @return average running time of a trial, in milliseconds
     */
    private static double naive_test_time(String[] patterns, String[] texts) {
        double sum = 0;
        for(int i=0; i<patterns.length; i++) {
            final long startTime = System.nanoTime();
            NaiveSearch.search(texts[i], patterns[i]);
            final long endTime = System.nanoTime();
            sum += endTime-startTime;
        }
        final int avg = (int)(sum/patterns.length);
        final double avg_temp = avg/1000000.0;
        return avg_temp;
    }

}
