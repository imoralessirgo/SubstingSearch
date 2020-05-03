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
        //time implementation
        //for each second, print out the second the loop is currently on
        //in addition, find the number of comparisons that were made in that second


        for (int i = 0; i <= N - M; i++) {
            //For current index i, check for pattern match
            for (int j = 0; j < M; j++) {
                numComp++;
                //If a match is not found, break the code
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
                //If a pattern is found, print out the index it was found at
                if (j == M)
                    System.out.println("Pattern found at index " + i);
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        //Define text and pattern
        String txt = "AABAACAADAABAAABAA";
        String pat = "AA";

        //Run search algorithm
        search(txt, pat);

        long start = System.nanoTime();
        //This counts as 1 second in program time
        for (int i = 1; i <  6; i++) {
            Thread.sleep(1000);
            System.out.println(i);
        }
        // finding the time after the operation is executed
        long end = System.nanoTime();
        //finding the time difference and converting it into seconds
        float sec = (end - start) / 1000F; System.out.println(sec + " seconds");



    }
}
