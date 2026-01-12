import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        // Initialize new TST to store dictionary
        TST tst = new TST();
        for (String word : dictionary) {
            tst.insert(word);
        }

        int rows = board.length;
        int cols = board[0].length;

        boolean[][] visited = new boolean[rows][cols];
        // Use an ArrayList instead of a HashSet (duplicates handled in TST)
        ArrayList<String> goodWords = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Use StringBuilder to avoid creating new strings each recursion
                // Got rid of String word = prefix + c
                dfs(board, visited, i, j, new StringBuilder(), tst.getRoot(), goodWords);
            }
        }

        String[] sol = goodWords.toArray(new String[0]);
        Arrays.sort(sol);
        return sol;
    }

    private static void dfs(char[][] board,
                            boolean[][] visited,
                            int i,
                            int j,
                            StringBuilder prefix,
                            TST.TSTNode node,
                            ArrayList<String> goodWords) {

        // Return if out of bounds
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }

        // Return if previously visited
        if (visited[i][j]) return;

        char c = board[i][j];
        node = nextNode(node, c);

        // Return if node is null
        if (node == null) return;

        // Append character instead of creating a new String
        prefix.append(c);

        // Add word if present in Dictionary
        if (node.isEndOfWord) {
            goodWords.add(prefix.toString());
            // Allows us to remove duplicates without adding additional time complexity
            // Makes HashSet redundant
            node.isEndOfWord = false;
        }

        // Mark node as visited
        visited[i][j] = true;

        // Recurse to adjacent squares
        dfs(board, visited, i - 1, j, prefix, node.mid, goodWords);
        dfs(board, visited, i + 1, j, prefix, node.mid, goodWords);
        dfs(board, visited, i, j - 1, prefix, node.mid, goodWords);
        dfs(board, visited, i, j + 1, prefix, node.mid, goodWords);

        // Backtrack by unvisiting and removing the last character
        visited[i][j] = false;
        prefix.deleteCharAt(prefix.length() - 1);
    }

    // Helper Method for recursion
    private static TST.TSTNode nextNode(TST.TSTNode node, char c) {
        // Allows for integration between dfs and TST
        while (node != null) {
            if (c < node.c) node = node.left;
            else if (c > node.c) node = node.right;
            else return node;
        }
        return null;
    }
}
