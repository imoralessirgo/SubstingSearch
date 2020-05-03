public class Main {

    public static void main(String[] args) {
        test_suites();
    }
    public static void test_suites() {
        //add a lot of tests for each of the three variables.
        //test_m(500, "abcdefghijklmnopqrstuvwxyz");
        //test_n(50,"abcdefghijklmnopqrstuvwxyz");
        for(int i=16384; i<4194304; i=i*2) test_alphabet(i, 10);
    }
    /*
        What we want to test (aka values that we can vary):
            M (pattern size)
            N (text size)
            Alphabet
        **only vary one at a time
        below is old code that will likely be deleted:
     */
    public static void test_suite() {
        int n_values[] = new int[14];
        for(int i=0; i<n_values.length; i++) n_values[i] = (int)Math.pow(2,i);
        int m_values[] = {1,140};
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        System.out.println("N\tM\tcomp-KMP\tcomp-Naive");
        for(int i: n_values) {
            for(int j = 0; j<m_values.length && m_values[j]<i; j++) System.out.println(i + "\t" + m_values[j] + "\t" + KMP.particular_test(i, m_values[j], alphabet) + "\t" + NaiveSearch.particular_test(i, m_values[j], alphabet));
        }
    }
    public static void test_m(int n, String alphabet) {
        int m_values[] = new int[23];
        for(int i=0; i<m_values.length; i++) m_values[i] = (int)Math.pow(2,i);
        //ABOVE CAN CHANGE
        System.out.println("Comparisons based on M, where N = " + n + " and alphabet = " + alphabet + "");
        System.out.println("M\tKMP\tNaive");
        for(int i=0;i<m_values.length&&m_values[i]<n;i++) System.out.println(m_values[i] + "\t" + KMP.particular_test(n, m_values[i], alphabet) + "\t" + NaiveSearch.particular_test(n, m_values[i], alphabet));
    }

    public static void test_n(int m, String alphabet) {
        int n_values[] = new int[23];
        for(int i=0; i<n_values.length; i++) n_values[i] = (int)Math.pow(2,i);
        //ABOVE CAN CHANGE
        System.out.println("Comparisons based on N, where M = " + m + " and alphabet = " + alphabet + "");
        System.out.println("N\tKMP\tNaive");
        for(int i=0;i<n_values.length&&n_values[i]>m;i++) System.out.println(n_values[i] + "\t" + KMP.particular_test(n_values[i], m, alphabet) + "\t" + NaiveSearch.particular_test(n_values[i], m, alphabet));
    }

    public static void test_alphabet(int n, int m) {
        String alphabets[] = {"abcdefghijklmnopqrstuvwxyz", "01", "actg"};
        //ABOVE CAN CHANGE
        System.out.println("Comparisons based on Alphabets, where N = " + n + " and M = " + m + "");
        System.out.println("KMP\tNaive\tAlphabet");
        for(String a:alphabets) System.out.println(KMP.particular_test(n, m, a) + "\t" + NaiveSearch.particular_test(n, m, a) + "\t" + a);
        System.out.println("------------------------------------------");
    }


}
