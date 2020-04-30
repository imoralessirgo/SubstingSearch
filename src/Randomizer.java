public class Randomizer {

    public static void main(String[] args) {
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // How to use the randomizer:                                                                                 //
        // 1. To set string length, alter the exponent in double length accordingly.                                  //
        //    Our tests must be conducted between 2^14 and 2^22, so keep that in mind when generating random strings. //
        // 2. To set a preferred string, enter it in as the second parameter in the println statement.                //
        //    For the DNA string, enter "dna." For the binary string, enter "binary." For alphabet, enter "alphabet." //
        //    If you'd like to make your own strings, enter in the characters you'd like the randomizer to pick from. //
        //    For example, if you want to randomize a string with characters a, b, and c, set selector to "abc"       //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //String length
        double length   =  Math.pow(2,14);

        //Strings
        String dna      = "actg";
        String binary   = "01";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String custom   = "";

        //Print statement, you can change the randomizer pool by changing the second parameter
        System.out.println(Randomizer.numRandomizer(length, dna));

    }

    public static String numRandomizer(double length, String selector){
        //New StringBuilder created to construct the random string
        StringBuilder sb = new StringBuilder((int) length);

        //create a string as long as what's entered
        for(int i = 0; i < length; i++){

            //Select randomized letter
            int index = (int)(selector.length()*Math.random());

            //Apply randomized letter to string
            sb.append(selector.charAt(index));
        }
        return sb.toString();
    }
}
