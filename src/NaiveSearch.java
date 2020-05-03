/*
*
* Retrieved From:
* GeeksForGeeks.com
*
*/

public class NaiveSearch {

    static int numComp;

    public static void search(String txt, String pat)
    {
        numComp = 0;
        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                numComp++;
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
                if (j == M)
                    System.out.println("Pattern found at index " + i);
            }
        }
    }

    public static void main(String[] args)
    {
        String txt = "AABAACAADAABAAABAA";
        String pat = "AABA";
        search(txt, pat);
    }

    public static double particular_test(int text_length, int pattern_length ,String alphabet) {
        double sum = 0;
        int trial_kount = 10;
        int max = 0; //this is to get worst_case
        for(int i=0; i<trial_kount; i++) {
            //int random = (int)Math.ceil((Math.random()*text_length)); //we can change this to a nonrandom, or a range, or something else
            String pattern = Randomizer.numRandomizer(pattern_length, alphabet);
            String text = Randomizer.numRandomizer(text_length, alphabet);
            NaiveSearch.search(text, pattern);
            sum+=NaiveSearch.numComp;
            if(NaiveSearch.numComp>max) max=NaiveSearch.numComp;
        }
        double average = sum/trial_kount;
        //System.out.println("Max for " + text_length + " trials: " + max);
        return average;
    }
}
