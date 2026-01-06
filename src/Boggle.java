import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<String>();

        // TODO: Complete the function findWords(). Add all words that are found both on the board
        //  and in the dictionary.

        // View the board as a series of nodes, then start at one node and traverse
        // using depth first search inorder traversal, adding all "words", even if not real, to a list
        // use recursive depth first search to keep track of visiting
        // if visited then mark as visited, but recursion allows to be unvisited
        // Then lookup each word in a TST made from the dictionary of words
        // Need seperate TST class
        // if word present in the TST, then add to the arraylist goodWords

        // Build TST of words in the dictionary
        TST tst = new TST();
        for (String word : dictionary) {
            tst.insert(word);
        }

        // Use dfs to check all words


        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }
}
