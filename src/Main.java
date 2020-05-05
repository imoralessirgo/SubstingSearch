public class Main {

    public static void main(String[] args) {
        test_suites();
    }

    /**
     *  TODO:
     *  fix up print statements
     *  divide into 6 functions, as we will do data-gathering on each of these 6 functions:
     *      comparisons_vary_n
     *      comparisons_vary_m
     *      comparisons_vary_alphabet
     *      comparisons_vary_
     *  -write tons of tests, record data, graph data, write analyses on data. Tests can be written in test_suites or
     *  other functions you create that are called by test_suites. Please do not write individual tests in other functions
     */

    /**
     * this is where we will write a bunch of tests to test things out such as
     *     worst case
     *     kmp big numbers
     *     naive big numbers
     *     where kmp == naive and kmp > naive
     *     other cool stuff
     */
    private static void test_suites() {
        //add a lot of tests for each of the three variables.
        //below are some example tests. feel free to also add tests via loops!
        test_n((int)Math.pow(2,9), "abcde", 100, false);
        test_m((int)Math.pow(2,19), "abcdef", 100, false);
        test_alphabet((int)Math.pow(2,10), (int)Math.pow(2,20), 100, false);
    }
    /*
        What we want to test (aka values that we can vary):
            M (pattern size)
            N (text size)
            Alphabet
        **only vary one at a time
*/
    // Please do not modify any of the below functions to include specific tests. Instead, write tests above that call
    // either test_m, test_n, or test_alphabet.

    /**
     * test suite for variable m (pattern length)
     * @param n N value (text size)
     * @param alphabet the alphabet used for text and pattern
     * @param trials the number of trials to be run
     */
    private static void test_m(int n, String alphabet, int trials, boolean is_time) {
        int[] m_values = new int[9];
        for(int i=6; i<=14; i++) m_values[i-6] = (int)Math.pow(2,i);
        System.out.println("Comparisons based on M, where N = " + n + " and alphabet = " + alphabet + "");
        System.out.println("M\tKMP\tNaive");
        String[] random_texts = initializeRandomStrings(n, trials, alphabet);
        for(int i=0;i<m_values.length&&m_values[i]<n;i++) {
            String[] random_patterns = initializeRandomStrings(m_values[i], trials, alphabet);
            if (is_time) System.out.println(m_values[i] + "\t" + kmp_test_time(random_patterns, random_texts) + "\t" + naive_test_time(random_patterns, random_texts));
            else System.out.println(m_values[i] + "\t" + kmp_tests(random_patterns, random_texts) + "\t" + naive_tests(random_patterns, random_texts));
        }
        System.out.println("------------------------------------------");
    }

    /**
     * test suite for variable n (text length)
     * @param m M value (pattern size)
     * @param alphabet the alphabet used for text and pattern
     * @param trials the number of trials to be run
     */
    private static void test_n(int m, String alphabet, int trials, boolean is_time) {
        int[] n_values = new int[9];
        for(int i=14; i<=22; i++) n_values[i-14] = (int)Math.pow(2,i);
        System.out.println("Comparisons based on N, where M = " + m + " and alphabet = " + alphabet + "");
        System.out.println("N\tKMP\tNaive");
        String[] random_patterns = initializeRandomStrings(m, trials, alphabet);
        for(int i=0;i<n_values.length&&n_values[i]>m;i++) {
            String[] random_texts = initializeRandomStrings(n_values[i], trials, alphabet);
            if(is_time)System.out.println(n_values[i] + "\t" + kmp_test_time(random_patterns, random_texts) + "\t" + naive_test_time(random_patterns, random_texts));
            else System.out.println(n_values[i] + "\t" + kmp_tests(random_patterns, random_texts) + "\t" + naive_tests(random_patterns, random_texts));
        }
        System.out.println("------------------------------------------");
    }

    /**
     * test suite for variable alphabet
     * @param n N value (text size)
     * @param m M value (pattern size)
     * @param trials the number of trials to be run
     */
    private static void test_alphabet(int n, int m, int trials, boolean is_time) {
        String[] alphabets = {"abcdefghijklmnopqrstuvwxyz", "01", "actg"};
        System.out.println("Comparisons based on Alphabets, where N = " + n + " and M = " + m + "");
        System.out.println("KMP\tNaive\tAlphabet");
        for(String a:alphabets) {
            String[] random_patterns = initializeRandomStrings(m, trials, a);
            String[] random_texts = initializeRandomStrings(n, trials, a);
            if (is_time) System.out.println(kmp_test_time(random_patterns, random_texts) + "\t" + naive_test_time(random_patterns, random_texts) + "\t" + a);
            else System.out.println(kmp_tests(random_patterns, random_texts) + "\t" + naive_tests(random_patterns, random_texts) + "\t" + a);
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
        int trial_kount = patterns.length;
        for(int i=0; i<trial_kount; i++) {
            String pattern = patterns[i];
            String text = texts[i];
            KMP kmp = new KMP(pattern);
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
        int trial_kount = patterns.length;
        for(int i=0; i<trial_kount; i++) {
            NaiveSearch.search(texts[i], patterns[i]);
            sum+=NaiveSearch.numComp;
        }
        return sum/trial_kount;
    }

    /**
     *
     * @param patterns
     * @param texts
     * @return average running time of a trial, in miliseconds
     */
    private static double kmp_test_time(String[] patterns, String[] texts) {
        double sum = 0;
        int trial_kount = patterns.length;
        for(int i=0; i<trial_kount; i++) {
            String pattern = patterns[i], text = texts[i];
            long startTime = System.nanoTime();
            KMP kmp = new KMP(pattern);
            kmp.search(text);
            long endTime = System.nanoTime();
            sum += (endTime-startTime);
        }
        return (sum/trial_kount)/1000000;
    }

    /**
     *
     * @param patterns
     * @param texts
     * @return
     */
    private static double naive_test_time(String[] patterns, String[] texts) {
        double sum = 0;
        for(int i=0; i<patterns.length; i++) {
            long startTime = System.nanoTime();
            NaiveSearch.search(texts[i], patterns[i]);
            long endTime = System.nanoTime();
            sum += endTime-startTime;
        }
        return (sum/patterns.length)/1000000;
    }

}
