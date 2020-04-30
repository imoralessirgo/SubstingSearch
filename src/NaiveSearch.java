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
}
