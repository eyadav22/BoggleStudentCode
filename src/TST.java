public class TST {
    private class TSTNode {
        // Current Char
        char c;
        // Three possible paths down
        TSTNode left, mid, right;
        // Marks valid words from sub-words
        boolean isEndOfWord;
    }

    private TSTNode root;

    // Inserts words at the beginning
    public void insert(String word) {
        if (word == null || word.isEmpty()) {return;}
        root = insert(root, word.toLowerCase(), 0);
    }

    // Inserts words at specific points
    private TSTNode insert(TSTNode node, String word, int index) {
        char c = word.charAt(index);
        if (node == null) {
            node = new TSTNode();
            node.c = c;
        }
        // Three paths down
        if (c < node.c) {node.left = insert(node.left, word, index);}
        else if (c > node.c) { node.right = insert(node.right, word, index);}
        else if (index < word.length() - 1) {node.mid = insert(node.mid, word, index + 1);}
        else {node.isEndOfWord = true;}
        return node;
    }

    public boolean contains(String word) {
        if (word == null || word.isEmpty()) {return false;}
        TSTNode node = get(root, word.toLowerCase(), 0);
        return node != null && node.isEndOfWord;
    }

    private TSTNode get(TSTNode node, String word, int index) {
        if (node == null) {return null;}
        char c = word.charAt(index);
        // Three paths down
        // Char is less than the current node
        if (c < node.c) {return get(node.left, word, index);}
        // Char is greater than the current node
        else if (c > node.c) {return get(node.right, word, index);}
        else if (index < word.length() - 1) {return get(node.mid, word, index + 1);}
        else {return node;}
    }
}
